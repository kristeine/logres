package Oving3;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: kristine
 * Date: 10/4/13
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public class AStar{
	private ArrayList<SearchNode> closed;
	private ArrayList<SearchNode> open;
	private HashMap<SearchNode, SearchNode> path;
	private ArrayList<String> goalBoard = new ArrayList<String>();
	private int generatedNodes;

	public  static void main(String[] args) {
		AStar aStar = new AStar();

		SearchNode start = new SearchNode();
		SearchNode goal = new SearchNode();
		aStar.find(start, goal);
	}

	public String find(SearchNode start, SearchNode goal){
		goalBoard.add("red"); goalBoard.add("red"); goalBoard.add("red"); goalBoard.add("");
		goalBoard.add("grey"); goalBoard.add("grey"); goalBoard.add("grey");
		generatedNodes = 0;
		closed = new ArrayList<SearchNode>();
		open = new ArrayList<SearchNode>(); open.add(start);
		path = new HashMap<SearchNode, SearchNode>();
		start.g = 0;
		start.f = start.g + heuristicCostEstimate(start, goal);

		while (!open.isEmpty()) {
			int tentative_low = 6; SearchNode current = open.get(0);
			for (SearchNode sn : open) {   //find the node with lowest f value
				if (sn.f < tentative_low) {
					tentative_low = sn.f; current = sn;
				}
			}

			//SearchNode current = open.get(0); //assume open sorted on f
			if (current.getBoard() == goalBoard) {   //check if node is goal
				System.out.println("Found the goal");
				return path(path, goal);
			}
			open.remove(current); closed.add(current);
			generatedNodes += current.generateChildren();

			for (SearchNode child : current.getChildren()) {
				System.out.println("Checking child of: " + current.color);
				int tentative_g = current.g + 1;
				int tentative_f = current.f + heuristicCostEstimate(child, goal);
				if (closed.contains(child) && tentative_f >= child.f) {
					continue;
				}
				if (!open.contains(child) || tentative_f < child.f) {
				    path.put(child, current);
					child.g = tentative_g;
					child.f = tentative_f;
					if (!open.contains(child)) {
						open.add(child);
					}
				}
			}
		}
		return "Error";
	}

	public int heuristicCostEstimate(SearchNode start, SearchNode goal) {
		return 0; //bredde først
	}

	public String path(HashMap<SearchNode, SearchNode> currentPath, SearchNode goal){
		if (currentPath.containsKey(goal)) {
			String parentPath = path(currentPath, currentPath.get(goal));
			return parentPath + ", " + goal.toString();
		}
		else {
			return goal.toString();
		}
	}

	public int getGeneratedNodes() {
		return generatedNodes;
	}
}
