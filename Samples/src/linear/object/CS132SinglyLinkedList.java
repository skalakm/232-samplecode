package linear.object;

/**
 * An implementation of the List interface backed with a singly linked list of
 * Objects.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 29, 2009
 */
public class CS132SinglyLinkedList implements CS132List {

    /*
     * This is a reference to the first node in the list.  If the list is
     * empty then this reference will be null.
     */
    private SinglyLinkedNode head;
    
    /*
     * This is a reference to the last node in the list.  If the list is
     * empty then this reference will be null.  If there is one node in the list
     * then head and tail will refer to the same node.
     */
    private SinglyLinkedNode tail;

    private int currentSize;
    

    /**
     * Construct a new SinglyLinkedListBackedList.
     */
    public CS132SinglyLinkedList() {
        head = null;
        tail = null;
        currentSize = 0;
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
    public void add(Object element) {
        SinglyLinkedNode newNode = new SinglyLinkedNode(element);

        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }

        currentSize++;
    }

    /**
     * {@inheritDoc}
     */
    public Object get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException("tried to access " + index + " but size is "
                    + currentSize + ".");
        }
        else {
            SinglyLinkedNode node = getNode(index);
            return node.element;
        }
    }

    /*
     * Get the node at the specified index by walking down the chain of
     * nodes to the index.
     */
    private SinglyLinkedNode getNode(int index) {
        SinglyLinkedNode node = head;

        /*
         * Walk down the chain of links until we get to the correct node.
         */
        for (int i=0; i<index; i++) {
            node = node.next;
        }

        return node;
    }

    /**
     * {@inheritDoc}
     */
    public void set(int index, Object element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException("tried to set " + index + " but size is "
                    + currentSize + ".");
        }
        else {
            SinglyLinkedNode node = getNode(index);
            node.element = element;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void insert(int index, Object element) throws IndexOutOfBoundsException {
        if (index < 0 || index > currentSize) {
            throw new IndexOutOfBoundsException("tried to set " + index + " but size is "
                    + currentSize + ".");
        }
        else {
            SinglyLinkedNode newNode = new SinglyLinkedNode(element);
            
            if (index == 0) {
                newNode.next = head;
                head = newNode;
            }
            else {
                /*
                 * Walk down the chain of links until pred refers to the node that will
                 * precede the new node in the list.
                 */
                SinglyLinkedNode pred = head;
                for (int i=0; i<index-1; i++) {
                    pred = pred.next;
                } 
                
                newNode.next = pred.next;
                pred.next = newNode;
            }
            
            currentSize++;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public Object remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException("tried to set " + index + " but size is "
                    + currentSize + ".");
        }
        else {
            Object obj = null;

            if (index == 0) {
                obj = head.element;
                head = head.next;
            }
            else {
                SinglyLinkedNode pred = head;
                
                for (int i=0; i<index-1; i++) {
                    pred = pred.next;
                } 
                
                SinglyLinkedNode node = pred.next;
                obj = node.element;
                pred.next = node.next;
               
                if (index == currentSize - 1) {
                    tail = pred;
                }
            }
            
            currentSize--;
            return obj;
        }
    }

    /*
     * Structure used to represent a node in the linked list.
     */
    private static class SinglyLinkedNode {
        public SinglyLinkedNode next;
        public Object element;

        public SinglyLinkedNode(Object element) {
            this.element = element;
            next = null;
        }
    }
}
