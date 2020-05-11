/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ Copyright ©2016-2020 Gary F. Pollice
 *******************************************************************************/
package escape.board;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import org.junit.jupiter.api.*;
import escape.board.coordinate.*;
import escape.exception.EscapeException;
import escape.piece.*;
import escape.piece.EscapePiece;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.File;
import org.junit.jupiter.api.Test;
import escape.board.coordinate.*;
import escape.piece.EscapePiece;
import static escape.piece.Player.*;
import static escape.piece.PieceName.*;
import static escape.piece.EscapePiece.*;


/**
 * Description
 * 
 * @version Apr 2, 2020
 */
class BoardTest
{
	// square board test
	@Test
	void buildSquareBoard() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig1.xml"));
		Board board = bb.makeBoard();
		assertTrue(board instanceof SquareBoard);
		// Now I will do some tests on this board and its contents.
		// Create a piece equal to the one passed by the file
		EscapePiece piece =  EscapePiece.makePiece(Player.PLAYER1, PieceName.HORSE);
		assertNotNull(board.getPieceAt(escape.board.coordinate.SquareCoordinate.makeCoordinate(2,2)));
		assertTrue(piece.getName() == board.getPieceAt(
				escape.board.coordinate.SquareCoordinate.makeCoordinate(2, 2)).getName() 
				&&
				piece.getPlayer() == board.getPieceAt(
						escape.board.coordinate.SquareCoordinate.makeCoordinate(2, 2)).getPlayer()
				
				);
		// Check that initilazing a location type is correct from file
		assertEquals(LocationType.BLOCK, ((SquareBoard) board).getLocationType(
				escape.board.coordinate.SquareCoordinate.makeCoordinate(3, 5)));

	}

	// acces piece outside of the board
	@Test
	void SquareAccesCoordinateOutsideBoard() throws Exception
	{

		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig1.xml"));
		Board board = bb.makeBoard();
		Assertions.assertThrows(Exception.class,
				() -> board.getPieceAt(SquareCoordinate.makeCoordinate(0, 7)));
	}

	// piece on a blocked location
	@Test
	void squarePutAPieceOnABlockedLocation() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig1.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = EscapePiece.makePiece(Player.PLAYER1, PieceName.FROG);
		Assertions.assertThrows(Exception.class,
				() -> board.putPieceAt(p, SquareCoordinate.makeCoordinate(3, 5)));

	}

	// put a piece outside the board
	@Test
	void squarePutAPieceOutsideOfTheBoard1() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig1.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = new EscapePiece(Player.PLAYER1, PieceName.FROG);
		Assertions.assertThrows(Exception.class,
				() -> board.putPieceAt(p, SquareCoordinate.makeCoordinate(9, 9)));
	}

	@Test
	void squarePutAPieceOutsideOfTheBoard2() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig1.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = new EscapePiece(Player.PLAYER1, PieceName.FROG);
		Assertions.assertThrows(Exception.class,
				() -> board.putPieceAt(p, SquareCoordinate.makeCoordinate(0, 7)));
	}

	// add a piece to an exit postion and then check that the positon is empty
	@Test
	void squareExtiLocationTest() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig1.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = new EscapePiece(Player.PLAYER1, PieceName.FROG);
		board.putPieceAt(p, SquareCoordinate.makeCoordinate(5, 4));
		assertEquals(board.getPieceAt(SquareCoordinate.makeCoordinate(5, 4)), null);
	}

	
	@Test
	void squareAddNullPiece() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig1.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = null;
		board.putPieceAt(p, SquareCoordinate.makeCoordinate(2, 2));
		assertNull(board.getPieceAt(SquareCoordinate.makeCoordinate(2, 2)));
	}
	
	@Test
	void squareOutOfBounds() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig1.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = null;
		Assertions.assertThrows(Exception.class,
				() -> ((SquareBoard)board).setLocationType( SquareCoordinate.makeCoordinate(0, 7),LocationType.BLOCK));
		Assertions.assertThrows(Exception.class,
				() -> board.putPieceAt(p, SquareCoordinate.makeCoordinate(0, 7)));
		Assertions.assertThrows(Exception.class,
				() -> board.getPieceAt(SquareCoordinate.makeCoordinate(0, 7)));
	}
	
	
	
	// hexagonal board test
	@Test
	void buildHexBoard() throws Exception
	{

		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig2.xml"));
		Board b = bb.makeBoard();
		assertTrue(b instanceof HexBoard);
		// create a piece
		EscapePiece piece = makePiece(Player.PLAYER1, PieceName.HORSE);
		assertNotNull(b.getPieceAt(escape.board.coordinate.HexCoordinate.makeCoordinate(2, 2)));
		assertTrue(piece.equals(b.getPieceAt(
				escape.board.coordinate.HexCoordinate.makeCoordinate(2, 2))));
		// Check that initilazing a location type is correct from file
		assertEquals(LocationType.BLOCK, ((HexBoard) b).getLocationType(
				escape.board.coordinate.HexCoordinate.makeCoordinate(3, 5)));

	}

	@Test
	void hexBoardGetFromlargeCoordinates() throws Exception
	{

		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig2.xml"));
		Board b = bb.makeBoard();
		assertEquals(LocationType.CLEAR, ((HexBoard) b).getLocationType(
				escape.board.coordinate.HexCoordinate.makeCoordinate(100, 200)));

	}

	// add a piece to an exit postion and then check that the positon is empty
	@Test
	void hexBoardExtiLocationTest() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig2.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = new EscapePiece(Player.PLAYER1, PieceName.FROG);
		board.putPieceAt(p, HexCoordinate.makeCoordinate(5, 4));
		assertEquals(board.getPieceAt(HexCoordinate.makeCoordinate(5, 4)), null);
	}

	@Test
	void hexBoardPutAPieceOnABlockedLocation() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig2.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = new EscapePiece(Player.PLAYER1, PieceName.FROG);
		Assertions.assertThrows(Exception.class,
				() -> board.putPieceAt(p, HexCoordinate.makeCoordinate(3, 5)));

	}

	@Test
	void hexBoardPutAPieceOnAUndeclaredFromFileLocation() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig2.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = new EscapePiece(Player.PLAYER1, PieceName.FROG);
		board.putPieceAt(p, HexCoordinate.makeCoordinate(40, 50));
		assertEquals(LocationType.CLEAR, ((HexBoard) board)
				.getLocationType(HexCoordinate.makeCoordinate(40, 50)));
	}
	
	@Test
	void hexAddNullPiece() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig2.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = null;
		board.putPieceAt(p, HexCoordinate.makeCoordinate(2, 2));
		assertNull(board.getPieceAt(HexCoordinate.makeCoordinate(2, 2)));
	}
	
	// Orthogonal board test
	@Test
	void buildOrthoBoard() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig3.xml"));
		Board b = bb.makeBoard();
		assertTrue(b instanceof OrthoBoard);
		EscapePiece piece = makePiece(Player.PLAYER1, PieceName.HORSE);
		assertNotNull(b.getPieceAt(escape.board.coordinate.OrthoSquareCoordinate.makeCoordinate(2, 2)));
		assertTrue(piece
				.equals(b.getPieceAt(escape.board.coordinate.OrthoSquareCoordinate
						.makeCoordinate(2, 2))));
		// Check that initilazing a location type is correct from file
		assertEquals(LocationType.BLOCK, ((OrthoBoard) b).getLocationType(
				escape.board.coordinate.OrthoSquareCoordinate.makeCoordinate(3, 5)));
	}

	// put a piece outside the board
	@Test
	void orthoPutAPieceOutsideOfTheBoard1() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig3.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = new EscapePiece(Player.PLAYER1, PieceName.FROG);
		Assertions.assertThrows(Exception.class,
				() -> board.putPieceAt(p, OrthoSquareCoordinate.makeCoordinate(9, 9)));
	}

	@Test
	void orthoPutAPieceOutsideOfTheBoard2() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig3.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = new EscapePiece(Player.PLAYER1, PieceName.FROG);
		Assertions.assertThrows(Exception.class,
				() -> board.putPieceAt(p, OrthoSquareCoordinate.makeCoordinate(0, 7)));
	}

	// add a piece to an exit postion and then check that the positon is empty
	@Test
	void orthoExtiLocationTest() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig3.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = new EscapePiece(Player.PLAYER1, PieceName.FROG);
		board.putPieceAt(p, OrthoSquareCoordinate.makeCoordinate(5, 4));
		assertEquals(board.getPieceAt(OrthoSquareCoordinate.makeCoordinate(5, 4)), null);
	}
	
	@Test
	void orthoPutAPieceOnABlockedLocation() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig3.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = new EscapePiece(Player.PLAYER1, PieceName.FROG);
		Assertions.assertThrows(EscapeException.class,
				() -> board.putPieceAt(p, OrthoSquareCoordinate.makeCoordinate(3, 5)));

	}
	
	@Test
	void orthoAddNullPiece() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig3.xml"));
		Board board = bb.makeBoard();
		EscapePiece p = null;
		board.putPieceAt(p, OrthoSquareCoordinate.makeCoordinate(2, 2));
		assertNull(board.getPieceAt(OrthoSquareCoordinate.makeCoordinate(2, 2)));
	}

	
	
	// Other test
	@Test
	void unknownCoordinateSystem() throws Exception
	{
		BoardBuilder bb = new BoardBuilder(
				new File("config/board/BoardConfig4.xml"));
		Assertions.assertThrows(EscapeException.class, () -> bb.makeBoard());

	}

}
