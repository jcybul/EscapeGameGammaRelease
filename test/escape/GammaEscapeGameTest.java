/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import org.junit.jupiter.api.Test;
import escape.board.coordinate.HexCoordinate;
import escape.piece.*;
import escape.util.PieceTypeInitializer;

/**
 * Description
 * @version 9 may. 2020
 */
public class GammaEscapeGameTest
{
	
	// HEX GAME TEST 
	@Test
    void CheckDefaultValueIsOne() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/HexObserver.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame);  
        assertEquals(1,emg.getPieceAt(HexCoordinate.makeCoordinate(2, 2)).getValue());
 
    }
	
	@Test
    void CheckDefaultValueIsGiven() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/HexObserver.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame);  
        assertEquals(5,emg.getPieceAt(HexCoordinate.makeCoordinate(5, 5)).getValue());
 
    }
	
	@Test
    void HexGamePlayer1Wins() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/HexGame.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame);  
        ObserverTest ot = new ObserverTest();
        emg.addObserver(ot);
        // attack piece and remove
        EscapePiece piece = EscapePiece.makePiece(Player.PLAYER1, PieceName.FROG);
        emg.move(emg.makeCoordinate(3, -2), emg.makeCoordinate(2,-2));
        
        //check the piece has been replaced 
        assertTrue(piece.equals(emg.getPieceAt(emg.makeCoordinate(2,-2))));
        
        //attempt move that is false to then check that player two can still
        //play
        emg.move(emg.makeCoordinate(-4, 0),emg.makeCoordinate(-3, 0));
        
        //move a player two piece 
        emg.move(emg.makeCoordinate(1, -3), emg.makeCoordinate(1, -4));
        
        //move to exit
        assertTrue(emg.move(emg.makeCoordinate(1,0),emg.makeCoordinate(0,0)));
        
        // check that the player won after whis move
        assertEquals(ot.m,"PLAYER1 wins");
       
        //make move and check that the observer tells us the game is over
        emg.move(emg.makeCoordinate(0,-1),emg.makeCoordinate(0, -2));
        assertEquals(ot.m, "Game is over and PLAYER1 has won");
 
    }
	
	@Test
    void HexGamePlayer2Wins() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/HexGame2.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame);  
        ObserverTest ot = new ObserverTest();
        emg.addObserver(ot);
      
        // 1  move a player one piece 
        emg.move(emg.makeCoordinate(1, -3), emg.makeCoordinate(1, -4));
        
        // 2  move to exit
        assertTrue(emg.move(emg.makeCoordinate(1,0),emg.makeCoordinate(0,0)));
        
        // 1  move ant reach the limit
        emg.move(emg.makeCoordinate(3, -2), emg.makeCoordinate(4, -2));

        // check that the player won after whis move
        assertEquals(ot.m,"PLAYER2 wins");
        
        //make move and check that the observer tells us the game is over
        emg.move(emg.makeCoordinate(0,-1),emg.makeCoordinate(0, -2));
        assertEquals(ot.m, "Game is over and PLAYER2 has won");
 
    }
	
	@Test
    void HexGamePointConlflict() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/HexGame3.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame);  
        ObserverTest ot = new ObserverTest();
        emg.addObserver(ot);
 
        // 1  move a player one piece 
        emg.move(emg.makeCoordinate(-1, 4), emg.makeCoordinate(0, 4));
        assertEquals(4,emg.getPieceAt(emg.makeCoordinate(0, 4)).getValue());
        emg.move(emg.makeCoordinate(-2, 5), emg.makeCoordinate(-1, 5));
        assertNull(emg.getPieceAt(emg.makeCoordinate(-1, 5)));
    }
	
	@Test
    void HexGameTiedGame() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/HexGame4.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame);  
        ObserverTest ot = new ObserverTest();
        emg.addObserver(ot);
        // 1  move a player one piece 
        emg.move(emg.makeCoordinate(-3, 1), emg.makeCoordinate(-4, 2));
        assertEquals(ot.m,", The game is tied");
    }
	
	// ORTHO GAME TEST 

	@Test
    void OrthoGamePlayer1Wins() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/OrthoGame.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);  
        ObserverTest ot = new ObserverTest();
        emg.addObserver(ot);
        // attack piece and remove
        EscapePiece piece = EscapePiece.makePiece(Player.PLAYER1, PieceName.HORSE);
        emg.move(emg.makeCoordinate(7, 8), emg.makeCoordinate(8,8));
        
        //check the piece has been replaced 
        assertTrue(piece.equals(emg.getPieceAt(emg.makeCoordinate(8,8))));
        
        //attempt move that is false to then check that player two can still
        //play
        emg.move(emg.makeCoordinate(1, 1),emg.makeCoordinate(1, 1));
        
        //move a player two piece 
        emg.move(emg.makeCoordinate(8, 3), emg.makeCoordinate(8, 2));
        
        //move to exit
        assertTrue(emg.move(emg.makeCoordinate(5,3),emg.makeCoordinate(4,3)));
        
        // check that the player won after whis move
        assertEquals(ot.m,"PLAYER1 wins");
       
        //make move and check that the observer tells us the game is over
        emg.move(emg.makeCoordinate(1,1),emg.makeCoordinate(1,1));
        assertEquals(ot.m, "Game is over and PLAYER1 has won");
 
    }
	
	@Test
    void OrthoGamePlayer2Wins() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/OrthoGame2.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);  
        ObserverTest ot = new ObserverTest();
        emg.addObserver(ot);
      
        // 1  move a player one piece 
        emg.move(emg.makeCoordinate(8, 3), emg.makeCoordinate(8, 4));
        
        // 2  move to exit
        assertTrue(emg.move(emg.makeCoordinate(5,3),emg.makeCoordinate(4,3)));
        
        // 1  move and reach the limit
        emg.move(emg.makeCoordinate(8,4), emg.makeCoordinate(7,4));

        // check that the player won after whis move
        assertEquals(ot.m,"PLAYER2 wins");
        
        //make move and check that the observer tells us the game is over
        emg.move(emg.makeCoordinate(0,-1),emg.makeCoordinate(0, -2));
        assertEquals(ot.m, "Game is over and PLAYER2 has won");
 
    }
	@Test
    void OrthoGamePointConlflict() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/OrthoGame3.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);  
        ObserverTest ot = new ObserverTest();
        emg.addObserver(ot);
 
        // 1  move a player one piece 
        emg.move(emg.makeCoordinate(7, 8), emg.makeCoordinate(8, 8));
        assertEquals(4,emg.getPieceAt(emg.makeCoordinate(8,8)).getValue());
        emg.move(emg.makeCoordinate(8, 3), emg.makeCoordinate(8, 4));
        assertNull(emg.getPieceAt(emg.makeCoordinate(8, 4)));
    }
	
	@Test
    void OrthoGameTiedGame() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/OrthoGame4.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);  
        ObserverTest ot = new ObserverTest();
        emg.addObserver(ot);
        // 1  move a player one piece 
        emg.move(emg.makeCoordinate(7, 8), emg.makeCoordinate(6, 8));
        assertEquals(ot.m,", The game is tied");
        emg.move(emg.makeCoordinate(7, 8), emg.makeCoordinate(6, 8));
        emg.removeObserver(ot);
    }
	
	
	
}
