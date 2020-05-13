/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape;

import java.util.*;
import escape.board.*;
import escape.board.coordinate.*;
import escape.exception.EscapeException;
import escape.piece.*;
import escape.rule.*;
import escape.rule.SquareGameRules.Rules;
import escape.util.*;

/**
 * Description
 * 
 * @version 27 abr. 2020
 * @param <C>
 */
public class SquareGame extends Game implements EscapeGameManager<SquareCoordinate>
{
	public SquareBoard b;
	private ArrayList<GameObserver> oList = new ArrayList<GameObserver>();

	public SquareGame(HashMap<PieceName, PieceTypeInitializer> PieceTypes,
			HashMap<RuleID, Rule> gameRules, SquareBoard b)
	{
		super(gameRules, PieceTypes);
		this.b = b;
		initicilizePieceValue();
	}

	/**
	 * set all the values for the pieces at the start of the game
	 */
	public void initicilizePieceValue()
	{
		if (this.b.getPieces() != null) {
			for (PieceTypeInitializer t : PieceTypes.values()) {
				for (EscapePiece p : b.pieces.values()) {
					if (p.getName() == t.getPieceName()) {
						p.setValue(PieceTypeInitializer.getValue(t.getAttributes()));
					}
				}
			}
		}
	}

	@Override
	public boolean move(SquareCoordinate from, SquareCoordinate to)
	{

		// checking to see if the turn limit has been reached\
		// or the score limit has been reached
		try {
			scoreLimit();
			tunrLimit();
		} catch (EscapeException e) {
			if (oList.size() >= 1) {
				if (Player1score != Player2score) {
					oList.get(0).notify("Game is over and " + winner() + " has won");
				} else {
					oList.get(0).notify("Game is over and " + winner());
				}
			}

			return false;
		}
		try {
			// check that the starting location has a piece to be moved
			emptyFromLocation(from);
			// check if it is moving to the same place it starts from
			destEqualsFrom(from, to);
			// check the turn is adecuate
			turnCheck(from);
			// check if the destiantion is blocked
			destIsBlocked(to);
			// check if there is a piece at the destination
			attackAlly(from, to);
			// must have remove to attack a piece
			removeOrConflict(to);
		} catch (EscapeException e) {
			if (oList.size() >= 1) {
				oList.get(0).notify(e.getMessage());
			}
			return false;
		}
		if (to.getX() > b.getXMax() || to.getY() > b.getYMax()) {
			if (oList.size() >= 1) {
				oList.get(0).notify("Coordinate out of bounds");
			}
			return false;
		}

		GameHashMapOfRules rules = new GameHashMapOfRules();

		MovementPatternID pattern = ((PieceTypeInitializer) PieceTypes
				.get(b.getPieceAt(from).getName())).getMovementPattern();
		ArrayList<Rules> list = rules.squareMovePattern.get(pattern);
		for (Rules r : list) {
			try {
				if (r.TestRule(from, to, this)) {
					// when the rules have remove
					if (gameRules != null && gameRules.containsKey(RuleID.REMOVE)
							&& b.getPieceAt(to) != null) {
						EscapePiece temp = b.getPieceAt(from);
						b.putPieceAt(temp, to);
						b.removePieceFrom(from);
					}
					// when the rules have point conflict
					else if (gameRules != null
							&& gameRules.containsKey(RuleID.POINT_CONFLICT)
							&& b.getPieceAt(to) != null) {
						// when they have the same value remove both
						if (b.getPieceAt(from).getValue() == b.getPieceAt(to)
								.getValue()) {
							b.removePieceFrom(from);
							b.removePieceFrom(to);
						}
						// when they have different values resolve the conflict and place
						// the wining
						// pice at the destination
						else {
							EscapePiece temp = conflict(b.getPieceAt(from),
									b.getPieceAt(to));
							b.removePieceFrom(from);
							b.removePieceFrom(to);
							b.putPieceAt(temp, to);
						}

					}
					// when the to location is empty
					else if (b.getPieceAt(to) == null) {
						EscapePiece temp = b.getPieceAt(from);
						b.putPieceAtMidGame(temp, to);
						b.removePieceFrom(from);
						b.getPieceAt(to);

					}

					// when the exit location is empty
					if (b.getLocationType(to) == LocationType.EXIT) {
						int val = b.getPieceAt(to).getValue();
						incrementScore(b.getPieceAt(to).getPlayer(), val);
						b.removePieceFrom(to);
					}
					turnHolder++;
					// check that after the move the game is over
					try {
						scoreLimit();
						tunrLimit();
					} catch (EscapeException e) {
						if (oList.size() >= 1) {
							if (Player1score != Player2score) {
								oList.get(0).notify(winner() + " wins");
							} else {
								oList.get(0).notify(winner());
							}
						}
					}
					return true;
				}
			}
			// catch why the move is false
			catch (EscapeException e) {
				if (oList.size() >= 1) {
					oList.get(0).notify(e.getMessage());
				}
				return false;
			}

		}
		return false;
	}

	/*
	 * @see escape.EscapeGameManager#getPieceAt(escape.board.coordinate.Coordinate)
	 */
	@Override
	public EscapePiece getPieceAt(SquareCoordinate coordinate)
	{
		return b.getPieceAt(coordinate);
	}

	/*
	 * @see escape.EscapeGameManager#makeCoordinate(int, int)
	 */
	@Override
	public SquareCoordinate makeCoordinate(int x, int y)
	{
		return SquareCoordinate.makeCoordinate(x, y);
	}

	/**
	 * Check if the two coordinates are the same to throw an excption
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public void destEqualsFrom(SquareCoordinate from, SquareCoordinate to)
	{

		if (from.equals(to)) {
			throw new EscapeException("Cant go to the same place it comes from");
		}

	}

	/**
	 * Check if the destination is blocked throw exception when true
	 * 
	 * @param to
	 */
	public void destIsBlocked(SquareCoordinate to)
	{
		if (b.getLocationType(to) == LocationType.BLOCK) {
			throw new EscapeException("Cant Land on a Blocked Location");
		}
	}

	/**
	 * check if there is a piece on the destination and is a enemy piece
	 * 
	 * @param from
	 * @param to
	 */
	public void attackAlly(SquareCoordinate from, SquareCoordinate to)
	{

		if (b.getPieceAt(to) != null
				&& b.getPieceAt(from).getPlayer() == b.getPieceAt(to).getPlayer()) {
			throw new EscapeException(
					"The piece at the destination must be an enemy peice");
		}
	}

	/**
	 * throw exception if to is not null and cant conflict or remove
	 */
	public void removeOrConflict(SquareCoordinate to)
	{
		if (b.getPieceAt(to) != null) {
			if (gameRules == null || !gameRules.containsKey(RuleID.REMOVE)
					&& !gameRules.containsKey(RuleID.POINT_CONFLICT)) {
				throw new EscapeException(
						"Must have remove or point conflict to attack a piece");
			}
		}
	}

	/**
	 * check that there is a piece at the starting location in order to move
	 * 
	 * @param from
	 */
	public void emptyFromLocation(SquareCoordinate from)
	{
		if (b.getPieceAt(from) == null) {
			throw new EscapeException("Starting location must have a piece to move");
		}
	}

	/**
	 * Check tha teh piece being moved is on its turn
	 * 
	 * @param from
	 */
	public void turnCheck(SquareCoordinate from)
	{
		if (turnHolder % 2 != 0
				&& b.getPieceAt(from).getPlayer() != Player.PLAYER1) {
			throw new EscapeException("Is Player 1 turn");
		} else if (turnHolder % 2 == 0
				&& b.getPieceAt(from).getPlayer() != Player.PLAYER2) {
			throw new EscapeException("Is Player 2 turn");
		}

	}

	/*
	 * @see escape.EscapeGameManager#removeObserver(escape.GameObserver)
	 */
	@Override
	public GameObserver removeObserver(GameObserver observer)
	{

		oList.remove(observer);
		return observer;
	}

	/*
	 * @see escape.EscapeGameManager#addObserver(escape.GameObserver)
	 */
	@Override
	public GameObserver addObserver(GameObserver observer)
	{
		oList.add(observer);
		return observer;
	}

}
