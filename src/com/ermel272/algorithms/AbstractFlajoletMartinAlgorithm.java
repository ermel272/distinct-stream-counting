package com.ermel272.algorithms;

import com.ermel272.hashes.Fnv1aHash;
import com.ermel272.hashes.FnvHash;
import com.ermel272.hashes.MorinHash;

import java.util.Arrays;

/**
 * Class:       AbstractFlajoletMartinAlgorithm.java
 *
 * Purpose:     Implements the Flagolet-Martin Algorithm for estimating
 *              the distinct number of elements in a data stream as described
 *              in Mining of Massive Data Sets by Leskovec, Rajaraman, & Ullman.
 *
 * Description: Maintains an array of maximumTailLength's of the binary representations
 *              of the hashes of a string. Can report the estimate of the number
 *              of distinct elements at any given moment via reportDistinctElements().
 *
 * @author Chris Ermel
 * @since 2016-11-26.
 */
abstract class AbstractFlajoletMartinAlgorithm {
    // Defines the number of bits in a Java int
    private static final int MAX_BITS = 32;
    private static final int NUM_HASHES = 4;

    int[] maxTailLengths;

    /**
     * Initialize the maxTailLengths to 0.
     */
    AbstractFlajoletMartinAlgorithm() {
        maxTailLengths = new int[] {0, 0, 0, 0};
    }

    /**
     * Reports the estimate of the number of distinct elements
     * seen so far.
     *
     * @return
     *          The estimated number of distinct elements
     *          seen so far.
     */
    public abstract double reportDistinctElements();

    /**
     * Processes the input string s to determine if its hash's
     * tailLength is greater than the current maxTailLengths.
     *
     * Uses the Java string hashcode.
     *
     * @param s
     *          The input string to be hashed.
     */
    public void processInput(String s) {
        int[] hashes = new int[NUM_HASHES];
        int[] tailLengths = new int[NUM_HASHES];

        // Step 1: Hash string s into a 32 bit signed int for each hash function
        hashes[0] = s.hashCode();
        hashes[1] = Fnv1aHash.hash32(s.getBytes());
        hashes[2] = FnvHash.hash32(s);
        hashes[3] = MorinHash.hash32(toByteArray(s.getBytes()));

        // Step 2: Compute the tail length of each hash value
        for (int i = 0; i < NUM_HASHES; i++) {
            tailLengths[i] = findTailLength(hashes[i]);
        }

        // Step 3: Replace the maxTailLengths if the given tailLength is larger
        for (int i = 0; i < NUM_HASHES; i++) {
            if (tailLengths[i] > maxTailLengths[i]) maxTailLengths[i] = tailLengths[i];
        }
    }

    /**
     * Computes the median of an array of int.
     *
     * Obtained from: http://stackoverflow.com/questions/11955728/how-to-calculate-the-median-of-an-array
     *
     * @param intArray
     *          The array of integers we are finding the median of.
     *
     * @return
     *          The median value of intArray.
     */
    double computeMedian(int[] intArray) {
        // Verify sort doesn't change overarching maxTailLengths array
        Arrays.sort(intArray);

        double median;
        if (intArray.length % 2 == 0)
            median = ((double)intArray[intArray.length/2] + (double)intArray[intArray.length/2 - 1])/2;
        else
            median = (double) intArray[intArray.length/2];

        return median;
    }

    /**
     * Finds the binary tail length of a given signed integer i.
     * Converts the signed integer to an unsigned integer
     * so that the tail length is accurate.
     *
     * @param i
     *          The integer we are finding the tail length of.
     *
     * @return
     *          An integer representing the number of zeros
     *          following the LS1B of the integer i.
     */
    private int findTailLength(int i) {
        // Compute number of trailing zeros
        int tailLength = Integer.numberOfTrailingZeros(i);

        // If tailLength is 32, then report 0 as there is no LS1B
        if (tailLength == MAX_BITS) tailLength = 0;

        return tailLength;
    }

    /**
     * Converts an array of byte objects to an array
     * of {@link Byte} objects.
     *
     * Obtained from : http://stackoverflow.com/questions/12944377/how-to-convert-byte-to-byte-and-the-other-way-around
     *
     * @param bytesPrim
     *          The array of byte objects.
     *
     * @return
     *          An array of {@link Byte} objects.
     */
    private Byte[] toByteArray(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];
        Arrays.setAll(bytes, n -> bytesPrim[n]);
        return bytes;
    }
}
