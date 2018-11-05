import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Madeleine Comtois
 *  @version 18/10/18
 */

/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * 
 * @param <T> This is a type parameter. T is used as a class name in the
 *        definition of this class.
 *
 *        When creating a new DoublyLinkedList, T should be instantiated with an
 *        actual class name that extends the class Comparable. Such classes
 *        include String and Integer.
 *
 *        For example to create a new DoublyLinkedList class containing String
 *        data: DoublyLinkedList<String> myStringList = new
 *        DoublyLinkedList<String>();
 *
 *        The class offers a toString() method which returns a comma-separated
 *        sting of all elements in the data structure.
 * 
 *        This is a bare minimum class you would need to completely implement.
 *        You can add additional methods to support your code. Each method will
 *        need to be tested by your jUnit tests -- for simplicity in jUnit
 *        testing introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>> {

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode {
		public final T data; // this field should never be updated. It gets its
								// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * 
		 * @param theData  : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) {
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	/**
	 * Constructor of an empty DLL
	 * 
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * 
	 * @return true if list is empty, and false otherwise
	 *
	 *         Worst-case asymptotic running time cost: Theta(1)
	 *
	 *         Justification: isEmpty performs one constant statement and returns a
	 *         boolean value. This is only executed once and thus has a constant run
	 *         time.
	 */
	public boolean isEmpty() {
		return (head == null);
	}

	/**
	 * Inserts an element in the doubly linked list
	 * 
	 * @param pos  : The integer location at which the new data should be inserted
	 *             in the list. We assume that the first position in the list is 0
	 *             (zero). If pos is less than 0 then add to the head of the list.
	 *             If pos is greater or equal to the size of the list then add the
	 *             element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 *         Worst-case asymptotic running time cost: O(N)
	 *
	 *         Justification: insertBefore has a linear run time because in a worse-case scenario
	 *         it runs through the the list until it finds the position that it needs. If we suppose
	 *         the length of the list is N, and with every line executed in the loop having a run
	 *         time of Theta(1), this multiplied by the size N gives a run time of O(N), which
	 *         is the longest time it would take to run if the element it is looking for is the last
	 *         element in the list. 
	 */
	public void insertBefore(int pos, T data) {

		DLLNode nodeToInsert = new DLLNode(data, null, null);
		int listLength = listLength();
		DLLNode currentNode = head;

		if (pos <= 0) {
			push(data);

		} else if (pos > listLength - 1) {
			enqueue(data);

		} else {
			int i = 0;
			while (i != pos - 1) {
				i++;
				currentNode = currentNode.next;
			}

			nodeToInsert.next = currentNode.next;
			currentNode.next = nodeToInsert;

		}
	}

	/**
	 * Returns the data stored at a particular position
	 * 
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null
	 *         otherwise.
	 *
	 *         Worst-case asymptotic running time cost: O(N)
	 *
	 *         Justification: This function iterates through the length of the list until a 
	 *         match in position is found. The code in the while loop has a run time of Theta(1),
	 *         and in the worse-case scenario the loop is run until it gets to the end of the list, 
	 *         which has a size of N.  This size times the number of Theta(1) executions gives a run
	 *         time of O(N)
	 *
	 */
	public T get(int pos) {
		int listLength = listLength();

		if (pos >= 0 && pos <= listLength - 1) {
			DLLNode currentNode = head;
			int count = 0;

			while (count < listLength) {
				if (count == pos)
					return currentNode.data;

				count++;
				currentNode = currentNode.next;
			}
		}
		return null;
	}

	/**
	 * Deletes the element of the list at position pos. First element in the list
	 * has position 0. If pos points outside the elements of the list then no
	 * modification happens to the list.
	 * 
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified.
	 *
	 *         Worst-case asymptotic running time cost: O(N)
	 *
	 *         Justification: The lines in this function have a run time of Theta(1) 
	 *         until the code reaches the while loop, where it iterates until it reaches the
	 *         position in the list that it needs. This loop can be executed up to N
	 *         times, where N is the size of the list. This N * Theta(1) gives us a run
	 *         time of O(N). 
	 */
	public boolean deleteAt(int pos) {
		DLLNode currentNode = head;

		if (isEmpty()) {
			return false;
		} else {
			if (listLength() == 1 && pos == 0) {
				head = null;
				tail = null;
				return true;
			} else if (pos == 0) {
				head = head.next;
				return true;
			} else if (pos < 0 || pos >= listLength())
				return false;
			else {
				int i = 0;
				while (i != pos) {
					currentNode = currentNode.next;
					i++;
				}
				if(currentNode.next == null) {
					tail = tail.prev;
					tail.next = null; 
					return true;
				}
				else {
					currentNode.prev.next = currentNode.next;
					currentNode.next.prev = currentNode.prev;
					return true;
				}				
			}
		}
	}

	/**
	 * Reverses the list. If the list contains "A", "B", "C", "D" before the method
	 * is called Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * 			Worst-case asymptotic running time cost: Theta(N)
	 *
	 * 			Justification: This function loops through the list re-attaching the node 
	 * 			connections, with each lines executing a Theta(1) run time. The while loop
	 * 			iterates until it reaches the end of the list, which has a size of N. The
	 * 			Theta(1) run times multiplied by the size N give a total run time of Theta(N).
	 */
	public void reverse() {
		DLLNode tempNode = head;
		DLLNode nextNode = null;
		DLLNode previousNode = null;

		while (tempNode != null) {
			nextNode = tempNode.next;
			tempNode.next = previousNode;
			previousNode = tempNode;
			tempNode = nextNode;
		}
		head = previousNode;
	}

	/**
	 * Removes all duplicate elements from the list. The method should remove the
	 * _least_number_ of elements to make all elements uniqueue. If the list
	 * contains "A", "B", "C", "B", "D", "A" before the method is called Then it
	 * should contain "A", "B", "C", "D" after it returns. The relative order of
	 * elements in the resulting list should be the same as the starting list.
	 *
	 * 			Worst-case asymptotic running time cost: Theta(N^2)
	 *
	 * 			Justification: This function has two while loops, one nested in the other.
	 * 			Given a list of size N, the first while loop iterates until it reaches the 
	 * 			end of the list. Meanwhile, the second while loop also iterates until it 
	 * 			reaches the end of the list. This gives both loops a run time of Theta(N),
	 * 			and Theta(N) * Theta(N) gives an overall run time of Theta(N^2).
	 */

	public void makeUnique() {
		DLLNode firstPointer = head;

		boolean duplicateFound = false;
		while (firstPointer != null) {
			DLLNode secondPointer = firstPointer.next;
			while (secondPointer != null && !duplicateFound) {
				if (firstPointer.data == secondPointer.data) {
					if (secondPointer.next == null) {
						tail = tail.prev;
						tail.next = null;
						duplicateFound = true;
					} else {
						secondPointer.next.prev = secondPointer.prev;
						secondPointer.prev.next = secondPointer.next;
						secondPointer = secondPointer.next;
					}
				} else {

					secondPointer = secondPointer.next;
					if (secondPointer == null)
						duplicateFound = true;
				}
			}
			firstPointer = firstPointer.next;
			duplicateFound = false;
		}
	}

	/*----------------------- STACK API 
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 */

	/**
	 * This method adds an element to the data structure. How exactly this will be
	 * represented in the Doubly Linked List is up to the programmer.
	 * 
	 * @param item : the item to push on the stack
	 *
	 *             Worst-case asymptotic running time cost: Theta(1)
	 *
	 *             Justification: Push runs a series of arguments with a
	 *             constant run time of Theta(1). There are no loops or 
	 *             more advanced methods called, therefore the overall run
	 *             time for the function is Theta(1).
	 */
	public void push(T item) {
		DLLNode newNode = new DLLNode(item, null, head);

		if (head != null)
			head.prev = newNode;
		head = newNode;
		if (tail == null) {
			tail = newNode;
			tail.next = null;
		}
	}

	/**
	 * This method returns and removes the element that was most recently added by
	 * the push method.
	 * 
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 *         Worst-case asymptotic running time cost: Theta(1)
	 *
	 *         Justification: Pop runs a series of constant arguments with run times
	 *         of Theta(1). It makes a call to the method isEmpty, which also has a run
	 *         time of Theta(1). Therefore, the run time for the entire function is also
	 *         Theta(1).
	 */
	public T pop() {
		if (isEmpty())
			return null;
		T data = head.data;
		head = head.next;

		return data;
	}

	/*----------------------- QUEUE API
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 */

	/**
	 * This method adds an element to the data structure. How exactly this will be
	 * represented in the Doubly Linked List is up to the programmer.
	 * 
	 * @param item : the item to be enqueued to the stack
	 *
	 *             Worst-case asymptotic running time cost: Theta(1)
	 *
	 *             Justification: This method adds a new node to the linked list
	 *             by creating a new tail. These computations run in Theta(1) time,
	 *             and the call to isEmpty() also has a run time of Theta(1). Without
	 *             loops or other code that would take longer to run, the
	 *             overall run time is Theta(1).
	 */
	public void enqueue(T item) {
		DLLNode newNode = new DLLNode(item, null, null);
		if (!isEmpty()) {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			tail.next = null;
		} else {
			head = newNode;
			tail = newNode;
		}
	}

	/**
	 * This method returns and removes the element that was least recently added by
	 * the enqueue method.
	 * 
	 * @return the earliest item inserted with an enqueue; or null when the list is
	 *         empty.
	 *
	 *         Worst-case asymptotic running time cost: Theta(1)
	 *
	 *         Justification: Dequeue removes nodes from the linked list by deleting
	 *         the head element. This code is run through Theta(1) time, and since there
	 *         are no loops or calls to other methods with different run times, the run
	 *         time for the function remains Theta(1).
	 */
	public T dequeue() {
		if (!isEmpty()) {
			T dataToDequeue = head.data;
			head = head.next;
			return dataToDequeue;
		} else
			return null;
	}

	/**
	 * @return a string with the elements of the list as a comma-separated list,
	 *         from beginning to end
	 *
	 *         Worst-case asymptotic running time cost: Theta(n)
	 *
	 *         Justification: We know from the Java documentation that
	 *         StringBuilder's append() method runs in Theta(1) asymptotic time. We
	 *         assume all other method calls here (e.g., the iterator methods above,
	 *         and the toString method) will execute in Theta(1) time. Thus, every
	 *         one iteration of the for-loop will have cost Theta(1). Suppose the
	 *         doubly-linked list has 'n' elements. The for-loop will always iterate
	 *         over all n elements of the list, and therefore the total cost of this
	 *         method will be n*Theta(1) = Theta(n).
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next) {
			if (!isFirst) {
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}

	/**
	 * This method returns the length of the list.
	 * 
	 * @return the number of elements in the list, i.e. its length.
	 *
	 *         Worst-case asymptotic running time cost: Theta(N)
	 *
	 *         Justification: listLength loops through the elements in the list and
	 *         increments a counter to get the length of the list. With a linked list
	 *         the size of N, it has to loop through N times, executing code which has
	 *         a run time of Theta(1). N * Theta(1) gives an overall run time of Theta(N).
	 */

	public int listLength() {
		DLLNode counterNode = head;
		int listLength = 0;

		while (counterNode != null) {
			counterNode = counterNode.next;
			listLength++;
		}
		return listLength;
	}	
}
