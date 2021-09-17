package com.application.tweetapp.tweet.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.application.tweetapp.tweet.document.Reply;
import com.application.tweetapp.tweet.document.Tweet;


@Repository
public class DReplyRepository {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	
	public Reply postReply(Reply reply) {
		  dynamoDBMapper.save(reply);
		 return reply;
	}
	

	public void updateReply(String tweetId, Reply reply) {
		dynamoDBMapper.save(reply, new DynamoDBSaveExpression().withExpectedEntry("tweetId",
				new ExpectedAttributeValue(new AttributeValue().withS(tweetId))));
	}
	
	

	public Reply getRepliesByTweetId(String userName) {

		 HashMap<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
		 expressionAttributeValues.put(":v1", new AttributeValue().withS(userName));

		 DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
		.withFilterExpression("begins_with(tweetId,:v1)").withExpressionAttributeValues(expressionAttributeValues);

		 List<Reply> replies = dynamoDBMapper.scan(Reply.class, scanExpression);

		 return replies.get(0);

		 }
}
