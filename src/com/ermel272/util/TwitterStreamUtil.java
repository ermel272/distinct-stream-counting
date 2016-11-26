package com.ermel272.util;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamUtil {

    private TwitterStreamFactory streamFactory;

    public TwitterStreamUtil() {
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("jsp03UWUtD5I3TC1kBs9bAxbB")
                .setOAuthConsumerSecret("Tx26S3LwTUKIYVnCqlDxMc3nAaY0fdQ51HgQuUL5rtSPS8CulR")
                .setOAuthAccessToken("73658445-B80R4NM5579rfSH6tGDfy19fZb23c9RsP6Y1nHKMY")
                .setOAuthAccessTokenSecret("f7qJYzE72C9Mf582BCoVLV4o1Pwic87uhyGtmgcslbxMG");

        streamFactory = new TwitterStreamFactory(cb.build());
    }

    public TwitterStream getTwitterStream() {
        return streamFactory.getInstance();
    }
}
