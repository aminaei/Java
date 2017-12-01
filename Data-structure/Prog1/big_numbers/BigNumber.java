package big_numbers;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.Comparable;

import data_structures.LinkedList;
import data_structures.ListI;

/**
 *
 * BigNumber class is a wrapper class which handle numbers.
 * BigNumber Methods:
 *	public Integer  getFirstDigit()
 *	public Integer  getLastDigit()
 *	public void  addFirstDigit(Integer num )
 *	public void  addLastDigit(Integer num )
 *	public void setNegative(boolean b) - set whether this is a positive or negative number
 *	public boolean getNegative() - get whether this number is negative
 *	public int length() - this method will return the length of the number
 *	public void stringToBigNumber(String s) - this method will accept a string of arbitrary length and add store it in the linked list
 *	public String toString() - this method will return the string representation of the number (i.e. 73691 for the above example).
 *
 */

public class BigNumber extends LinkedList {
	LinkedList<Integer> Intlist;
	int NumSign = 1;

	/** BigNumber() constructor: Implements a linkedlist type Integer.
	 * 
	 */
	public BigNumber(){
		Intlist = new LinkedList<Integer>();
	}

	/** getFirstDigit() Method:
	 *  This method removes and returns the first element of the list 
	 * @return first element of the list
	 */
	public Integer getFirstDigit(){
		return Intlist.removeFirst();  
	}

	/** getLastDigit() Method:
	 *  This method removes and returns the last element of the list 
	 * @return last element of the list
	 */
	public Integer getLastDigit(){ 
		return Intlist.removeLast(); 
	}

	/** addFirstDigit(Integer num) Method:
	 * Add digits to beginning of the list
	 * 
	 * @param Integer Number num to be added to beginning of the list.
	 */
	public void addFirstDigit(Integer num ){ 
		Intlist.addFirst(num);

	}

	/** addLastDigit(Integer num) Method:
	 * Add digits to the End of the list
	 * 
	 * @param Integer Number num to be added to end of the list.
	 */
	public  void  addLastDigit(Integer num ){
		Intlist.addLast(num);
	}

	/** setNegative(boolean b)
	 * set whether this is a positive or negative number
	 * Set NumSign = -1 if number is negative Else NumSign = +1
	 * @param b: boolean type, if list id negative
	 */
	public void setNegative(boolean b){
		NumSign = b ? -1 : 1;
	}

	/** getNegative()
	 * get whether this number is negative:
	 * Return true (Negative) if NumSign = -1 Else False (positive)
	 * @return boolean type, if list is negative
	 */	
	public boolean getNegative(){
		return (NumSign<0); 
	}

	/** length() Method:
	 * this method will return the length of the number
	 * @return : integer, size of the list
	 */
	public int length() {
		return Intlist.size(); 
	}

	/** stringToBigNumber(String s) Method
	 * this method will accept a string of arbitrary length and add store it in the linked list
	 * @param s: String type
	 */
	public void stringToBigNumber(String s){

		for(int i=0; i<s.length(); i++){
			int x = Character.getNumericValue(s.charAt(i));
			addLastDigit(x);
		}

	}

	/** toString() Method:
	 * this method will return the string representation of the number (i.e. 73691 for the above example).
	 * @return String type
	 */
	public String toString(){
		String Result;

		if(!Intlist.isEmpty()){
			if(getNegative())
				Result = String.valueOf(-1*getFirstDigit());
			else
				Result = String.valueOf(getFirstDigit());

			while(!Intlist.isEmpty())
				Result = Result + String.valueOf(getFirstDigit());
			return Result;
		} else
			return null;
	} 

}
