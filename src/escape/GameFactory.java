/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape;

import java.util.HashMap;
import escape.board.*;
import escape.board.coordinate.*;
import escape.piece.PieceName;
import escape.rule.*;
import escape.util.PieceTypeInitializer;

/**
 * Description
 * 
 * @version 28 abr. 2020
 */
public interface GameFactory
{

	/**
	 * Create the aproppiate game given the board and Coordinate Type
	 * 
	 * @param id
	 * @param b
	 * @return
	 */
	public static EscapeGameManager CreateGame(CoordinateID id, Board b,
			HashMap<PieceName, PieceTypeInitializer> types,HashMap<RuleID,Rule> gameRules)
	{

		EscapeGameManager ret = null;
		switch (id) {
			// switch depending on the CoordinateID
			case SQUARE:
				ret = new SquareGame(types,gameRules,(SquareBoard) b) {
				};
				break;
			case HEX:
				ret = new HexGame(types,gameRules,(HexBoard) b);
				break;

			case ORTHOSQUARE:
				ret = new OrthoGame(types,gameRules,(OrthoBoard) b);
				break;
		}
		return ret;
	}
}
