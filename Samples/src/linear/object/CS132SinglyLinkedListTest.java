package linear.object;

import org.junit.Before;

public class CS132SinglyLinkedListTest extends CS132ArrayListTest {

    /*
     * Because the tests in CS132ArrayListTest all use the CS132List interface
     * this class will inherit all of those tests and use the
     * CS132SinglyLinkedList instead!
     */

    @Before
    public void setUp() throws Exception {
        myList = new CS132SinglyLinkedList();
    }
}
