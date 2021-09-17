package com.application.tweetapp.tweet.repository;

import com.application.tweetapp.tweet.document.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TweetRepository extends MongoRepository<Tweet,Integer> {
//    void findTweetsByUserLoginId();
}
