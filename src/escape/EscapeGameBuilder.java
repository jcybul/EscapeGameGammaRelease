/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2020 Gary F. Pollice
 *******************************************************************************/

package escape;

import static escape.board.LocationType.CLEAR;
import java.io.*;
import java.util.HashMap;
import javax.xml.bind.*;
import escape.board.*;
import escape.board.coordinate.*;
import escape.exception.EscapeException;
import escape.piece.*;
import escape.rule.*;
import escape.util.*;

/**
 * This class is what a client will use to creat an instance of a game, given an Escape
 * game configuration file. The configuration file contains the information needed to
 * create an instance of the Escape game.
 * 
 * @version Apr 22, 2020
 */
public class EscapeGameBuilder
{
	private EscapeGameInitializer gameInitializer;

	/**
	 * The constructor takes a file that points to the Escape game configuration file. It
	 * should get the necessary information to be ready to create the game manager
	 * specified by the configuration file and other configuration files that it links to.
	 * 
	 * @param fileName
	 *            the file for the Escape game configuration file.
	 * @throws Exception
	 */
	public EscapeGameBuilder(File fileName) throws Exception
	{
		JAXBContext contextObj = JAXBContext
				.newInstance(EscapeGameInitializer.class);
		Unmarshaller mub = contextObj.createUnmarshaller();
		gameInitializer = (EscapeGameInitializer) mub
				.unmarshal(new FileReader(fileName));

	}

	/**
	 * Once the builder is constructed, this method creates the EscapeGameManager
	 * instance.
	 * 
	 * @return
	 */
	public EscapeGameManager makeGameManager()
	{
		// create a board and a escape game manager
		EscapeGameManager manager = null;
		Board gameBoard = BoardFactory.createB(gameInitializer.getCoordinateType(),
				gameInitializer.getxMax(), gameInitializer.getyMax());
		boardInitilizer(gameBoard, gameInitializer.getLocationInitializers());
		HashMap<PieceName, PieceTypeInitializer> type = typeInicializer(gameBoard,
				gameInitializer.getPieceTypes());
		HashMap<RuleID, Rule> rule = ruleInicilizer(gameInitializer.getRules());
		manager = GameFactory.CreateGame(gameInitializer.getCoordinateType(),
				gameBoard, type, rule);
		return manager;

	}

	/**
	 * Inicilize a hash map of rules for the given initilizers
	 * 
	 * @param initializers
	 * @return
	 */
	private HashMap<RuleID, Rule> ruleInicilizer(Rule... initializers)
	{
		HashMap<RuleID, Rule> r = null;
		if (initializers != null) {
			r = new HashMap<>();
			for (Rule s : initializers) {
				r.put(s.getId(), s);
			}
			if (PieceTypeInitializer.pointConflict(initializers)
					&& PieceTypeInitializer.remove(initializers)) {
				throw new EscapeException(
						"Cant have both remove and point conflict ");

			}

		}

		return r;
	}

	/**
	 * Inicialize the hashmap with the piece types
	 * 
	 * @param d
	 * @param initializers
	 * @return
	 */
	private HashMap<PieceName, PieceTypeInitializer> typeInicializer(Board d,
			PieceTypeInitializer... initializers)
	{
		HashMap<PieceName, PieceTypeInitializer> types = new HashMap<PieceName, PieceTypeInitializer>();
		CoordinateID id = null;
		if (d instanceof SquareBoard) {
			id = CoordinateID.SQUARE;
		} else if (d instanceof HexBoard) {
			id = CoordinateID.HEX;
		} else if (d instanceof OrthoBoard) {
			id = CoordinateID.ORTHOSQUARE;
		}

		if (initializers != null) {
			for (PieceTypeInitializer i : initializers) {
				if (id == CoordinateID.HEX) {
					if (i.getMovementPattern() == MovementPatternID.ORTHOGONAL || i
							.getMovementPattern() == MovementPatternID.DIAGONAL) {
						throw new EscapeException(
								"WRONG Movement pattern: hex cant have diagonal or Orthogonal");
					}
				} else if (id == CoordinateID.ORTHOSQUARE
						&& i.getMovementPattern() == MovementPatternID.DIAGONAL) {
					throw new EscapeException(
							"WRONG Movement pattern: Ortho cant have diagonal");

				}
				types.put(i.getPieceName(), i);
				if (PieceTypeInitializer.canFly(i.getAttributes())
						&& PieceTypeInitializer.isDistance(i.getAttributes())) {
					throw new EscapeException("cant have both Fly and distance");

				}
				if (!PieceTypeInitializer.canFly(i.getAttributes())
						&& !PieceTypeInitializer.isDistance(i.getAttributes())) {
					throw new EscapeException(
							"Must Have A Fly Or Distance Attribute");
				}

			}

		} else {

			throw new EscapeException("Must have at least one piece type");
		}
		return types;
	}

	/**
	 * Inicialize the board with the given pieces
	 * 
	 * @param b
	 * @param initializers
	 */
	private void boardInitilizer(Board b, LocationInitializer... initializers)
	{
		if (initializers != null) {
			if (b instanceof SquareBoard) {

				for (LocationInitializer li : initializers) {
					SquareCoordinate c = SquareCoordinate.makeCoordinate(li.x, li.y);
					if (li.pieceName != null) {
						b.putPieceAt(new EscapePiece(li.player, li.pieceName), c);
					}

					if (li.locationType != null && li.locationType != CLEAR) {
						((SquareBoard) b).setLocationType(c, li.locationType);
					}
				}
			} else if (b instanceof HexBoard) {

				for (LocationInitializer li : initializers) {
					HexCoordinate c = HexCoordinate.makeCoordinate(li.x, li.y);
					if (li.pieceName != null) {
						b.putPieceAt(new EscapePiece(li.player, li.pieceName), c);
					}

					if (li.locationType != null && li.locationType != CLEAR) {
						((HexBoard) b).setLocationType(c, li.locationType);
					}
				}

			} else if (b instanceof OrthoBoard) {

				for (LocationInitializer li : initializers) {
					OrthoSquareCoordinate c = OrthoSquareCoordinate
							.makeCoordinate(li.x, li.y);
					if (li.pieceName != null) {
						b.putPieceAt(new EscapePiece(li.player, li.pieceName), c);
					}

					if (li.locationType != null && li.locationType != CLEAR) {
						((OrthoBoard) b).setLocationType(c, li.locationType);
					}
				}
			}

		}
	}

}
