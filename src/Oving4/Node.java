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
	int k;
	// Neighbour generation

	public Node(boolean initial, int width, int k) {
		this.k = k;
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

	public void move() {
		// For each threatened egg:
		// For width-k:    (number of possible moves within the same row)
		// Make a new node where that move has been made
	}

	public boolean isThreatened(int i, int j){
		// Check if that position is threatened
		// I.e: whether there are x > k eggs on this position's row or column or diagonal
		return false;
	}
	public boolean equals(Node other) {
		return this.board.equals(other.board);
	}
}
