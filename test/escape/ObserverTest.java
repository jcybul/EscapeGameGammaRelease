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
	
	@Test
	public void HexGametestobserver() throws Exception {
		EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/Final/Final1.xml"));
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
        = new EscapeGameBuilder(new File("config/Final/Final1.xml"));
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
        = new EscapeGameBuilder(new File("config/Final/Final1.xml"));
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
        = new EscapeGameBuilder(new File("config/Final/Final1.xml"));
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
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
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
            = new EscapeGameBuilder(new File("config/Final/Final1.xml"));
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
            = new EscapeGameBuilder(new File("config/Final/Final1.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        ObserverTest ot = new ObserverTest();
        emg.addObserver(ot);
        assertTrue(emg.move(emg.makeCoordinate(3,3), emg.makeCoordinate(3,4)));
        emg.move(emg.makeCoordinate(0,-1), emg.makeCoordinate(-5,4));
        assertEquals(ot.m, "Is Player 2 turn");
        
    }
	
    
	
	
	
	
	
	

	


	
	
	

}
