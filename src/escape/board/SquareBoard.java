/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ Copyright ©2016-2020 Gary F. Pollice
 *******************************************************************************/
package escape.board;

import java.util.*;
import escape.board.coordinate.*;
import escape.exception.EscapeException;
import escape.piece.EscapePiece;

/**
 * An example of how a Board might be implemented. This board has square coordinates and
 * finite bounds, represented by xMax and yMax. All methods required by the Board
 * interface have been implemented. Students would naturally add methods based upon theire
 * design.
 * 
 * @version Apr 2, 2020
 */
public class SquareBoard implements Board<SquareCoordinate>
{
	Map<SquareCoordinate, LocationType> squares;
	Map<SquareCoordinate, EscapePiece> pieces;
	private CoordinateID coorType = CoordinateID.SQUARE;

	private final int xMax, yMax;

	public SquareBoard(int xMax, int yMax)
	{
		this.xMax = xMax;
		this.yMax = yMax;
		pieces = new HashMap<SquareCoordinate, EscapePiece>();
		squares = new HashMap<SquareCoordinate, LocationType>();
	}

	public int getXMax()
	{
		return xMax;
	}

	public int getYMax()
	{
		return yMax;
	}

	/*
	 * @see escape.board.Board#getPieceAt(escape.board.coordinate.Coordinate)
	 */
	@Override
	public EscapePiece getPieceAt(SquareCoordinate coord)
	{
		if (coord.getX() > xMax || coord.getY() > yMax || coord.getX() < 1
				|| coord.getY() < 1) {
			throw new EscapeException("Coordinate out of Board");
		}
		return pieces.get(coord);
	}

	/*
	 * @see escape.board.Board#putPieceAt(escape.piece.EscapePiece,
	 * escape.board.coordinate.Coordinate)
	 */
	@Override
	public void putPieceAt(EscapePiece p, SquareCoordinate coord)
	{
		// check coordinate is in bounds
		if (coord.getX() > xMax || coord.getY() > yMax || coord.getX() < 1
				|| coord.getY() < 1) {
			throw new EscapeException("Coordinate out of Board");
		}
		// check location is valid
		else if (getLocationType(coord) == LocationType.BLOCK) {
			throw new EscapeException("Location is Bloqued");
		} else {

			if (p == null && pieces.containsKey(coord)) {
				pieces.remove(coord);
			}
			// if is at exit location dont place but dont give errors
			else if (getLocationType(coord) != LocationType.EXIT) {
				setLocationType(coord, LocationType.CLEAR);
				pieces.put(coord, p);
			}
		}
	}

	/**
	 * Description set location type at given coordinate
	 * 
	 * @param c
	 * @param lt
	 */
	public void setLocationType(SquareCoordinate c, LocationType lt)
	{
		if (c.getX() > xMax || c.getY() > yMax || c.getX() < 1 || c.getY() < 1) {
			throw new EscapeException("Coordinate out of Board");
		}
		squares.put(c, lt);
	}

	public void removePieceFrom(SquareCoordinate from)
	{
		if (getPieceAt(from) != null) {
			pieces.remove(from);
		}
	}

	/**
	 * Description get location type at given coordinate,
	 * 
	 * @param coord
	 * @return if not declared return CLEAR, else return the declaration
	 */
	public LocationType getLocationType(SquareCoordinate coord)
	{
		if (squares.get(coord) == null) {
			return LocationType.CLEAR;
		}
		return squares.get(coord);

	}
}
