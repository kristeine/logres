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
	private ArrayList<ArrayList<String>> closed;
	private ArrayList<SearchNode> open;
	private HashMap<SearchNode, SearchNode> path;
	private ArrayList<String> goalBoard = new ArrayList<String>();
	private int generatedNodes;

	public  static void main(String[] args) {
		AStar aStar = new AStar();

		SearchNode start = new SearchNode();
		SearchNode goal = new SearchNode();
		aStar.find(start);
	}

	public String find(SearchNode start){
		goalBoard.add("red"); goalBoard.add("red"); goalBoard.add("red"); goalBoard.add("");
		goalBoard.add("grey"); goalBoard.add("grey"); goalBoard.add("grey");
		//goalBoard.add("red"); goalBoard.add(""); goalBoard.add("red"); goalBoard.add("red");
		generatedNodes = 0;
		closed = new ArrayList<ArrayList<String>>();
		open = new ArrayList<SearchNode>(); open.add(start);
		path = new HashMap<SearchNode, SearchNode>();
		start.f = start.g + heuristicCostEstimate(start, goalBoard);

		while (!open.isEmpty()) {
			System.out.println("Open size: " + open.size());
			System.out.println("Closed size: " + closed.size());
			/*if (open.size() > 110) {
				return "Error: too many open nodes";
			} */
			int tentative_low = 6; SearchNode current = open.get(0);
			for (SearchNode sn : open) {   //find the node with lowest f value
				if (sn.f < tentative_low) {
					tentative_low = sn.f; current = sn;
				}
			}

			System.out.println("Lowest f-score: " + current.f);
			System.out.println("Current board: " + current.getBoard());

			//SearchNode current = open.get(0); //assume open sorted on f
			if (current.f == 0) {   //check if node is goal
				System.out.println("Found the goal");
				return path(path, current);
			}
			open.remove(current); closed.add(current.getBoard());
			generatedNodes += current.generateChildren();

			for (SearchNode child : current.getChildren()) {
				//System.out.println("Checking child of: " + current.number);
				int tentative_g = current.g + 1;
				//int tentative_f = current.g + heuristicCostEstimate(child, goalBoard);
				int tentative_f = heuristicCostEstimate(child, goalBoard);

				if (closed.contains(child.getBoard())) {
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

	public int heuristicCostEstimate(SearchNode start, ArrayList<String> goalBoard) {
		int middleIndex = (int) Math.floor(goalBoard.size()/2);
		ArrayList<String> currentBoard = start.getBoard();
		int result = 0;
		/*for (int i = 0; i < goalBoard.size(); i++) {
			if (currentBoard.get(i) != goalBoard.get(i) && currentBoard.get(i)!="") {
				result +=1;
			} //number of misplaced pieces
		}*/
		for (String s : currentBoard) {
			int location = currentBoard.indexOf(s);
			if (s == "grey" && location <= middleIndex) {
				result += 1 + (middleIndex-location);
			}
			else if (s == "red" && location >= middleIndex) {
				result += 1 + (location-middleIndex);
			}
		}
		return result;
	}

	public String path(HashMap<SearchNode, SearchNode> currentPath, SearchNode goal){
		if (currentPath.containsKey(goal)) {
			String parentPath = path(currentPath, currentPath.get(goal));
			return parentPath + "\n" + goal.getBoard();
		}
		else {
			return "\n" + goal.getBoard().toString();
		}
	}

	public int getGeneratedNodes() {
		return generatedNodes;
	}
}
