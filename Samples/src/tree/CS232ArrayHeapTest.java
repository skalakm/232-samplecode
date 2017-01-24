package tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CS232ArrayHeapTest {

	private CS232ArrayHeap<Integer, String> heap;
	
	@Before
	public void setUp() {
		heap = new CS232ArrayHeap<Integer, String>();
	}
	
	@Test
	public void testArrayConstructorValidHeap() {
		Integer[] keys = new Integer[] {9,8,7,6,5,4,3,2,1};
		Integer[] vals = new Integer[] {1,2,3,4,5,6,7,8,9};
		CS232ArrayHeap<Integer,Integer> heap = new CS232ArrayHeap<Integer,Integer>(keys, vals);
		assertEquals("Incorrect heap size", 9, heap.size());
		assertEquals("Incorrect top element", new Integer(1), heap.peek());
	}

	@Test
	public void testArrayConstructorNotValidHeap() {
		try {
			Integer[] keys = new Integer[] {2,1,3};
			Integer[] vals = new Integer[] {1,2,3};
			new CS232ArrayHeap<Integer,Integer>(keys, vals);
			fail("Should throw exception on invalid heap.");
		}
		catch(IllegalArgumentException e) {
			// pass
		}
		catch(Exception e) {
			fail("Threw incorrect exception type");
		}
	}
	
	@Test
	public void testEmptyHeap() {
		assertEquals("Incorrect size", 0, heap.size());
		assertNull("Peek on empty heap is incorrect", heap.peek());
		assertNull("Remove on empty heap is incorrect", heap.remove());
	}
	
	@Test
	public void testAddToEmptyHeap() {
		heap.add(1, "A");
		assertEquals("Incorrect size", 1, heap.size());
		assertEquals("Top of heap is not correct", "A", heap.peek());
	}
	
	@Test
	public void testAddLargestToOneElementHeap() {
		heap.add(1, "A");
		heap.add(2, "B");
		assertEquals("Incorrect size", 2, heap.size());
		assertEquals("Top of heap is not correct", "B", heap.peek());
	}
	
	@Test
	public void testAddNonLargestToOneElementHeap() {
		heap.add(2, "B");
		heap.add(1, "A");
		assertEquals("Incorrect size", 2, heap.size());
		assertEquals("Top of heap is not correct", "B", heap.peek());
	}
	
	@Test
	public void testAddLargestToGrowingHeap() {
		for (int i=0; i<100; i++) {
			heap.add(i, "" + i);
			assertEquals("Incorrect size", i+1, heap.size());
			assertEquals("Top of heap is not correct", "" + i, heap.peek());
		}
	}
	
	@Test
	public void testAddNotLargestToGrowingHeap() {
		for (int i=100; i>=0; i--) {
			heap.add(i, "" + i);
			assertEquals("Incorrect size", 100-i+1, heap.size());
			assertEquals("Top of heap is not correct", "100", heap.peek());
		}
	}
	
	@Test
	public void testRemoveFromEmptyHeap() {
		assertEquals("Incorrect heap size", 0, heap.size());
		assertNull("Incorrectly removed from empty heap", heap.remove());
	}
	
	@Test
	public void testRemoveFromOneNodeHeap() {
		Integer[] keys = new Integer[] {1};
		String[] vals = new String[] {"A"};
		CS232ArrayHeap<Integer,String> heap = new CS232ArrayHeap<Integer,String>(keys, vals);
		
		assertEquals("Incorrectly value removed from heap", "A", heap.remove());
		assertEquals("Incorrect heap size after remove", 0, heap.size());
	}
	
	@Test
	public void testRemoveFromTwoNodeHeap() {
		Integer[] keys = new Integer[] {2, 1};
		String[] vals = new String[] {"B", "A"};
		CS232ArrayHeap<Integer,String> heap = new CS232ArrayHeap<Integer,String>(keys, vals);
		
		assertEquals("Incorrectly value removed from heap", "B", heap.remove());
		assertEquals("Incorrect heap size after remove", 1, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "A", heap.remove());
		assertEquals("Incorrect heap size after remove", 0, heap.size());
	}
	
	@Test
	public void testRemoveFromThreeNodeHeap() {
		Integer[] keys = new Integer[] {3, 2, 1};
		String[] vals = new String[] {"C", "B", "A"};
		CS232ArrayHeap<Integer,String> heap = new CS232ArrayHeap<Integer,String>(keys, vals);
		
		assertEquals("Incorrectly value removed from heap", "C", heap.remove());
		assertEquals("Incorrect heap size after remove", 2, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "B", heap.remove());
		assertEquals("Incorrect heap size after remove", 1, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "A", heap.remove());
		assertEquals("Incorrect heap size after remove", 0, heap.size());
	}
	
	@Test
	public void testRemoveFromFourNodeHeap() {
		Integer[] keys = new Integer[] {4, 3, 2, 1};
		String[] vals = new String[] {"D", "C", "B", "A"};
		CS232ArrayHeap<Integer,String> heap = new CS232ArrayHeap<Integer,String>(keys, vals);
		
		assertEquals("Incorrectly value removed from heap", "D", heap.remove());
		assertEquals("Incorrect heap size after remove", 3, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "C", heap.remove());
		assertEquals("Incorrect heap size after remove", 2, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "B", heap.remove());
		assertEquals("Incorrect heap size after remove", 1, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "A", heap.remove());
		assertEquals("Incorrect heap size after remove", 0, heap.size());
	}
	
	@Test
	public void testRemoveFromFiveNodeHeap() {
		Integer[] keys = new Integer[] {5, 4, 3, 2, 1};
		String[] vals = new String[] {"E", "D", "C", "B", "A"};
		CS232ArrayHeap<Integer,String> heap = new CS232ArrayHeap<Integer,String>(keys, vals);
		
		assertEquals("Incorrectly value removed from heap", "E", heap.remove());
		assertEquals("Incorrect heap size after remove", 4, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "D", heap.remove());
		assertEquals("Incorrect heap size after remove", 3, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "C", heap.remove());
		assertEquals("Incorrect heap size after remove", 2, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "B", heap.remove());
		assertEquals("Incorrect heap size after remove", 1, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "A", heap.remove());
		assertEquals("Incorrect heap size after remove", 0, heap.size());
	}
	
	@Test
	public void testRemoveFromSixNodeHeap() {
		Integer[] keys = new Integer[] {6, 5, 4, 3, 2, 1};
		String[] vals = new String[] {"F", "E", "D", "C", "B", "A"};
		CS232ArrayHeap<Integer,String> heap = new CS232ArrayHeap<Integer,String>(keys, vals);
		
		assertEquals("Incorrectly value removed from heap", "F", heap.remove());
		assertEquals("Incorrect heap size after remove", 5, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "E", heap.remove());
		assertEquals("Incorrect heap size after remove", 4, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "D", heap.remove());
		assertEquals("Incorrect heap size after remove", 3, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "C", heap.remove());
		assertEquals("Incorrect heap size after remove", 2, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "B", heap.remove());
		assertEquals("Incorrect heap size after remove", 1, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "A", heap.remove());
		assertEquals("Incorrect heap size after remove", 0, heap.size());
	}
	
	@Test
	public void testRemoveFromSevenNodeHeap() {
		Integer[] keys = new Integer[] {7, 6, 5, 4, 3, 2, 1};
		String[] vals = new String[] {"G", "F", "E", "D", "C", "B", "A"};
		CS232ArrayHeap<Integer,String> heap = new CS232ArrayHeap<Integer,String>(keys, vals);
		
		assertEquals("Incorrectly value removed from heap", "G", heap.remove());
		assertEquals("Incorrect heap size after remove", 6, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "F", heap.remove());
		assertEquals("Incorrect heap size after remove", 5, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "E", heap.remove());
		assertEquals("Incorrect heap size after remove", 4, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "D", heap.remove());
		assertEquals("Incorrect heap size after remove", 3, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "C", heap.remove());
		assertEquals("Incorrect heap size after remove", 2, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "B", heap.remove());
		assertEquals("Incorrect heap size after remove", 1, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "A", heap.remove());
		assertEquals("Incorrect heap size after remove", 0, heap.size());
	}
	
	@Test
	public void testRemoveFromTenNodeHeap() {
		Integer[] keys = new Integer[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		String[] vals = new String[] {"J", "I", "H", "G", "F", "E", "D", "C", "B", "A"};
		CS232ArrayHeap<Integer,String> heap = new CS232ArrayHeap<Integer,String>(keys, vals);
		
		assertEquals("Incorrectly value removed from heap", "J", heap.remove());
		assertEquals("Incorrect heap size after remove", 9, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "I", heap.remove());
		assertEquals("Incorrect heap size after remove", 8, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "H", heap.remove());
		assertEquals("Incorrect heap size after remove", 7, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "G", heap.remove());
		assertEquals("Incorrect heap size after remove", 6, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "F", heap.remove());
		assertEquals("Incorrect heap size after remove", 5, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "E", heap.remove());
		assertEquals("Incorrect heap size after remove", 4, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "D", heap.remove());
		assertEquals("Incorrect heap size after remove", 3, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "C", heap.remove());
		assertEquals("Incorrect heap size after remove", 2, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "B", heap.remove());
		assertEquals("Incorrect heap size after remove", 1, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "A", heap.remove());
		assertEquals("Incorrect heap size after remove", 0, heap.size());
	}
	
	@Test
	public void testRemoveFromThirteenNodeHeap() {
		Integer[] keys = new Integer[] {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		String[] vals = new String[] {"M", "L", "K", "J", "I", "H", "G", "F", "E", "D", "C", "B", "A"};
		CS232ArrayHeap<Integer,String> heap = new CS232ArrayHeap<Integer,String>(keys, vals);

		assertEquals("Incorrectly value removed from heap", "M", heap.remove());
		assertEquals("Incorrect heap size after remove", 12, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "L", heap.remove());
		assertEquals("Incorrect heap size after remove", 11, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "K", heap.remove());
		assertEquals("Incorrect heap size after remove", 10, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "J", heap.remove());
		assertEquals("Incorrect heap size after remove", 9, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "I", heap.remove());
		assertEquals("Incorrect heap size after remove", 8, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "H", heap.remove());
		assertEquals("Incorrect heap size after remove", 7, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "G", heap.remove());
		assertEquals("Incorrect heap size after remove", 6, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "F", heap.remove());
		assertEquals("Incorrect heap size after remove", 5, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "E", heap.remove());
		assertEquals("Incorrect heap size after remove", 4, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "D", heap.remove());
		assertEquals("Incorrect heap size after remove", 3, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "C", heap.remove());
		assertEquals("Incorrect heap size after remove", 2, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "B", heap.remove());
		assertEquals("Incorrect heap size after remove", 1, heap.size());
		
		assertEquals("Incorrectly value removed from heap", "A", heap.remove());
		assertEquals("Incorrect heap size after remove", 0, heap.size());
	}
}
