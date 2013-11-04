package Oving5;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: kristine
 * Date: 04/11/13
 * Time: 16:22
 * To change this template use File | Settings | File Templates.
 */
public class Backtrack {
	private ArrayList<Queen> queens;

	public Backtrack(ArrayList<Queen> queens) {
		this.queens = queens;
	}

	public static void main(String[] args) {
		ArrayList<Queen> q = new ArrayList<Queen>();
		q.add(new Queen());
		Backtrack b = new Backtrack(q);
		b.run();
	}

	public void run() {

		// select unassigned queen with the smallest domain
		// assign the queen to a column
		// if finished, return
		// while unfinished:
		//      reduce all other queens' domains according to the moving of the selected queen
		//      if any queen's domain has been reduced to an empty set, undo the last move and it's consequences and remove the move from the queen's domain
		//      select next unassigned queen with the smallest domain
		//      assign the queen to a column
		// return solution
	}
}
