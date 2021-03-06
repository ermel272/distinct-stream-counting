package com.ermel272.algorithms;

/**
 * Class:       TwoByTwoFlajoletMartinAlgorithm.java
 *
 * Purpose:     Implements the Flagolet-Martin Algorithm for estimating
 *              the distinct number of elements in a data stream as described
 *              in Mining of Massive Data Sets by Leskovec, Rajaraman, & Ullman.
 *              Uses two groups of two hash functions for reporting distinct elements.
 *
 * Description: Maintains an array of maximumTailLengths of the binary representation
 *              of the hashes of a string. Can report the estimate of the number
 *              of distinct elements at any given moment via reportDistinctElements().
 *
 * @author Chris Ermel
 * @since 2016-11-26.
 */
public class TwoByTwoFlajoletMartinAlgorithm extends AbstractFlajoletMartinAlgorithm {

    @Override
    public double reportDistinctElements() {
        // Find averages of both group of maxTailLengths
        double[] averageValues = new double[2];
        averageValues[0] = ((double) maxTailLengths[0] + (double) maxTailLengths[1]) / 2.0;
        averageValues[1] = ((double) maxTailLengths[2] + (double) maxTailLengths[3]) / 2.0;

        // Return median of the averages raised to the power 2
        return Math.pow(2.0, computeMedian(averageValues));
    }
}
