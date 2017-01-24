package linear.object;

/**
 * A Linked-List based implementation of the Stack ADT.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Dec 8, 2009
 */
public class CS132LinkedStack implements CS132Stack {

	private CS132List elements;

	/**
	 * Create a new Linked-list based stack.
	 */
	public CS132LinkedStack() {
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
	public Object pop() {
		if (elements.size() > 0) {
			return elements.remove(0);
		} else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void push(Object obj) {
		elements.insert(0, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int size() {
		return elements.size();
	}
}
