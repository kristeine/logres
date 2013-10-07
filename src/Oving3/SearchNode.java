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
		int boardSize = board.size();
		for (int i = 0; i < boardSize; i++) {
			if (board.get(i) == "") { //find empty space
				if (i == 0 || i == boardSize-1) {   //edge position
					possibleMoves = 2;
				}
				else if (i == 1 || i == boardSize-2) { //close to edge
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
			children.add(newChild(board, blankPosition, 1));
			children.add(newChild(board, blankPosition, 2));
		}
		else if (blankPosition == 1) {
			children.add(newChild(board, blankPosition, 0));
			children.add(newChild(board, blankPosition, 2));
			children.add(newChild(board, blankPosition, 3));
		}
		else if (blankPosition == boardSize-1) {
			children.add(newChild(board, blankPosition, blankPosition-1));
			children.add(newChild(board, blankPosition, blankPosition-2));
		}
		else if (blankPosition == boardSize-2) {
			children.add(newChild(board, blankPosition, boardSize-1));
			children.add(newChild(board, blankPosition, blankPosition-1));
			children.add(newChild(board, blankPosition, blankPosition-2));
		}

		else if (blankPosition >= 2 && blankPosition <= boardSize-3) {
			children.add(newChild(board, blankPosition, blankPosition-2));
			children.add(newChild(board, blankPosition, blankPosition-1));
			children.add(newChild(board, blankPosition, blankPosition+1));
			children.add(newChild(board, blankPosition, blankPosition+2));
		}

		//sjekke brettet
		//for hvert mulige trekk for denne noden: opprett barnenode med nytt board
		//returner antall opprettede noder
		return possibleMoves;
	}

	private SearchNode newChild(ArrayList<String> board, int blankPosition, int valuePosition) {   //return child node with new board values
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
