/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.board.coordinate;

import java.util.*;
import escape.astar.OrthoBoardAStar;
import escape.board.*;
import escape.exception.EscapeException;
import escape.util.PieceTypeInitializer;
import escape.util.PieceTypeInitializer.PieceAttribute;

/**
 * Description
 * 
 * @version 13 abr. 2020
 */
public class OrthoSquareCoordinate implements Coordinate
{

	private final int x;
	private final int y;

	private OrthoSquareCoordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public static OrthoSquareCoordinate makeCoordinate(int x, int y)
	{
		return new OrthoSquareCoordinate(x, y);
	}

	/*
	 * @see
	 * escape.board.coordinate.Coordinate#distanceTo(escape.board.coordinate.Coordinate)
	 */
	@Override
	public int distanceTo(Coordinate c)
	{
		if (c instanceof OrthoSquareCoordinate) {
			OrthoSquareCoordinate s = (OrthoSquareCoordinate) c;
			int dx = Math.abs(this.x - s.x);
			int dy = Math.abs(this.y - s.y);
			return dx + dy;
		} else {
			throw new EscapeException("wrong coordinate type");
		}

	}

	/**
	 * Find if two coordinates are in the same Orthogonal path
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public boolean sameOrthogonal(OrthoSquareCoordinate to)
	{
		// check if its horizontal
		if (this.getX() == to.getX() && this.getY() != to.getY()) {
			return true;
		}
		// check if its vertical
		else if (this.getY() == to.getY() && this.getX() != to.getX()) {
			return true;
		} else
			return false;
	}

	/**
	 * Description calculate if the orthogonal is clear from given location and Pieces
	 * 
	 * @param to
	 *            Coordinate to destination
	 * @param b
	 *            board
	 * @param l
	 *            location type to check for
	 * @param Pieces
	 *            if true check for pieces in the path else check the given location
	 * @param Jump
	 *            true if want to check to see if the path is a jumpbable path
	 * @return true if the orthogonal is clear or jumpable
	 */
	public boolean orthagonalILocationClear(OrthoSquareCoordinate to, OrthoBoard b,
			LocationType l, boolean Pieces, boolean Jump)
	{

		// check all four direction options

		// when going horizontal and to the rigth
		if (this.getX() == to.getX() && this.getY() < to.getY()) {
			int ypos = this.getY() + 1;
			int jumpCounter = 0;
			for (int i = 0; i < this.distanceTo(to) - 1; i++) {

				if (Pieces) {
					if (b.getPieceAt(makeCoordinate(this.getX(), ypos)) != null) {
						return false;
					}
				} else if (Jump) {
					if (b.getPieceAt(makeCoordinate(this.getX(), ypos)) != null) {
						jumpCounter++;
					} else {
						jumpCounter--;
					}
					if (jumpCounter > 1) {
						return false;
					}
				}

				else {
					if ((b.getLocationType(
							makeCoordinate(this.getX(), ypos)) == l)) {
						return false;
					}
				}
				ypos++;
			}

		}
		// when going horizontal and to the left
		else if (this.getX() == to.getX() && this.getY() > to.getY()) {
			int ypos = this.getY() - 1;
			int jumpCounter = 0;
			for (int i = 0; i < this.distanceTo(to) - 1; i++) {
				if (Pieces) {
					if (b.getPieceAt(makeCoordinate(this.getX(), ypos)) != null) {
						return false;
					}
				} else if (Jump) {
					if (b.getPieceAt(makeCoordinate(this.getX(), ypos)) != null) {
						jumpCounter++;
					} else {
						jumpCounter--;
					}
					if (jumpCounter > 1) {
						return false;
					}
				}

				else {
					if ((b.getLocationType(
							makeCoordinate(this.getX(), ypos)) == l)) {
						return false;
					}
				}
				ypos--;
			}
		}
		// when going vertical and up
		else if (this.getY() == to.getY() && this.getX() < to.getX()) {
			int xpos = this.getX() + 1;
			int jumpCounter = 0;
			for (int i = 0; i < this.distanceTo(to) - 1; i++) {
				if (Pieces) {
					if (b.getPieceAt(makeCoordinate(xpos, this.getY())) != null) {
						return false;
					}
				} else if (Jump) {
					if (b.getPieceAt(makeCoordinate(xpos, this.getY())) != null) {
						jumpCounter++;
					} else {
						jumpCounter--;
					}
					if (jumpCounter > 1) {
						return false;
					}
				} else {
					if ((b.getLocationType(
							makeCoordinate(xpos, this.getY())) == l)) {
						return false;
					}
				}
				xpos++;
			}
		}
		// when going vertical and down
		else if (this.getY() == to.getY() && this.getX() > to.getX()) {
			int xpos = this.getX() - 1;
			int jumpCounter = 0;
			for (int i = 0; i < this.distanceTo(to) - 1; i++) {
				if (Pieces) {
					if (b.getPieceAt(makeCoordinate(xpos, this.getY())) != null) {
						return false;
					}
				} else if (Jump) {
					if (b.getPieceAt(makeCoordinate(xpos, this.getY())) != null) {
						jumpCounter++;
					} else {
						jumpCounter--;
					}
					if (jumpCounter > 1) {
						return false;
					}
				} else {
					if ((b.getLocationType(
							makeCoordinate(xpos, this.getY())) == l)) {
						return false;
					}
				}
				xpos--;
			}
		}

		return true;
	}

	/**
	 * find if there is a path between this an a given coordinate
	 * 
	 * @param b
	 *            the board
	 * @param to
	 *            the destination
	 * @param p
	 *            the attributes to set rules
	 * @return true when there is a path
	 */
	public boolean PathFind(OrthoBoard b, OrthoSquareCoordinate to,
			PieceAttribute[] p)
	{

		OrthoBoardAStar o = new OrthoBoardAStar(b, this.x,this.y, p);
		ArrayList<OrthoBoardAStar.Node> path = o.findPathToOrtho(to.getX(),
				to.getY());
		int pathsize = path.size()-1;
		if (path != null
				&& path.size() - 1 <= PieceTypeInitializer.getMaxDistance(p)) {

			return true;
		} else {
			if(path == null) {
				throw new EscapeException("No path found to destination");
				
			}
			else if(pathsize > PieceTypeInitializer.getMaxDistance(p)) {
				throw new EscapeException("Destiantion is too far to reach");
			}
			return false;
		}
	}

	/**
	 * @return the x
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY()
	{
		return y;
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(x, y);
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{

		if (!(obj instanceof OrthoSquareCoordinate)) {
			return false;
		}
		OrthoSquareCoordinate other = (OrthoSquareCoordinate) obj;
		return x == other.getX() && y == other.getY();
	}

}
