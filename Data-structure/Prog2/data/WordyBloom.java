package data;

import data_structures.LinkedList;
import data_structures.Hash;
import data_structures.BloomFilter;

public class WordyBloom{
    private int hcode, hcode1, hcode2, hcode3;

    int bFilterSize = 1021;
    BloomFilter bloomFilter = new BloomFilter(bFilterSize);

    int hTableSize = 521;
    Hash<String, Integer> hashTable = new Hash<String, Integer>(hTableSize);

    int bfnumElement;

    /**
     * this will add a word to the Bloom filter and the hash
     *
     * @param w
     * @param observations
     */
    public void addWordy(Wordy w, int observations){
//        System.out.println("-- Bloom Filter Size = "+bFilterSize);
//        System.out.println("-- Hash Table Size = "+hTableSize);

        hcode = w.hashCode() % bFilterSize;
        hcode1 = w.hashOne() % bFilterSize;
        hcode2 = w.hashTwo() % bFilterSize;
        hcode3 = w.hashThree() % bFilterSize;

        System.out.println(" +++ hcode = "+hcode);
        System.out.println(" +++ hcode1 = "+hcode1);
        System.out.println(" +++ hcode2 = "+hcode2);
        System.out.println(" +++ hcode3 = "+hcode3);

        hashTable.add(w.word(),observations);
        bloomFilter.setBit(hcode);
        bloomFilter.setBit(hcode1);
        bloomFilter.setBit(hcode2);
        bloomFilter.setBit(hcode3);
        bfnumElement++;

        hashTable.add(w.word(),observations);

    }

    /**
     * this will return true if the Wordy Object is in the Bloom filter (and thus the hash)
     *
     * @param w
     * @return
     */
    public boolean hasWordy(Wordy w) {
        boolean bfHas;

        bfHas = bloomFilter.getBit(hcode)  &
                bloomFilter.getBit(hcode1) &
                bloomFilter.getBit(hcode2) &
                bloomFilter.getBit(hcode3);

        return bfHas;
    }

    /**
     * // this will get the number of times the Wordy object has been seen (i.e. the value associated with the key)
     * @param w
     * @return
     */
    public Integer getCount(Wordy w){
        int val = hashTable.getValue(w.word());
        return val;
    }

    /**
     * this will return the probability that the hasWordy() boolean is correct (i.e. that it is not a false positive).
     * @return
     */
    public double probability(){
        int numHF = 4;

        // Math.pow((1 - Math.exp(-numHF * (double) numElements / (double) size)), numHF);
        return Math.pow((1 - Math.exp(-numHF * (double) bfnumElement / (double) bFilterSize)), numHF);
        // return 0.0;

    }
    }

