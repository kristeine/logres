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

	Node top;
	int temperature = 100; //tmax
	int delta = 1;

	public static void main (String[] args) {
		SimulatedAnnealing sa = new SimulatedAnnealing();
		sa.simulate();
	}

	public void simulate() {
		int m = 5;
		int k = 2;
		top = new Node(true, m, k);
		// General algorithm
		int fCurrent = top.objectiveFunction();
		int target = 2*m + (2*((m-1)+(m-2)));
		/* if (fCurrent >= target) {
			System.out.println("This is the solution!");
		}  */

		//TORKJEL TESTER
		
		//TORKJEL FERDIG MED TEST
	}
}
