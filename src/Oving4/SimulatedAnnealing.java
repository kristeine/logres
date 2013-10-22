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
	Node top;
	double temperature = 1000; //tmax
	double delta = 0.01;

	public static void main (String[] args) {
		SimulatedAnnealing sa = new SimulatedAnnealing();
		sa.simulate();
	}

	public void simulate() {
		
		int m = 8;
		int k = 1;
		/**
		 * For m = 5, k = 2, enter m = 5, k = 2
		 */
		top = new Node(true, m, k);
		// General algorithm
		int fCurrent;
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

			Random rnd = new Random();
			float x = rnd.nextFloat();
			
			if(x > p) {
				top = kidMax;
			} else {
				int h = (int)(Math.random()*kids.size());
				top = kids.get(h);
			}
			temperature = temperature - delta;
			if(temperature <= 0) {
				System.out.println("Temperature reached 0");
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
