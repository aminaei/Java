package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.Comparable;

public class LinkedList<E> implements Iterable<E> {


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

    /** add Method:
     * Adds an object to the beginning of the list.
     *
     * @param obj the object to be added to the list.
     */
    public void add(E obj){
        Node<E> tempNode =  new Node(obj);

//        System.out.println("llist: New node is being added = " +tempNode);

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
    public boolean remove(E obj){
        Node<E> previous = null;
        Node<E> current = head;

        while(current != null &&
            ((Comparable<E>)obj).compareTo(current.data) != 0){
            previous = current;
            current = current.next;
        }

        if(current == null)
            return false;          // List is Empty or Node not found

        // List is not Empty:
        if(current == head)        // find element in the 1st node, remove 1st node
            head = head.next;
        else if(current == tail) { // find element in the last node, remove last node
            previous.next = null;
            tail = previous;
        }else                      // remove one in middle
            previous.next = current.next;

        if(head == null)
            tail = null;
        size--;
        return true;

    }

    public E get(E obj){
        Node<E> current = head;

        while(current != null){
            if(((Comparable<E>)obj).compareTo(current.data) == 0){
                return current.data;
            }
            current = current.next;
        }

        // System.out.println("------> current.data = "+current.data);
        return null;

    }

    public boolean contain(E obj){
        Node<E> current = head;

        while(current != null){
            if(((Comparable<E>)obj).compareTo(current.data) == 0){
                return true;
            }
            current = current.next;
        }

        return false;

    }
    public int size(){
        return size;
    }
	/** removeFirst Method:
	 * Removes the first Object in the list and returns it.
	 * Returns null if the list is empty.
	 * @return the object removed.
	 */
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
	/** isEmpty method:
	 * Return True if size==0
	 * @param : None
	 */
	public boolean isEmpty(){
		return size == 0;
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

