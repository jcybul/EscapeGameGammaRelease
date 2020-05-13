/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.board.coordinate;

import java.util.*;
import java.util.stream.Stream;
import escape.astar.HexBoardAStar;
import escape.board.*;
import escape.exception.EscapeException;
import escape.util.PieceTypeInitializer;
import escape.util.PieceTypeInitializer.PieceAttribute;

/**
 * Description
 * 
 * @version 13 abr. 2020
 */
public class HexCoordinate implements Coordinate
{
	private final int x;
	private final int y;

	/**
	 * Description
	 */
	private HexCoordinate(int x, int y)
	{
		this.x = x;
		this.y = y;

	}

	/**
	 * Create a new hex coordinate object with the given x and y coordinates.
	 * 
	 * @param x
	 * @param y
	 * @return a newly created HexCoordinate object
	 */
	public static HexCoordinate makeCoordinate(int x, int y)
	{

		return new HexCoordinate(x, y);
	}

	/*
	 * @see
	 * escape.board.coordinate.Coordinate#distanceTo(escape.board.coordinate.Coordinate)
	 * implemented using the algorithm Found on:
	 * https://www.redblobgames.com/grids/hexagons/#distances on the distances section and
	 * axial coordinates sub-section
	 */
	@Override
	public int distanceTo(Coordinate c)
	{
		if (c instanceof HexCoordinate) {
			HexCoordinate h = (HexCoordinate) c;
			return (Math.abs(this.x - h.x) + Math.abs(this.x + this.y - h.x - h.y)
					+ Math.abs(this.y - h.y)) / 2;
		} else {
			throw new EscapeException("wrong coordinate type");
		}

	}

	/**
	 * Depending on the input values check if the path is clear from pices block and exit
	 * spots
	 * 
	 * @param to
	 *            destiantion
	 * @param b
	 *            board to check
	 * @param l
	 *            location type to check for
	 * @param Pieces
	 *            true to check for pieces
	 * @param Jump
	 *            true to check fro legal jumps
	 * @return true if the path is clear
	 */
	public boolean pathIsClear(HexCoordinate to, HexBoard b, LocationType l,
			boolean Pieces, boolean Jump)
	{
		// one of these cases
		///// 1////
		if (this.getX() > to.getX() && this.getY() < to.getY()) {

			int ypos = this.getY() + 1;
			int xpos = this.getX() - 1;
			int jumpCounter = 0;
			for (int i = 0; i < this.distanceTo(to) - 1; i++) {

				if (Pieces) {
					if (b.getPieceAt(makeCoordinate(xpos, ypos)) != null) {
						return false;
					}
				} else if (Jump) {
					if (b.getPieceAt(makeCoordinate(xpos, ypos)) != null) {
						jumpCounter++;
					} else {
						jumpCounter--;
					}
					if (jumpCounter > 1) {
						return false;
					}
				}

				else {
					if ((b.getLocationType(makeCoordinate(xpos, ypos)) == l)) {
						return false;
					}
				}
				ypos++;
				xpos--;
			}
			if (!to.equals(HexCoordinate.makeCoordinate(xpos, ypos))) {
				return false;
			}
		}
		//// 2/////
		else if (this.getX() < to.getX() && this.getY() > to.getY()) {

			int ypos = this.getY() - 1;
			int xpos = this.getX() + 1;
			int jumpCounter = 0;
			for (int i = 0; i < this.distanceTo(to) - 1; i++) {

				if (Pieces) {
					if (b.getPieceAt(makeCoordinate(xpos, ypos)) != null) {
						return false;
					}
				} else if (Jump) {
					if (b.getPieceAt(makeCoordinate(xpos, ypos)) != null) {
						jumpCounter++;
					} else {
						jumpCounter--;
					}
					if (jumpCounter > 1) {
						return false;
					}
				}

				else {
					if ((b.getLocationType(makeCoordinate(xpos, ypos)) == l)) {
						return false;
					}
				}
				ypos--;
				xpos++;
			}
			if (!to.equals(HexCoordinate.makeCoordinate(xpos, ypos))) {
				return false;
			}

		}
		////// 3//////
		else if (this.getX() == to.getX() && this.getY() < to.getY()) {

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
			if (!to.equals(HexCoordinate.makeCoordinate(this.x, ypos))) {
				return false;
			}

		}
		///// 4//////
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
			if (!to.equals(HexCoordinate.makeCoordinate(this.x, ypos))) {
				return false;
			}
		}
		// when going vertical and up
		/////// 5//////
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
			if (!to.equals(HexCoordinate.makeCoordinate(xpos, this.y))) {
				return false;
			}
		}
		// when going vertical and down
		////// 6//////
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
			if (!to.equals(HexCoordinate.makeCoordinate(xpos, this.y))) {
				return false;
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
	public boolean PathFind(HexBoard b, HexCoordinate to, PieceAttribute[] p)
	{

		HexBoardAStar o = new HexBoardAStar(b, this.x, this.y, p);
		ArrayList<HexBoardAStar.Node> path = o.findPathToHex(to.getX(), to.getY());

		if (path != null
				&& path.size() - 1 <= PieceTypeInitializer.getMaxDistance(p)) {
			return true;
		} else {
			if (path == null) {
				throw new EscapeException("invalid Path ");
			}

			if (path.size() - 1 > PieceTypeInitializer.getMaxDistance(p)) {
				throw new EscapeException("Destiantion is too far to reach");
			}
			return false;
		}

	}

	/**
	 * check if two points are in the same line on a hex board
	 * 
	 * @param to
	 * @return
	 */
	public boolean sameLine(HexCoordinate to)
	{
		if (this.getX() == to.getX() || this.getY() == to.getY() || ((Math
				.abs(this.getX() - to.getX()) == Math.abs(this.getY() - to.getY()))
				&& ((this.getX() > to.getX() && this.getY() < to.getY())
						|| (this.getX() < to.getX() && this.getY() > to.getY())))) {
			return true;
		} else {
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

		if (!(obj instanceof HexCoordinate)) {
			return false;
		}
		HexCoordinate other = (HexCoordinate) obj;
		return x == other.getX() && y == other.getY();
	}

}
