package linear.generic;

/**
 * Some examples illustrating the use of Iterators to iterate over the elements
 * in a list. Also some comparison of the performance to iteration using the get
 * list operation.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 24, 2016
 */
public class IteratorUseExamples {

	/**
	 * Run a comparison of List traversal with get vs. iterator.
	 * 
	 * @param args
	 *            none.
	 */
	public static void main(String[] args) {
		compareTraversals();
	}

	/**
	 * Compare the performance of traversing a LinkedList using the get
	 * operation to the same traversal using an iterator.
	 */
	public static void compareTraversals() {
		System.out.println("Building lists...");
		CS232IterableDoublyLinkedList<String> linkedList = new CS232IterableDoublyLinkedList<String>();
		fillList(linkedList, 100000);
		CS232ArrayList<String> arrayList = new CS232ArrayList<String>();
		fillList(arrayList, 100000);
		System.out.println("Lists built.");

		System.out.println();
		System.out.println("Traversing 100k element LinkedList with get...");
		long start = System.currentTimeMillis();
		traverseListWithGet(linkedList);
		long end = System.currentTimeMillis();
		System.out.println("Done in: " + (end - start) / 1e3 + " sec.");

		System.out.println();
		System.out
				.println("Traversing 100k element LinkedList with Iterator...");
		start = System.currentTimeMillis();
		traverseListWithIterator(linkedList);
		end = System.currentTimeMillis();
		System.out.println("Done in: " + (end - start) / 1e3 + " sec.");

		System.out.println();
		System.out.println("Traversing 100k element ArrayList with get...");
		start = System.currentTimeMillis();
		traverseListWithGet(arrayList);
		end = System.currentTimeMillis();
		System.out.println("Done in: " + (end - start) / 1e3 + " sec.");

		/*
		 * NOTE: Can't do a traversal of the ArrayList using an iterator because
		 * the CS232ArrayList class in the CS232Sampes.linear.generic package
		 * does not implement CS232Iterable. That is a HW question! So you could
		 * try this out after completing the homework if you like.
		 */
	}

	/**
	 * Traverse the list using the get operation. When using a LinkedList, this
	 * will be dramatically slower than traversing the list using an iterator.
	 * This approach using get() with a LinkedList will require O(n^2) vs O(n)
	 * with an iterator.
	 * 
	 * @param list
	 *            the list to traverse
	 */
	public static void traverseListWithGet(CS232List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			// do something with s.
		}
	}

	/**
	 * Traverse the list using an iterator operation. When using a LinkedList,
	 * this will be dramatically faster than traversing the list using the get()
	 * method. This approach using an iterator with a LinkedList will require
	 * O(n) vs O(n^2) with the get() method.
	 * 
	 * @param list
	 *            the list to traverse
	 */
	public static void traverseListWithIterator(CS232Iterable<String> list) {
		CS232Iterator<String> it = list.getIterator();
		while (it.hasNext()) {
			String s = it.next();
			// do something with s.
		}
	}

	/*
	 * Helper method to fill a list with a given number of elements.
	 */
	private static void fillList(CS232List<String> list, int num) {
		for (int i = 0; i < num; i++) {
			list.add(new String("Item " + i));
		}
	}
}
