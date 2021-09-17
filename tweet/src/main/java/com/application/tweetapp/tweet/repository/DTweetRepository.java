package com.application.tweetapp.tweet.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.application.tweetapp.tweet.document.Tweet;
import com.application.tweetapp.tweet.document.User;

@Repository
public class DTweetRepository{
	
	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	public Tweet postTweet(Tweet tweet) {
		  dynamoDBMapper.save(tweet);
		 return tweet;
	}
	

	public void updateTweet(String tweetId, Tweet tweet) {
		dynamoDBMapper.save(tweet, new DynamoDBSaveExpression().withExpectedEntry("tweetId",
				new ExpectedAttributeValue(new AttributeValue().withS(tweetId))));
	}
	
	public int updateLikes(String tweetId) {
		Tweet tw=dynamoDBMapper.load(Tweet.class, tweetId);
		int likes=tw.getLikes();
		tw.setLikes(++likes);
		dynamoDBMapper.save(tw, new DynamoDBSaveExpression().withExpectedEntry("tweetId",
				new ExpectedAttributeValue(new AttributeValue().withS(tweetId))));
		return tw.getLikes();
	}

	public List<Tweet> searchUserByLogin(String userName) {

		 HashMap<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
		 expressionAttributeValues.put(":v1", new AttributeValue().withS(userName));

		 DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
		.withFilterExpression("begins_with(userLoginId,:v1)").withExpressionAttributeValues(expressionAttributeValues);

		 List<Tweet> users = dynamoDBMapper.scan(Tweet.class, scanExpression);

		 return users;

		 }

	
	public String deleteTweet(String tweetId) {
		Tweet tw=dynamoDBMapper.load(Tweet.class, tweetId);
		dynamoDBMapper.delete(tw);
		return "Tweet Deleted!!!";
	}
	
	public Tweet getTweetByID(String tweetId) {
		Tweet tw=dynamoDBMapper.load(Tweet.class, tweetId);
		return tw;
	}

	public List<Tweet> getAllTweets() {

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Tweet> iList = dynamoDBMapper.scan(Tweet.class, scanExpression);
		return iList;
	}

}