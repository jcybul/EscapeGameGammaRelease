/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.astar;

import java.util.ArrayList;
import escape.astar.HexBoardAStar.Node;
import escape.board.*;
import escape.util.PieceTypeInitializer.PieceAttribute;

/**
 * Description
 * 
 * @version 10 may. 2020
 */
public abstract class Astar
{

	protected final Board b;
	protected PieceAttribute[] p;
	protected final int xstart;
	protected final int ystart;
	protected int xend, yend;

	/**
	 * Description
	 */
	public Astar(Board b, int fromx, int fromy, PieceAttribute[] p)
	{
		this.b = b;
		this.p = p;
		this.xstart = fromx;
		this.ystart = fromy;

	}

}
