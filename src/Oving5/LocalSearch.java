package Oving5;

import java.util.ArrayList;
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
	private Integer[][] board;
	private Queen[] queens;

	public LocalSearch(int size) {
		this.size = size;
		board = new Integer[size][size];
	}

	public static void main(String[] args) {
		LocalSearch ls = new LocalSearch(5);
		ls.run();
	}
	/** traverse a row
	 * place a queen on column i
	 * evaluate violations for the queen and save the value to an array
	 * remove the queen and continue to next column
	 * after traversing find the min-value of the violations array and move the queen there
	 * don't put the queen in the column it was in unless no other column is as good as it
	 */

	public void run(){
		queens = new Queen[size];
		System.out.println("Evaluating...");
		System.out.println("Valid queens: ");
		int totalViolations = size;
		while (totalViolations != 0) {
			moveQueen();
			totalViolations = totalViolations();
			System.out.print(totalViolations + ", ");
		}
		System.out.println("\nFound a solution:");
		printBoard();

	}

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
		// TODO: fix
		return size;
	}

	public int violations(int row) {
		int result = 0;
		for (int i = 0; i < size; i++) {
			if (i == row) {continue;}
			else if (queens[row].threatens(queens[i])) {
				result += 1;
			}
		}
		return result;
	}

	public void printBoard() {
		// TODO: fix
	}
}
