package com.ermel272.hashes;

/**
 * Class:       Fnv1aHash.java
 *
 * Purpose:     Implements the FNV1a hashing algorithm for 32 bit hashing
 *              described here: http://www.isthe.com/chongo/tech/comp/fnv/index.html.
 *
 * Description: Implementation of FNV1a hash by Prasanth Jayachandran.
 *
 * @author Prasanth Jayachandran
 * @since 2016-11-26.
 * @link https://github.com/prasanthj/hasher/blob/master/src/main/java/hasher/FNV1a.java
 */
public class Fnv1aHash {

    private static final int FNV1_32_INIT = 0x811c9dc5;
    private static final int FNV1_PRIME_32 = 16777619;

    public static int hash32(byte[] data) {
        return hash32(data, data.length);
    }

    private static int hash32(byte[] data, int length) {
        int hash = FNV1_32_INIT;
        for (int i = 0; i < length; i++) {
            hash ^= (data[i] & 0xff);
            hash *= FNV1_PRIME_32;
        }

        return hash;
    }
}
