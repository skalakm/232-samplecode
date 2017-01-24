package linear.generic;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import linear.object.CS132SinglyLinkedList;

import org.junit.Before;
import org.junit.Test;

public class CS232IterableDoublyLinkedListTest extends
		CS232DoublyLinkedListTest {

	/*
	 * Because the tests in CS232DoublyLinkedListTest all use the CS232List
	 * interface this class will inherit all of those tests and use the
	 * CS232IterableDoublyLinkedList instead! Some of those tests will fail on
	 * the sample implementation because of unimplemented mehtods (See the HW!)
	 */

	private CS232Iterator<String> it;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		myList = new CS232IterableDoublyLinkedList<String>();
		it = ((CS232Iterable<String>) myList).getIterator();
	}

	private void buildList() {
		String[] list = { "one", "two", "three", "four", "five" };
		for (String s : list) {
			myList.add(s);
		}
	}

	@Test
	public void testInitialHasNext() {
		buildList();
		assertTrue("should have next", it.hasNext());
	}

	@Test
	public void testNext() {
		buildList();
		assertEquals("Incorrect value from next", "one", it.next());
	}

	@Test
	public void testNextHasNext() {
		buildList();
		for (int i = 0; i < myList.size(); i++) {
			assertTrue("should have next", it.hasNext());
			it.next();
		}
		assertFalse("should not have next", it.hasNext());
	}

	@Test
	public void testNextException() {
		buildList();
		for (int i = 0; i < myList.size(); i++) {
			it.next();
		}

		try {
			it.next();
			fail("Should throw NoSuchElementException.");
		} catch (NoSuchElementException e) {
			// pass.
		} catch (Exception e) {
			fail("Threw incorrect exception type, should be NoSuchElementException.");
		}
	}

	@Test
	public void testInsertFirst() {
		buildList();
		it.insert("new");
		assertEquals("Item not inserted at first spot", "new", myList.get(0));
		assertEquals("Incorrect size after insert", 6, myList.size());
		assertEquals("Wrong next item after insert.", "one", it.next());
	}

	@Test
	public void testInsertMiddle() {
		buildList();
		it.next(); // 0
		it.next(); // 1

		it.insert("new");
		assertEquals("Item not inserted in middle spot", "new", myList.get(2));
		assertEquals("Incorrect size after insert", 6, myList.size());
		assertEquals("Wrong next item after insert.", "three", it.next());
	}

	@Test
	public void testInsertEnd() {
		buildList();
		while (it.hasNext()) {
			it.next();
		}

		it.insert("new");
		assertEquals("Item not inserted at last spot", "new", myList.get(5));
		assertEquals("Incorrect size after insert", 6, myList.size());
		assertFalse("Should be no next item.", it.hasNext());
	}

	@Test
	public void testMultipleInsert() {
		buildList();
		it.next(); // one
		it.next(); // two
		it.insert("new1");
		it.insert("new2");
		it.insert("new3");

		assertEquals("First item not inserted", "new1", myList.get(2));
		assertEquals("Second item not inserted", "new2", myList.get(3));
		assertEquals("Third item not inserted", "new3", myList.get(4));
		assertEquals("Incorrect size after insert", 8, myList.size());
		assertEquals("Wrong next item after multiple insert.", "three",
				it.next());
	}
}
