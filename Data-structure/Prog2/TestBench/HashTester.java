package TestBench;


    import java.util.Iterator;
    import java.util.Random;

    import data_structures.Hash;
    import data_structures.HashI;

/**
 * @author Rob Edwards
 *
 */
public class HashTester {

    public static void main(String args[]) {
        /**
         * To test the hash we need to add a key and a value
         * the hash should have three constructors:
         * a default, a tableSize, and a loadFactor
         * We will test each of these.
         *
         *
         */

        /**
         * First, create a hash, add keys and values
         */

        System.out.println("Testing the hash with default constructors");
        int maxN=1000;
        HashI<Integer, Integer> mh = new Hash<Integer, Integer>(maxN);

        for (int i=0;i<maxN;i++) {
            int j = maxN-i;
            mh.add(i, j);
        }

        int size = mh.size();
        if (size != maxN)
            System.err.println("Size is " + size + " but should be " + maxN);

        // test the contains method using five random numbers
        Random randomNumber = new Random();
        for (int i=0; i<5; i++) {
            int lookFor = randomNumber.nextInt(maxN);
//            if (mh.getValue(lookFor)) {
//                System.err.println("The hash does not contain " + lookFor + ", but it should!");
//            }
        }

        // test getting the values for all keys
        for (int i=0; i<5; i++) {
            int lookFor = randomNumber.nextInt(maxN);
            int val = mh.getValue(lookFor);
            if ((val + lookFor) != maxN)
                System.err.println(lookFor + " returned " + val + " but we were expecting " + (maxN-lookFor));
        }

        // test remove
        for (int i=maxN-1; i>=10; i--) {
            boolean removed = mh.remove(i);
//            if ((i + removed) != maxN)
//                System.err.println("Removed " + removed + " for key " + i + " but expected " + (maxN - i));
        }

        // size
        if (mh.size() != 10)
            System.err.println("Size is " + mh.size() + " but it should be 10");

//        Object[] keys =  mh.keys();
//        if (keys.length != 10)
//            System.err.println("We got " + keys.length + " keys, but should have 10 keys");
//
//        for (Object j : keys) {
//            Integer i = (Integer) j;
//            if (i > 10)
//                System.err.println("Got a key " + i + " but it should be less than 10");
//        }


        // now reset and test size/resize
//        mh = new Hash<Integer, Integer>(100, 1);
//
//        // add 100 things
//        for (int i=0;i<75;i++)
//            mh.add(i, i);
//
//        System.out.print("Size: " + mh.size() + " Load factor: " + mh.getLoadFactor() + ". After adding a couple of things ");
//
//        for (int i=100;i<120;i++)
//            mh.add(i, i);
//
//        System.out.println("Size: " + mh.size() + " Load factor: " + mh.getLoadFactor());
//
//        // one more time!
//        mh = new Hash<Integer, Integer>(100);
//
//        // add 100 things
//        for (int i=0;i<75;i++)
//            mh.add(i, i);
//
//        System.out.print("Size: " + mh.size() + " Load factor: " + mh.getLoadFactor() + ". After adding a couple of things ");
//
//        for (int i=100;i<120;i++)
//            mh.add(i, i);
//
//        System.out.println("Size: " + mh.size() + " Load factor: " + mh.getLoadFactor());
//
//        System.out.println("Testing done");
    }
}