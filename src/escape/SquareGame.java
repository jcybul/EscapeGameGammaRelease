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
	

	public SquareGame(
			HashMap<PieceName, PieceTypeInitializer> PieceTypes,
			HashMap<RuleID,Rule> gameRules,SquareBoard b)
	{	
		super(gameRules,PieceTypes);
		this.b = b;
	}
	
	@Override 
	public boolean move(SquareCoordinate from, SquareCoordinate to)
	{
		if (from.equals(to)) {
			return false;
		}
		if (to.getX() > b.getXMax() || to.getY() > b.getYMax()) {
			return false;
		}
		GameHashMapOfRules rules = new GameHashMapOfRules();
		if (b.getPieceAt(from) == null) {
			return false;
		}

		MovementPatternID pattern = ((PieceTypeInitializer) PieceTypes.get(b.getPieceAt(from).getName()))
				.getMovementPattern();
		ArrayList<Rules> list = rules.squareMovePattern.get(pattern);
		for (Rules r : list) {
			if (r.TestRule(from, to, this)) {
				if (b.getPieceAt(to) != null && b.getPieceAt(from).getPlayer() == b
						.getPieceAt(to).getPlayer()) {
					return false;
				}
				EscapePiece temp = b.getPieceAt(from);
				b.putPieceAt(temp, to);
				b.removePieceFrom(from);
				if (b.getLocationType(to) == LocationType.EXIT) {
					b.removePieceFrom(to);
				}
				return true;
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



}
