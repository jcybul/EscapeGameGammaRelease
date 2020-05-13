/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package escape.astar;

import java.util.*;
import escape.board.*;
import escape.board.coordinate.OrthoSquareCoordinate;
import escape.util.PieceTypeInitializer;
import escape.util.PieceTypeInitializer.PieceAttribute;

/*
 * using alogrithm explanide here: https://rosettacode.org/wiki/A*_search_algorithm#Java
 */
/**
 * Description
 * 
 * @version 3 may. 2020
 */
public class OrthoBoardAStar extends Astar
{

	private final ArrayList<Node> open;
	private final ArrayList<Node> closed;
	private final ArrayList<Node> path;
	private Node now;

	// Node class for convienience
	public static class Node implements Comparable
	{
		public Node parent;
		public int x, y;
		public double g;
		public double h;

		Node(Node parent, OrthoSquareCoordinate coord, double g, double h)
		{
			this.parent = parent;
			this.x = coord.getX();
			this.y = coord.getY();
			this.g = g;
			this.h = h;
		}

		// Compare by f value (g + h)
		@Override
		public int compareTo(Object o)
		{
			Node that = (Node) o;
			return (int) ((this.g + this.h) - (that.g + that.h));
		}
	}

	public OrthoBoardAStar(OrthoBoard b, int fromx, int fromy, PieceAttribute[] p)
	{
		super(b, fromx, fromy, p);
		this.open = new ArrayList<>();
		this.closed = new ArrayList<>();
		this.path = new ArrayList<>();
		this.now = new Node(null, OrthoSquareCoordinate.makeCoordinate(fromx, fromy),
				0, 0);
	}

	/*
	 ** Finds path to xend/yend or returns null
	 ** @param (int) xend coordinates of the target position
	 ** @param (int) yend
	 ** @return (List<Node> | null) the path
	 */
	public ArrayList<Node> findPathToOrtho(int xend, int yend)
	{

		this.xend = xend;
		this.yend = yend;
		this.closed.add(this.now);
		addNeigborsToOpenListOrtho();
		while (this.now.x != this.xend || this.now.y != this.yend) {
			if (this.open.isEmpty()) { // Nothing to examine
				return null;
			}
			this.now = this.open.get(0); // get first node (lowest f score)
			this.open.remove(0); // remove it
			this.closed.add(this.now); // and add to the closed
			addNeigborsToOpenListOrtho();
		}
		this.path.add(0, this.now);
		while (this.now.x != this.xstart || this.now.y != this.ystart) {
			this.now = this.now.parent;
			this.path.add(0, this.now);
		}

		return this.path;
	}

	/*
	 ** Looks in a given List<> for a node
	 ** @return (bool) NeightborInListFound
	 */
	private static boolean findNeighborInList(ArrayList<Node> array, Node node)
	{
		return array.stream().anyMatch((n) -> (n.x == node.x && n.y == node.y));
	}

	private void addNeigborsToOpenListOrtho()
	{
		Node node;
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				node = new Node(this.now,
						OrthoSquareCoordinate
								.makeCoordinate(this.now.x + x, this.now.y + y),
						this.now.g,
						(double) OrthoSquareCoordinate.makeCoordinate(now.x, now.y)
								.distanceTo(
										OrthoSquareCoordinate.makeCoordinate(x, y)));
				if ((x != 0 || y != 0) && (x != -1 || y != -1) && (x != -1 || y != 1)
						&& (x != 1 || y != -1) && (x != 1 || y != 1) // not this.now
						&& this.now.x + x >= 1
						&& this.now.x + x < ((OrthoBoard) b).getXMax() // check
						// maze
						// boundaries
						&& this.now.y + y >= 1
						&& this.now.y + y < ((OrthoBoard) b).getYMax() && (

						// check location is not blocked or can fly or can unlblock
						((OrthoBoard) b).getLocationType(
								OrthoSquareCoordinate.makeCoordinate(this.now.x + x,
										this.now.y + y)) != LocationType.BLOCK
								|| PieceTypeInitializer.canUnblock(p)
								|| PieceTypeInitializer.canFly(p))
						// check location is not exit or is the end
						&& (((OrthoBoard) b).getLocationType(
								OrthoSquareCoordinate.makeCoordinate(this.now.x + x,
										this.now.y + y)) != LocationType.EXIT
								|| PieceTypeInitializer.canFly(p)
								|| OrthoSquareCoordinate.makeCoordinate(xend, yend)
										.equals(OrthoSquareCoordinate.makeCoordinate(
												this.now.x + x, this.now.y + y)))
						// check next position is empty or it can fly
						&& (((b.getPieceAt(OrthoSquareCoordinate
								.makeCoordinate(this.now.x, this.now.y))) == null
								|| now.x == xstart && now.y == ystart)
								|| PieceTypeInitializer.canJump(p) && ((b
										.getPieceAt(OrthoSquareCoordinate
												.makeCoordinate(this.now.x + x,
														this.now.y + y))) == null
										&& (this.now.parent == null
												|| OrthoSquareCoordinate
														.makeCoordinate(
																this.now.parent.x,
																this.now.parent.y)
														.sameOrthogonal(
																OrthoSquareCoordinate
																		.makeCoordinate(
																				node.x,
																				node.y)))
										|| (b.getPieceAt(
												OrthoSquareCoordinate.makeCoordinate(
														now.x, now.y)) == null
												&& ((node.x != xend
														&& node.y != yend)
														|| (OrthoSquareCoordinate
																.makeCoordinate(xend,
																		yend)
																.equals(OrthoSquareCoordinate
																		.makeCoordinate(
																				this.now.x
																						+ x,
																				this.now.y
																						+ y))
																&& b.getPieceAt(
																		OrthoSquareCoordinate
																				.makeCoordinate(
																						xend,
																						yend))
																		.getPlayer() != b
																				.getPieceAt(
																						OrthoSquareCoordinate
																								.makeCoordinate(
																										xstart,
																										ystart))
																				.getPlayer()))))
								||
								// it can fly
								PieceTypeInitializer.canFly(p)
								|| ((node.x == xend && node.y == yend)
										&& (b.getPieceAt(OrthoSquareCoordinate
												.makeCoordinate(xend, yend)) != null)
										&& (OrthoSquareCoordinate
												.makeCoordinate(xend, yend)
												.equals(OrthoSquareCoordinate
														.makeCoordinate(
																this.now.x + x,
																this.now.y + y))
												&& b.getPieceAt(OrthoSquareCoordinate
														.makeCoordinate(xend, yend))
														.getPlayer() != b.getPieceAt(
																OrthoSquareCoordinate
																		.makeCoordinate(
																				xstart,
																				ystart))
																.getPlayer())))

						&& OrthoSquareCoordinate.makeCoordinate(node.x, node.y)
								.distanceTo(OrthoSquareCoordinate.makeCoordinate(
										now.x, now.y)) <= PieceTypeInitializer
												.getMaxDistance(p)
						&& !findNeighborInList(this.open, node)
						&& !findNeighborInList(this.closed, node)) { // if not already
																		// done
					node.g = node.parent.g + 1; // Horizontal/vertical cost = 1.0
					node.g += (double) OrthoSquareCoordinate
							.makeCoordinate(now.x, now.y)
							.distanceTo(OrthoSquareCoordinate.makeCoordinate(x, y));
					// add movement cost for this square

					this.open.add(node);
				}
			}
		}
		Collections.sort(this.open);
	}

}
