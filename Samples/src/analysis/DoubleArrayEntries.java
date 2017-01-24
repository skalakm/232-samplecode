package analysis;

/**
 * First example used in introducing and practicing analysis of algorithms. Used
 * to illustrate basic operation counts and that idea that the constants and
 * slower growing terms do not matter as much as problems get large.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 8, 2016
 */
public class DoubleArrayEntries {

	public static void doubleArrayEntries(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			int cur = arr[i];
			int dubCur = cur * 2;
			arr[i] = dubCur;
		}
	}

	public static void doubleArrayEntries2(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i] * 2;
		}
	}
}
