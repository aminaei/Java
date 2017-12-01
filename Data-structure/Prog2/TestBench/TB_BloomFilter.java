package TestBench;

/**
 * Created by am on 4/6/16.
 */
import data_structures.HashI;
import data_structures.LinkedList;
import data_structures.Hash;
import data_structures.BloomFilter;

public class TB_BloomFilter {
    static BloomFilter BFilter;

    public static void main(String[] args) {
        System.out.println("Staring Bloom Filter Tester....");
        int size = 19;
        BFilter = new BloomFilter(size);

        for (int i=0;i<size;i++)
            System.out.println("--- Value of BFilter index ["+i+"] = "+BFilter.get(i)+" --- ");

        System.out.println("");

//        for(int i=2;i<size+2;i=i+2)
//            BFilter.setBit(i);
//
//        System.out.println("");

        for (int i=0;i<size;i++)
            System.out.println("--- Value of BFilter index ["+i+"] = "+BFilter.get(i)+" --- ");

    }

    }
