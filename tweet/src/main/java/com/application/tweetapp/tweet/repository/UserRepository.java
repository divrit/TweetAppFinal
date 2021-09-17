package com.application.tweetapp.tweet.repository;

import com.application.tweetapp.tweet.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
	
	public User findByLoginid(String name);
}
