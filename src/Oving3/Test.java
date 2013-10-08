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

	private ArrayList<String> createBoard(int numberOfEachColor, boolean goal) {
		ArrayList<String> board = new ArrayList<String>();
		String color1 = "grey";
		String color2 = "red";
		if (goal) {
			color1 = "red";
			color2 = "grey";
		}
		for (int i = 0; i < numberOfEachColor; i++) {
			board.add(color1);
		}
		board.add("");
		for (int i = 0; i < numberOfEachColor; i++) {
			board.add(color2);
		}
		return board;
	}

	public void run() {
		//Board setup
		ArrayList<String> board;
		ArrayList<String> goalBoard;

		board = createBoard(3, false);
		goalBoard = createBoard(3, true);

		//Top node setup
		topNode = new SearchNode(board);
		topNode.name = "blue";
		topNode.number = 0;
		topNode.g = 0;

		AStar aStar = new AStar(goalBoard);
		//Print statistics
		System.out.println("Path: " + aStar.find(topNode));
		System.out.println("Number of generated nodes: " + aStar.getGeneratedNodes());
	}
}
