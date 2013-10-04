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
	private int id;
	private boolean open;
	private SearchNode parent;
	private ArrayList<SearchNode> children = new ArrayList<SearchNode>();

	public String getStatus() {
		if (open) {return "open";}
		else {return "closed";}
	}

	public SearchNode getParent() {
		return parent;
	}

	public ArrayList<SearchNode> getChildren(){
		return children;
	}
}
