package TestBench;

/**
 * Created by am on 4/6/16.
 */
import data_structures.BloomFilter;
import data_structures.Hash;
import data_structures.HashI;

import java.util.Iterator;


public class TB_Hash {
    static BloomFilter BFilter;
    static int size = 3;

    static Hash<String, Integer> hashTable = new Hash<String, Integer>(size);

    public static void main(String[] args) {
        System.out.println("Staring Hash Tester....");

        hashTable.add("one",1);
        hashTable.add("two",2);
        hashTable.add("tree",3);
        hashTable.add("four",4);
        hashTable.add("five",5);
        hashTable.add("six",6);

        Iterator<String> it = hashTable.keys();
        while(it.hasNext()) {
            String keys = it.next();
            System.out.println(keys+" ");
        }

        System.out.println("\n");
        hashTable.remove("four");
        hashTable.remove("tree");
        Iterator<String> it2 = hashTable.keys();
        while(it2.hasNext()) {
            String keys = it2.next();
            System.out.println(keys+" ");
        }


    }

    }
