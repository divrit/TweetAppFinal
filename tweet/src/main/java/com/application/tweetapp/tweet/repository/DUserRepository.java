package com.application.tweetapp.tweet.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.application.tweetapp.tweet.document.Tweet;
import com.application.tweetapp.tweet.document.User;

@Repository
public class DUserRepository {
	
	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	public void newUserRegistration(User user) {
		dynamoDBMapper.save(user);
	}
	
	public User searchUserByLogin(String userName) {

		 HashMap<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
		 expressionAttributeValues.put(":v1", new AttributeValue().withS(userName));

		 DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
		.withFilterExpression("begins_with(loginid,:v1)").withExpressionAttributeValues(expressionAttributeValues);

		 List<User> users  = dynamoDBMapper.scan(User.class, scanExpression);
		
		return  users.get(0);
		 

		 }

	public User findByEmail(String email) {
		User u=dynamoDBMapper.load(User.class, email);
		System.out.println(u);
		return dynamoDBMapper.load(User.class, email);
	}

	public void updatePassword(String username, User user) {
		dynamoDBMapper.save(user, new DynamoDBSaveExpression().withExpectedEntry("loginId",
				new ExpectedAttributeValue(new AttributeValue().withS(username))));
	}

	public List<User> searchUserByName(String userName) {

		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(userName));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("begins_with(loginId,:v1)").withExpressionAttributeValues(eav);

		List<User> users = dynamoDBMapper.scan(User.class, scanExpression);

		return users;

	}

	public List<User> getAllUser() {

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<User> iList = dynamoDBMapper.scan(User.class, scanExpression);
		return iList;
	}

}