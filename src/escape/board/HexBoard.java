/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.board;

import java.util.*;
import escape.board.coordinate.*;
import escape.exception.EscapeException;
import escape.piece.EscapePiece;
import escape.util.LocationInitializer;

/**
 * Description
 * 
 * @version 15 abr. 2020
 */
public class HexBoard implements Board<HexCoordinate>
{
	Map<HexCoordinate, LocationType> hex;
	public Map<HexCoordinate, EscapePiece> pieces;

	private final int xmax, ymax;

	/**
	 * Description
	 */
	public HexBoard(int xmax, int ymax)
	{
		this.xmax = xmax;
		this.ymax = ymax;
		this.hex = new HashMap<HexCoordinate, LocationType>();
		this.pieces = new HashMap<HexCoordinate, EscapePiece>();

	}

	/*
	 * @see escape.board.Board#getPieceAt(escape.board.coordinate.Coordinate)
	 */
	@Override
	public EscapePiece getPieceAt(HexCoordinate coord)
	{
		return pieces.get(coord);
	}

	/*
	 * @see escape.board.Board#putPieceAt(escape.piece.EscapePiece,
	 * escape.board.coordinate.Coordinate)
	 */
	@Override
	public void putPieceAt(EscapePiece p, HexCoordinate coord)
	{
		// check that the piece can be placed in that location
		// if is at exit location dont place but dont give errors
		if (p == null && pieces.containsKey(coord)) {
			pieces.remove(coord);
		} else if ((getLocationType(coord) == null
				|| getLocationType(coord) == LocationType.CLEAR) &&
				getLocationType(coord) != LocationType.EXIT){
			this.pieces.put(coord, p);
		}

		// throw exception if is a blocked positon
		else if (getLocationType(coord) == LocationType.BLOCK) {
			throw new EscapeException("ups, location is blocked");
		}

	}
	/**
	 * This method is a forced puth piece, be carefull when
	 * using
	 * @param p
	 * @param coord
	 */
	public void putPieceAtMidGame(EscapePiece p, HexCoordinate coord)
	{
		// check that the piece can be placed in that location
		// if is at exit location dont place but dont give errors
		this.pieces.put(coord, p);
	}

	/**
	 * Remove a piece from the given location on the board
	 * 
	 * @param coordinate
	 *            where piece is to be removed
	 */
	public void removePieceFrom(HexCoordinate from)
	{
		if (getPieceAt(from) != null) {
			pieces.remove(from);
		}
	}
	/**
	 * @return the pieces
	 */
	public Map<HexCoordinate, EscapePiece> getPieces()
	{
		return pieces;
	}
	/**
	 * Description
	 * 
	 * @param c
	 * @param lt
	 */
	public void setLocationType(HexCoordinate c, LocationType lt)
	{
		hex.put(c, lt);
	}

	/**
	 * Description
	 * 
	 * @param coord
	 * @return
	 */
	public LocationType getLocationType(HexCoordinate coord)
	{
		// if the location was not declared in the config file default is clear
		if (hex.get(coord) == null) {
			setLocationType(coord, LocationType.CLEAR);
		}
		return hex.get(coord);

	}

}
