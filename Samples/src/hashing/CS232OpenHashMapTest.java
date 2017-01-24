package hashing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class CS232OpenHashMapTest {

	protected CS232HashMap<Integer, Character> hm;

	@Before
	public void setUp() {
		hm = new CS232OpenHashMap<Integer, Character>();
	}

	// get a shuffled list of the values 0...(size-1)
	protected ArrayList<Integer> getShuffledList(int size) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		return list;
	}

	@Test
	public void testPutNullThrowsException() {
		try {
			hm.put(null, 'A');
			fail("putting null key into map should throw exception.");
		} catch (IllegalArgumentException e) {
			// pass
		} catch (Exception e) {
			fail("Incorrect exception type thrown.");
		}
	}

	@Test
	public void testPutGetNoResize() {
		ArrayList<Integer> keys = getShuffledList(16);

		for (int key : keys) {
			hm.put(key, (char) (key + 65));
		}

		for (int key : keys) {
			char val = hm.get(key);
			assertEquals("Incorrect value for key: " + key, key + 65, val);
		}

		assertEquals("Incorrect size", 16, hm.size());
		assertEquals("Incorrect capacity, should not have grown", 16,
				hm.capacity());
	}

	@Test
	public void testPutSameKeyReplacesValue() {
		hm.put(5, 'A');
		assertEquals("Did not find value for key: 5", (Character) 'A',
				hm.get(5));

		hm.put(5, 'B');
		assertEquals("Value for key: 5 was not replaced", (Character) 'B',
				hm.get(5));
	}

	@Test
	public void testGetKeyNotInMap() {
		ArrayList<Integer> keys = getShuffledList(12);
		for (int key : keys) {
			hm.put(key, (char) (key + 65));
		}

		for (int key : keys) {
			char val = hm.get(key);
			assertEquals("Incorrect value for key: " + key, key + 65, val);
		}

		assertNull("Should not get value for key 13", hm.get(13));
		assertNull("Should not get value for key 14", hm.get(14));

		assertEquals("Incorrect size", 12, hm.size());
		assertEquals("Incorrect capacity, should not have grown", 16,
				hm.capacity());
	}

	@Test
	public void testHashSeverlKeysToSameHomeIndex() {
		hm.put(5, 'A');
		hm.put(20, 'B');
		hm.put(36, 'C');
		hm.put(52, 'D');

		assertEquals("Did not find value for key: 5", (Character) 'A',
				hm.get(5));
		assertEquals("Did not find value for key: 20", (Character) 'B',
				hm.get(20));
		assertEquals("Did not find value for key: 36", (Character) 'C',
				hm.get(36));
		assertEquals("Did not find value for key: 52", (Character) 'D',
				hm.get(52));
	}

	@Test
	public void testResizesOnceAtRightSize() {
		ArrayList<Integer> keys = getShuffledList(17);
		for (int key : keys) {
			hm.put(key, (char) (key + 65));
		}

		for (int key : keys) {
			char val = hm.get(key);
			assertEquals("Incorrect value for key: " + key, key + 65, val);
		}

		assertEquals("Incorrect size", 17, hm.size());
		assertEquals("Incorrect capacity, should have doubled", 32,
				hm.capacity());
	}

	@Test
	public void testResizesSeveralTimes() {
		ArrayList<Integer> keys = getShuffledList(513);
		for (int key : keys) {
			hm.put(key, (char) ((key + 65) % 128));
		}

		for (int key : keys) {
			char val = hm.get(key);
			assertEquals("Incorrect value for key: " + key, ((key + 65) % 128),
					val);
		}

		assertEquals("Incorrect size", 513, hm.size());
		assertEquals("Incorrect capacity, should have increased several times",
				1024, hm.capacity());
	}

	@Test
	public void testRemoveKeyInMap() {
		ArrayList<Integer> keys = getShuffledList(12);
		for (int key : keys) {
			hm.put(key, (char) (key + 65));
		}

		for (int key : keys) {
			Character valCh = hm.remove(key);

			assertNotNull("Key " + key + " should be in map.", valCh);
			char val = valCh;

			assertEquals("Incorrect value for removed key: " + key, key + 65,
					val);
		}

		assertEquals("Incorrect size", 0, hm.size());
		assertEquals("Incorrect capacity, should not have changed", 16,
				hm.capacity());
	}

	@Test
	public void testRemoveKeyWithMultipleHashedToSameIndex() {
		hm.put(5, 'A');
		hm.put(20, 'B');
		hm.put(36, 'C');
		hm.put(52, 'D');

		hm.remove(36);

		assertEquals("Did not find value for key: 5", (Character) 'A',
				hm.get(5));
		assertEquals("Did not find value for key: 20", (Character) 'B',
				hm.get(20));
		assertNull("Should not find value for key: 36", hm.get(36));
		assertEquals("Did not find value for key: 52", (Character) 'D',
				hm.get(52));
	}

	@Test
	public void testRemoveNotInMap() {
		ArrayList<Integer> keys = getShuffledList(12);
		for (int key : keys) {
			hm.put(key, (char) (key + 65));
		}

		assertNull("Should not get value for key 13", hm.remove(13));
		assertNull("Should not get value for key 14", hm.remove(14));

		assertEquals("Incorrect size", 12, hm.size());
		assertEquals("Incorrect capacity, should not have grown", 16,
				hm.capacity());
	}
}
