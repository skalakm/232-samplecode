package hashing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class CS232ClosedHashMapTest extends CS232OpenHashMapTest {

	@Before
	public void setUp() {
		hm = new CS232ClosedHashMap<Integer, Character>();
	}

	@Test
	public void testResizesOnceAtRightSize() {
		// Override because this infinite loops without resize functionality in
		// closed hash map.
		fail("Resize functionality not implemented.");
	}

	@Test
	public void testResizesSeveralTimes() {
		// Override because this infinite loops without resize functionality in
		// closed hash map.
		fail("Resize functionality not implemented.");
	}

	@Test
	public void testLinearProbeingWrapsAround() {
		hm.put(15, 'A');
		hm.put(30, 'B');
		hm.put(45, 'C');
		hm.put(60, 'D');

		assertEquals("Did not find value for key: 15", (Character) 'A',
				hm.get(15));
		assertEquals("Did not find value for key: 30", (Character) 'B',
				hm.get(30));
		assertEquals("Did not find value for key: 45", (Character) 'C',
				hm.get(45));
		assertEquals("Did not find value for key: 60", (Character) 'D',
				hm.get(60));	
	}
	
	@Test
	public void testPutToIndexOfRemovedValue() {
		ArrayList<Integer> keys = getShuffledList(12);
		for (int key : keys) {
			hm.put(key, (char) (key + 65));
		}

		hm.remove(10);
		assertNull("Value should be removed", hm.get(10));

		hm.put(10, 'Z');
		assertEquals("Value for key: 10 should be found", (Character) 'Z',
				hm.get(10));
	}

	@Test
	public void testPutToIndexOfRemovedValueInProbeSequence() {
		ArrayList<Integer> keys = getShuffledList(12);
		for (int key : keys) {
			hm.put(key, (char) (key + 65));
		}

		hm.put(0, 'A');
		hm.put(16, 'B');
		hm.put(32, 'C');
		hm.put(48, 'D');

		hm.remove(32);
		assertNull("Value should be removed", hm.get(32));

		hm.put(64, 'E');
		assertEquals("Value for key: 64 should be found", (Character) 'E',
				hm.get(64));
	}

	@Test
	public void testRemoveAddsDELsPutUsesDELs() {
		ArrayList<Integer> keys = getShuffledList(15);
		for (int key : keys) {
			if (key != 9) {  // ensure null is still in table.
				hm.put(key, (char) (key + 65));
			}
		}
		
		hm.remove(3);
		hm.remove(5);
		hm.remove(14);
		
		hm.put(14, 'Z');
		assertEquals("Value for key: 12 should be found", (Character) 'Z',
				hm.get(14));
		
		// these wrap around...
		
		hm.put(30, 'Y');  // maps to 14, then probes to 3.
		assertEquals("Value for key: 30 should be found", (Character) 'Y',
				hm.get(30));
		
		hm.put(31, 'X');  // maps to 14, then probes to 5.
		assertEquals("Value for key: 31 should be found", (Character) 'X',
				hm.get(31));	
	}
}
