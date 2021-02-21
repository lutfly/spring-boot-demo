package com.lutfly.guava.demo;


import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.BitSet;

/**
 *
 *
 * @author lutong
 * @since 2020/10/16
 */
public class BitArithmetic {

    public static void main(String[] args) {
        bloomFilter();
    }

    private static void bitSet(){
        BitSet bitSet = new BitSet(70);
        bitSet.set(2);
        bitSet.set(8);
        bitSet.set(11);
        bitSet.set(39);
        System.out.println(bitSet);
    }


    private static void bloomFilter(){
        BloomFilter<byte[]> bloomFilter = BloomFilter.create(Funnels.byteArrayFunnel(), 10000);
    }

}
