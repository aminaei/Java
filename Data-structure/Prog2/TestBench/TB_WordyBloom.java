package TestBench;

/**
 * Created by am on 4/6/16.
 */
import com.sun.org.apache.xpath.internal.SourceTree;
import data.Wordy;
import data.WordyBloom;
import data_structures.BloomFilter;

import java.util.Iterator;
import data_structures.*;

public class TB_WordyBloom {
    static WordyBloom WB_TB = new WordyBloom();


    public static void main(String[] args) {
        System.out.println("Staring Wordy Bloom Tester....");

        Wordy w1 = new Wordy("one");
        Wordy w2 = new Wordy("two");
        Wordy w3 = new Wordy("tree");


        WB_TB.addWordy(w1, 1);
        WB_TB.addWordy(w2, 2);
        WB_TB.addWordy(w3, 3);

        System.out.println("Has w1 = "+WB_TB.hasWordy(w1));
        System.out.println("Has w2 = "+WB_TB.hasWordy(w2));
        System.out.println("Has w3 = "+WB_TB.hasWordy(w3));
        System.out.println("w2 Value = "+ WB_TB.getCount(w2));
        System.out.println("Probability = "+WB_TB.probability());

    }

    }
