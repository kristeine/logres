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

	public  static void main(String[] args) {
		AStar aStar = new AStar();

		SearchNode start = new SearchNode();
		SearchNode goal = new SearchNode();
		aStar.find(start, goal);
	}

	public String find(SearchNode start, SearchNode goal){
		closed = new ArrayList<SearchNode>();
		open = new ArrayList<SearchNode>(); open.add(start);
		path = new HashMap<SearchNode, SearchNode>();
		start.g = 0;
		start.f = start.g + heuristicCostEstimate(start, goal);

		while (!open.isEmpty()) {
			SearchNode current = open.get(0); //assume open sorted on f
			if (current == goal) {
				return path(path, goal);
			}
			open.remove(current); closed.add(current);

			for (SearchNode child : current.getChildren()) {
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
			return parentPath + goal.toString();
		}
		else {
			return goal.toString();
		}
	}
}
