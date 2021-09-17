package com.application.tweetapp.tweet.controller;

import com.application.tweetapp.tweet.document.Tweet;
import com.application.tweetapp.tweet.repository.DTweetRepository;
import com.application.tweetapp.tweet.repository.TweetRepository;
import com.application.tweetapp.tweet.service.TweetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value="Tweet Management", description="Operations related to Tweet")
@RestController
@RequestMapping("/api/v1/tweet")
//@CrossOrigin(origins = "*",allowedHeaders = "*")\
@CrossOrigin(origins = "*")
public class TweetController {

    @Autowired
    TweetRepository tweetRepository;
    
    @Autowired
    DTweetRepository DtweetRepository;
    
    

    @Autowired
    TweetService tweetService;

    @PostMapping("/postTweet")
    @ApiOperation(value="Posts a new Tweet",response=Tweet.class)
    public Tweet postTweet(@RequestBody Tweet tweet){
    	return DtweetRepository.postTweet(tweet);

    }
    

    @GetMapping("/{loginid}")
    @ApiOperation(value="Get User tweets based on loginId",response=List.class)
    public List<Tweet> getUserTweets(@PathVariable String loginid){

        return DtweetRepository.searchUserByLogin(loginid);
    }

    @GetMapping("/getAllTweets")
    @ApiOperation(value="Get all Tweets",response=List.class)
    public List<Tweet> getAllTweets(){
        return DtweetRepository.getAllTweets();
    }


    @DeleteMapping("/deleteTweet/{tweetid}")
    @ApiOperation(value="Delete a tweet based on tweetid",response=String.class)
    public String deleteTweet(@PathVariable String tweetid){
    	DtweetRepository.deleteTweet(tweetid);
        return "tweet deleted successfully!!";
    }

    @PutMapping("/editTweet/{tweetid}")
    @ApiOperation(value="Edits already existed tweet",response=String.class)
    public String editTweet(@PathVariable String tweetid,@RequestBody Tweet tweet){
    	DtweetRepository.updateTweet(tweetid,tweet);
        return "tweet updated successfully..!!";
    }

    @GetMapping("/getTweet/{tweetid}")
    @ApiOperation(value="Gets Tweet details by tweetId",response=Tweet.class)
    public Tweet getTweetByTweetId(@PathVariable String tweetid){
        return DtweetRepository.getTweetByID(tweetid);
    }
    
    @PutMapping("/like/{tweetid}")
    @ApiOperation(value="Get Likes",response=Integer.class)
    public int updateLike(@PathVariable String tweetid) {
    	return DtweetRepository.updateLikes(tweetid);
    }
}
