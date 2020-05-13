/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.rule;

import escape.*;
import escape.board.*;
import escape.board.coordinate.*;
import escape.exception.EscapeException;
import escape.piece.MovementPatternID;
import escape.util.PieceTypeInitializer;

/**
 * Description
 * 
 * @version 29 abr. 2020
 */
public class SquareGameRules
{

	@FunctionalInterface
	public interface Rules
	{
		boolean TestRule(SquareCoordinate from, SquareCoordinate to, SquareGame g);
	}

	// helpers
	static Rules getPiece = (from, to, g) -> g.b.getPieceAt(from) != null;
	static Rules getDistance = (from, to, g) -> from
			.distanceTo(to) <= PieceTypeInitializer.getMaxDistance((g.PieceTypes
					.get(g.b.getPieceAt(from).getName()).getAttributes()))
			&& -1 != PieceTypeInitializer.getMaxDistance((g.PieceTypes
					.get(g.b.getPieceAt(from).getName()).getAttributes()));
	static Rules canJump = (from, to, g) -> PieceTypeInitializer.canJump(
			(g.PieceTypes.get(g.b.getPieceAt(from).getName()).getAttributes()));
	static Rules canFly = (from, to, g) -> PieceTypeInitializer.canFly(
			(g.PieceTypes.get(g.b.getPieceAt(from).getName()).getAttributes()));
	static Rules canUnblock = (from, to, g) -> PieceTypeInitializer.canUnblock(
			(g.PieceTypes.get(g.b.getPieceAt(from).getName()).getAttributes()));

	// Movement Pattern rules

	/*
	 * FOR LINEAR
	 */
	// we need to check if the path is diagonal or orthogonal and if there are pieces if
	// it can jump
	// and if there are any blocked locations in the path and it can unblock
	static Rules LinearDiagonal = (from, to, g) ->
	// the path is diagonal
	from.sameDiagonal(to) &&
	// the distance is inside what is allowed
			linearTestDistance(from, to, g) &&
			// the path is clear from pieces or (it can jump there is a jumpable path) or
			// can fly
			(from.diagonalIsLocationClear(to, (SquareBoard) g.b, null, true, false)
					|| (canJump.TestRule(from, to, g)
							&& from.diagonalIsLocationClear(to, (SquareBoard) g.b,
									null, false, true))
					|| canFly.TestRule(from, to, g))
			&&
			// check that a location that path is unblocked and or it can unblock or it
			// can fly
			(from.diagonalIsLocationClear(to, (SquareBoard) g.b, LocationType.BLOCK,
					false, false) || canUnblock.TestRule(from, to, g)
					|| canFly.TestRule(from, to, g))
			&&
			// clear from exits or fly
			(from.diagonalIsLocationClear(to, (SquareBoard) g.b, LocationType.EXIT,
					false, false) || canFly.TestRule(from, to, g))
			&&
			// landing is not blocked
			((SquareBoard) g.b).getLocationType(to) != LocationType.BLOCK;

	static Rules LinearOrthogonal = (from, to, g) ->
	// is a ortogonal path to destination
	from.sameOrthogonal(to) &&
	// the distance is allowed flying or normal moving
			linearTestDistance(from, to, g) &&
			// the path is clear from pieces or (it can jump and there is a jumpable path)
			// or can fly
			(from.orthagonalILocationClear(to, (SquareBoard) g.b, null, true, false)
					|| (canJump.TestRule(from, to, g)
							&& from.orthagonalILocationClear(to, (SquareBoard) g.b,
									null, false, true)
							|| canFly.TestRule(from, to, g)))
			&&
			// check that a location that path is unblocked and or it can unblock or it
			// can fly
			(from.orthagonalILocationClear(to, (SquareBoard) g.b, LocationType.BLOCK,
					false, false) || canUnblock.TestRule(from, to, g)
					|| canFly.TestRule(from, to, g))
			&&
			// clear from exits or fly
			(from.orthagonalILocationClear(to, (SquareBoard) g.b, LocationType.EXIT,
					false, false) || canFly.TestRule(from, to, g))
			&&
			// landing position is not blocked
			((SquareBoard) g.b).getLocationType(to) != LocationType.BLOCK;

	/*
	 * FOR DIAGONAL we use the LinearDiagonal rules adding it to the list on the hashmap
	 */
	static Rules DiagonalRules = (from, to,
			g) -> ((SquareBoard) g.b).getLocationType(to) != LocationType.BLOCK
					&& from.PathFind(g.b, to, MovementPatternID.DIAGONAL,
							(g.PieceTypes.get(g.b.getPieceAt(from).getName())
									.getAttributes()));

	/*
	 * FOR OMNI USING PATHFINDIN
	 */

	static Rules OmniRules = (from, to,
			g) -> ((SquareBoard) g.b).getLocationType(to) != LocationType.BLOCK
					&& from.PathFind(g.b, to, MovementPatternID.OMNI, (g.PieceTypes
							.get(g.b.getPieceAt(from).getName()).getAttributes()));

	/*
	 * FOR ORTHO
	 */
	static Rules OrthoRules = (from, to,
			g) -> ((SquareBoard) g.b).getLocationType(to) != LocationType.BLOCK
					&& from.PathFind(g.b, to, MovementPatternID.ORTHOGONAL,
							(g.PieceTypes.get(g.b.getPieceAt(from).getName())
									.getAttributes()));

	private static boolean linearTestDistance(SquareCoordinate from,
			SquareCoordinate to, SquareGame g)
	{
		if (getDistance.TestRule(from, to, g)) {
			return true;
		} else {
			throw new EscapeException("Destiantion is too far to reach");
		}
	}

}
