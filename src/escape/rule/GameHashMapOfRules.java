/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.rule;

import java.util.*;
import escape.piece.MovementPatternID;
import escape.rule.HexGameRules.HRules;
import escape.rule.OrthoGameRules.ORules;
import escape.rule.SquareGameRules.Rules;

/**
 * Description
 * 
 * @version 29 abr. 2020
 */
public class GameHashMapOfRules
{
	// hash maps of rules
	public HashMap<MovementPatternID, ArrayList<Rules>> squareMovePattern = new HashMap<>();
	public HashMap<MovementPatternID, ArrayList<ORules>> orthoMovePattern = new HashMap<>();
	public HashMap<MovementPatternID, ArrayList<HRules>> hexMovePattern = new HashMap<>();

	// rules list for square boards
	ArrayList<Rules> SquareLinearRules = new ArrayList<Rules>();
	ArrayList<Rules> SquareDiagonalRules = new ArrayList<Rules>();
	ArrayList<Rules> SquareOmnilRules = new ArrayList<Rules>();
	ArrayList<Rules> SquareOrthoRules = new ArrayList<Rules>();

	// rules list for orthoboard
	ArrayList<ORules> OrthoOmnilRules = new ArrayList<ORules>();
	ArrayList<ORules> OrthoOrthoRules = new ArrayList<ORules>();
	ArrayList<ORules> LinearOrthoRules = new ArrayList<ORules>();

	// rules lsit for hex baord
	ArrayList<HRules> OmniHexRules = new ArrayList<HRules>();
	ArrayList<HRules> LinearHexRules = new ArrayList<HRules>();

	public GameHashMapOfRules()
	{

		// SQUARE GAME
		// Linear rules
		SquareLinearRules.add(SquareGameRules.LinearDiagonal);
		SquareLinearRules.add(SquareGameRules.LinearOrthogonal);
		// omni rules
		SquareOmnilRules.add(SquareGameRules.OmniRules);
		// diagonal rules
		SquareDiagonalRules.add(SquareGameRules.DiagonalRules);
		// ortho rules
		SquareOrthoRules.add(SquareGameRules.OrthoRules);

		squareMovePattern.put(MovementPatternID.LINEAR, SquareLinearRules);
		squareMovePattern.put(MovementPatternID.DIAGONAL, SquareDiagonalRules);
		squareMovePattern.put(MovementPatternID.OMNI, SquareOmnilRules);
		squareMovePattern.put(MovementPatternID.ORTHOGONAL, SquareOrthoRules);
		// SQUARE GAME

		// ORTHO GAME
		OrthoOmnilRules.add(OrthoGameRules.OrthoOmniRules);
		OrthoOrthoRules.add(OrthoGameRules.OrthoOmniRules);
		LinearOrthoRules.add(OrthoGameRules.OrthoLinear);

		orthoMovePattern.put(MovementPatternID.OMNI, OrthoOmnilRules);
		orthoMovePattern.put(MovementPatternID.ORTHOGONAL, OrthoOrthoRules);
		orthoMovePattern.put(MovementPatternID.LINEAR, LinearOrthoRules);

		// ORTHO GAME

		// HEX GAME
		OmniHexRules.add(HexGameRules.OmniHex);
		LinearHexRules.add(HexGameRules.LinearHex);

		hexMovePattern.put(MovementPatternID.OMNI, OmniHexRules);
		hexMovePattern.put(MovementPatternID.LINEAR, LinearHexRules);

		// HEX GAME

	}

}
