package Oving3;

/**
 * Created with IntelliJ IDEA.
 * User: kristine
 * Date: 10/7/13
 * Time: 11:40
 * To change this template use File | Settings | File Templates.
 */
public class Test {
	SearchNode topNode = new SearchNode("red");

	public static void main(String[] args) {
		Test test = new Test();
		test.run();
	}

	public void run() {
		topNode.g = 0;
		SearchNode a = new SearchNode("blue"), b = new SearchNode("green"), c = new SearchNode("yellow");
		topNode.addChild(a);
		topNode.addChild(b);
		topNode.addChild(c);
		AStar aStar = new AStar();
		System.out.println("Path: " + aStar.find(topNode, c));
		System.out.println("Number of generated nodes: " + aStar.getGeneratedNodes());
	}
}
