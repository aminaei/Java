/**
 * -- CS310 -Assignment1 -- LinkedList
 * -- LinkedList<E>:
 * 		-- Node<E>
 * 		-- LinkedList()
 * 		-- addFirst(E obj)
 * 		-- addLast(E obj)
 * 		-- removeFirst()
 * 		-- removeLast()
 * 		-- peekFirst()
 * 		-- peekLast()
 * 		-- makeEmpty()
 * 		-- isEmpty()
 * 		-- isFull()
 * 		-- size()
 * 		-- contains(E obj)
 * 		-- reverse()
 * 		-- IteratorHelper
 * 		-- iterator()
 * 
 */
package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.Comparable;

public class LinkedList<E> implements ListI<E> {	
	/**
	 * LinkList nested Node class which stores Node data and reference
	 *
	 * @param <E> : The element stored at this node.
	 */
	class Node<E> {
		E data;
		Node<E> next;

		public Node(E obj){
			data = obj;
			next = null;
		}
	}

	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;

	/**
	 * LinkedList constructor
	 */
	public LinkedList() {}

	/** addFirst Method: 
	 * Adds an object to the beginning of the list.
	 * 
	 * @param obj the object to be added to the list.
	 */
	@Override
	public void addFirst(E obj) {
		Node<E> tempNode =  new Node(obj);

		if(size == 0){
			head = tempNode;
			tail = head;
		}
		else{
			tempNode.next = head;
			head = tempNode;
		}

		size++;
	}

	/** addLast Method: 
	 * Add Node to the End of the LinkList
	 * @param obj: Node Element
	 */
	@Override
	public void addLast(E obj){
		Node<E> NewNode = new Node(obj);

		if(isEmpty()){
			head = NewNode;
			tail = head;
		} else 
			tail.next = NewNode;

		tail = NewNode;
		size++;
	}

	/** removeFirst Method:
	 * Removes the first Object in the list and returns it.
	 * Returns null if the list is empty.
	 * @return the object removed.
	 */
	@Override
	public E removeFirst(){
		if(isEmpty()){
			System.out.println("List is Empty!!!");
			return null;
		} else {
			E answer = head.data;
			head = head.next;
			size--;

			return answer;			
		}

	}

	/** removeLast Method:
	 * Removes the last Object in the list and returns it.
	 * Returns null if the list is empty.
	 * @return the object removed.
	 */
	@Override
	public E removeLast(){

		Node<E> TempNode = head; 

		if(isEmpty()){
			System.out.println("List is Empty!!!");
			return null;
		}
		else if(head == tail){
			E LastElem = tail.data;	
			head = null;
			tail = null;
			size--;

			return LastElem;
		}
		else {
			while(TempNode.next != tail)
				TempNode = TempNode.next;

			E LastElem = tail.data;
			tail = TempNode;
			tail.next = null;

			size--;

			return LastElem;
		}
	}

	/** peekFirst(0 Method:
	 * Peek at the First element of the LinkedList
	 * @see data_structures.ListI#peekFirst()
	 */
	@Override
	public E peekFirst(){
		if(isEmpty()) 
			return null;
		else
			return head.data;
	}

	/**
	 * Peek at the last element of the LinkedList
	 * @see data_structures.ListI#peekLast()
	 */
	@Override
	public E peekLast(){
		if(isEmpty()) 
			return null;
		else
			return tail.data;
	}

	/** 
	 * makeEmpty Method: Empty the LinkLIst
	 * @param : None
	 */
	public void makeEmpty(){
		head = null;
		tail = null;
		size = 0;

	}

	/** isEmpty method: 
	 * Return True if size==0
	 * @param : None
	 */
	public boolean isEmpty(){
		return size == 0;
	}

	/** isFull() Method:
	 * Test whether the list is full.
	 * @return true if the list is full, otherwise false
	 */
	@Override
	public boolean isFull(){
		// TODO Auto-generated method stub
		return false;
	}

	/** size() Method:
	 * Return Size of the LinkedList
	 * @see data_structures.ListI#size()
	 */
	@Override
	public int size(){
		return size;
	}

	/** contains Method: 
	 * Test whether the list contains an object. This will use the object's
	 * compareTo method to determine whether two objects are the same.
	 * 
	 * @param obj The object to look for in the list
	 * @return true if the object is found in the list, false if it is not found
	 */
	@Override
	public boolean contains(E obj){
		Node<E> TempNode = head;

		if(isEmpty()) 
			return false;
		else{
			while(TempNode != null){
				if((((Comparable<E>) TempNode.data).compareTo(obj)) == 0)
					return true;
				TempNode = TempNode.next;
			}
		}

		return false;		
	}

	/** reverse() Method:
	 * Reverse the order of the list.
	 * This will reverse the order of the list, so the first element is 
	 * last, and vice-versa.
	 */
	@Override
	public void reverse(){

		Node<E> Previous_PT = null;
		Node<E> Current_PT = head;
		Node<E> PT = Previous_PT;

		if(!isEmpty()){
			if(head.next != null){
				Current_PT = head.next;
				Previous_PT = head;
				PT = Previous_PT;
				Previous_PT.next = null;
			}

			while(Current_PT.next != null){
				Previous_PT = Current_PT;
				Current_PT = Current_PT.next;
				Previous_PT.next = PT;
				PT = Previous_PT;
			}

			tail.next = Previous_PT;

			PT = tail;
			tail = head;
			head = PT;

		} else
			System.out.println("list is Empty!!!");		
	}

	/** IteratorHelper Class:
	 * IteratorHelper class implements Iterator class,
	 * IteratorHelper class includes:
	 * -- IteratorHelper()
	 * -- hasNext()
	 * -- next()
	 * -- remove()
	 */
	public class IteratorHelper implements  Iterator<E> {
		Node<E> index;

		public IteratorHelper(){
			index = head;
		}
		public boolean hasNext(){
			return index != null; 
		}
		public E next(){ 
			if(!hasNext())
				throw new NoSuchElementException();

			E temp = index.data;
			index = index.next;
			return temp;	
		}
		public void remove(){
			throw new UnsupportedOperationException("Not enabled");
		}
	}
	/* (non-Javadoc)
	 * @see data_structures.ListI#iterator()
	 */
	@Override
	public Iterator<E> iterator(){
		return new IteratorHelper();
	}

}
