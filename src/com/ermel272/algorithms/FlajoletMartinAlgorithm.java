package com.ermel272.algorithms;

/**
 * Class:       FlajoletMartinAlgorithm.java
 *
 * Purpose:     Implements the Flagolet-Martin Algorithm for estimating
 *              the distinct number of elements in a data stream as described
 *              in Mining of Massive Data Sets by Leskovec, Rajaraman, & Ullman.
 *
 * Description: Maintains a maximumTailLength of the binary representation
 *              of a hash of a string. Can report the estimate of the number
 *              of distinct elements at any given moment via reportDistinctElements().
 *
 * @author Chris Ermel
 * @since 2016-10-26.
 */
public class FlajoletMartinAlgorithm {

    // Defines the number of bits in a Java int
    private static final int MAX_BITS = 32;

    private int maxTailLength;

    /**
     * Initialize the maxTailLength to 0.
     */
    public FlajoletMartinAlgorithm() {
        maxTailLength = 0;
    }

    /**
     * Reports the estimate of the number of distinct elements
     * seen so far.
     *
     * @return
     *          2^maxTailLength
     */
    public int reportDistinctElements() {
        // Report 2^maxTailLength
        return (int) Math.pow(2.0, (double) maxTailLength);
    }

    /**
     * Processes the input string s to determine if its hash's
     * tailLength is greater than the current maxTailLength.
     *
     * Uses the Java string hashcode.
     *
     * @param s
     *          The input string to be hashed.
     */
    public void processInput(String s) {
        // Step 1: Hash string s into a 32 bit signed int
        int i = s.hashCode();

        // Step 2: Compute the tail length of i
        int tailLength = findTailLength(i);

        // Step 3: Replace the maxTailLength if tailLength is larger
        if (tailLength > maxTailLength) maxTailLength = tailLength;
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
}
