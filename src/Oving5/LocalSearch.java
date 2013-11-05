package Oving5;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: kristine
 * Date: 05/11/13
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
public class LocalSearch {
	private int size;
	private int[][] board;
	private Queen[] queens;

	public LocalSearch(int size) {
		this.size = size;
		board = new int[size][size];
	}

	public static void main(String[] args) {
		LocalSearch ls = new LocalSearch(5);

		ls.run();
	}

	public void run(){
		queens = new Queen[size];
		for (int i = 0; i < size; i++) {
			queens[i] = new Queen(i, i);
		}
		Calendar time = Calendar.getInstance();
		System.out.println(time.getTime() +"\nEvaluating...");
		System.out.println("Violations: ");
		int totalViolations = size;
		int iterations = 0;
		/**
		 * While there are violations, move a queen and reevaluate
		*/
		while (totalViolations != 0) {
			moveQueen();
			totalViolations = totalViolations();
			iterations += 1;
			System.out.print(totalViolations + ", ");
			if (iterations % 30 == 0) {
				System.out.print("\n");
			}
		}
		makeBoard();
		System.out.println("\n" + time.getTime() + "\nFound a solution:");
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	/** traverse a row
	 * place a queen on column i
	 * evaluate violations for the queen and save the value to an array
	 * remove the queen and continue to next column
	 * after traversing find the min-value of the violations array and move the queen there
	 * don't put the queen in the column it was in unless no other column is as good as it
	 */

	public void moveQueen() {
		Random r = new Random();
		int row = r.nextInt(size);
		int previousColumn = queens[row].getColumn();
		Integer[] violations = new Integer[size];
		for (int i = 0; i < size; i++) {
			queens[row].setColumn(i);
			violations[i] = violations(row);
		}
		int minColumn = 0;
		for (int i = 1; i < size; i++) {
			if (i != previousColumn && violations[i] <= violations[minColumn]){
				minColumn = i;
			}
		}
		if (violations[previousColumn] < violations[minColumn]) {
			minColumn = previousColumn;
		}
		queens[row].setColumn(minColumn);
	}

	public int totalViolations() {
		int result = 0;
		for (int i = 0; i < size; i++) {
			result += violations(i);
		}
		return result;
	}

	public int violations(int row) {
		int result = 0;
		for (int i = 0; i < size; i++) {
			if (i != row && queens[row].threatens(queens[i])) {
				result += 1;
			}
		}
		return result;
	}

	public void makeBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = 0;
			}
		}
		for (Queen q: queens) {
			board[q.getRow()][q.getColumn()] = 1;
		}
	}

}
