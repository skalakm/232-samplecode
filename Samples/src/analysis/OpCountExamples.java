package analysis;

/**
 * A few examples used for practice in finding operation counts.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 8, 2016
 */
public class OpCountExamples {

	/**
	 * Replace the first occurrence of x in arr with y.
	 * 
	 * @param arr
	 *            the array of values
	 * @param x
	 *            the value to be replaced
	 * @param y
	 *            the value that replaces x
	 */
	public static void replaceFirstX(int[] arr, int x, int y) {
		int i = 0;
		boolean done = false;
		while (!done && i < arr.length) {
			if (arr[i] == x) {
				arr[i] = y;
				done = true;
			}
			i++;
		}
	}

	/**
	 * Find the most frequent value in the given arr. Assume that arr is square
	 * and that the values in arr are between 0 and arr.length.
	 * 
	 * @param arr
	 *            a square 2d array of values in the range [0...arr.length]
	 * @return the most frequent value in arr.
	 */
	public static int mostFrequentValue(int[][] arr) {
		int[] counts = new int[arr.length];
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr.length; c++) {
				int val = arr[r][c];
				counts[val]++;
			}
		}

		int max = 0;
		for (int v = 0; v < counts.length; v++) {
			if (counts[v] > max) {
				max = counts[v];
			}
		}

		return max;
	}

	/**
	 * Compute the sum of all of the elements in the lower triangle of array.
	 * Assume that arr is square.
	 * 
	 * @param arr
	 *            a square 2d array.
	 * @return the lower triangular sum of arr.
	 */
	public static int lowerTriangularSum(int[][] arr) {
		int total = 0;
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c <= r; c++) {
				total = total + arr[r][c];
			}
		}
		return total;
	}
}
