import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 12/10/18
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if insertBefore() works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    
    // ----------------------------------------------------------
    /**
     * Check if isEmpty() works
     */
    @Test
    public void testIsEmpty() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	// test empty list
    	assertTrue("Checking to see if the empty list returns true", testDLL.isEmpty());
    	
    	//test list with elements
    	testDLL.insertBefore(0,1);
    	assertFalse("Checking to see if a list with elements returns false", testDLL.isEmpty());
    }
    
 // ----------------------------------------------------------
    /**
     * Check if get() works
     */
    @Test
    public void testGet() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(1);
    	testDLL.push(2);  
    	testDLL.push(3);

    	int testResult = testDLL.get(0);
    	assertEquals("Checking get to a list that contains 3 elements at position 0", 3, testResult);
    	
    	testResult = testDLL.get(1);
    	assertEquals("Checking get to a list that contains 3 elements at position 1", 2, testResult);
    	
    	testResult = testDLL.get(2);
    	assertEquals("Checking get to a list that contains 3 elements at position 2", 1, testResult);
    		
    	//test out of range
    	assertNull("Checking that getting an element at a position larger than the list size returns null", testDLL.get(10));
    	assertNull("Checking that getting an element at a negative position returns null", testDLL.get(-5));
    	
    	//test on empty list
    	DoublyLinkedList<Integer> testDLLEmpty = new DoublyLinkedList<Integer>();
    	assertNull("Checking that getting an element of an empty list returns null", testDLLEmpty.get(0));
    }  
    
    /**
     * Check if deleteAt() works
     */
    @Test
    public void testDeleteAt() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(1);
    	testDLL.push(2); 
    	testDLL.push(3);
    	
    	assertTrue("Checking that deleteAt for a list containing 3 elements at position 0 return true", testDLL.deleteAt(0));
    	assertEquals("Checking the string of the list after deleteAt", "2,1", testDLL.toString());
    	
    	assertTrue("Checking that deleteAt for a list containing 2 elements at position 1 returns true", testDLL.deleteAt(1));
    	assertEquals("Checking the string of the list after deleteAt", "2", testDLL.toString());	

    	//test on position out of range of the list
    	testDLL.push(3);
    	testDLL.push(4); 
    	
    	assertFalse("Checking that deleteAt for a list containing 3 elements at position 5 returns false", testDLL.deleteAt(5));
    	assertEquals("Checking the string of the list after deleteAt at position 5", "4,3,2", testDLL.toString());
    	
    	assertFalse("Checking that deleteAt for a list containing 3 elements at position -1", testDLL.deleteAt(-1));
    	assertEquals("Checking the string of the list after deleteAt", "4,3,2", testDLL.toString());
    	
    	//test on a position out of range for a list with one element
    	DoublyLinkedList<Integer> testDLLOneElement = new DoublyLinkedList<Integer>();
    	testDLLOneElement.push(1);
    	
    	assertFalse("Checking that deleteAt for a list containing 1 element at position -2 returns false", testDLLOneElement.deleteAt(-2));
    	assertEquals("Checking the string of the list after deleteAt", "1", testDLLOneElement.toString());
    	
    	//test for deleting head
    	DoublyLinkedList<Integer> testDLLHead = new DoublyLinkedList<Integer>();
    	testDLLHead.push(1);
    	testDLLHead.push(2);  

    	assertTrue("Checking that deleteAt for a list containing 2 elements at position 0 returns true", testDLLHead.deleteAt(0));
    	assertEquals("Checking the string of the list after deleteAt at position 0", "1", testDLLHead.toString());
    	
    	//test on empty list
    	DoublyLinkedList<Integer> testDLLEmpty = new DoublyLinkedList<Integer>();
    	
    	assertFalse("Checking that deleteAt for a list containing no elements returns false", testDLLEmpty.deleteAt(0));
    	assertEquals("Checking the string of the list after deleteAt on a list with no elements", "", testDLLEmpty.toString());
    	
    	//test on a list of strings
    	DoublyLinkedList<String> stringDLL = new DoublyLinkedList<String>();
    	stringDLL.push("hello");
    	stringDLL.push("hola"); 

    	assertTrue("Checking that deleteAt for a list containing 2 strings at position 1 returns true", stringDLL.deleteAt(1));
    	assertEquals("Checking the string of the list after deleteAt", "hola", stringDLL.toString());
    	
    	assertTrue("Checking that deleteAt for a list containing 1 string at position 0 returns true", stringDLL.deleteAt(0));
    	assertEquals("Checking the string of the list after deleteAt", "", stringDLL.toString());
    	
    	//test for making a list unique and then deleting the second value
    	DoublyLinkedList<String> string2DLL = new DoublyLinkedList<String>();
    	string2DLL.push("test");  string2DLL.push("test");
    	string2DLL.makeUnique();

    	assertFalse("Checking that deleteAt for a list containing 1 string at position 1 returns false", string2DLL.deleteAt(1));
    	
    	//test that the middle element of a list gets deleted successfully
    	testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(1);  testDLL.push((2));  testDLL.push(3);

    	assertTrue("Checking that delteAt for a list containing 3 elements at position 1 returns true", testDLL.deleteAt(1));
    	assertEquals("Checking the string of the list after deleteAt", "3,1", testDLL.toString());

    }
     
    /** 
     * Check if reverse() works
     */
    @Test
    public void testReverse() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(1);
    	testDLL.push(2);
    	testDLL.push(3);
    	
    	testDLL.reverse();
    	assertEquals("Checking that reverse reverses the elements in a list containing 3 elements", "1,2,3", testDLL.toString());
    	
    	testDLL.push(4);
    	testDLL.push(5);
    	
    	testDLL.reverse();
    	assertEquals("Checking that reverse reverses the elements in a list containing 5 elements", "3,2,1,4,5", testDLL.toString());
    	
    	//test if the list is empty
    	DoublyLinkedList<Integer> testDLLEmpty = new DoublyLinkedList<Integer>();
    	testDLLEmpty.reverse();
    	assertEquals("Checking that reverse does nothing to an empty list", "", testDLLEmpty.toString());
    }
    
    /**
     * Check if makeUnique() works
     */
    @Test
    public void testMakeUnique() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(1);
    	testDLL.push(2);
    	testDLL.push(3);
    	testDLL.push(2);
    	testDLL.push(4);
    	testDLL.push(1);
    	
    	testDLL.makeUnique();
    	assertEquals("Checking that the list removes 2 elements", "1,4,2,3", testDLL.toString());
    	
    	testDLL.makeUnique();
    	assertEquals("Checking that a list with no repeated elements is not modified", "1,4,2,3", testDLL.toString());
    	
    	//test for empty list
    	DoublyLinkedList<Integer> testDLLEmpty = new DoublyLinkedList<Integer>();
    	assertEquals("Checking that the list is an empty list", "", testDLLEmpty.toString());
    	
    	DoublyLinkedList<String> stringDLL = new DoublyLinkedList<String>();
    	stringDLL.push("hi");
    	stringDLL.push("hello");
    	stringDLL.push("yeet");
    	stringDLL.push("hi");
    	stringDLL.push("bonjour");
    	stringDLL.makeUnique();
    	assertEquals("Chceking that a string list is unique", "bonjour,hi,yeet,hello", stringDLL.toString());
    	
    	//test for multiple duplicate elements
    	DoublyLinkedList<Integer> testMultiples = new DoublyLinkedList<Integer>();
    	testMultiples.push(1);  testMultiples.push(1);  testMultiples.push(1);  testMultiples.push(1);  testMultiples.push(1);
    	testMultiples.push(2);  testMultiples.push(2);  testMultiples.push(2);  testMultiples.push(2);
    	testMultiples.makeUnique();
    	assertEquals("Checking that the list removes all multiples", "2,1", testMultiples.toString());
    }
     
    /**
     * Check if push() works
     */
    @Test
    public void testPush() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(1);
    	assertEquals("Checking that push adds 1 to the stack of 0 elements", "1", testDLL.toString());
    	
    	testDLL.push(2);
    	assertEquals("Checking that push adds 2 to the stack of 1 elements", "2,1", testDLL.toString());
    	
    	testDLL.push(3);
    	assertEquals("Checking that push adds 3 to the stack of 2 elements", "3,2,1", testDLL.toString());
    	
    	//test a negative number
    	testDLL.push(-6);
    	assertEquals("Checking that push adds -6 to the stack of 3 elements", "-6,3,2,1", testDLL.toString());
    }
    
    /**
     * Check if pop() works
     */
    @Test
    public void testPop() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(1);
    	testDLL.push(2);
    	testDLL.push(3);
    	
    	int poppedData = testDLL.pop();
    	assertEquals("Checking that the popped item was the most recent element removed from the list", 3, poppedData);
    	assertEquals("Checking that the most recent element was removed from the list", "2,1", testDLL.toString());
    	 
    	poppedData = testDLL.pop();
    	assertEquals("Checking that the popped item was the most recent element removed from the list", 2, poppedData);
    	assertEquals("Checking that the most recent element was removed from the list", "1", testDLL.toString());
    	
    	poppedData = testDLL.pop();
    	assertEquals("Checking that the popped item was the most recent element removed from the list", 1, poppedData);
    	assertEquals("Checking that the most recent element was removed from the list", "", testDLL.toString());
    	
    	//test empty list
    	assertNull("Checking that pop on an empty list returns null", testDLL.pop());
    	System.out.println(testDLL.toString());
    } 
    
    /**
     * Check if enqueue() works
     */
    @Test
    public void testEnqueue() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.enqueue(1);
    	testDLL.enqueue(2);
    	testDLL.enqueue(3);
    	
    	assertEquals("Checking the enqueue for a list with 3 elements", "1,2,3", testDLL.toString());
    	
    	//test with negative number
    	testDLL.enqueue(-4);
    	assertEquals("Checking the enqueue for a list with 4 elements", "1,2,3,-4", testDLL.toString());
    }
    
    /**
     * Check if dequeue() works
     */
    @Test
    public void testDequeue() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.enqueue(1);
    	testDLL.enqueue(2);
    	testDLL.enqueue(3);
    	
    	int dequeuedData = testDLL.dequeue();

    	assertEquals("Checking that the dequeued item was the least recent element removed from the list", 1, dequeuedData);
    	assertEquals("Checking that the least recent element was removed from the list", "2,3", testDLL.toString());
    	
    	dequeuedData = testDLL.dequeue();
    	assertEquals("Checking that the dequeued item was the least recent element removed from the list", 2, dequeuedData);
    	assertEquals("Checking that the least recent element was removed from the list", "3", testDLL.toString());
    	
    	dequeuedData = testDLL.dequeue();
    	assertEquals("Checking that the dequeued item was the least recent element removed from the list", 3, dequeuedData);
    	assertEquals("Checking that the least recent element was removed from the list", "", testDLL.toString());
    	
    	//test empty list
    	assertNull("Checking that dequeue on an empty list returns null", testDLL.dequeue());	
    }
    
    /**
     * Check if listLength() works
     */
    @Test
    public void testListLength() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.enqueue(1);
    	testDLL.enqueue(2);
    	testDLL.enqueue(3);
    	
    	assertEquals("Checking that the list length of a list containing 3 elements is 3", 3, testDLL.listLength());
    	
    	//test for empty list
    	DoublyLinkedList<Integer> test2DLL = new DoublyLinkedList<Integer>();
    	assertEquals("Checking that the list length of a list containing 0 elements is 0", 0, test2DLL.listLength());
    }

}
