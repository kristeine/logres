package Oving3;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: kristine
 * Date: 10/4/13
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */
public class State {
	private int number = 0;

	public int getNumber() {
		int previousNumber = number;
		number++;
		return previousNumber;
	}
}
