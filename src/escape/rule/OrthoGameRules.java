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
import escape.rule.SquareGameRules.Rules;
import escape.util.PieceTypeInitializer;

// interface for creating the lambdas
public class OrthoGameRules
{

	@FunctionalInterface
	public interface ORules
	{

		public boolean oTest(OrthoSquareCoordinate from, OrthoSquareCoordinate to,
				OrthoGame g);
	}

	// attribute helpers
	static ORules getDistance = (from, to, g) -> from
			.distanceTo(to) <= PieceTypeInitializer.getMaxDistance((g.PieceTypes
					.get(g.b.getPieceAt(from).getName()).getAttributes()))
			&& -1 != PieceTypeInitializer.getMaxDistance((g.PieceTypes
					.get(g.b.getPieceAt(from).getName()).getAttributes()));
	static ORules canJump = (from, to, g) -> PieceTypeInitializer.canJump(
			(g.PieceTypes.get(g.b.getPieceAt(from).getName()).getAttributes()));
	static ORules canFly = (from, to, g) -> PieceTypeInitializer.canFly(
			(g.PieceTypes.get(g.b.getPieceAt(from).getName()).getAttributes()));
	static ORules canUnblock = (from, to, g) -> PieceTypeInitializer.canUnblock(
			(g.PieceTypes.get(g.b.getPieceAt(from).getName()).getAttributes()));

	static ORules OrthoLinear = (from, to, g) ->
	// is a ortogonal path to destination
	from.sameOrthogonal(to) &&
	// the distance is allowed flying or normal moving
			linearTestDistance(from, to, g) &&
			// the path is clear from pieces or (it can jump and there is a jumpable path)
			// or can fly
			(from.orthagonalILocationClear(to, (OrthoBoard) g.b, null, true, false)
					|| (canJump.oTest(from, to, g) && from.orthagonalILocationClear(
							to, (OrthoBoard) g.b, null, false, true)
							|| canFly.oTest(from, to, g)))
			&&
			// check that a location that path is unblocked and or it can unblock or it
			// can fly
			(from.orthagonalILocationClear(to, (OrthoBoard) g.b, LocationType.BLOCK,
					false, false) || canUnblock.oTest(from, to, g)
					|| canFly.oTest(from, to, g))
			&&
			// clear from exits or fly
			(from.orthagonalILocationClear(to, (OrthoBoard) g.b, LocationType.EXIT,
					false, false) || canFly.oTest(from, to, g))
			&&
			// landing position is not blocked
			(((OrthoBoard) g.b).getLocationType(to) != LocationType.BLOCK);
	// destiantion is clear or enemy piece

	// Rules for Ortho
	static ORules OrthoOmniRules = (from, to,
			g) -> ((OrthoBoard) g.b).getLocationType(to) != LocationType.BLOCK
					&& from.PathFind(g.b, to, (g.PieceTypes
							.get(g.b.getPieceAt(from).getName()).getAttributes()));

	private static boolean linearTestDistance(OrthoSquareCoordinate from,
			OrthoSquareCoordinate to, OrthoGame g)
	{
		if (getDistance.oTest(from, to, g)) {
			return true;
		} else {
			throw new EscapeException("Destiantion is too far to reach");
		}
	}

}
