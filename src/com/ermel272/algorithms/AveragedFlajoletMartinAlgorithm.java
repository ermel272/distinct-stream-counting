package com.ermel272.algorithms;

import java.util.ArrayList;

/**
 * Class:       AveragedFlajoletMartinAlgorithm.java
 *
 * Purpose:     Implements an averaged version of the Flajolet-Martin
 *              Algorithm.
 *
 * Description: Defines a block size of stream elements within
 *              which the max tail length of each block is maintained.
 *              Computes the number of distinct stream elements as the
 *              average of the 2^maxTailLength's of each of the blocks.
 *
 * @author Chris Ermel
 * @since 2016-10-30.
 */
public class AveragedFlajoletMartinAlgorithm {

    // Defines the number of bits in a Java int
    private static final int MAX_BITS = 32;

    private ArrayList<Integer> maxTailLengths;
    private int maxBlockSize;
    private int currentBlockSize;
    private int blockIndex;

    public AveragedFlajoletMartinAlgorithm(final int maxBlockSize) {
        // Assume a maxBlockSize of at least 1
        this.maxBlockSize = maxBlockSize;
        this.blockIndex = 0;
        this.maxTailLengths = new ArrayList<>();
        this.maxTailLengths.add(blockIndex, 0);
        this.currentBlockSize = 0;
    }

    /**
     * Reports the estimate of the number of distinct elements
     * seen so far.
     *
     * @return
     *          The estimate of the number of distinct elements
     *          seen so far in the stream.
     */
    public double reportDistinctElements() {
        // Report the average of all 2^maxTailLengths
        int runningTotal = 0;

        // Accumulate the sum of the 2^maxTailLengths
        for (int i = 0; i < blockIndex + 1; i++) {
            runningTotal += 2 ^ maxTailLengths.get(i);
        }

        // Return the average of all 2^maxTailLengths
        return runningTotal / (blockIndex + 1);
    }

    /**
     * Processes the input string s to determine if its hash's
     * tailLength is greater than the current block's maxTailLength.
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

        // Step 3: Replace the currentBlockSize's maxTailLength if it is larger
        if (tailLength > maxTailLengths.get(blockIndex)) maxTailLengths.set(blockIndex, tailLength);

        // Step 4: Increment currentBlockSize up by 1
        currentBlockSize++;

        // Step 5: Shift blockIndex up one if we have reached the maxBlockSize
        if (currentBlockSize == maxBlockSize) {
            currentBlockSize = 0;
            blockIndex++;

            // Init maxTailLength at the blockIndex to 0
            maxTailLengths.add(blockIndex, 0);
        }
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
        // Convert i to Integer to use unsigned integer
        Integer i2 = i;

        // Compute number of trailing zeros
        int tailLength = Integer.numberOfTrailingZeros(i2);

        // If tailLength is 32, then report 0 as there is no LS1B
        if (tailLength == MAX_BITS) tailLength = 0;

        return tailLength;
    }
}
