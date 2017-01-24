package hashing;

import hashing.list.*;

/**
 * An implementation of the HashMap interface that uses open hashing (closed
 * addressing).
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version April 17, 2016
 */
public class CS232OpenHashMap<K, V> implements CS232HashMap<K, V> {

	private static final int INITIAL_CAPACITY = 16;
	private static final double MAX_LOAD_FACTOR = 1.0;

	private int currentSize;
	private CS232IterableDoublyLinkedList<KeyValuePair<K, V>>[] hashTable;

	public CS232OpenHashMap() {
		/*
		 * NOTE: Java does not support the creation of arrays of a generic type.
		 * So instead we create an array of the unbounded type using a wild card
		 * (?) and cast it to the appropriate specified type.
		 */
		hashTable = (CS232IterableDoublyLinkedList<KeyValuePair<K, V>>[]) new CS232IterableDoublyLinkedList<?>[INITIAL_CAPACITY];
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = new CS232IterableDoublyLinkedList<KeyValuePair<K, V>>();
		}
		currentSize = 0;
	}

	/*
	 * Helper method to compute an index into the hash table from the hash code.
	 */
	private int getIndex(K key) {
		return key.hashCode() % hashTable.length;
	}

	/*
	 * Helper method that gets the KeyValuePair matching the key if it exists,
	 * otherwise it returns null;
	 */
	private KeyValuePair<K, V> getKeyValuePair(K key, int index) {
		// Use and iterator to go through the linked list and find the key.
		CS232Iterator<KeyValuePair<K, V>> it = hashTable[index].getIterator();
		while (it.hasNext()) {
			KeyValuePair<K, V> kvp = it.next();
			if (kvp.key.equals(key)) {
				// found it...
				return kvp;
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void put(K key, V value) {
		// NOTE: Does not resize. See Homework Assignment!

		if (key == null) {
			throw new IllegalArgumentException("key cannot be null.");
		}

		// Get the index to which the key hashes.
		int index = getIndex(key);
		KeyValuePair<K, V> kvp = getKeyValuePair(key, index);
		if (kvp != null) {
			/*
			 * key,value pair already exists in the map so just replace the
			 * value.
			 */
			kvp.value = value;
		} else {
			/*
			 * key,value pair does not exist in the map, so create a new
			 * key,value pair and add it to the linked list at the appropriate
			 * index. NOTE: Insert the new value at the start of the list rather
			 * than at the end to take advantage of temporal locality (i.e. it
			 * was used recently so it will likely be used again soon.)
			 */
			hashTable[index].insert(0, new KeyValuePair<K, V>(key, value));
			currentSize++;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public V get(K key) {
		// Get the index to which the key hashes.
		int index = getIndex(key);
		KeyValuePair<K, V> kvp = getKeyValuePair(key, index);
		if (kvp != null) {
			return kvp.value;
		} else {
			return null;
		}
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
