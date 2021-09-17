package com.application.tweetapp.tweet.service;

import java.util.List;

import com.application.tweetapp.tweet.document.Reply;
import com.application.tweetapp.tweet.document.Tweet;

public interface ReplyService {

	Reply postReply(Reply reply, int tweetId);

	List<Reply> getRepliesByTweetId(int tweetId);

}
