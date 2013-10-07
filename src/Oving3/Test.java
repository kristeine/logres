package Oving3;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: kristine
 * Date: 10/7/13
 * Time: 11:40
 * To change this template use File | Settings | File Templates.
 */
public class Test {
	SearchNode topNode = new SearchNode("red");

	public static void main(String[] args) {
		Test test = new Test();
		test.run();
	}

	public void run() {
		ArrayList<String> board = new ArrayList<String>();
		board.add("grey"); board.add("grey"); board.add("grey"); board.add("");
		board.add("red"); board.add("red"); board.add("red");
		//board.add("grey");   board.add("grey"); board.add("grey"); board.add("");
		topNode = new SearchNode(board);
		topNode.name = "blue";
		topNode.number = 0;
		topNode.g = 0;
		//System.out.println("Board: " + board);
		//topNode.generateChildren();
		AStar aStar = new AStar();
		System.out.println("Path: " + aStar.find(topNode));
		System.out.println("Number of generated nodes: " + aStar.getGeneratedNodes());
	}
}
