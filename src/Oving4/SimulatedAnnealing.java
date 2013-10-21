package Oving4;

/**
 * Created with IntelliJ IDEA.
 * User: kristine
 * Date: 10/21/13
 * Time: 11:06
 * To change this template use File | Settings | File Templates.
 */
public class SimulatedAnnealing {
	// Objective function
	// Acceptance probability function

	Node top = new Node(true, 5, 2);
	int temperature = 100; //tmax
	int delta = 1;

	public static void main (String[] args) {
		SimulatedAnnealing sa = new SimulatedAnnealing();
		sa.simulate();
	}

	public void simulate() {
		// General algorithm
	}
}
