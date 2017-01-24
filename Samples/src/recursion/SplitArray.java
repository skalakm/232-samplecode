package recursion;

/**
 * Use recursive backtracking to determine if an array of values can be split
 * into two groups such that both groups have the same sum.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jan 28, 2016
 */
public class SplitArray {

	/**
	 * Determine if the values in nums can be split into two groups such that
	 * the groups have the same sum.
	 * 
	 * @param nums
	 *            the numbers.
	 * @return true if the numbers can be split into two groups with the same
	 *         sum, and false otherwise.
	 */
	public static boolean splitArray(int[] nums) {
		return splitArray(nums, 0, 0, 0); // Line2
	}

	/**
	 * Recursive problem transformation:
	 * 
	 * Given a list of numbers, starting point in the list and initial values for
	 * gp1Sum and gp2Sum, determine if the values in nums beginning at index sum
	 * can be split into two groups such that when the values one group are
	 * added to gp1Sum and the values in the other are added to gp2Sum, the
	 * resulting totals are equal.
	 * 
	 * @param nums
	 *            the numbers
	 * @param start
	 *            the starting point in numbers.
	 * @param gp1Sum
	 *            initial sum for group 1.
	 * @param gp2Sum
	 *            initial sum for group 2.
	 * @return true if the nums can be split correctly, false if not.
	 */
	private static boolean splitArray(int[] nums, int start, int gp1Sum, int gp2Sum) {
		/*
		 * If there are no numbers left (i.e. start is too large) and the sums
		 * are equal then there is a solution.
		 * 
		 * If there are no numbers left and the sums are not equal then there is
		 * no solution.
		 * 
		 * Otherwise, try putting the number at start into group 1 and see if
		 * that leads to a solution. If so, then there is a solution! If not,
		 * try putting the number at start into group 2 and see if that leads to
		 * a solution. If so there is a solution! If not then there is no
		 * solution with the number at start in either group, thus there is no
		 * solution.
		 */
		if (start >= nums.length && gp1Sum == gp2Sum) {
			// No numbers left and the sums are equal - solution!
			return true;
		} else if (start >= nums.length) {
			// No numbers left and the sums are not equal - no solution!
			return false;
		} else {
			// try putting nums[start] in group 1.
			if (splitArray(nums, start + 1, gp1Sum + nums[start], gp2Sum)) {  // Line3
				// Found a solution to rest of problem with nums[start] in group 1!
				return true;
			}
			// no solution with nums[start] in group 1

			// nums[start] didn't work in group 1 so now try it in group 2
			if (splitArray(nums, start + 1, gp1Sum, gp2Sum + nums[start])) {  // Line4
				// Found a solution to rest of problem with nums[start] in group 2!
				return true;
			}
			// no solution with nums[start] in group 2

			// nums[start] didn't work in either group... 
			// so no solution...
			// Backtrack!
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(splitArray(new int[] { 5, 1, 4 })); // Line1
	}
}
