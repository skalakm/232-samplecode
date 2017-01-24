package hashing;

/**
 * Generic HashMap interface that defines the operations supported by all of the
 * implementations of the HashMap ADT.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version April 17, 2016
 */
public interface CS232HashMap<K, V> {

	/**
	 * Put the key,value pair into the hashmap. If the key already exists in the
	 * hashmap, the value associated with the key, the value is replaced.
	 * 
	 * @param key
	 *            the key, may not be null.
	 * @param value
	 *            the value
	 * @throws IllegalArgumentException if key is null.
	 */
	public void put(K key, V value);

	/**
	 * Get the value associated with the provided key.
	 * 
	 * @param key
	 *            the key
	 * @return the value associated with the key, or null if key does not appear
	 *         in the map.
	 */
	public V get(K key);

	/**
	 * Remove the key,value pair for the specified key from the map.
	 * 
	 * @param key
	 *            the key
	 * @return the value that was removed, or null if key does not appear in the
	 *         map.
	 */
	public V remove(K key);

	/**
	 * Get the number of key,value pairs contained in the map.
	 * 
	 * @return the number of key,value pairs in the map.
	 */
	public int size();

	/**
	 * Get the capacity of the hash table (i.e. the array) on which this HashMap
	 * is built.
	 * 
	 * @return the capacity of the underlying hash table.
	 */
	public int capacity();
}
