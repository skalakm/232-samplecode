package analysis;

/**
 * A few examples used for practice in analyzing the average case.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 17, 2016
 */
public class AverageCase {

	/**
	 * Find the index of the first occurrence of a character in a String.
	 * 
	 * @param ch
	 *            the character to search for.
	 * @param s
	 *            the string to search
	 * @return the index the first ch in s or -1 if ch does not appear in s.
	 */
	public static int indexOf(char ch, String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ch) {
				return i;
			}
		}
		// Not found.
		return -1;
	}
}
