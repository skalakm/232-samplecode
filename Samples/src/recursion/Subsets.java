package recursion;

import java.util.ArrayList;

/**
 * Recursive generation of all subsets of the characters in a string.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jan 28, 2016
 */
public class Subsets {

	/**
	 * Generate all subsets of the characters in the string s.
	 * 
	 * @param s
	 *            a string.
	 * @return a list of all of the subsets of the characters in s.
	 */
	public static ArrayList<String> subsets(String s) {
		return subsets("", s); // Line2
	}

	/**
	 * Recursive Problem Transformation:
	 * 
	 * Generate all subsets of the characters in rest prefixed by the characters
	 * in prefix.
	 * 
	 * <code> 
	 * For example: 
	 *   subsets("", "ABC") -> "" before {ABC, AB, AC, A, BC, B, C, ""}
	 *                      -> {ABC, AB, AC, A, BC, B, C, ""}
	 *                      
	 *   subsets("A", "BC") -> A before {BC, B, C, ""} 
	 *                      -> {ABC, AB, AC, A} 
	 *                      
	 *   subsets("", "BC")  -> "" before {BC, B, C, ""} 
	 *                      -> {BC, B, C, ""} 
	 *                      
	 *   subsets("AB", "C") -> AB before {C, ""}
	 *                      -> {ABC, AB}
	 * </code>
	 */
	private static ArrayList<String> subsets(String prefix, String rest) {
		/**
		 * If rest is empty then prefix is the only subset of rest prefixed by
		 * prefix.
		 * 
		 * If rest is not empty then the full list of sub-sets can be obtained
		 * by combining all subsets that include the first character of rest and
		 * all subsets that do not contains the first character of rest.
		 * 
		 * <code>
		 * For example:
		 *   subsets("","ABC") -> subsets("A","BC") + subsets("","BC")
		 *                     -> {ABC, AB, AC, A}  + {BC, B, C, ""}
		 * </code>
		 */
		ArrayList<String> subs = new ArrayList<String>();
		if (rest.length() == 0) {
			// rest is empty, so prefix is the only subset.
			subs.add(prefix);
			return subs;
		} else {
			// Add all subsets that include the first char of rest
			ArrayList<String> incl = subsets(prefix + rest.charAt(0),
					rest.substring(1)); // Line3
			subs.addAll(incl);

			// Add all subsets that do not include the first char of rest
			ArrayList<String> excl = subsets(prefix, rest.substring(1)); // Line4
			subs.addAll(excl);

			return subs;
		}
	}

	public static void main(String[] args) {
		ArrayList<String> subs = subsets("ABC"); // Line1
		System.out.println("Size: " + subs.size());
		for (int i = 0; i < subs.size(); i++) {
			System.out.println(i + ": " + subs.get(i));
		}
	}
}
