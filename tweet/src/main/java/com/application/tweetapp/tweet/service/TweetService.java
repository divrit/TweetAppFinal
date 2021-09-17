package com.application.tweetapp.tweet.service;

import com.application.tweetapp.tweet.document.Tweet;

import java.util.List;

public interface TweetService {
    Tweet postTweet(Tweet tweet, String loginid);

    List<Tweet> getAllTweets();

    String editTweet(Tweet tweet,Integer tweetid);

    List<Tweet> getUserTweets(String loginid);

    Tweet getTweetByTweetId(int tweetid);

	int updateLike(int tweetid);
}
