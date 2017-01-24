package linear.generic;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class CS232DoublyLinkedListTest {

    protected CS232List<String> myList;

    @Before
    public void setUp() throws Exception {
        myList = new CS232DoublyLinkedList<String>();
    }

    @Test
    public void testSizeOnEmptyList() {
        assertEquals("wrong size", 0, myList.size());
    }

    @Test
    public void testAddAndGet() {
        for (int i = 0; i <= 8; i++) {
            myList.add("Item" + i);
        }
        assertEquals("wrong size", 9, myList.size());
        assertEquals("wrong 0th element", "Item0", myList.get(0));
        assertEquals("wrong 1st element", "Item1", myList.get(1));
        assertEquals("wrong 2nd element", "Item2", myList.get(2));
        assertEquals("wrong 7th element", "Item7", myList.get(7));
        assertEquals("wrong 8th element", "Item8", myList.get(8));
    }

    @Test
    public void testAddABunch() {
        // causes 3 resizes, 10->20 and 20->40 and 40->80.
        for (int i = 0; i <= 50; i++) {
            myList.add("Item" + i);
        }
        assertEquals("wrong size", 51, myList.size());
        assertEquals("wrong 0th element", "Item0", myList.get(0));
        assertEquals("wrong 50th element", "Item50", myList.get(50));
    }

    @Test
    public void testGetOutOfBounds() {
        myList.add("Test1");
        myList.add("Test2");

        try {
            myList.get(-1);
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }

        try {
            myList.get(2);
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
    }

    @Test
    public void testSetFirst() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");

        myList.set(0, "NewValue");
        assertEquals("element not set", "NewValue", myList.get(0));
    }

    @Test
    public void testSetMiddle() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");

        myList.set(1, "NewValue");
        assertEquals("element not set", "NewValue", myList.get(1));
    }

    @Test
    public void testSetLast() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");

        myList.set(2, "NewValue");
        assertEquals("element not set", "NewValue", myList.get(2));
    }

    @Test
    public void testSetOutOfBounds() {
        myList.add("Test1");
        myList.add("Test2");

        try {
            myList.set(-1, "Error");
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
        
        try {
            myList.set(2, "Error");
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
    }

    
    @Test
    public void testInsertAtStart() {
        myList.add("Test1");
        myList.add("Test2");
        
        myList.insert(0, "NewValue");
        
        assertEquals("wrong size", 3, myList.size());
        assertEquals("element not inserted", "NewValue", myList.get(0));
        assertEquals("element not shifted by insert", "Test1", myList.get(1));
        assertEquals("element not shifted by insert", "Test2", myList.get(2));
        
        myList.insert(0, "NewValue2");
        
        assertEquals("wrong size", 4, myList.size());
        assertEquals("element not inserted", "NewValue2", myList.get(0));
        assertEquals("element not inserted", "NewValue", myList.get(1));
        assertEquals("element not shifted by insert", "Test1", myList.get(2));
        assertEquals("element not shifted by insert", "Test2", myList.get(3));
    }
    
    @Test
    public void testInsertInMiddle() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        myList.add("Test4");  
        
        myList.insert(2, "NewValue");
        
        assertEquals("wrong size", 5, myList.size());
        assertEquals("element should not have moved", "Test1", myList.get(0));
        assertEquals("element should not have moved", "Test2", myList.get(1));
        assertEquals("element not inserted", "NewValue", myList.get(2));
        assertEquals("element not shifted by insert", "Test3", myList.get(3));
        assertEquals("element not shifted by insert", "Test4", myList.get(4));
    }
    
    @Test
    public void testInsertIllegal() {
        myList.add("Test1");
        myList.add("Test2");
        
        try {
            myList.insert(3, "NewValue");
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
        
        try {
            myList.insert(-1, "NewValue");
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
    }
    
    @Test
    public void testInsertEmptyList() {
        myList.insert(0, "NewValue");
        assertEquals("wrong size", 1, myList.size());
        assertEquals("element should be first", "NewValue", myList.get(0));
    }
    
    @Test
    public void testInsertAtEnd() {
        myList.add("Test1");
        myList.add("Test2");
        
        myList.insert(2, "NewValue");
        
        assertEquals("wrong size", 3, myList.size());
        assertEquals("element should be last", "NewValue", myList.get(2));
    }
    
    @Test
    public void testInsertMany() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        myList.add("Test4");  
        
        for (int i=0; i<30; i++) { 
            myList.insert(2, "NewItem"+i);
        }
        
        assertEquals("wrong size", 34, myList.size());

        for (int i=0; i<30; i++) {
            assertEquals("Wrong item found at index " + (i+2), "NewItem"+(29-i), myList.get(i+2));
        }
    }
    
    @Test
    public void testRemoveAtStart() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        
        Object obj = myList.remove(0);
        assertEquals("wrong object removed", "Test1", obj);
        assertEquals("wrong size", 2, myList.size());
        assertEquals("element should have shifted down", "Test2", myList.get(0));
        assertEquals("element should have shifted down", "Test3", myList.get(1));
    }
    
    @Test
    public void testRemoveInMiddle() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        myList.add("Test4");
        myList.add("Test5");
             
        Object obj = myList.remove(2);
        assertEquals("wrong object removed", "Test3", obj);
        assertEquals("wrong size", 4, myList.size());
        assertEquals("element should not have changed", "Test1", myList.get(0));
        assertEquals("element should not have changed", "Test2", myList.get(1));
        assertEquals("element should have shifted down", "Test4", myList.get(2));
        assertEquals("element should have shifted down", "Test5", myList.get(3));
    }
    
    @Test
    public void testRemoveAtEnd() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        myList.add("Test4");
        
        Object obj = myList.remove(3);
        assertEquals("wrong object removed", "Test4", obj);
        assertEquals("wrong size", 3, myList.size());
        assertEquals("element should not have changed", "Test1", myList.get(0));
        assertEquals("element should not have changed", "Test2", myList.get(1));
        assertEquals("element should not have changed", "Test3", myList.get(2));
    }
    
    @Test
    public void testRemoveMany() {
    	for (int i=0; i<100; i++) {
    		myList.add("" + i);
    	}
    	Random rnd = new Random();
    	for (int i=0; i<98; i++) {
    		myList.remove(rnd.nextInt(myList.size()));
    	}
    	
    	assertEquals("Incorrect list size", 2, myList.size());
    }
    
    @Test
    public void testRemoveAtEndThenAdd() {
        myList.add("Test1");
        myList.add("Test2");
        myList.add("Test3");
        myList.add("Test4");
        
        myList.remove(3);
        myList.add("NewValue");
        assertEquals("wrong size", 4, myList.size());
        assertEquals("element should not have changed", "Test1", myList.get(0));
        assertEquals("element should not have changed", "Test2", myList.get(1));
        assertEquals("element should not have changed", "Test3", myList.get(2));
        assertEquals("element should not have changed", "NewValue", myList.get(3));
    }
    
    @Test
    public void testRemoveIllegal() {
        myList.add("Test1");
        myList.add("Test2");
        
        try {
            myList.remove(2);
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
        
        try {
            myList.remove(-1);
            fail("should have thrown exception");
        }
        catch (IndexOutOfBoundsException e) {
        }
        catch(Exception e) {
            fail("Incorrect type of exception.");
        }
    }
    
    private void listCompare(ArrayList<String> al) {
    	assertEquals("Lists have different size.", al.size(), myList.size());
    	for (int i=0; i<al.size(); i++) {
    		if (!al.get(i).equals(myList.get(i))) {
    			fail("List elements do not match at index " + i);
    		}
    	}
    }
    
    @Test
    public void stressTest() {
    	ArrayList<String> al = new ArrayList<String>();
    	Random rnd = new Random();
    	
    	for (int i=0; i<100; i++) {
    		al.add(""+ i);
    		myList.add("" + i);
    	}
    	listCompare(al);
    	
    	for (int i=0; i<2000; i++) {
    		int op = rnd.nextInt(4);
    		int index = rnd.nextInt(al.size());
    		
    		switch(op) {
    		case 0: // add
    			myList.add("A" + i);
    			al.add("A" + i);
    			break;
    		case 1: // set
    			myList.set(index, "S" + i);
    			al.set(index, "S" + i);
    			break;
    		case 2: // insert
    			myList.insert(index, "I" + i);
    			al.add(index, "I" + i);
    			break;
    		case 3: // remove
    			myList.remove(index);
    			al.remove(index);
    			break;
    		}
    		listCompare(al);
    	}
    }
}
