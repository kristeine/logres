package Oving3;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: kristine
 * Date: 10/4/13
 * Time: 11:22
 * To change this template use File | Settings | File Templates.
 */
public class SearchNode {
	public State state;
	public int g; //cost of arrival
	public int h; //estimated cost to goal
	public int f; //g+h, estimated total cost to goal
	private ArrayList<SearchNode> children = new ArrayList<SearchNode>();
	public String color;
	private ArrayList<String> board = new ArrayList<String>();
	private int position;

	public SearchNode() {}

	public SearchNode(String color) {
		this.color = color;
	}

	public SearchNode(ArrayList<String> board) {
		this.board = board;
	}

	public ArrayList<SearchNode> getChildren(){
		return children;
	}

	public void addChild(SearchNode sn) {
		children.add(sn);
	}

	public String toString() {
		return color;
	}

	public int generateChildren(){
		int possibleMoves = 0;
		int blankPosition = 0;
		for (int i = 0; i < board.size(); i++) {
			if (board.get(i) == "") { //find empty space
				if (i == 0 || i == board.size()-1) {   //edge position
					possibleMoves = 2;
				}
				else if (i == 1 || i == board.size()-2) { //close to edge
					possibleMoves =  3;
				}
				else {
					possibleMoves =  4;
				}
				blankPosition = i;
				break;
			}
		}
		if (blankPosition == 0) {
			children.add(newChild(board, 0, 1));
			children.add(newChild(board, 0, 2));

		}
		//sjekke brettet
		//for hvert mulige trekk for denne noden: opprett barnenode med nytt board
		//returner antall opprettede noder
		return possibleMoves;
	}

	private SearchNode newChild(ArrayList<String> board, int blankPosition, int valuePosition) {
		ArrayList<String> newBoard = board;
		newBoard.set(blankPosition, newBoard.get(valuePosition));
		newBoard.set(valuePosition, "");
		SearchNode child = new SearchNode(newBoard);
		return child;
	}

	public ArrayList<String> getBoard() {
		return board;
	}
}
