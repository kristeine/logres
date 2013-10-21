package Oving4;


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
	// Neighbour generation

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
			move();
		}
		else {
			// Create board with width
			board = new int[width][width];
		}
	}
	
	public int objectiveFunction(){
		int points = 0;
		
		int numberInColumn;
		int numberInDiagonal;
		
		for(int i = 0; i<board[0].length; i++) {
			numberInColumn = 0;
			for(int j = 0; j<board.length; j++) {
				//Column
				if(board[j][i] == 1){
					numberInColumn++;
				}
			}
			if(numberInColumn == k)
				points += 2;
			else if(numberInColumn < k)
				points++;
			
			for(int l = 0; l<board.length; l++) {
				
			}
			
			
			
			for(int j = 0; j < width; j++) {
				for(int n = 0; n < width; n++) {
					System.out.println(board[j+1][n+1]);
				}
			}
			
			
		}
		return points;
	}

	
	
	
	public void move() {
		// For each threatened egg:
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if((board[i][j] == 1) && isThreatened(i, j)) {
					for(int l = 0; l < width; l++) { //width-k funker ikke, for må jo finne de som er possible
						if(board[i][l] == 0) {
							//MAKE board[i][l] A NEIGHBOUR
							
							/*Sjekka med Sysout, og den går hit 10 ganger, noe som er riktig ettersom det er 10 brikker
							 *og alle er truet i initial
							 */
						}
					}
				}
			}
		}
	}

	public boolean isThreatened(int i, int j){
		//THE UGLIEST WALL OF TRY-CATCH INC!
		
		// Check if that position is threatened
		// I.e: whether there are x > k eggs on this position's row or column or diagonal
		int collidingEggs = 0;
		for(int l = 1; l < (width); l++) {
			//check row
			try {
				if(board[i-l][j] == 1)
					collidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			try {
				if(board[i+l][j] == 1)
					collidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			
			//check columns
			try {
				if(board[i][j-l] == 1)
					collidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			try {
				if(board[i][j+l] == 1)
					collidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			
			//check diagonals
			try {
				if(board[i-l][j-l] == 1)
					collidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			try {
				if(board[i+l][j-l] == 1)
					collidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			try {
				if(board[i-l][j+l] == 1)
					collidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			try {
				if(board[i+l][j+l] == 1)
					collidingEggs++;
			} catch(IndexOutOfBoundsException e){
			}
			if(collidingEggs > this.k)
				return true;
		}
		return false;
	}
	public boolean equals(Node other) {
		return this.board.equals(other.board);
	}
}
