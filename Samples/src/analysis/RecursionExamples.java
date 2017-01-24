package analysis;

/**
 * A few examples used for practice in analyzing recursive
 * algorithms using recurrence relations.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 17, 2016
 */
public class RecursionExamples {

	/**
	 * Compute the factorial of n.
	 * 
	 * @param n the value.
	 * @return the factorial of n (n!)
	 */
	public static long factorial(long n) {
		if (n == 0) {
			return 1;
		}
		else {
			return n * factorial(n-1);
		}
	}	
}
