package com.ermel272.algorithms;

/**
 * Class:       FlajoletMartinAlgorithm.java
 *
 * Purpose:     Implements the HyperLogLog Algorithm for estimating
 *              the distinct number of elements in a data stream as described
 *              in HyperLogLog: the analysis of a near-optimal cardinality estimation
 *              algorithm by Flajolet, Fusy, Gandouet, and Meunier.
 *
 * Description: Maintains m registers of the positions of the leftmost one bits
 *              of stream element hashes. Alpha value obtained from
 *              https://web.archive.org/web/20150323055945/http://research.neustar.biz/2012/10/25/sketch-of-the-day-hyperloglog-cornerstone-of-a-big-data-infrastructure/
 *
 * @author Chris Ermel
 * @since 2016-11-26.
 * @link http://algo.inria.fr/flajolet/Publications/FlFuGaMe07.pdf
 */
public class HyperLogLogAlgorithm {

    private static final int MAX_BITS = 32;

    private int b = 4;
    private int m = (int) Math.pow(2.0, (double) b);    // Computes m = 2^b
    private double alpha = 0.673;

    private int[] registers;

    public HyperLogLogAlgorithm() {
        // Initialize registers to negative infinity
        int negativeInfinity = -2147483648;
        registers = new int[m];

        for (int i = 0; i < registers.length; i++) {
            registers[i] = negativeInfinity;
        }
    }

    /**
     * Reports the estimate of the number of distinct elements
     * seen so far.
     *
     * @return
     *          alpha * m^2 * Z
     */
    public double reportDistinctElements() {
        // Compute Z value
        double Z = 0;

        for (int j = 0; j < m; j++) {
            Z += (1 / Math.pow(2.0, registers[j]));
        }

        Z = 1 / Z;

        // Compute the estimate of the cardinality of the stream
        return alpha * Math.pow(m, 2.0) * Z;
    }

    /**
     * Processes the input string s to determine if its hash's
     * position of the leftmost 1 value is greater than the previous
     * value stored at a register.
     *
     * Uses the Java string hashcode.
     *
     * @param s
     *          The input string to be hashed.
     */
    public void processInput(String s) {
        // Step 1: Hash string s into a 32 bit signed int
        int x = s.hashCode();

        // Step 2: Compute binary address j determined by the first 4 bits of x
        int j = 1 + (x >>> (MAX_BITS - b));

        // Step 3: Zero out the first b bits of the x value to obtain w
        int w = (x << b) >>> b;

        // Step 4: Compute the position of the leftmost one-bit of w
        int p = Integer.highestOneBit(w);

        // Step 5: Replace the register m[j] if p is greater
        if (p > registers[j]) registers[j] = p;
    }
}
