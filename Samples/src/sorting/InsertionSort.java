package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Implementation of Insertion sort.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version April 4, 2016
 */
public class InsertionSort {

	/**
	 * An implementation of Insertion Sort.
	 * 
	 * @param vals
	 *            the values to be sorted.
	 */
	public static void insertionSort(int[] vals) {
		/*
		 * The values from index 0 up to but not including i will always be in
		 * sorted order. Note: Initially, there is only one value here, vals[0],
		 * so it is sorted.
		 * 
		 * For each unsorted value, it is swapped with the one to its left until
		 * it is moved into the correct location among the sorted values.
		 */
		for (int i = 1; i < vals.length; i++) {
			int valueToInsert = vals[i];

			/*
			 * While the value to be inserted is less than the one to its left
			 * swap them.
			 */
			int j = i;
			while (j > 0 && vals[j - 1] > valueToInsert) {
				swap(vals, j, j - 1);
				j--;
			}
			// valueToInsert is now in the right spot!
		}
	}

	/*
	 * Swap two elements in an array.
	 */
	private static void swap(int[] vals, int i1, int i2) {
		int temp = vals[i1];
		vals[i1] = vals[i2];
		vals[i2] = temp;
	}

	/*
	 * Generate a list of integers and sort them.
	 */
	public static void main(String[] args) {
		int size = 20;
		Random rnd = new Random();
		int[] list = new int[size];
		for (int i = 0; i < list.length; i++) {
			list[i] = rnd.nextInt(100);
		}

		System.out.println("Unsorted List: " + Arrays.toString(list));
		insertionSort(list);
		System.out.println("  Sorted List: " + Arrays.toString(list));
	}
}
