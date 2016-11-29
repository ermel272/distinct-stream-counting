package com.ermel272.algorithms;

/**
 * Class:       FourByOneFlajoletMartinAlgorithm.java
 *
 * Purpose:     Implements the Flagolet-Martin Algorithm for estimating
 *              the distinct number of elements in a data stream as described
 *              in Mining of Massive Data Sets by Leskovec, Rajaraman, & Ullman.
 *              Uses four groups of one hash function for reporting distinct elements.
 *
 * Description: Maintains an array of maximumTailLengths of the binary representation
 *              of the hashes of a string. Can report the estimate of the number
 *              of distinct elements at any given moment via reportDistinctElements().
 *
 * @author Chris Ermel
 * @since 2016-11-26.
 */
public class FourByOneFlajoletMartinAlgorithm extends AbstractFlajoletMartinAlgorithm {

    @Override
    public double reportDistinctElements() {
        // As each group has one hash function, averages do not need to be taken
        double[] averageValues = new double[4];
        averageValues[0] = maxTailLengths[0];
        averageValues[1] = maxTailLengths[1];
        averageValues[2] = maxTailLengths[2];
        averageValues[3] = maxTailLengths[3];

        return Math.pow(2.0, computeMedian(averageValues));
    }
}
