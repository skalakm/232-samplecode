package hashing;

/**
 * An implementation of the HashMap interface that uses closed hashing (open
 * addressing).
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version April 17, 2016
 */
public class CS232ClosedHashMap<K, V> implements CS232HashMap<K, V> {

	private static final int INITIAL_CAPACITY = 16;
	private static final double MAX_LOAD_FACTOR = 0.5;

	/*
	 * Special value that is inserted into the hash table in place of a
	 * key,value pair that has been removed. This allows such locations to be
	 * skipped over during probing.
	 */
	private final KeyValuePair<K, V> DEL = new KeyValuePair<K, V>(null, null);

	private int currentSize;
	private KeyValuePair<K, V>[] hashTable;

	public CS232ClosedHashMap() {
		/*
		 * NOTE: Java does not support the creation of arrays of a generic type.
		 * So instead we create an array of type Object cast it to the
		 * appropriate specified type.
		 */
		hashTable = (KeyValuePair<K, V>[]) new KeyValuePair<?, ?>[INITIAL_CAPACITY];
		currentSize = 0;
	}

	/*
	 * Helper method to compute an index into the hash table from the hash code.
	 */
	private int getIndex(K key) {
		return key.hashCode() % hashTable.length;
	}

	/**
	 * Probe function. This implementation performs linear probing. Can be
	 * overridden in sub-classes to implement different types of probing.
	 * 
	 * @param key
	 *            the key
	 * @param i
	 *            the number of the probe.
	 * @return the offset from the home index to the ith probe location.
	 */
	protected int probeFunction(K key, int i) {
		return i;
	}

	/*
	 * Helper method that gets the index of the KeyValuePair matching the key if
	 * it exists, or the location where it would be placed if it does not exist.
	 */
	private int probeForIndex(K key, int homeIndex) {

		// Compute the cur index in case the probe function includes a constant.
		int curIndex = (homeIndex + probeFunction(key, 0)) % hashTable.length;

		/*
		 * Starting at the homeIndex, probe using the probeFunction while the
		 * current index is not empty and is not the key. I.e. stop when we find
		 * an empty spot (key is not in table) or we find the key.
		 */
		int probeNum = 0;
		while (hashTable[curIndex] != null
				&& !hashTable[curIndex].key.equals(key)) {

			// get the next index based on the probe function...
			probeNum++;
			curIndex = (homeIndex + probeFunction(key, probeNum))
					% hashTable.length;
		}

		return curIndex;
	}

	/**
	 * {@inheritDoc}
	 */
	public void put(K key, V value) {
		/*
		 * NOTE: This implementation does not currently resize the hash table.
		 * This will cause the getKeyValuePairIndex method to infinite loop if
		 * the table is full and the key does not exist in the table. In a full
		 * working implementation, we would resize once the load factor is
		 * greater than MAX_LOAD_FACTOR and this problem goes away.
		 */

		/*
		 * NOTE: This implementation will have to be modified to operate
		 * correctly in the presence of DEL entries after the remove operation
		 * is implemented.
		 */

		if (key == null) {
			throw new IllegalArgumentException("key cannot be null.");
		}

		// Find the home index for the key.
		int homeIndex = getIndex(key);

		/*
		 * Probe the table to find the index where the key,value pair is or
		 * should be.
		 */
		int index = probeForIndex(key, homeIndex);

		if (hashTable[index] != null) {
			// entry is not null, so key is already at index, replace the value.
			hashTable[index].value = value;
		} else {
			// entry is null, so key is not in table, add new key,value pair.
			hashTable[index] = new KeyValuePair<K, V>(key, value);
			currentSize++;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public V get(K key) {
		// Intentionally not implemented - see homework assignment.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * {@inheritDoc}
	 */
	public V remove(K key) {
		// Intentionally not implemented - see homework assignment.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * {@inheritDoc}
	 */
	public int size() {
		return currentSize;
	}

	/**
	 * {@inheritDoc}
	 */
	public int capacity() {
		return hashTable.length;
	}

	/*
	 * Class defining the objects that holds the key,value pairs in the hash
	 * table.
	 */
	private static class KeyValuePair<K, V> {
		public K key;
		public V value;

		public KeyValuePair(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}
