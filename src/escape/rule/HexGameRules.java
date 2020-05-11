/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.rule;

import escape.HexGame;
import escape.board.*;
import escape.board.coordinate.HexCoordinate;
import escape.exception.EscapeException;
import escape.rule.OrthoGameRules.ORules;
import escape.util.PieceTypeInitializer;

/**
 * Description
 * 
 * @version 4 may. 2020
 */
public class HexGameRules
{

	@FunctionalInterface
	public interface HRules
	{

		public boolean hTest(HexCoordinate from, HexCoordinate to, HexGame h);

	}

	// for getting the attributes of the piece to move
	static HRules getDistance = (from, to, g) -> from
			.distanceTo(to) <= PieceTypeInitializer.getMaxDistance((( g.PieceTypes
					.get(g.b.getPieceAt(from).getName())).getAttributes()))
			&& -1 != PieceTypeInitializer.getMaxDistance((( g.PieceTypes
					.get(g.b.getPieceAt(from).getName())).getAttributes()));
	static HRules canJump = (from, to, g) -> PieceTypeInitializer.canJump(
			(((PieceTypeInitializer) g.PieceTypes.get(g.b.getPieceAt(from).getName())).getAttributes()));
	static HRules canFly = (from, to, g) -> PieceTypeInitializer.canFly(
			(((PieceTypeInitializer) g.PieceTypes.get(g.b.getPieceAt(from).getName())).getAttributes()));
	static HRules canUnblock = (from, to, g) -> PieceTypeInitializer.canUnblock(
			(((PieceTypeInitializer) g.PieceTypes.get(g.b.getPieceAt(from).getName())).getAttributes()));

	static HRules OmniHex = (from, to, g) ->
			 from.PathFind(g.b, to, ((g.PieceTypes
					.get(g.b.getPieceAt(from).getName())).getAttributes()));

	static HRules LinearHex = (from, to, g) ->
	// is a ortogonal path to destination
	// from.sameLine(to) &&
	// the distance is allowed flying or normal moving
	      linearTestDistance(from, to, g) &&
	// the path is clear from pieces or (it can jump and there is a jumpable path) or can
	// fly
			(from.pathIsClear(to, g.b, null, true, false) || (canJump.hTest(from, to,
					g) && from.pathIsClear(to, (HexBoard) g.b, null, false, true)
					|| canFly.hTest(from, to, g)))
			&&
			// check that a location that path is unblocked and or it can unblock or it
			// can fly
			(from.pathIsClear(to, g.b, LocationType.BLOCK, false, false)
					|| canUnblock.hTest(from, to, g) || canFly.hTest(from, to, g))
			&&
			// clear from exits or fly
			(from.pathIsClear(to, g.b, LocationType.EXIT, false, false)
					|| canFly.hTest(from, to, g))
			&&
			// landing position is not blocked
			((g.b).getLocationType(to) != LocationType.BLOCK) && from.sameLine(to);

	
	
	
	private static boolean linearTestDistance(HexCoordinate from, HexCoordinate to,HexGame g) {
		if(getDistance.hTest(from, to, g)) {
			return true;
		}
		else {
			throw new EscapeException("Destiantion is too far to reach");
		}
	}
	
	
}
