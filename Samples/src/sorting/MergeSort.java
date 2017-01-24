package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Implementation of the Merge Sort algorithm.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version April 4, 2016
 */
public class MergeSort {

	/**
	 * Merge sort the provided array.
	 * 
	 * @param arr
	 *            the array to be sorted.
	 */
	public static void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length);
	}

	/*
	 * Recursive helper method that does the sorting. This method sorts the part
	 * of arr beginning at start (inclusive) up to, but not including end
	 * (exclusive).
	 * 
	 * Note: The left and right parts of the array are not copied into new
	 * arrays.
	 */
	private static void mergeSort(int[] arr, int start, int end) {

		/*
		 * Only need to sort if there is more than one element in the part of
		 * the array being sorted. If there is only one element in there, then
		 * it is by definition sorted!
		 */
		if (end - start > 1) {

			// Find the mid point of the part of the array being sorted.
			int mid = start + (end - start) / 2;
			/*
			 * NOTE: Could use (start+end)/2 which may be more intuitive, but
			 * with very large arrays the value of start+end might overflow the
			 * int type. Doing the calculation this way ensures that overflow
			 * does not happen.
			 */

			// Sort the left part of the array.
			mergeSort(arr, start, mid);

			// Sort the right part of the array.
			mergeSort(arr, mid, end);

			// Merge the two sorted parts.
			merge(arr, start, mid, end);
		}
	}

	/*
	 * Merge the values in arr[start...mid) and arr[mid...end) assuming that
	 * they are each sorted. This method uses an additional array of size
	 * end-start to perform the merge.
	 * 
	 * The running time for this method is THETA(n) where, n = end-start.
	 */
	private static void merge(int[] arr, int start, int mid, int end) {
		int[] mergedValues = new int[end - start];
		int leftIndex = start;
		int rightIndex = mid;
		int mergedIndex = 0;

		// Keep taking values from left or right until the merged array is full.
		while (mergedIndex < mergedValues.length) {

			if (leftIndex < mid && rightIndex < end) {
				/*
				 * Still have values in both left and right that have not been
				 * used, so take the smaller of the two and put it into merged.
				 */
				if (arr[leftIndex] < arr[rightIndex]) {
					mergedValues[mergedIndex] = arr[leftIndex];
					leftIndex++;
				} else {
					mergedValues[mergedIndex] = arr[rightIndex];
					rightIndex++;
				}
			} else if (leftIndex < mid) {
				/*
				 * There are still values in left that have not been used but
				 * right has been exhausted so take the next value from left.
				 */
				mergedValues[mergedIndex] = arr[leftIndex];
				leftIndex++;
			} else {
				/*
				 * There are still values in right that have not been used but
				 * left has been exhausted so take the next value from right.
				 */
				mergedValues[mergedIndex] = arr[rightIndex];
				rightIndex++;
			}

			mergedIndex++;
		}

		/*
		 * Copy the values from the mergedValues back into arr.
		 */
		mergedIndex = 0;
		for (int i = start; i < end; i++) {
			arr[i] = mergedValues[mergedIndex];
			mergedIndex++;
		}
	}

	/**
	 * Sort a list of integer values using the merge sort.
	 */
	public static void main(String[] args) {
		int size = 20;
		Random rnd = new Random();
		int[] list = new int[size];
		for (int i = 0; i < list.length; i++) {
			list[i] = rnd.nextInt(100);
		}

		System.out.println("Unsorted List: " + Arrays.toString(list));
		mergeSort(list);
		System.out.println("  Sorted List: " + Arrays.toString(list));
	}
}
