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


	public AStar(ArrayList<String> goalBoard) { //Initialize with a goal
		this.goalBoard = goalBoard;
	}

	public String find(SearchNode start){
		generatedNodes = 0;
		closed = new ArrayList<SearchNode>();   //Make sure the arrays are empty
		open = new ArrayList<SearchNode>(); open.add(start);
		path = new HashMap<SearchNode, SearchNode>();
		start.f = heuristicCostEstimate(start, goalBoard);

		while (!open.isEmpty()) {
			if (closed.size() % 1000 == 0) {
				System.out.println("Closed size: " + closed.size());
			}

			SearchNode current = open.get(0); //Start with first node

			ArrayList<SearchNode> duplicates = new ArrayList<SearchNode>();
			for (SearchNode sn : open) {
				if (closed.contains(sn)) {  //find duplicates
					duplicates.add(sn);
				}
				else if (sn.f < current.f) {   //Check if other nodes are "better" than current node
					current = sn;
				}
			}
			for (SearchNode sn : duplicates) {  //Remove duplicates for efficiency
				open.remove(sn);
			}
			//current = open.get(open.size()-1); //DFS act like a stack, LIFO

			if (current.getBoard().equals(goalBoard)) {   //check if node is goal
				System.out.println("Found the goal");
				return path(path, current);
			}
			open.remove(current); closed.add(current);
			if (current.getChildren().isEmpty()) { //Don't generate children if they are already generated
				current.generateChildren();
			}

			for (SearchNode child : current.getChildren()) {
				int tentative_g = current.g + 1;
				int tentative_f = heuristicCostEstimate(child, goalBoard);

				if (closed.contains(child)) {
					//Duplicate child is already checked, can be ignored
					continue;
				}
				if (!open.contains(child)) {
					//This is an accepted child
					generatedNodes++;
					path.put(child, current); //Save the parent
					child.g = tentative_g;
					child.f = tentative_f;
					open.add(child);
				}
			}
		}
		return "Error";
	}

	public int heuristicCostEstimate(SearchNode start, ArrayList<String> goalBoard) {
		//Evaluate similarity to goalBoard
		//Traverse board. For each misplaced piece, add 1 + the distance to correct place to the result
		int middleIndex = (int) Math.floor(goalBoard.size()/2);
		ArrayList<String> currentBoard = start.getBoard();
		int result = 0;
		for (int i = 0; i < goalBoard.size(); i++) {
			String s = currentBoard.get(i);
			int location = i;
			if (s == "grey" && location <= middleIndex) {
				result += 1;
				result += 1.5*(middleIndex-location);
			}
			else if (s == "red" && location >= middleIndex) {
				result += 1;
				result += 1.5*(location-middleIndex);
			}
		}
		return result;
		//return 0; For DFS, BFS
	}

	public String path(HashMap<SearchNode, SearchNode> currentPath, SearchNode goal){
		if (currentPath.containsKey(goal)) { //If the HashMap of parents has the goal as a key, it knows the goals parent
			String parentPath = path(currentPath, currentPath.get(goal));
			return parentPath + "\n" + goal.getBoard(); //Recursive call to add full path
		}
		else {  //If there is no parent, this is the top node
			return "\n" + goal.getBoard().toString();
		}
	}

	public int getGeneratedNodes() {
		return generatedNodes;
	}
}
