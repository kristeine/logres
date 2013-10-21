package Oving4;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: kristine
 * Date: 10/21/13
 * Time: 11:06
 * To change this template use File | Settings | File Templates.
 */
public class Node {
	int[][] board;
	// Neighbour generation

	public Node(boolean initial, int width, int k) {
		if (initial) {
			// Empty board
			board = new int[width][width];
			// Place K eggs on each row
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < k; j++) {
					board[i][j] = 1;
				}
				for (int l = k; l < width; l++) {
					board[i][l] = 0;
				}
			}
		}
		else {
			// Create board with width
			board = new int[width][width];
		}
	}

	public void generateNeighbours(){
		move();
	}

	public void move() {
		Random rng = new Random();

		// For each row:
		int indexToMove = rng.nextInt(1);
		// Move the chosen index to another column on the same row
	}

	public boolean equals(Node other) {
		return this.board.equals(other.board);
	}
}
