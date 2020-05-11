/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ Copyright ©2016-2020 Gary F. Pollice
 *******************************************************************************/
package escape.board;

import static escape.board.LocationType.CLEAR;
import java.io.*;
import javax.xml.bind.*;
import escape.board.coordinate.*;
import escape.exception.EscapeException;
import escape.piece.EscapePiece;
import escape.util.*;

/**
 * A Builder class for creating Boards. It is only an example and builds just Square
 * boards. If you choose to use this
 * 
 * @version Apr 2, 2020
 */
public class BoardBuilder
{
	private BoardInitializer bi;

	/**
	 * The constructor for this takes a file name. It is either an absolute path or a path
	 * relative to the beginning of this project.
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public BoardBuilder(File fileName) throws Exception
	{
		JAXBContext contextObj = JAXBContext.newInstance(BoardInitializer.class);
		Unmarshaller mub = contextObj.createUnmarshaller();
		bi = (BoardInitializer) mub.unmarshal(new FileReader(fileName));
	}

	/**
	 * Description create a board with the specifications given on the board
	 * 
	 * @return Board
	 * @throws Exception
	 *             the information given on the file is incorrect
	 */
	public Board makeBoard()
	{
		Board b = null;
		try {
			// create the appropiate board using data from xml and the board factory
			b = BoardFactory.createB(bi.getCoordinateId(), bi.getxMax(),
					bi.getyMax());
			// initialize the board
			initializer(b, bi.getLocationInitializers());
		} catch (Exception e) {
			throw new EscapeException("oops");
		}

		return b;

	}

	/**
	 * Description initilize a given board of any type to the values decribed on the xml
	 * files
	 * 
	 * @param board
	 *            b
	 * @param initializers
	 */
	private void initializer(Board b, LocationInitializer... initializers)
	{
		// check what type of bord it is
		if (initializers != null) {
			if (b instanceof SquareBoard) {

				for (LocationInitializer li : initializers) {
					SquareCoordinate c = SquareCoordinate.makeCoordinate(li.x, li.y);
					if (li.pieceName != null) {
						b.putPieceAt(new EscapePiece(li.player, li.pieceName), c);
					}

					if (li.locationType != null && li.locationType != CLEAR) {
						((SquareBoard) b).setLocationType(c, li.locationType);
					}
				}
			} else if (b instanceof HexBoard) {

				for (LocationInitializer li : initializers) {
					HexCoordinate c = HexCoordinate.makeCoordinate(li.x, li.y);
					if (li.pieceName != null) {
						b.putPieceAt(new EscapePiece(li.player, li.pieceName), c);
					}

					if (li.locationType != null && li.locationType != CLEAR) {
						((HexBoard) b).setLocationType(c, li.locationType);
					}
				}

			} else if (b instanceof OrthoBoard) {

				for (LocationInitializer li : initializers) {
					OrthoSquareCoordinate c = OrthoSquareCoordinate
							.makeCoordinate(li.x, li.y);
					if (li.pieceName != null) {
						b.putPieceAt(new EscapePiece(li.player, li.pieceName), c);
					}

					if (li.locationType != null && li.locationType != CLEAR) {
						((OrthoBoard) b).setLocationType(c, li.locationType);
					}
				}
			}

		}
	}

}
