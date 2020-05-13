/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.board;

import java.awt.color.CMMException;
import java.util.*;
import escape.board.coordinate.*;
import escape.exception.EscapeException;
import escape.piece.EscapePiece;

/**
 * Description
 * 
 * @version 18 abr. 2020
 */

public class OrthoBoard implements Board<OrthoSquareCoordinate>
{
	Map<OrthoSquareCoordinate, LocationType> ortho;
	public Map<OrthoSquareCoordinate, EscapePiece> pieces;

	private final int xMax, yMax;

	OrthoBoard(int xmax, int ymax)
	{
		this.xMax = xmax;
		this.yMax = ymax;
		this.ortho = new HashMap<OrthoSquareCoordinate, LocationType>();
		this.pieces = new HashMap<OrthoSquareCoordinate, EscapePiece>();

	}

	public int getXMax()
	{
		return this.xMax;
	}

	public int getYMax()
	{
		return this.yMax;
	}

	/*
	 * @see escape.board.Board#getPieceAt(escape.board.coordinate.Coordinate)
	 */
	@Override
	public EscapePiece getPieceAt(OrthoSquareCoordinate coord)
	{

		return pieces.get(coord);
	}

	/*
	 * @see escape.board.Board#putPieceAt(escape.piece.EscapePiece,
	 * escape.board.coordinate.Coordinate)
	 */
	@Override
	public void putPieceAt(EscapePiece p, OrthoSquareCoordinate coord)
	{
		// check that the coordinate is in bounds
		if (coord.getX() > xMax || coord.getY() > yMax || coord.getX() < 1
				|| coord.getY() < 1) {
			throw new EscapeException("Coordinate out of Board");
		}
		// check the location is valid
		else if (getLocationType(coord) == LocationType.BLOCK) {
			throw new EscapeException("Location is Bloqued");
		} else {
			// if is at exit location dont place but dont give errors
			if (p == null && pieces.containsKey(coord)) {
				pieces.remove(coord);
			} else if (getLocationType(coord) != LocationType.EXIT)
				pieces.put(coord, p);
			setLocationType(coord, LocationType.CLEAR);
		}

	}

	/**
	 * Description set the location type of given coordinate
	 * 
	 * @param c
	 * @param lt
	 */
	public void setLocationType(OrthoSquareCoordinate c, LocationType lt)
	{
		if (c.getX() > xMax || c.getY() > yMax || c.getX() < 1 || c.getY() < 1) {
			throw new EscapeException("Coordinate out of Board");
		}
		ortho.put(c, lt);
	}

	/**
	 * Remove a piece from the given location on the board
	 * 
	 * @param coordinate
	 *            where piece is to be removed
	 */
	public void removePieceFrom(OrthoSquareCoordinate from)
	{
		if (getPieceAt(from) != null) {
			pieces.remove(from);
		}
	}

	/**
	 * Description get the type of given location, if it is undeclared get CLEAR
	 * 
	 * @param coord
	 * @return the locationtype at the spot
	 */
	public LocationType getLocationType(OrthoSquareCoordinate coord)
	{

		if (ortho.get(coord) == null) {
			return LocationType.CLEAR;
		}
		return ortho.get(coord);

	}

	/**
	 * This method is a forced puth piece, be carefull when using
	 * 
	 * @param p
	 * @param coord
	 */
	public void putPieceAtMidGame(EscapePiece p, OrthoSquareCoordinate coord)
	{
		// check that the piece can be placed in that location
		// if is at exit location dont place but dont give errors
		this.pieces.put(coord, p);
	}

	/**
	 * @return the pieces
	 */
	public Map<OrthoSquareCoordinate, EscapePiece> getPieces()
	{
		return pieces;
	}

}
