package com.ermel272.hashes;

/**
 * Class:       FnvHash.java
 *
 * Purpose:     Implements the FNV1 hashing algorithm for 32 bit hashing
 *              described here: http://www.isthe.com/chongo/tech/comp/fnv/index.html.
 *
 * Description: Implementation of FNV hash by Makoto YUI (yuin405+xbird@gmail.com),
 *              modified slightly to swap order of xor and multiplication to make this
 *              an FNV1 hashing algorithm.
 *
 * @author Makoto YUI & Chris Ermel
 * @since 2016-11-26.
 * @link http://www.java2s.com/Code/Java/Development-Class/FNVHash.htm
 */
public class FnvHash {

    private static final int FNV_32_INIT = 0x811c9dc5;
    private static final int FNV_32_PRIME = 0x01000193;

    public static int hash32(final String k) {
        int rv = FNV_32_INIT;
        final int len = k.length();
        for(int i = 0; i < len; i++) {
            // Swapped order of xor and multiplication
            rv *= FNV_32_PRIME;
            rv ^= k.charAt(i);
        }
        return rv;
    }
}
