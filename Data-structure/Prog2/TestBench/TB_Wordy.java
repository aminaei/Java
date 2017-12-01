package TestBench;

/**
 * Created by am on 4/6/16.
 */
import data.Wordy;
import data.WordyBloom;

public class TB_Wordy {

    public static void main(String[] args) {
        System.out.println("Staring Wordy Bloom Tester....");

        Wordy w1 = new Wordy("one");
        Wordy w2 = new Wordy("two");
        Wordy w3 = new Wordy("tree");

        System.out.println(" +++ hcode = "+w1.hashCode());
        System.out.println(" +++ hcode1 = "+w1.hashOne());
        System.out.println(" +++ hcode2 = "+w1.hashTwo());
        System.out.println(" +++ hcode3 = "+w1.hashThree());




    }

    }
