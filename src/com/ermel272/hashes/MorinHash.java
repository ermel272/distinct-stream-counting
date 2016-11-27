package com.ermel272.hashes;

/**
 * Class:       MorinHash.java
 *
 * Purpose:     Implements the hashing algorithm written by Pat Morin
 *              described in his textbook, 'Open Data Structures'.
 *
 * Description: Implementation of hash function by Pat Morin,
 *              modified slightly to account for string data type.
 *
 * @author Pat Morin & Chris Ermel
 * @since 2016-11-26.
 * @link http://opendatastructures.org/ods-java/5_3_Hash_Codes.html
 */
public class MorinHash {

    public static int hash32(final Byte[] x) {
        long p = (1L<<32)-5;   // prime: 2^32 - 5
        long z = 0x64b6055aL;  // 32 bits from random.org
        int z2 = 0x5067d19d;   // random odd 32 bit number
        long s = 0;
        long zi = 1;
        for (Byte aX : x) {
            // reduce to 31 bits
            long xi = (aX.hashCode() * z2) >>> 1;
            s = (s + zi * xi) % p;
            zi = (zi * z) % p;
        }
        s = (s + zi * (p-1)) % p;
        return (int)s;
    }
}
