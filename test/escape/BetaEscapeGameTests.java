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

package escape;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import org.junit.jupiter.api.*;
import escape.board.LocationType;
import escape.board.coordinate.*;
import escape.exception.EscapeException;
import escape.piece.*;
import escape.util.LocationInitializer;

/**
 * Description
 * @version Apr 24, 2020
 */
class BetaEscapeGameTests
{
    /**
     * Example of how the game manager tests will be structured.
     * @throws Exception
     */
	// move  a frog from a position to other on a clean path trough diagonal
    @Test
    void SquareLinearDiagonal1() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(7, 7)));
        
    }
    
    @Test
    void SquareLinearOrthogonal1() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(5, 1)));

        
    }
    @Test
    void SquareLinearRandom() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(8, 3)));
        
    }
    @Test
    void SquareLinearMoreThanDistance() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(5,12), emg.makeCoordinate(5, 18)));
    }
    
    @Test
    void SquareLinearJumoOverOnePiece() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
    }
    
    @Test
    void SquareLinearJumoOverTwoPieces() throws Exception
    {
    	
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(3,5), emg.makeCoordinate(3, 2)));
    }
    @Test
    void SquareLinearFlyOverTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(7,4), emg.makeCoordinate(7, 1)));
    }
    
    @Test
    void SquareLineRUnblockAPosition() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(1,5), emg.makeCoordinate(1,2)));
    }
    @Test
    void SquareLinearFlyOverBlocked() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(4,8), emg.makeCoordinate(1,8)));
    }
    
    @Test
    void SquareLinearLandOnOverBlocked() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(4,8), emg.makeCoordinate(3,8)));
    }
    
    @Test
    void SquareLinearDiagonalJumpOverPiece() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        LocationInitializer l = new LocationInitializer(0,0,LocationType.BLOCK,Player.PLAYER1, null);
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(5,3), emg.makeCoordinate(3,1)));
        //since is true lets test the move was made
        EscapePiece frog = new EscapePiece(Player.PLAYER2,PieceName.FROG);
        assertTrue(emg.getPieceAt((emg.makeCoordinate(5, 3))) == null);
        assertTrue(frog.equals(emg.getPieceAt(emg.makeCoordinate(3, 1))));
        //reset game 
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(17,8), emg.makeCoordinate(19,6)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(17,8), emg.makeCoordinate(19,8)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(17,8), emg.makeCoordinate(19,10)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(17,8), emg.makeCoordinate(17,6)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(17,8), emg.makeCoordinate(17,10)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(17,8), emg.makeCoordinate(15,6)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(17,8), emg.makeCoordinate(15,8)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(17,8), emg.makeCoordinate(15,10)));
    }
    
    @Test
    void SquareLinearJumpOverTwoPiece() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(5,8), emg.makeCoordinate(2,5)));
    }
    
    @Test
    void SquareLinearFlyOverTwoPiece() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(8,7), emg.makeCoordinate(11,4)));
    }
    
    @Test
    void SquareLinearBlockedPosition() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(8,2), emg.makeCoordinate(10,4)));
    }
    
    @Test
    void SquareLinearFlyOverBlockedPosition() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(10,6), emg.makeCoordinate(13,9)));
    }
    
    
    @Test
    void SquareLinearJumpExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(15,14), emg.makeCoordinate(15,16)));
    }
    
    @Test
    void SquareLinearFLyExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(13,16), emg.makeCoordinate(11,18)));
    }
    
    @Test
    void SquareLinearLandOnExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(15,2), emg.makeCoordinate(16,2)));
    }
    
    @Test
    void SquareLinearAttackAlly() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(17,16), emg.makeCoordinate(17,17)));
    }
    
    @Test
    void SquareLinearAttackEnemy() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(18,15),emg.makeCoordinate(19,16)));
    }
    @Test
    void SquareLinearJumpTwoNonConsecutivePieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(3,13),emg.makeCoordinate(3,17)));
    }
    
    @Test
    void SquareLinearJumpAttack() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(2, 5)));
        assertTrue(emg.move(emg.makeCoordinate(7,16),emg.makeCoordinate(9,18)));
        assertNull(emg.getPieceAt(emg.makeCoordinate(7, 16)));
    }
    
    
    /*
     * Diagonal on square board 
     */
    
    @Test
    void Diagonaldir1() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(7,3)));
    }
    @Test
    void Diagonaldir2() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(3,3)));
    }

    @Test
    void Diagonaldir3() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(7,7)));
    }

    @Test
    void Diagonaldir4() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,5), emg.makeCoordinate(3,7)));
    }
   
    @Test
    void Diagonaljumpone1() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(10,5), emg.makeCoordinate(12,3)));
    }
    @Test
    void Diagonaljumpone1DirChange() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(9,15), emg.makeCoordinate(12,14)));
    }
    
    @Test
    void Diagonaljumpone2() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(10,5), emg.makeCoordinate(12,7)));
    }
    @Test
    void Diagonaljumpone3() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(10,5), emg.makeCoordinate(8,7)));
    }
    @Test
    void Diagonaljumpone4() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(10,5), emg.makeCoordinate(8,3)));
    }

    @Test
    void DiagonaljumponeTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(2,1), emg.makeCoordinate(5,4)));
    }
    
    @Test
    void DiagonalFlyTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(5,6), emg.makeCoordinate(8,9)));
    }
    
    
    @Test
    void DiagonalRandom() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(7,8), emg.makeCoordinate(6,8)));
    }
    
    @Test
    void DiagonalUnblockAbility() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(3,5), emg.makeCoordinate(1,7)));
    }
    
    @Test
    void DiagonalFlyOverBlock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(10,9), emg.makeCoordinate(12,11)));
    }
    
    @Test
    void JumpOverTwoConsecutiveDiagonalPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(13,8), emg.makeCoordinate(18,13)));
    }
    
    @Test
    void SquareDiagonalLandOnBlockedLocation() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(17,5), emg.makeCoordinate(18,6)));
    }
    
    @Test
    void SquareDiagonalDistanceMoreThanAllowed() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(4,14), emg.makeCoordinate(9,19)));
    }
    
    @Test
    void SquareDiagonalJumpAnExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(14,15), emg.makeCoordinate(16,17)));
    }

    @Test
    void SquareDiagonalFlyOverExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(14,5), emg.makeCoordinate(16,7)));
    }
    
    @Test
    void SquareDiagonalLandExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(6,11), emg.makeCoordinate(5,10)));
    }
    
    @Test
    void SquareDiagonalAttackEnemy() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(8,12), emg.makeCoordinate(7,13)));
    }

    @Test
    void SquareDiagonalAttackAlly() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(18,10), emg.makeCoordinate(19,11)));
    }
    
    @Test
    void SquareDiagonalJumpOverPieceToCapture() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(2,15), emg.makeCoordinate(4,17)));
    }
    
    @Test
    void SquareDiagonalWeirdJump() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareDiagonal.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(2,10), emg.makeCoordinate(2,12)));
    }



    
    //OMNI ON A SQUARE BOARD
    @Test
    void SquareOmniPathFindTest1() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(2,8), emg.makeCoordinate(5,8)));
    }
    
    @Test
    void SquareOmniPathFindNoPath() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(17,14), emg.makeCoordinate(17,12)));
    }
    
    @Test
    void SquareOmniTooFar() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(11,6), emg.makeCoordinate(16,6)));
    }
    
    @Test
    void SquareOmnipathFindJumpOne() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(11,14), emg.makeCoordinate(11,12)));
    }
    
    @Test
    void SquareOmnipathFindJumpTwoNonConsecutive() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(2,2), emg.makeCoordinate(6,2)));
    }
    
    @Test
    void SquareOmnipathFindJumpTwoConsecutive() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(3,12), emg.makeCoordinate(7,12)));
    }
    @Test
    void SquareOmnipathFindUnblock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(3,16), emg.makeCoordinate(5,16)));
    }
    
    @Test
    void SquareOmniFlyOverTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(8,7), emg.makeCoordinate(8,4)));
    }
    
    @Test
    void SquareOmniLandOnBlocked() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(14,3), emg.makeCoordinate(16,2)));
    }
    @Test
    void SquareOmniJumpAnExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(7,18), emg.makeCoordinate(9,18)));
    }
    @Test
    void SquareOmniFlyOverAnExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(14,13), emg.makeCoordinate(14,15)));
    }
    
    @Test
    void SquareOmniLandOnAnExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(17,7), emg.makeCoordinate(18,8)));
    }
    
    @Test
    void SquareOmniAttackEnemy() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(13,19), emg.makeCoordinate(14,19)));
    }
    
    @Test
    void SquareOmniAttackAlly() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(14,18), emg.makeCoordinate(13,18)));
    }
    
    @Test
    void SquareOmniJumpAttack() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(2,15), emg.makeCoordinate(2,17)));
    }
    
    
    @Test
    void SquareOmniJumpEdgeCase() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(10,18), emg.makeCoordinate(12,18)));
    }
    
    
    
    

    
    
    
    
   //ORTHO ON SQUARE BOARD
    
    @Test
    void SquareOrthoPathFindOrtho() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(6,7), emg.makeCoordinate(8,5)));
    }
    
    @Test
    void SquareOrthoCantUnblock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(12,2), emg.makeCoordinate(12,4)));
    }
    @Test
    void SquareOrthoLandOnblocked() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(15,6), emg.makeCoordinate(17,6)));
    }
    @Test
    void SquarOrthoDistanceMoreThanAllowed() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(14,3), emg.makeCoordinate(20,3)));
    }
    
    @Test
    void SquarOrthoFlyOverTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(14,13), emg.makeCoordinate(11,13)));
    }
    
    @Test
    void SquarOrthoJumpOverTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(14,15), emg.makeCoordinate(14,18)));
    }
    
    @Test
    void SquarOrthoJumpOverExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(17,14), emg.makeCoordinate(19,14)));
    }
    
    @Test
    void SquarOrthoFlyOverExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(6,14), emg.makeCoordinate(6,16)));
    }
    
    @Test
    void SquarOrthoLandOnExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(9,17), emg.makeCoordinate(10,17)));
    }
    @Test
    void SquarOrthoFlyOverBlock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(17,18), emg.makeCoordinate(19,18)));
       
    }
    
    @Test
    void SquarOrthoAttackEnemy() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(3,3), emg.makeCoordinate(3,4)));
    }
    
    @Test
    void SquarOrthoAttackAlly() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(1,4), emg.makeCoordinate(2,4)));
    }
    @Test
    void SquarOrthoAttackJump() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(3,13), emg.makeCoordinate(3,15)));
    }
    
    @Test
    void SquarOrthoDiagonal() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(4,18), emg.makeCoordinate(3,19)));
    }
    
    @Test
    void SquarOrthoJumpOnePiece() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(7,20), emg.makeCoordinate(9,20)));
    }
    
    @Test
    void SquarOrthoJumpNoneConsecutivePieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(4,11), emg.makeCoordinate(8,11)));
    }
    
    @Test
    void SquarOrthoJumpEdgeCases() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/SquareOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof SquareGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(10,8), emg.makeCoordinate(11,7)));
        
    }
    
    
    /*
     * ORTHO GAME TEST
     */
    
    // ortho 
    @Test
    void OrthoOrthoPathFind() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        assertTrue(emg.move(emg.makeCoordinate(3,17), emg.makeCoordinate(6,15)));
    }
    
    @Test
    void OrthoOrthoPathUnblock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        assertTrue(emg.move(emg.makeCoordinate(4,6), emg.makeCoordinate(6,6)));
    }
    @Test
    void OrthoOrthoPathLandOnBlock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(10,9), emg.makeCoordinate(12,8)));
    }
    @Test
    void OrthoOrthoPathTooFar() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(8,14), emg.makeCoordinate(14,14)));
    }
    
    @Test
    void OrthoOrthoPathFlyOverTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        assertTrue(emg.move(emg.makeCoordinate(15,4), emg.makeCoordinate(18,4)));
    }
    
    @Test
    void OrthoOrthoPathJumpTwoPiecesNonConsecutive() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        assertTrue(emg.move(emg.makeCoordinate(15,10), emg.makeCoordinate(17,8)));
    }
    @Test
    void OrthoOrthoPathExitInPath() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(9,18), emg.makeCoordinate(11,18)));
    }
    @Test
    void OrthoOrthoPathExitInPathFly() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        assertTrue(emg.move(emg.makeCoordinate(3,1), emg.makeCoordinate(5,1)));
    }
    @Test
    void OrthoOrthoPathJumpTwoToAttack() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        assertTrue(emg.move(emg.makeCoordinate(5,3), emg.makeCoordinate(8,3)));
    }
    @Test
    void OrthoOrthoPathLandOnExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        assertTrue(emg.move(emg.makeCoordinate(16,16), emg.makeCoordinate(18,17)));
      //check piece has been removed
        assertNull(emg.getPieceAt(emg.makeCoordinate(18,17)));
    }
    
    @Test
    void OrthoOrthoPathAttackAlly() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(8,10), emg.makeCoordinate(8,11)));

    }
    
    @Test
    void OrthoOrthoSamePiece() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(8,10), emg.makeCoordinate(8,10)));
        assertFalse(emg.move(emg.makeCoordinate(20,20), emg.makeCoordinate(8,10)));
        assertFalse(emg.move(emg.makeCoordinate(0,0), emg.makeCoordinate(8,10)));

    }
    @Test
    void OrthoOrthoPathAttackEnemy() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);  
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        assertTrue(emg.move(emg.makeCoordinate(7,10), emg.makeCoordinate(7,11)));

    }
    
    @Test
    void OrthoOrthoRandom() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(10,5), emg.makeCoordinate(11,4)));

    }
    
    @Test
    void OrthoOrthoJumpOne() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));

    }
    
    @Test
    void OrthoOrthoJumpTwoNoneConsecutive() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        assertTrue(emg.move(emg.makeCoordinate(12,1), emg.makeCoordinate(16,1)));

    }
    
    @Test
    void OrthoOrthoJumpEdgeCase() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOrtho.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(13,18), emg.makeCoordinate(14,17)));

    }
    
    
    
    // omni
    
    @Test
    void OrthoOmniPathFindOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        assertTrue(emg.move(emg.makeCoordinate(3,17), emg.makeCoordinate(6,15)));
    }
    
    @Test
    void OrthoOmniPathUnblockOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(4,6), emg.makeCoordinate(6,6)));
    }
    @Test
    void OrthoOmniPathLandOnBlockOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(10,9), emg.makeCoordinate(12,8)));
    }
    @Test
    void OrthoOmniPathTooFarOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(8,14), emg.makeCoordinate(14,14)));
    }
    
    @Test
    void OrthoOmniPathFlyOverTwoPiecesOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(15,4), emg.makeCoordinate(18,4)));
    }
    
    @Test
    void OrthoOmniPathJumpTwoPiecesNonConsecutiveOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        assertTrue(emg.move(emg.makeCoordinate(15,10), emg.makeCoordinate(17,8)));
    }
    @Test
    void OrthoOmniPathExitInPathOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(9,18), emg.makeCoordinate(11,18)));
    }
    @Test
    void OrthoOmniPathExitInPathFlyOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(3,1), emg.makeCoordinate(5,1)));
    }
    @Test
    void OrthoOmniPathJumpTwoToAttackOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
    }
    
    @Test
    void OrthoOmniPathLandOnExitOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(16,16), emg.makeCoordinate(18,17)));
        //check piece has been removed
        assertNull(emg.getPieceAt(emg.makeCoordinate(18,17)));
    }
    
    @Test
    void OrthoOmniPathAttackAllyOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(8,10), emg.makeCoordinate(8,11)));

    }
    
    @Test
    void OrthoOmniPathAttackEnemyOmni() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        assertTrue(emg.move(emg.makeCoordinate(7,10), emg.makeCoordinate(7,11)));
        EscapePiece p = new EscapePiece(Player.PLAYER2, PieceName.FROG);
        assertTrue(p.equals(emg.getPieceAt(emg.makeCoordinate(7, 11))));

    }
    @Test
    void OrthoOmniRandom() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(10,5), emg.makeCoordinate(11,4)));

    }
    @Test
    void OrthoOmniJumpOne() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));

    }
    @Test
    void OrthoOmniJumpTwoNoneConsecutivePieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertTrue(emg.move(emg.makeCoordinate(19,11), emg.makeCoordinate(19,13)));
        assertTrue(emg.move(emg.makeCoordinate(12,1), emg.makeCoordinate(16,1)));

    }
    
    @Test
    void OrthoOmniJumpEdgeCase() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        // Exercise the game now: make moves, check the board, etc.
        assertFalse(emg.move(emg.makeCoordinate(13,18), emg.makeCoordinate(14,17)));

    }
    
    
    //linear ortho 
    @Test
    void OrthoPathFindLinearFourDir() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(16,10)));
        assertTrue(emg.move(emg.makeCoordinate(11,6), emg.makeCoordinate(11,8)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(16,10)));
        assertTrue(emg.move(emg.makeCoordinate(11,6), emg.makeCoordinate(11,4)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(16,10)));
        assertTrue(emg.move(emg.makeCoordinate(11,6), emg.makeCoordinate(13,6)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(16,10)));
        assertTrue(emg.move(emg.makeCoordinate(11,6), emg.makeCoordinate(9,6)));
    }
    
    @Test
    void OrthoPathFindLinearUnblock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
       
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(16,10)));
        assertTrue(emg.move(emg.makeCoordinate(12,15), emg.makeCoordinate(14,15)));
 
    }
    @Test
    void OrthoPathFindLinearLandOnblock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertFalse(emg.move(emg.makeCoordinate(5,14), emg.makeCoordinate(5,16)));
        
    }
    @Test
    void OrthoPathFindLinearMaxDistanceAllowed() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertFalse(emg.move(emg.makeCoordinate(14,3), emg.makeCoordinate(20,3)));
        
    }
    @Test
    void OrthoPathFindLinearFlyTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(16,10)));
        assertTrue(emg.move(emg.makeCoordinate(17,13), emg.makeCoordinate(17,16)));
        
    }
    @Test
    void OrthoPathFindLinearJumpTwoNonConsecutivePieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(16,10)));
        assertTrue(emg.move(emg.makeCoordinate(3,3), emg.makeCoordinate(7,3)));
        
    }
    @Test
    void OrthoPathFindLinearJumpExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        
        assertFalse(emg.move(emg.makeCoordinate(9,15), emg.makeCoordinate(9,17)));
        
    }
    
    @Test
    void OrthoPathFindLinearFlyExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(16,10)));
        assertTrue(emg.move(emg.makeCoordinate(3,14), emg.makeCoordinate(3,16)));
        
    }
    @Test
    void OrthoPathFindLinearLandExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(16,10)));
        assertTrue(emg.move(emg.makeCoordinate(16,6), emg.makeCoordinate(16,7)));
      //check piece has been removed
        assertNull(emg.getPieceAt(emg.makeCoordinate(16,7)));
    }
    
    @Test
    void OrthoPathFindLinearAttackEnemy() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame);
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(16,10)));
        assertTrue(emg.move(emg.makeCoordinate(7,7), emg.makeCoordinate(7,8)));
        
    }
    
    @Test
    void OrthoPathFindLinearAttackAlly() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame); 
        assertFalse(emg.move(emg.makeCoordinate(6,7), emg.makeCoordinate(6,8)));    
    }
    
    @Test
    void OrthoPathJumpAndCapture() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
   
        assertTrue(emg instanceof OrthoGame); 
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(16,10)));
        assertTrue(emg.move(emg.makeCoordinate(7,13), emg.makeCoordinate(7,15)));
        
    }
    @Test
    void OrthoLinearPathJumpTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame); 
        assertFalse(emg.move(emg.makeCoordinate(2,5), emg.makeCoordinate(2,8)));
        
    }
    
    @Test
    void OrthoLinearRandomNonLinear() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame); 
        assertFalse(emg.move(emg.makeCoordinate(10,12), emg.makeCoordinate(11,13)));
        
    }
    
    @Test
    void OrthoLinearJumpOneAllDir() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/OrthoLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof OrthoGame); 
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(16,10)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(12,10)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(14,8)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(14,10), emg.makeCoordinate(14,12)));
    
    }

    
    
    /*
     * ORTHO GAME TEST
     */
    
    
    /*
     * HEX GAME TEST
     */
    
    //omni on hex 
    @Test
    void HexOmniPathFind() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(1,-4), emg.makeCoordinate(4,-6)));
        
    }
    
    @Test
    void HexOmniBlockedPathCantUnblock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(-3,-1), emg.makeCoordinate(-5,-1)));
        
    }
    
    @Test
    void HexOmniLandOnBlocked() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(-2,-3), emg.makeCoordinate(-2,-4)));
        
    }
    
    @Test
    void HexOmniDistanceMoreThanAllowed() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(-5,6), emg.makeCoordinate(0,5)));
        
    }
    
    @Test
    void HexOmniFlyOverTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(-3,5), emg.makeCoordinate(0,3)));
        
    }
    
    @Test
    void HexOmniJumpOverTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(4,0), emg.makeCoordinate(4,-3)));
        
    }
    
    @Test
    void HexOmniJumpOverExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(2,0), emg.makeCoordinate(2,-2)));
        
    }
    
    @Test
    void HexOmniFlyOverExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(1,0), emg.makeCoordinate(3,-2)));
        
    }
    @Test
    void HexOmniLandOnExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(-5,3), emg.makeCoordinate(-6,2)));
        
    }
    
    @Test
    void HexOmniAttackEnemy() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(3,3), emg.makeCoordinate(4,2)));
        
    }
    
    @Test
    void HexOmniAttackAlly() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(2,4), emg.makeCoordinate(2,3)));
        
    }
    
    
   
    
    @Test
    void HexOmniJumpOne() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(-2,3), emg.makeCoordinate(-2,1)));
        
    }
    
    @Test
    void HexOmniJumpTwoNoneConsecutive() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexOmni.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(-2,0), emg.makeCoordinate(2,-4)));
        
    }
    
    //linear:
    
    @Test
    void HexLinearPathFind() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(3,-4), emg.makeCoordinate(4,-4)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(3,-4), emg.makeCoordinate(3,-3)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(3,-4), emg.makeCoordinate(2,-3)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(3,-4), emg.makeCoordinate(2,-4)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(3,-4), emg.makeCoordinate(3,-5)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(3,-4), emg.makeCoordinate(4,-5)));
        emg = egb.makeGameManager();
    }
    
    @Test
    void HexLinearUnblockPath() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(-1,-1), emg.makeCoordinate(-3,-1)));
        
    }
    
    @Test
    void HexLinearLandOnBlock() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(-1,-1), emg.makeCoordinate(-2,0)));
        
    }
    
    @Test
    void HexLinearDistanceMoreThanAllowed() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(0,-1), emg.makeCoordinate(-5,4)));
        
    }
    
    @Test
    void HexLinearFlyOverTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(0,3), emg.makeCoordinate(3,3)));
        
    }
    
    @Test
    void HexLinearJumpOverTwoPieces() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(3,1), emg.makeCoordinate(3,-2)));
        
    }
    
    @Test
    void HexLinearJumpOverExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(0,1), emg.makeCoordinate(-2,3)));
        
    }
    @Test
    void HexLinearFlyOverExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(-1,-3), emg.makeCoordinate(1,-5)));
        
    }
    
    @Test
    void HexLinearLandOnrExit() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(1,0), emg.makeCoordinate(2,0)));
        
    }
    @Test
    void HexLinearAttackEnemy() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(0,-2), emg.makeCoordinate(1,-2)));
        EscapePiece p = new EscapePiece(Player.PLAYER1, PieceName.SNAIL);
        assertTrue(p.equals(emg.getPieceAt(emg.makeCoordinate(1, -2))));
        
    }
    @Test
    void HexLinearAttackAlly() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame);
        assertTrue(emg.move(emg.makeCoordinate(-5,2), emg.makeCoordinate(-5,0)));
        assertFalse(emg.move(emg.makeCoordinate(0,-2), emg.makeCoordinate(0,-3)));
        
    }
    
    @Test
    void HexLinearJumpToCapture() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(-5,2), emg.makeCoordinate(-5,0)));
        assertNull(emg.getPieceAt(emg.makeCoordinate(-5, 2)));
        
    }
    @Test
    void HexLinearRandomMove() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(-1,-4), emg.makeCoordinate(-3,-3)));
        
    }
    
    @Test
    void HexLinearMoveToSameSpot() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(-1,-4), emg.makeCoordinate(-1,-4)));
        
    }
    @Test
    void HexLinearNoPieceAtStart() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(0,6), emg.makeCoordinate(-1,0)));
        
    }
    @Test
    void HexLinearJumpOne() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(-2,5), emg.makeCoordinate(-5,5)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(-2,5), emg.makeCoordinate(-2,3)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(-2,5), emg.makeCoordinate(0,5)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(1,2), emg.makeCoordinate(1,4)));
    }
    @Test
    void HexLinearJumpTwoNoneConsecutives() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(-3,3), emg.makeCoordinate(1,-1)));
        
    }
    @Test
    void HexLinearJumpTwoNoneConsecutives2() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear2.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertTrue(emg.move(emg.makeCoordinate(-2,4), emg.makeCoordinate(2,4)));
        emg = egb.makeGameManager();
        assertTrue(emg.move(emg.makeCoordinate(-2,4), emg.makeCoordinate(-6,4)));

    }
    @Test
    void HexLinearJumpTwoNO() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear2.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(2,-3), emg.makeCoordinate(2,0)));
        assertFalse(emg.move(emg.makeCoordinate(2,-3), emg.makeCoordinate(2,-6)));
    }
    @Test
    void HexLinearJumpTwoNO2() throws Exception
    {
        EscapeGameBuilder egb 
            = new EscapeGameBuilder(new File("config/HexLinear2.xml"));
        EscapeGameManager emg = egb.makeGameManager();
        assertTrue(emg instanceof HexGame); 
        assertFalse(emg.move(emg.makeCoordinate(4,-2), emg.makeCoordinate(4,1)));
    }
    
    /*
     * HEX GAME TEST
     */
    
    
    /*
     * EXCEPTIONS 
     * 
     * 
     */
    
    @Test
	void bothDistanceAndFly() throws Exception {
    	EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/BothFlyAndDistanceException.xml"));
		Assertions.assertThrows(EscapeException.class, ()-> egb.makeGameManager());
	}
    
    @Test
	void MustHaveDistanceOrFly() throws Exception {
    	EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/MustHaveDistanceOrFly.xml"));
		Assertions.assertThrows(EscapeException.class, ()-> egb.makeGameManager());
	}
    
    @Test
	void MustHaveOneOrMorePieceTypes() throws Exception {
    	EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/NoPieceTypes.xml"));
		Assertions.assertThrows(EscapeException.class, ()-> egb.makeGameManager());
	}
    @Test
	void MovementpatternWrong() throws Exception {
    	EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/m1.xml"));
		Assertions.assertThrows(EscapeException.class, ()-> egb.makeGameManager());
	}
  
    @Test
	void MovementpatternWrong2() throws Exception {
    	EscapeGameBuilder egb 
        = new EscapeGameBuilder(new File("config/m2.xml"));
		Assertions.assertThrows(EscapeException.class, ()-> egb.makeGameManager());
	}
  
    
}
