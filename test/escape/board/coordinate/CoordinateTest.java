/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2020 Gary F. Pollice
 *******************************************************************************/

package escape.board.coordinate;

import static escape.board.coordinate.SquareCoordinate.makeCoordinate;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;
import escape.exception.EscapeException;
import static escape.board.coordinate.HexCoordinate.makeCoordinate;
import static escape.board.coordinate.OrthoSquareCoordinate.makeCoordinate;

/**
 * Tests for various coordinates
 * @version Mar 28, 2020
 */
class CoordinateTest
{
	
	//Square Coordinate Test
	@Test
	void diagonalTest() {
		assertFalse(SquareCoordinate.makeCoordinate(7, 8).sameDiagonal(SquareCoordinate.makeCoordinate(6, 8)));
	}
	@Test
	void squareCoordinateTestDistance1() {
		assertEquals(1,SquareCoordinate.makeCoordinate(1,1).distanceTo(SquareCoordinate.makeCoordinate(2,2)));
	}
	@Test
	void squareCoordinateTestDistance2() {
		assertEquals(3,SquareCoordinate.makeCoordinate(1,2).distanceTo(SquareCoordinate.makeCoordinate(3,5)));
	}
	@Test
	void squareWithOrthoSquareException() {
		Assertions.assertThrows(EscapeException.class, ()-> SquareCoordinate.makeCoordinate(1,2).distanceTo(OrthoSquareCoordinate.makeCoordinate(3,5)));
	}
	
	
	
	
	
	//Hex Coordinate test
	@Test
	void hexCoordinateTestDistance1() {
		assertEquals(2,HexCoordinate.makeCoordinate(0,0).distanceTo(HexCoordinate.makeCoordinate(-1,2)));
	}
	@Test
	void hexCoordinateTestDistance2() {
		assertEquals(4,HexCoordinate.makeCoordinate(-1,2).distanceTo(HexCoordinate.makeCoordinate(2,-2)));
	}
	@Test
	void hexCoordinateTestDistance3() {
		assertEquals(4,HexCoordinate.makeCoordinate(-1,-1).distanceTo(HexCoordinate.makeCoordinate(1,1)));
	}
	@Test
	void hexCoordinateTestDistance4() {
		assertEquals(4,HexCoordinate.makeCoordinate(-1,-1).distanceTo(HexCoordinate.makeCoordinate(2,0)));
	}
	@Test
	void hexCoordinateTestDistance5() {
		assertEquals(6,HexCoordinate.makeCoordinate(3,0).distanceTo(HexCoordinate.makeCoordinate(0,-3)));
	}
	
	@Test
	void hexCoordinaTrueteSameLine() {
		assertTrue(HexCoordinate.makeCoordinate(-2,0).sameLine(HexCoordinate.makeCoordinate(0,-2)));
	}
	
	@Test
	void hexCoordinaFalseteSameLine2() {
		assertFalse(HexCoordinate.makeCoordinate(-1,-4).sameLine(HexCoordinate.makeCoordinate(-3,-3)));
	}
	@Test
	void hexCoordinaFalseteSameLine3() {
		assertFalse(HexCoordinate.makeCoordinate(-1,-4).sameLine(HexCoordinate.makeCoordinate(1,-5)));
	}
	@Test
	void hexWithSquareException() {
		Assertions.assertThrows(EscapeException.class, ()-> HexCoordinate.makeCoordinate(1,2).distanceTo(SquareCoordinate.makeCoordinate(3,5)));
	}
	
	

	
	//Orthogonal Square Coordinate Test
	@Test
	void orthoCoordinateTestDistance1() {
		assertEquals(2,OrthoSquareCoordinate.makeCoordinate(1,1).distanceTo(OrthoSquareCoordinate.makeCoordinate(2,2)));
	}
	@Test
	void orthoCoordinateTestDistance2() {
		assertEquals(5,OrthoSquareCoordinate.makeCoordinate(1,2).distanceTo(OrthoSquareCoordinate.makeCoordinate(3,5)));
	}
	@Test
	void orthoCoordinateTestDistance3() {
		assertEquals(8,OrthoSquareCoordinate.makeCoordinate(7,8).distanceTo(OrthoSquareCoordinate.makeCoordinate(4,3)));
	}
	
	@Test
	void OrthoWithSquareException() {
		Assertions.assertThrows(EscapeException.class, ()-> OrthoSquareCoordinate.makeCoordinate(1,2).distanceTo(HexCoordinate.makeCoordinate(3,5)));
	}
	
	// test equals
	
	@Test
	void OrthoNotEquals() {
		assertFalse(OrthoSquareCoordinate.makeCoordinate(2, 2).equals(HexCoordinate.makeCoordinate(1, 1)));
	}
	
	@Test
	void HexNotEquals() {
		assertFalse(HexCoordinate.makeCoordinate(2, 2).equals(SquareCoordinate.makeCoordinate(1, 1)));
	}
	
	
	
	
    
}
