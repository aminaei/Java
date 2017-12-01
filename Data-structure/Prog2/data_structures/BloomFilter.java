package data_structures;

import java.util.BitSet;

public class BloomFilter {
    private BitSet bitSet;
    private int bitSetSize =0;

    /** BloomFilter constructor
     * where size is the length of the BloomFilter
     *
     * @param size
     */
    public BloomFilter(int size){
        System.out.println("***** BloomFilter Size = "+size+" ***** ");
        bitSetSize = size;
        bitSet = new BitSet(bitSetSize);

    }

    // That set and get the bit index refered to by the int.
    // Note that bitIndex is only guaranteed to be an int, and therefore may be larger than the size of the array.
    // You will need to figure out how to handle that.
    public void setBit(int bitIndex){
        if(bitIndex >= bitSetSize)
            throw new IllegalArgumentException("Illegal Bloom filter Size ");

        set(bitIndex);
    }
    public boolean getBit(int bitIndex){

        if(bitIndex >= bitSetSize)
            throw new IllegalArgumentException("Illegal Bloom filter Size ");

        return get(bitIndex);
    }

    /**
     * Sets the bit at the specified index to true
     * @param bitIndex
     */
    public void set(int bitIndex){
        bitSet.set(bitIndex,true);
    }

    /**
     * Returns the value of the bit with the specified index.
     *
     * @param bitIndex
     * @return
     */
    public boolean get(int bitIndex){

        return bitSet.get(bitIndex);
    }

    /**
     * Sets all bits to false in the Bloom filter.
     */
    public void clear() {
        bitSet.clear();
    }

}
