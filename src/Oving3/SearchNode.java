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
	public int g; //cost of arrival
	public int f; //g+h, estimated total cost to goal
	private ArrayList<SearchNode> children = new ArrayList<SearchNode>();
	public String name;
	public int number;
	private ArrayList<String> board = new ArrayList<String>();

	public SearchNode(String color) {
		this.name = color;
	}

	public SearchNode(ArrayList<String> board) {
		this.board = board;
	}

	public ArrayList<SearchNode> getChildren(){
		return children;
	}

	public String toString() {
		return name;
	}

	public int generateChildren(){
		//Find the blank position
		int possibleMoves = 0;
		int blankPosition = 0;
		int boardSize = board.size();
		for (String s : board) {
			if (s == "") {
				blankPosition = board.indexOf(s);
				break;
			}
		}
		//Evaluate number of possible moves
		//For each possible move, create child node with board where the move has been made
		if (blankPosition == 0) {
			possibleMoves = 2;
			children.add(newChild(board, blankPosition, 1));
			children.add(newChild(board, blankPosition, 2));
		}
		else if (blankPosition == 1) {
			possibleMoves =  3;
			children.add(newChild(board, blankPosition, 0));
			children.add(newChild(board, blankPosition, 2));
			children.add(newChild(board, blankPosition, 3));
		}
		else if (blankPosition == boardSize-1) {
			possibleMoves = 2;
			children.add(newChild(board, blankPosition, blankPosition-1));
			children.add(newChild(board, blankPosition, blankPosition-2));
		}
		else if (blankPosition == boardSize-2) {
			possibleMoves =  3;
			children.add(newChild(board, blankPosition, boardSize-1));
			children.add(newChild(board, blankPosition, blankPosition-1));
			children.add(newChild(board, blankPosition, blankPosition-2));
		}

		else if (blankPosition >= 2 && blankPosition <= boardSize-3) {
			possibleMoves =  4;
			children.add(newChild(board, blankPosition, blankPosition-2));
			children.add(newChild(board, blankPosition, blankPosition-1));
			children.add(newChild(board, blankPosition, blankPosition+1));
			children.add(newChild(board, blankPosition, blankPosition+2));
		}
		return possibleMoves;
	}

	private SearchNode newChild(ArrayList<String> board, int blankPosition, int valuePosition) {
		//return child node with new board values
		ArrayList<String> newBoard = new ArrayList<String>(board.size());
		for (String s : board) {
			newBoard.add(s);
		}
		newBoard.set(blankPosition, newBoard.get(valuePosition));
		newBoard.set(valuePosition, "");
		SearchNode child = new SearchNode(newBoard);
		child.name = this.name + "1";
		child.number = this.number + 1;
		return child;
	}

	public ArrayList<String> getBoard() {
		return board;
	}

	@Override
	public boolean equals(Object o) { //Equality is based on the board
		SearchNode sn = (SearchNode) o;
		boolean result = false;
		if (sn.getBoard().equals(this.getBoard())) {
			result = true;
		}
		return result;
	}
}
