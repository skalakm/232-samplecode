package recursion;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Illustrates the use of recursion to count the number of 'x' characters in a
 * string.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jan 28, 2016
 */
public class CountX {

	/**
	 * Count the number of 'x' characters in s.
	 * 
	 * This version of the method is written to emphasize the recursive view of
	 * the problem.
	 * 
	 * @param s
	 *            the string.
	 * @return the number of 'x's in s.
	 */
	public static int countX(String s) {
		/*
		 * An empty string has 0 'x's. Any string that starts with 'x' has 1
		 * more than the number of x's in the rest of the string. Any string
		 * that does not start with an 'x' has just the number of 'x's in the
		 * rest of the string.
		 */
		if (s.length() == 0) {
			// string is empty, no 'x's.
			return 0;									// Line 1
		} else {
			if (s.charAt(0) == 'x') {
				// string starts with 'x', one more than rest...
				return 1 + countX(s.substring(1));		// Line 2
			} else {
				// string does not start with 'x', just the rest...
				return countX(s.substring(1));			// Line 3
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(countX("axbx"));				// Line 4
	}
	
	@Test
	public void testEmpty() {
		assertEquals("Incorrect result on empty string.", 0, countX(""));
	}

	@Test
	public void testNoX() {
		assertEquals("Incorrect with no x's.", 0, countX("abcd"));
	}

	@Test
	public void testSomeX() {
		assertEquals("Incorrect result with some x's.", 3, countX("abxcxxd"));
	}

	@Test
	public void testXStartEnd() {
		assertEquals("Incorrect result with x on beginning and end.", 5,
				countX("xabxcxxdx"));
	}
}
