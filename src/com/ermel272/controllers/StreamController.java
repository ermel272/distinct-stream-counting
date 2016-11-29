package com.ermel272.controllers;

import com.ermel272.algorithms.*;
import com.ermel272.util.TwitterStreamUtil;
import twitter4j.*;

/**
 * Class:       StreamController.java
 *
 * Purpose:
 *
 * Description:
 *
 * @author Chris Ermel
 * @since 2016-10-31.
 */
public class StreamController {

    private TwitterStream twitterStream;

    private DeterministicAlgorithm detAlg;
    private FlajoletMartinAlgorithm fmAlg;
    private AveragedFlajoletMartinAlgorithm aFmAlg;
    private FourByOneFlajoletMartinAlgorithm fourByOneFmAlg;
    private TwoByTwoFlajoletMartinAlgorithm twoByTwoFmAlg;

    private int tweetCount;

    public StreamController() {
        TwitterStreamUtil streamUtil = new TwitterStreamUtil();
        twitterStream = streamUtil.getTwitterStream();

        // Tweet counter for use in CSV format graphing
        tweetCount = 0;

        // Initialize algorithms
        detAlg = new DeterministicAlgorithm();
        fmAlg = new FlajoletMartinAlgorithm();
        aFmAlg = new AveragedFlajoletMartinAlgorithm(1000);
        fourByOneFmAlg = new FourByOneFlajoletMartinAlgorithm();
        twoByTwoFmAlg = new TwoByTwoFlajoletMartinAlgorithm();

        // Print out csv column header
        System.out.println("TweetCount,DistinctElements,FMAlg,AveragedFMAlg,FourByOneFMAlg,TwoByTwoFMAlg");

        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                tweetCount++;

                // Step 1: Grab twitter user name
                String userName = status.getUser().getScreenName();

                // Step 2: Process tweet name through each algorithm
                detAlg.processInput(userName);
                fmAlg.processInput(userName);
                aFmAlg.processInput(userName);
                fourByOneFmAlg.processInput(userName);
                twoByTwoFmAlg.processInput(userName);

                // Step 3: Output current status of algorithm estimations
                System.out.println(tweetCount + "," + detAlg.reportDistinctElements() + "," +
                        fmAlg.reportDistinctElements() + "," + aFmAlg.reportDistinctElements() + ","
                        + fourByOneFmAlg.reportDistinctElements() + "," + twoByTwoFmAlg.reportDistinctElements());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
            }

            @Override
            public void onStallWarning(StallWarning warning) {
            }

            @Override
            public void onException(Exception ex) {
            }
        };

        twitterStream.addListener(listener);
    }

    public void startSimulation() {
        twitterStream.sample();
    }
}
