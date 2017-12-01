package TestBench;

/**
 * Created by am on 3/30/16.
 */
import data_structures.HashI;
import data_structures.LinkedList;
import data_structures.Hash;
import data_structures.BloomFilter;




public class Tester1 {
    static HashI<String, Integer> htable = null;


    public static void main(String[] args) {
        System.out.println("Staring Assignment 2....");
        String str1 = "hello";


        htable = new Hash<String, Integer>(7);

        htable.add("A",1);
        htable.add("B",2);
        htable.add("C",3);
        htable.add("D",4);
        htable.add("E",5);

        System.out.println("Get Values from HashTable ....");
        System.out.println("Get Value for Key = A : Value = "+htable.getValue("A"));
        System.out.println("Get Value for Key = B : Value = "+htable.getValue("B"));
        System.out.println("Get Value for Key = C : Value = "+htable.getValue("C"));
        System.out.println("Get Value for Key = D : Value = "+htable.getValue("D"));
        System.out.println("Get Value for Key = E : Value = "+htable.getValue("E"));

        System.out.println("");
        htable.remove("C");
        System.out.println();
        System.out.println("Get Value for Key = C : Value = "+htable.getValue("C"));









    }
}
