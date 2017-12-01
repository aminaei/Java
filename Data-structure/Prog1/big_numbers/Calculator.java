/**
 * The Calculator class will perform the operations on two large numbers. 
 * First, we will need to convert Strings to numbers, and then we will need to perform the desired operation.
 * 
 * The two numbers should be converted to BigNumber objects (for example, by the stringToBigNumber() method in that class), 
 * and then separate (non-public) methods in the Calculator class will implement addition (+), subtraction (-), 
 * multiplication (*), and division (/) of the two big numbers, depending on whatever the operator is as shown in parentheses. 
 * For example, if the operator is "+" then we will add number1 and number2 and return the result as a string. 
 * For the calculation you will need to use at least one other big number class to hold the result and then you can just return the toString() function from that.
 */
package big_numbers;
import data_structures.LinkedList;
import data_structures.ListI;

/** Calculator Class:
 * Calculator Class has Calculator constructor method and public calculate method
 *
 */
public class Calculator{
	public Calculator(){}

	public String Calculator (String number1, String operation, String number2){
		return  calculate(number1, operation, number2);

	}

	/** calculate Method 
	 * 
	 * @param number1 : String type input
	 * @param operation : String Type input:['+','-','*','/']
	 * @param number2 : String type input
	 * @return String result of calculator operation
	 * 
	 */
	public String calculate(String number1, String operation, String number2){
		String Result = null;
		//		System.out.println(number1+""+operation+""+number2+" = ");

		switch (operation){
			case "+" : Result = AddOP(number1,number2);
			break;
			case "-" : Result = SubtractionOP(number1,number2);
			break;
			case "*" : Result = MultiplicationOP(number1,number2);
			break;
			case "/" : Result = DivisionOP(number1,number2);
			break;
			default  : System.err.println("**** Invalid operation *****");
			break;
		}

		return Result;
	}  

	/** AddOP Method:
	 * 1. convert input number1 & number2 to lists of integers
	 * 2. Add last digits of list1 & list2 & carry
	 * 3. if sum is > 9 them subtract 10 from sum and add 1 to carry Else carry = 0
	 * 4. convert ADD result to string and return String Result.
	 * 
	 * @param number1 : String type large integer number input
	 * @param number2 : String type large integer number input
	 * @return : String type, Returns ADD operation results. 
	 */
	private String AddOP(String number1, String number2){
		//		System.out.println("-- Add --> Number1 = "+number1);
		//		System.out.println("-- Add --> Number2 = "+number2);

		BigNumber Numberlist1= new BigNumber();
		BigNumber Numberlist2= new BigNumber();
		BigNumber SumList = new BigNumber();

		Numberlist1.stringToBigNumber(number1);
		Numberlist2.stringToBigNumber(number2);

		int carry = 0;
		int sum = 0;


		// Check whether number1 or number2 or both are negative numbers.
		if((Numberlist1.Intlist.peekFirst()>=0)&&(Numberlist2.Intlist.peekFirst()==-1)){
			Numberlist2.getFirstDigit();
			number2 = Numberlist2.toString();
			return SubtractionOP(number1,number2);
		}else if((Numberlist1.Intlist.peekFirst()==-1)&&(Numberlist2.Intlist.peekFirst()>=0)){
			Numberlist1.getFirstDigit();
			number1 = Numberlist1.toString();
			return SubtractionOP(number2,number1);
		} else{
			// if both number are negative number set result negative
			if((Numberlist1.Intlist.peekFirst()==-1)&&(Numberlist2.Intlist.peekFirst()==-1)){
				Numberlist1.getFirstDigit();
				Numberlist2.getFirstDigit();
				SumList.setNegative(true);
			}
			int Numlist1_size = Numberlist1.length();
			int Numlist2_size = Numberlist1.length();
			int list_length = (Numlist1_size > Numlist2_size) ? Numlist1_size : Numlist2_size;

			for (int i =0; i<list_length;i++){
				if(Numberlist1.Intlist.isEmpty())
					sum = Numberlist2.getLastDigit() + carry;
				if(Numberlist2.Intlist.isEmpty())
					sum = Numberlist1.getLastDigit() + carry;
				else
					sum = Numberlist1.getLastDigit() + Numberlist2.getLastDigit() + carry;

				if((sum > 9)&&(i!=list_length-1)){
					sum -= 10;
					carry = 1;
				} else
					carry = 0;

				SumList.addFirstDigit(sum);
			}
			return SumList.toString();
		}
	}

	/** SubtractionOP Method:
	 * 1. convert input number1 & number2 to lists of integers
	 * 2. Check list length, if they are not same then add zeros to the front of shortest list.
	 * 3. Start from end of the lists, subtract last digit of list1 & list2 & borrow 
	 * 4. if subtraction result < 0, add 10 to the difference and 1 to borrow, Else borrow =0
	 * 5. Return subtraction result stored in DiffList as a String.
	 * 
	 * @param number1 : String type large integer number input
	 * @param number2 : String type large integer number input
	 * @return : String type, Returns Subtraction operation results. 
	 */
	private String SubtractionOP(String number1, String number2){
		//		System.out.println("-- Sub --> Number1 = "+number1);
		//		System.out.println("-- Sub --> Number2 = "+number2);

		BigNumber Numberlist1= new BigNumber();
		BigNumber Numberlist2= new BigNumber();
		BigNumber DiffList = new BigNumber();

		Numberlist1.stringToBigNumber(number1);
		Numberlist2.stringToBigNumber(number2);
		// Check whether number1 or number2 or both are negative numbers.
		if((Numberlist1.Intlist.peekFirst()>=0)&&(Numberlist2.Intlist.peekFirst()==-1)){
			Numberlist2.getFirstDigit();
			number2 = Numberlist2.toString();
			return AddOP(number1,number2);
		}else if((Numberlist1.Intlist.peekFirst()==-1)&&(Numberlist2.Intlist.peekFirst()>=0)){
			int tempInt = Numberlist2.getFirstDigit() * -1;
			Numberlist2.addFirstDigit(tempInt);
			number2 = Numberlist2.toString();
			return AddOP(number1,number2);
		} else{
			if((Numberlist1.Intlist.peekFirst()==-1)&&(Numberlist2.Intlist.peekFirst()==-1)){
				Numberlist1.getFirstDigit();
				Numberlist2.getFirstDigit();
				DiffList.setNegative(true);
			}

			int borrow = 0;
			int diff = 0;
			int l = 0;

			int Numlist1_size = Numberlist1.length();
			int Numlist2_size = Numberlist2.length();

			if(Numlist1_size > Numlist2_size){
				l = Numberlist1.length()-Numberlist2.length();
				for (int i=0;i<l;i++)
					Numberlist2.addFirstDigit(0);;
			} 
			if(Numlist1_size < Numlist2_size){
				l = Numberlist2.length()-Numberlist1.length();
				for (int i=0;i<l;i++)
					Numberlist1.addFirstDigit(0);
			}
			String Tempnum;
			String Num1,Num2;
			Num1 = Numberlist1.toString();
			Num2 = Numberlist2.toString();

			if(Num1.compareTo(Num2) < 0){
				// number2 is larger than number1
				DiffList.setNegative(true);
				Tempnum = Num1;
				Num1 = Num2;
				Num2 = Tempnum;
			}

			Numberlist1.stringToBigNumber(Num1);
			Numberlist2.stringToBigNumber(Num2);

			int lnum1 = Numberlist1.length();
			for (int i =0; i<lnum1;i++){
				diff = Numberlist1.getLastDigit() - Numberlist2.getLastDigit() -borrow;

				if(diff < 0){
					diff += 10;
					borrow = 1;
				} else
					borrow = 0;

				DiffList.addFirstDigit(diff);
			}

			if(CompareNum(number1,number2)!=0)
				while(DiffList.Intlist.peekFirst() == 0)
					DiffList.getFirstDigit();

			return DiffList.toString();
		}
	}

	/** DivisionOP Method:
	 * 1. convert input number1 & number2 to lists of integers
	 * 2. Subtract number2 list from number1 list until the difference < Number1
	 * 3. Return number of subtraction as quotient and difference as remainder. 
	 * 
	 * @param number1 : String type large integer number input
	 * @param number2 : String type large integer number input
	 * @return : String type, Returns Division operation results. 
	 */
	private String DivisionOP(String number1, String number2){
		BigNumber Numberlist1= new BigNumber();
		BigNumber Numberlist2= new BigNumber();
		BigNumber numList = new BigNumber();

		Numberlist1.stringToBigNumber(number1);
		Numberlist2.stringToBigNumber(number2);

		String sign="";
		if((Numberlist1.Intlist.peekFirst()>=0)&&(Numberlist2.Intlist.peekFirst()==-1)){
			sign = "-";
			Numberlist2.getFirstDigit();
			number2 = Numberlist2.toString();
		} else if((Numberlist1.Intlist.peekFirst()==-1)&&(Numberlist2.Intlist.peekFirst()>=0)){
			sign = "-";
			Numberlist1.getFirstDigit();
			number1 = Numberlist1.toString();
		} else if((Numberlist1.Intlist.peekFirst()==-1)&&(Numberlist2.Intlist.peekFirst()==-1)){
			sign = "";
			Numberlist1.getFirstDigit();
			number1 = Numberlist1.toString();
			Numberlist2.getFirstDigit();
			number2 = Numberlist2.toString();
		} else
			sign = "";

		String diff;
		int Q = 0;
		String Result;	

		if(CompareNum(number1,number2) <0){
			Result = "0R0";	
		} else{
			diff = number1;

			while(true){
				numList.Intlist.makeEmpty();
				diff = SubtractionOP(diff,number2);

				Q++;
				numList.stringToBigNumber(diff);

				if(CompareNum(diff,number2) <0)
					break;
			}

			Result = sign+String.valueOf(Q)+"R"+String.valueOf(diff);
		}
		return Result;
	}

	/** MultiplicationOP Method:
	 * 1. convert input number1 & number2 to lists of integers
	 * 2. Reverse number1 list
	 * 3. Multiply the last digit of number2 list through number1 list
	 * 4. Add results of multiplication to previous multiplication operation.  
	 * 5. Return multiplication operation result as String
	 * 
	 * @param number1 : String type large integer number input
	 * @param number2 : String type large integer number input
	 * @return : String type, Returns Division operation results. 
	 */
	private String MultiplicationOP(String number1, String number2){
		BigNumber Numberlist1= new BigNumber();
		BigNumber Numberlist2= new BigNumber();
		BigNumber MultiList = new BigNumber();
		BigNumber SumList = new BigNumber();

		Numberlist1.stringToBigNumber(number1);
		Numberlist2.stringToBigNumber(number2);

		if((Numberlist1.Intlist.peekFirst()>=0)&&(Numberlist2.Intlist.peekFirst()==-1)){
			Numberlist2.getFirstDigit();
			MultiList.setNegative(true);
		} else if((Numberlist1.Intlist.peekFirst()==-1)&&(Numberlist2.Intlist.peekFirst()>=0)){
			Numberlist1.getFirstDigit();
			MultiList.setNegative(true);
		} else if((Numberlist1.Intlist.peekFirst()==-1)&&(Numberlist2.Intlist.peekFirst()==-1)){
			Numberlist1.getFirstDigit();
			Numberlist2.getFirstDigit();
		} 

		Numberlist1.Intlist.reverse();

		int addzeros = 0;
		int lastdig = 0;
		String Sum = "0";
		int carry = 0;
		int Multiply =0;
		int Tnum =0;
		String addNum1 = "0";

		for(int i=0;i<Numberlist1.Intlist.size()-1;i++)
			Sum = Sum + "0";

		while(!Numberlist2.Intlist.isEmpty()){
			lastdig = Numberlist2.getLastDigit();
			addNum1 = "0";

			for(Integer i : Numberlist1.Intlist){
				Multiply = i * lastdig + carry;
				Tnum =  Multiply%10;
				carry = Multiply/10;
				SumList.addFirstDigit(Tnum);
			}
			addNum1 = SumList.toString();

			for(int i=0;i<addzeros;i++){
				Sum = "0" + Sum;
				addNum1 = addNum1 + "0";

			}

			addzeros++;

			Sum = AddOP(Sum,addNum1);

		}
		MultiList.stringToBigNumber(Sum);
		while(MultiList.Intlist.peekFirst() == 0)
			MultiList.getFirstDigit();



		return MultiList.toString();
	}

	/** CompareNum Method
	 * Compare 2 string type numbers
	 * 
	 * @param Num1 : Number1 String type
	 * @param Num2 : Number2 String type
	 * @return     : Integer[-1,0,1]
	 */
	private Integer CompareNum(String Num1, String Num2){
		int lNum1 = Num1.length();
		int lNum2 = Num2.length();
		int l =0;
		int R = 0;

		String Number1 = Num1;
		String Number2 = Num2;
		if(lNum1 > lNum2){
			l = lNum1 - lNum2;
			for (int i=0;i<l;i++)
				Number2 = "0" + Number2;
		} 
		if(lNum1 < lNum2){
			l = lNum2 - lNum1;
			for (int i=0;i<l;i++)
				Number1 = "0" + Number1;
		} 

		if(Number1.compareTo(Number2) < 0)
			R = -1;
		if(Number1.compareTo(Number2) == 0)
			R = 0;
		if(Number1.compareTo(Number2) > 0)
			R = 1;

		return R;
	}
}
