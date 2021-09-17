package com.application.tweetapp.tweet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.tweetapp.tweet.document.Reply;

public interface ReplyRepository extends MongoRepository<Reply, Integer> {

}
