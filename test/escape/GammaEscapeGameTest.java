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

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import org.junit.jupiter.api.Test;
import escape.util.PieceTypeInitializer;

/**
 * Description
 * @version 9 may. 2020
 */
public class GammaEscapeGameTest
{
	
	
	@Test
    void CheckDefaultValueIsOne() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/Final/Final1.xml"));
        EscapeGameManager emg = egb.makeGameManager();
  
        assertTrue(emg instanceof HexGame);  
 
    }
}
