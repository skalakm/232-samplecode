package recursion;

import java.util.ArrayList;

/**
 * Generates a list of all of the permutations of the characters of a given
 * string.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jan 28, 2016
 */
public class Permutations {

	/**
	 * Generate all of the permutations of the characters in the specified
	 * String.
	 * 
	 * @param s
	 *            the string of characters to permute.
	 * @return the permutations of the characters in s.
	 */
	public static ArrayList<String> permute(String s) {
		/*
		 * If s has one character (or none) then s is its only permutation.
		 * 
		 * Otherwise, for each character c in s, get the permutations of s
		 * without c. Then prepend c to each of those permutations. E.g. for ABC
		 * we prepend A onto BC and CB, giving ABC and ACB. We then prepend B
		 * onto AC and CA, giving BAC and BCA. Finally, we prepend C onto AB and
		 * BA, giving CAB and CBA.
		 */
		ArrayList<String> perms = new ArrayList<String>();

		if (s.length() <= 1) {
			// s is its own (and only) permutation.
			perms.add(s);
			return perms;
		} else {
			
			// For each character c in s
			for (int i = 0; i < s.length(); i++) {
				char ch = s.charAt(i);
				
				// Get the permutations of s without c
				String noCh = s.substring(0, i) + s.substring(i + 1);
				ArrayList<String> subPerms = permute(noCh);			// Line 1
				
				// Prepend c to each of the permutations of s without c
				for (String sp : subPerms) {
					perms.add(new String(ch + sp));
				}
			}

			return perms;
		}
	}

	public static void main(String[] args) {
		ArrayList<String> perms = permute("ABC");					// Line 2
		for (String s : perms) {
			System.out.println(s);
		}
	}
}
