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

import java.util.HashMap;
import escape.board.coordinate.*;
import escape.exception.EscapeException;
import escape.piece.*;
import escape.rule.*;
import escape.util.PieceTypeInitializer;

/**
 * Description
 * @version 8 may. 2020
 * @param <C>
 */
public abstract class Game
{
	

	public HashMap<PieceName, PieceTypeInitializer> PieceTypes;
	public HashMap<RuleID,Rule> gameRules;
	public int turnHolder = 1;
	public int Player1score = 0;
	public int Player2score = 0;
	
	/**
	 * Description
	 */
	public Game(HashMap<RuleID,Rule> gameRules,HashMap<PieceName,
			PieceTypeInitializer> PieceTypes){
	this.PieceTypes = PieceTypes;
	this.gameRules = gameRules;
	}
	
		/**
		 * this method is called once a ending condition is reached
		 * @return return the string showing the winner
		 */
		public String winner() {
		
		if(Player1score > Player2score) {
			return "PLAYER1";
		}
		else if(Player1score == Player2score) {
			return", The game is tied";
		}
		else {
			return "PLAYER2";
		}
	}
		
		/**
		 * throw  exception if the limit has been reached
		 */
		public void tunrLimit() {
			if(gameRules != null && gameRules.containsKey(RuleID.TURN_LIMIT) == true){
				if(gameRules.get(RuleID.TURN_LIMIT).getIntValue() <= turnHolder -1) {
					throw new EscapeException(" game turn limit reached");
				}
			}
		}
		
		/**
		 * throw exception when the score limit has been reached
		 */
		public void scoreLimit() {
			if( gameRules != null && gameRules.containsKey(RuleID.SCORE) == true) {
				if(gameRules.get(RuleID.SCORE).getIntValue() <= Player1score || 
						gameRules.get(RuleID.SCORE).getIntValue() <= Player2score) {
					throw new EscapeException("Score limit has been reached");
						
		
				}
					
			}
		}
		
		/**
		 * check which piece has the highes value, subtract and 
		 * @param p
		 * @param c
		 * @return
		 */
		public EscapePiece conflict(EscapePiece p, EscapePiece c) {
			int rvalue = Math.abs(p.getValue() - c.getValue());
			if(p.getValue() > c.getValue()) {
				p.setValue(rvalue);
				return p;
			}
			else {
				c.setValue(rvalue);
				return c;
			}
		}
		
		/**
		 * Increment the score of the player depending on the given value and player 
		 * @param p player
		 * @param value to increment 
		 */
		public void incrementScore(Player p,int value) {
			if(p == Player.PLAYER1) {
				Player1score = Player1score + value;
			}
			else if(p == Player.PLAYER2) {
				Player2score = Player2score + value;
				
			}
		}



}
