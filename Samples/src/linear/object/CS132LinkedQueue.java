package linear.object;

/**
 * A Linked-List based implementation of the Queue ADT.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Dec 8, 2009
 */
public class CS132LinkedQueue implements CS132Queue {

	private CS132List elements;

	/**
	 * Create a new Linked-list based stack.
	 */
	public CS132LinkedQueue() {
		elements = new CS132SinglyLinkedList();
	}

	/**
	 * {@inheritDoc}
	 */
	public Object peek() {
		if (elements.size() > 0) {
			return elements.get(0);
		} else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Object remove() {
		if (elements.size() > 0) {
			return elements.remove(0);
		} else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void add(Object obj) {
		elements.add(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int size() {
		return elements.size();
	}
}
