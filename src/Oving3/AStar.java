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
		aStar.find(start);
	}

	public String find(SearchNode start){
		for (int i = 0; i < 3; i++) {
			goalBoard.add("red");
		}
		goalBoard.add("");
		for (int i = 0; i < 3; i++) {
			goalBoard.add("grey");
		}
		generatedNodes = 0;
		closed = new ArrayList<SearchNode>();
		open = new ArrayList<SearchNode>(); open.add(start);
		path = new HashMap<SearchNode, SearchNode>();
		start.f = start.g + heuristicCostEstimate(start, goalBoard);
		//start.f = 5000 - heuristicCostEstimate(start, goalBoard);

		while (!open.isEmpty()) {
			System.out.println("Open size: " + open.size());
			System.out.println("Closed size: " + closed.size());
			/*if (open.size() > 110) {
				return "Error: too many open nodes";
			} */
			int tentative_low = 6; SearchNode current = open.get(0);
			ArrayList<SearchNode> duplicates = new ArrayList<SearchNode>();
			for (SearchNode sn : open) {   //find the node with lowest f value
				if (closed.contains(sn)) {
					duplicates.add(sn);
				}
				else if (sn.f < tentative_low) {
					tentative_low = sn.f; current = sn;
				}
			}
			for (SearchNode sn : duplicates) {
				open.remove(sn);
			}

			System.out.println("Lowest f-score: " + current.f);
			System.out.println("Current board: " + current.getBoard());

			//SearchNode current = open.get(0); //assume open sorted on f
			if (current.getBoard().equals(goalBoard)) {   //check if node is goal
				System.out.println("Found the goal");
				return path(path, current);
			}
			open.remove(current); closed.add(current);
			if (current.getChildren().isEmpty()) {
				current.generateChildren();
			}

			for (SearchNode child : current.getChildren()) {
				System.out.println("Traversing children");
				//System.out.println("Checking child of: " + current.number);
				int tentative_g = current.g + 1;
				//int tentative_f = current.g + heuristicCostEstimate(child, goalBoard);
				int tentative_f = heuristicCostEstimate(child, goalBoard);
				//int tentative_f = current.f - heuristicCostEstimate(child, goalBoard);

				if (closed.contains(child)) {
					System.out.println("Duplicate child");
					continue;
				}
				if (!open.contains(child)) {
					generatedNodes++;
					System.out.println("Child not yet checked");
					path.put(child, current);
					child.g = tentative_g;
					child.f = tentative_f;
					open.add(child);

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
		//return 0;
		//return 5;
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
