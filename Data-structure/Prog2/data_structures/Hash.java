package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Hash<K, V> implements HashI<K, V> {



	/**
	 * The Linked list from Assignment 1 accept a single data item. 
	 * In order to insert two data items: the key and the value, the  HashTableElement<K,V>
	 * wrapper class provides a solution to reuse Linked lists code from Ass. 1. 
	 * 
	 * @param <K>
	 * @param <V>
	 */
	private class HashTableElement<K,V> implements Comparable<HashTableElement<K,V>> {

		K key;
		V value;
		public HashTableElement(K key,V value){
			this.key = key;
			this.value = value;
		}
		public int compareTo(HashTableElement<K,V> node){
			return ((Comparable<K>) key).compareTo((K) node.key);
		}
	}
	
	LinkedList<HashTableElement<K,V>> [] array;
	int tableSize;
	int numElements;
	double maxLoadFactor;
	
	public Hash(int size){
		this.tableSize = size;
        numElements = 0;
		array = new LinkedList[tableSize];

		for(int i=0; i<tableSize; i++)
			array[i] = new LinkedList<HashTableElement<K,V>>();

		maxLoadFactor = 0.75;

	}

    /** add method:
     *  Adds the given key/value pair to the dictionary.  Returns
     *  false if the dictionary is full, or if the key is a duplicate.
     *  Returns true if addition succeeded.
     * @param key
     * @param value
     * @return
     */
	@Override
	public boolean add(K key, V value) {
		HashTableElement<K,V> HashElement = new HashTableElement(key,value);

        if(loadFactor() > maxLoadFactor){
            int newtableSize = tableSize * 2;
            resize(newtableSize);
        }


        if(array[getHashCode(key)].contain(new HashTableElement<K, V>(key,null)))
            return false;

        array[getHashCode(key)].add(HashElement);
		numElements++;

//        System.out.println("H-add: tableSize = "+tableSize);
//        System.out.println("     : numElements = "+numElements);
//        System.out.println("     : Key = "+HashElement.key);
//        System.out.println("     : Value = "+HashElement.value);
//        System.out.println("<---------------------------------->");

		return true;
	}

    /** remove(K key) Method
     *  Deletes the key/value pair identified by the key parameter.
     *  Returns true if the key/value pair was found and removed,
     *  otherwise false.
     * @param key
     * @return
     */
	@Override
	public boolean remove(K key) {
        V value;

//        if(array[getHashCode(key)].contain(new HashTableElement<K, V>(key,null)))
//            return false;

        if(array[getHashCode(key)] == null)
			return false;

		value = getValue(key);
        HashTableElement<K,V> HashElement = new HashTableElement(key,value);
        // System.out.println("Removing Key = "+key+" Value = "+value);
		numElements --;

		return array[getHashCode(key)].remove(HashElement);

	}

	/**
	 * Returns the value associated with the parameter key.  Returns
	 * null if the key is not found or the dictionary is empty.
	 * @param key
	 * @return
     */
	@Override
	public V getValue(K key) {
        HashTableElement<K,V> HashElement = new HashTableElement(key,null);

        HashTableElement<K,V> temp = array[getHashCode(key)].get(HashElement);

//        System.out.println("Temp = "+temp);

        if(temp == null) {
//            System.out.println("Value for Key = "+key+" does not Exist!!!");
            return null;
        }
		return temp.value;
	}

    public Integer getHashCode(K key){
        int hashcode = key.hashCode();
        hashcode = hashcode & 0x7fffffff;
        hashcode = hashcode % tableSize;
        return hashcode;
    }

    /** size() method
     * Returns the number of key/value pairs currently stored in the dictionary
     * @return
     */
	@Override
	public int size() {
		return numElements;
	}

	/**
	 * Returns true if the dictionary is empty
	 * @return
     */
	@Override
	public boolean isEmpty() {
		return numElements == 0;
	}

    /** loadFactor() method
     * Number of data entries / Number of buckets
     * Returns the current load factor of the dictionary (lambda)
     * @return
     */
	@Override
	public double loadFactor() {
		return (double) numElements/tableSize;
	}

	/**
	 * resizes the dictionary
	 * @param newSize
     */
	@Override
	public void resize(int newSize) {
        this.tableSize = newSize;
        LinkedList<HashTableElement<K,V>> [] NewArray = new LinkedList[newSize];
        LinkedList<HashTableElement<K,V>> list = null;
        HashTableElement<K,V> tempNode = null;
        int newNumElement = 0;

        for(int i=0; i<tableSize; i++)
            NewArray[i]= new LinkedList<HashTableElement<K, V>>();

        // System.out.println("---> inside of resize method Size = "+tableSize);

        for (int i=0; i<tableSize;i++){
            if(newNumElement == numElements)
                break;
            list = array[i];  // store LinkedList i
            while(!list.isEmpty()){
                // System.out.println("---> inside of resize method <--- ");
                tempNode = list.removeFirst();
                // System.out.println("size of list i "+i+" = "+list.size());
                // System.out.println("--- Key = "+tempNode.key);
                // System.out.println("--- value = "+tempNode.value);
                NewArray[getHashCode(tempNode.key)].add(tempNode);
                newNumElement++;
            }
        }

        array = NewArray;
		
	}

	/**
	 * Returns an Iterator of the keys in the dictionary, in ascending
	 * sorted order
	 * @return
     */
	@Override
	public Iterator<K> iterator() {
		return new IteratorHelper<K>();
	}

	/**
	 * Returns an Iterator of the keys in the dictionary, in ascending
	 * sorted order
	 * @return
     */
	@Override
	public Iterator<K> keys() {

		return new KeyIteratorHelper<K>();
	}
	/**
	 * Returns an Iterator of the values in the dictionary.  The
	 * order of the values must match the order of the keys.
	 * @return
     */
	@Override
	public Iterator<V> values() {

		return new ValueIteratorHelper<V>();
	}


	protected class IteratorHelper<E> implements Iterator<E>{

		private HashTableElement [] nodes;
		private int idx;
		private long modCheck;

		public IteratorHelper(){
            nodes = new HashTableElement[numElements];
			idx = 0;
			int j = 0;
            modCheck = numElements;
  //          System.out.println("+++ IteratorHelper idx = "+idx);
            for(int i=0; i<tableSize; i++)
				for(HashTableElement n : array[i])
					nodes[j++] = n;

		}

		public boolean hasNext() {
            if(modCheck != numElements)
                throw new ConcurrentModificationException();
            return idx < numElements;

        }
		
		public E next(){
			return (E) nodes[idx++].key;

        }
		
		public void remove() {
            throw new UnsupportedOperationException();
        }

	}
    protected class KeyIteratorHelper<K> implements Iterator<K> {
        private HashTableElement [] nodes;
        private int idx;
        // protected long modCheck;

        public KeyIteratorHelper(){
            nodes = new HashTableElement[numElements];
            idx = 0;
            int j = 0;
            // modCheck = numElements;
//            System.out.println("+++ KeyIteratorHelper idx = "+idx);
            for(int i=0; i<tableSize; i++)
                for(HashTableElement n : array[i])
                    nodes[j++] = n;

        }

        public boolean hasNext() {

            return idx < numElements;

        }

        public K next(){
            if(!hasNext())
                throw new NoSuchElementException();

  //          System.out.println(" ===> KeyIteratorHelper Next idx = "+idx);

            return (K) nodes[idx++].key;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

//        public KeyIteratorHelper(){
//            super();
//        }
//        public K next() {
//            return (K) nodes[idx++].key;
//        }
    }
    public class ValueIteratorHelper<V> extends IteratorHelper<V> {

        Iterator<K> iter;

        public ValueIteratorHelper(){
            iter = keys();
        }
        public boolean hasNext(){
            return iter.hasNext();
        }
        public V next() {
            return (V) getValue(iter.next());
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
//        public ValueIteratorHelper(){
//            super();
//        }
//        public V next(){
//            return (V) nodes[idx++].value;
//        }
    }



//    public  class quickSort<E>{
//        E [] n;
//        public E[] quickSort(E array[]){
//            E [] n = array;
//            quickSort(0,n.length-1);
//            return n;
//
//        }
//        private void quickSort(int left, int right){
//            if(right-left <= 0) return;
//            E pivot = n[right];
//            int partition = getPartition(left,right,pivot);
//            quickSort(left,partition-1);
//            quickSort(partition+1,right);
//        }
//        private int getPartition(int left, int right, E pivot){
//            int lPtr = left-1;
//            int rPtr = right;
//
//            for(;;){
//				while((((Comparable<E>) n[++lPtr]).compareTo(pivot)) < 0);
//                while ((rPtr >0) && ((((Comparable<E>) n[--lPtr]).compareTo(pivot)) > 0));
//                if(lPtr >= rPtr)
//                    break;
//                else
//                    swap(lPtr,rPtr);
//
//            }
//            swap(lPtr,right);
//            return lPtr;
//
//        }
//        private void swap(int one, int two){
//            E temp = n[one];
//            n[one] = n[two];
//            n[two] = temp;
//        }
//    }
}
