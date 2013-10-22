package Oving4;


import java.util.ArrayList;

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
	int width;
	ArrayList<Node> kids = new ArrayList<Node>();

	public Node(boolean initial, int width, int k) {
		this.k = k;
		this.width = width;
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
	
	public int objectiveFunction(){
		int points = 0;
		
		int numberInColumn;
		for(int i = 0; i< width; i++) {  // For each column
			// Checking columns
			numberInColumn = 0;
			for(int j = 0; j< width; j++) {  //For each row in the column
				if(board[j][i] == 1){
					numberInColumn++;
				}
			}
			if(numberInColumn == k)
				points += 2;
			else if(numberInColumn < k)
				points++;
		}
		// Run eggsOnDiagonal to the right for each in first row and column except last index
		for (int i = 0; i < width-1; i++) {
			int j = eggsOnDiagonal(i, 0, true);
			int l = eggsOnDiagonal(0, i, true);
			int m = eggsOnDiagonal(i, 0, false);
			if (i == 0) {
				l = 0; // Or else we will count the rightbound diagonal from 0,0 twice
				m = 0; // Don't count edges
			}
			int n = eggsOnDiagonal(width-1, i, false);
			points += j + l + m + n;
		}

		return points;
	}

	public int eggsOnDiagonal(int i, int j, boolean pointingRight) {
		// Count eggs on the diagonal starting on board[i][j]
		int numberInDiagonal = 0;
		while (i < width && j < width && pointingRight) {
			if (board[i][j] == 1) {
				numberInDiagonal++;
			}
			i++;
			j++;
		}
		while (i >= 0 && j < width && !pointingRight) {
			if (board[i][j] == 1) {
				numberInDiagonal++;
			}
			i--;
			j++;
		}
		if (numberInDiagonal <= k) {
			return 1;
		}
		return 0;
	}
	
	public ArrayList<Node> move() {
		// For each threatened egg:
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if((board[i][j] == 1) && isThreatened(i, j)) {
					for(int l = 0; l < width; l++) {
						if(board[i][l] == 0) {
							Node kid = new Node(false, width, k);
							for(int a = 0; a<width; a++) {
								for(int b = 0; b<width; b++){
									kid.board[a][b] = this.board[a][b];
								}
							}
							kid.board[i][j] = 0;
							kid.board[i][l] = 1;
							kids.add(kid);
						}
					}
				}
			}
		}
		return kids;
	}

	public boolean isThreatened(int i, int j){
		//THE UGLIEST WALL OF TRY-CATCH INC!
		
		// Check if that position is threatened
		// I.e: whether there are x > k eggs on this position's row or column or diagonal
		int rowCollidingEggs = 0;
		int columnCollidingEggs = 0;
		int diagCollidingEggs = 0;
		for(int l = 1; l < (width); l++) {
			//check row
			try {
				if(board[i][j-l] == 1)
					rowCollidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			try {
				if(board[i][j+l] == 1)
					rowCollidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			
			//check columns
			try {
				if(board[i-l][j] == 1)
					columnCollidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			try {
				if(board[i+l][j] == 1)
					columnCollidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			//check diagonals
			try {
				if(board[i-l][j-l] == 1)
					diagCollidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			try {
				if(board[i+l][j-l] == 1)
					diagCollidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			try {
				if(board[i-l][j+l] == 1)
					diagCollidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			try {
				if(board[i+l][j+l] == 1)
					diagCollidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			
		}

		//endret > til >= ettersom collidingEggs er egg som er der I TILLEGG til det vi allerede vet at er der!
		if(rowCollidingEggs >= this.k || columnCollidingEggs >= k || diagCollidingEggs >= k)
				return true;
		return false;
	}
	public boolean equals(Node other) {
		return this.board.equals(other.board);
	}
}
