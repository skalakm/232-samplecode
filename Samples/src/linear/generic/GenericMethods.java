package linear.generic;

/**
 * Illustrate the advantages of using iterators for writing reusable algorithms and
 * how Generic Methods can further enhance the reusability of code.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 24, 2016
 */
public class GenericMethods {

	/**
	 * Perform a linear search of a list to find the index of a desired element.
	 * 
	 * This illustrates how using an iterator allows us to write methods that
	 * will not care if the list we are given is an ArrayList or a LinkedList.
	 * The performance will be nearly identical in both cases when we use an
	 * iterator.
	 * 
	 * Note: If we want to use such a search on objects of different types we
	 * would still have to rewrite it using this approach! See the
	 * genericLinearSearchList method below show how a Generic Method can help!
	 * 
	 * @param list
	 *            the list to search.
	 * @param str
	 *            the element to find.
	 * @return the index of the element in the list or -1 if the element is not
	 *         in the list.
	 */
	public static int linearSearchList(CS232Iterable<String> list, String str) {
		CS232Iterator<String> it = list.getIterator();
		int index = 0;
		while (it.hasNext()) {
			String curStr = it.next();
			if (curStr.equals(str)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	/**
	 * Illustrates that we can only call the linearSearchList with lists that
	 * contain String objects.
	 */
	public static void callLinearSearchList() {

		// we can pass in a list of String objects.
		CS232IterableDoublyLinkedList<String> idllStr = new CS232IterableDoublyLinkedList<String>();
		linearSearchList(idllStr, "Item 10");

		// but we cannot pass in a list of Integer objects.
		CS232IterableDoublyLinkedList<Integer> idllInt = new CS232IterableDoublyLinkedList<Integer>();
		// linearSearchList(idllInt, 32);	// no good!

		/*
		 * using a CS232Iterable object containing any type other than string
		 * won't work.
		 */
	}

	/**
	 * Perform a linear search of a list to find the index of a desired element.
	 * 
	 * This goes one step further than the linearSearchList above and
	 * illustrates how using both an iterator and a generic method allows us to
	 * write methods that will not care if the list we are given is an ArrayList
	 * or a LinkedList and also works regardless of the type of objects
	 * contained in the list.
	 * 
	 * @param list
	 *            the list to search.
	 * @param str
	 *            the element to find.
	 * @return the index of the element in the list or -1 if the element is not
	 *         in the list.
	 */
	public static <E> int genericLinearSearchList(CS232Iterable<E> list,
			E element) {
		CS232Iterator<E> it = list.getIterator();
		int index = 0;
		while (it.hasNext()) {
			E curElement = it.next();
			if (curElement.equals(element)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	/**
	 * Illustrates that we can call the generic linearSearchList method with
	 * lists that have different types of objects in them. The type parameter
	 * for the method is determined by the type of objects in the list that is
	 * passed in.
	 */
	public static void callGenericLinearSearchList() {

		// we can pass in a list of String objects.
		CS232IterableDoublyLinkedList<String> idllStr = new CS232IterableDoublyLinkedList<String>();
		genericLinearSearchList(idllStr, "Item 10");

		// or we can pass in a list of Integer objects.
		CS232IterableDoublyLinkedList<Integer> idllInt = new CS232IterableDoublyLinkedList<Integer>();
		genericLinearSearchList(idllInt, 32);

		// or a CS232Iterable object that contains any other type.
	}
}
