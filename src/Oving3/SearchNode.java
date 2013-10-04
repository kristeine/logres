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
	public int cost_arrival;
	public int cost_toGoal;
	public int cost_total;
	private boolean open;
	private SearchNode parent;
	private ArrayList<SearchNode> children = new ArrayList<SearchNode>();

	public String getStatus() {
		if (open) {return "open";}
		else {return "closed";}
	}
}
