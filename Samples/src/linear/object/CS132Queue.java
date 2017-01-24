package linear.object;

/**
 * Interface specifying a Queue ADT.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version May 3, 2010
 */
public interface CS132Queue {
    
    /**
     * Add the provided Object to the end of the Queue.
     * 
     * @param obj the Object to add
     */
    public void add(Object obj);
    
    /**
     * Remove and return the Object at the head of the Queue.
     * 
     * @return the Object at the head of the Queue or null
     * if the Queue is empty.
     */
    public Object remove();
    
    /**
     * Return a reference to the Object at the head of the Queue without 
     * removing it from the Queue.
     * 
     * @return a reference to the Object at the head of the Queue or
     * null if the Queue is empty.
     */
    public Object peek();
    
    /**
     * Return the number of elements in the Queue.
     * 
     * @return the size of the Queue.
     */
    public int size();
}
