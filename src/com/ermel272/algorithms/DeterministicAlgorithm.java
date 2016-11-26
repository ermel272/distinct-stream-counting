package com.ermel272.algorithms;

import java.util.HashSet;

/**
 * Class:       DeterministicAlgorithm.java
 *
 * Purpose:     Implements a deterministic algorithm for finding
 *              the exact number of distinct elements in a stream.
 *
 * Description: Maintains a HashSet of strings (Twitter Usernames).
 *              Reports the size of the set as the number of distinct
 *              stream elements.
 *
 * @author Chris Ermel
 * @since 2016-10-31.
 */
public class DeterministicAlgorithm {

    private HashSet<String> usernameSet;

    public DeterministicAlgorithm() {
        // Initialize the set of distinct user names
        usernameSet = new HashSet<>();
    }

    public int reportDistinctElements() {
        // Return the current size of the HashSet
        return usernameSet.size();
    }

    public void processInput(String s) {
        // Add the string the the HashSet
        usernameSet.add(s);
    }
}
