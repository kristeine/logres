package Oving4;

import java.util.ArrayList;
import java.util.Random;


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
	double temperature = 1000; //tmax
	double delta = 0.01;

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
		while(true) {	
			fCurrent = top.objectiveFunction();
			if (fCurrent >= target) {
				System.out.println("A solution was found!");
				break;
			}
			
			ArrayList<Node> kids = top.move();
			Node kidMax = kids.get(0);
			for (Node n : kids) {
			      if (n.objectiveFunction() > kidMax.objectiveFunction()) 
			    	  kidMax = n;
			}
			
			double kidMaxObjFun = (double)kidMax.objectiveFunction();
			double topObjFun = (double)top.objectiveFunction();
			double q = (kidMaxObjFun - topObjFun) / (topObjFun);
			double p = Math.min(1, Math.pow(Math.E, -q/temperature));

			System.out.println("q: " + q);
			System.out.println("Toppen av q: " + (kidMax.objectiveFunction() - top.objectiveFunction()));
			System.out.println("Bunnen av q: " + (top.objectiveFunction()));
			System.out.println("e^(-q/t): " + Math.pow(Math.E, -q/temperature));
			Random rnd = new Random();
			float x = rnd.nextFloat();
			
			if(x > p) {
				System.out.println("top = kidMax");
				top = kidMax;
			} else {
				System.out.println("top = random kid");
				int h = (int)(Math.random()*kids.size());
				top = kids.get(h);
			}
			temperature = temperature - delta;
			if(temperature <= 0) {
				System.out.println("Temperature reached 0. Lame solution:");
				break;
			}
		}
		
		for(int i = 0; i < top.board.length; i++) {
			for(int j = 0; j < top.board.length; j++) {
				System.out.print(top.board[i][j] + ", ");
			}
			System.out.println();
		}
	}
}
