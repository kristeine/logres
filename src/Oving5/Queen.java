package Oving5;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: kristine
 * Date: 04/11/13
 * Time: 16:22
 * To change this template use File | Settings | File Templates.
 */
public class Queen {
	private int row;
	private int column;
	private Integer[] domain;
	private Stack<Integer[]> conflicts;

	public Queen(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public boolean threatens(Queen other) {
		if (this.column == other.column || this.column == other.column - Math.abs(this.row - other.row)
				|| this.column == other.column + Math.abs(this.row - other.row)) {
			return true;
		}
		return false;
	}

}
