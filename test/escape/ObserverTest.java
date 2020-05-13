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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import escape.board.coordinate.*;

/**
 * Description
 * @version 9 may. 2020
 */
public class ObserverTest implements GameObserver
{
	
	String m;
	/*
	 * @see escape.GameObserver#notify(java.lang.String)
	 */
	@Override
	public void notify(String message)
	{
		m = message;
		
	}

	/*
	 * @see escape.GameObserver#notify(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void notify(String message, Throwable cause)
	{
		// TODO Auto-generated method stub
		
	}
	
	
	/// hex game observer test
	
	@Test
	public void HexGametestobserver() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/HexObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof HexGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    assertFalse(emg.move(emg.makeCoordinate(0, 2),emg.makeCoordinate(1, 1)));
    assertEquals(ot.m, "Starting location must have a piece to move");
    emg.removeObserver(ot);
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(2, 2),emg.makeCoordinate(2, 2));
    assertEquals(ot.m, "Cant go to the same place it comes from");
	}
	
	@Test
	public void HexGamemoveToSameLocation() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/HexObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof HexGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(2, 2),emg.makeCoordinate(2, 2));
    assertEquals(ot.m, "Cant go to the same place it comes from");
	}

	@Test
	public void HexGameLandOnBlockedLocation() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/HexObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof HexGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(2, 2),emg.makeCoordinate(-2,-1));
    assertEquals(ot.m, "Cant Land on a Blocked Location");
	}
	
	@Test
	public void HexGameLandOnAlly() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/HexObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof HexGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(2, 2),emg.makeCoordinate(1,2));
    assertEquals(ot.m, "The piece at the destination must be an enemy peice");
	}
	
	@Test
    public void HexGameDistanceMoreThanAllowed() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/HexObserver.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        ObserverTest ot = new ObserverTest();
        emg.addObserver(ot);
        assertFalse(emg.move(emg.makeCoordinate(0,-1), emg.makeCoordinate(-5,4)));
        assertEquals(ot.m, "Destiantion is too far to reach");
        
    }
    
	@Test
    public void HexGameWrongTurnPlayer1() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/HexObserver.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        ObserverTest ot = new ObserverTest();
        emg.addObserver(ot);
        assertFalse(emg.move(emg.makeCoordinate(3,2), emg.makeCoordinate(-5,4)));
        assertEquals(ot.m, "Is Player 1 turn");
        
    }
	
	@Test
    public void HexGameWrongTurnPLayer2() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/HexObserver.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        ObserverTest ot = new ObserverTest();
        emg.addObserver(ot);
        assertTrue(emg.move(emg.makeCoordinate(3,3), emg.makeCoordinate(3,4)));
        emg.move(emg.makeCoordinate(0,-1), emg.makeCoordinate(-5,4));
        assertEquals(ot.m, "Is Player 2 turn");
        
    }
	
    
	// orho game observer test 
	
	@Test
	public void OrthoGameMoveToSameLocation() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/OrthoObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof OrthoGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(5, 3),emg.makeCoordinate(5, 3));
    assertEquals(ot.m, "Cant go to the same place it comes from");
	}
	
	@Test
	public void OrthoGameLandOnBlock() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/OrthoObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof OrthoGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(5, 3),emg.makeCoordinate(5, 2));
    assertEquals(ot.m, "Cant Land on a Blocked Location");
	}
	
	@Test
	public void OrthoGameLandAttackAlly() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/OrthoObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof OrthoGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(7, 8),emg.makeCoordinate(8, 8));
    assertEquals(ot.m, "The piece at the destination must be an enemy peice");
	}
	
	
	@Test
	public void OrthoGameWrongTurn() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/OrthoObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof OrthoGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(8,3),emg.makeCoordinate(8, 4));
    assertEquals(ot.m, "Is Player 1 turn");
	}
	
	@Test
	public void OrthoGameTooFar() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/OrthoObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof OrthoGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(5,3),emg.makeCoordinate(1, 5));
    assertEquals(ot.m, "Destination is too far to reach");
	}
	
	@Test
    public void OrthoGameCheckDefaultValueIsOne() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/OrthoObserver.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);  
        assertEquals(1,emg.getPieceAt(OrthoSquareCoordinate.makeCoordinate(5, 3)).getValue());
 
    }
	
	@Test
    public void OrhoGameCheckDefaultValueIsGiven() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/OrthoObserver.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);  
        assertEquals(5,emg.getPieceAt(OrthoSquareCoordinate.makeCoordinate(8, 3)).getValue());
 
    }
	
	// square game observer test 
	@Test
	public void SquareGameMoveToSameLocation() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/SquareObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof SquareGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(5, 3),emg.makeCoordinate(5, 3));
    assertEquals(ot.m, "Cant go to the same place it comes from");
	}
	
	@Test
	public void SquareGameLandOnBlock() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/SquareObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof SquareGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(5, 3),emg.makeCoordinate(5, 2));
    assertEquals(ot.m, "Cant Land on a Blocked Location");
	}
	
	@Test
	public void SquareGameLandAttackAlly() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/SquareObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof SquareGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(7, 8),emg.makeCoordinate(8, 8));
    assertEquals(ot.m, "The piece at the destination must be an enemy peice");
	}
	
	@Test
	public void SquareGameWrongTurn() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/SquareObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof SquareGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(8,3),emg.makeCoordinate(8, 4));
    assertEquals(ot.m, "Is Player 1 turn");
	}
	
	@Test
	public void SquareGameTooFar() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/SquareObserver.xml"));
    EscapeGameManager emg = egb.makeGameManager();
    assertTrue(emg instanceof SquareGame);  
    ObserverTest ot = new ObserverTest();
    emg.addObserver(ot);
    emg.move(emg.makeCoordinate(5,3),emg.makeCoordinate(1, 5));
    assertEquals(ot.m, "Destination is too far to reach");
	}
	
	@Test
    public void SquareGameCheckDefaultValueIsOne() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/SquareObserver.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);  
        assertEquals(1,emg.getPieceAt(SquareCoordinate.makeCoordinate(5, 3)).getValue());
 
    }
	
	@Test
    public void SquareGameCheckDefaultValueIsGiven() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/SquareObserver.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);  
        assertEquals(5,emg.getPieceAt(SquareCoordinate.makeCoordinate(8, 3)).getValue());
 
    }
	
	
	

}
