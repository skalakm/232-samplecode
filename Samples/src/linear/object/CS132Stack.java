package linear.object;

/**
 * Interface specifying a Stack ADT.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Dec 8, 2009
 */
public interface CS132Stack {

    /**
     * Add the provided Object to the top of the stack.
     * 
     * @param obj the Object to push onto the stack.
     */
    public void push(Object obj);

    /**
     * Remove the Object from of the top of the stack and return it.
     * 
     * @return the Object from the top of the stack or null if the stack is
     *         empty.
     */
    public Object pop();

    /**
     * Return a reference to the Object on the top of the stack without
     * removing it.
     * 
     * @return a reference to the object on the top of the stack or null if the
     *         stack is empty.
     */
    public Object peek();
    
    /**
     * Return the number of elements contained in the stack.
     * 
     * @return the size of the stack.
     */
    public int size();
}
