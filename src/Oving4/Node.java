package Oving4;


import java.util.ArrayList;
import java.util.Arrays;

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
			if (i < 1) {m = 0;}
			int n = eggsOnDiagonal(width-1, i, false);
			points += j + l + m + n;
		}

		return points;
	}

	public int eggsOnDiagonal(int i, int j, boolean pointingRight) {
		// Count eggs on the diagonal starting on board[i][j]
		int numberInDiagonal = 0;
		while (i<width && j < width && pointingRight) {
			if (board[i][j] == 1) {
				numberInDiagonal++;
			}
			i++;
			j++;
		}
		while (i >= 0 && j >= 0 && !pointingRight) {
			if (board[i][j] == 1) {
				numberInDiagonal++;
			}
			i--;
			j--;
		}
		// Evaluate points for the objectiveFunction
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
							//kid.board = this.board;
							//TESTER DEEP COPY... edit: funka <3 nå er det bare objectiveFunction som er feil tror jeg
							// eller nei, det er vel isThreatened som er feil... fiksa nå! Kommenterte fixen i metoden sjølve
							for(int a = 0; a<width; a++) {
								for(int b = 0; b<width; b++){
									kid.board[a][b] = this.board[a][b];
								}
							}
							//-----------------------------
							kid.board[i][j] = 0;
							kid.board[i][l] = 1;
							kids.add(kid);
							/*//--------------------DEBUG-------------------------------
							for(int q = 0; q < kid.board.length; q++) {
								for(int w = 0; w < kid.board.length; w++) {
									System.out.print(kid.board[q][w] + ", ");
								}
								System.out.println();
							}
							System.out.println("-----------BOARD END----------------");
							//---------------------------------------------------------*/
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
