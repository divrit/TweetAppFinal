package com.application.tweetapp.tweet.service;

import com.application.tweetapp.tweet.document.Tweet;
import com.application.tweetapp.tweet.document.User;
import com.application.tweetapp.tweet.exception.RecordNotFoundException;
import com.application.tweetapp.tweet.repository.TweetRepository;
import com.application.tweetapp.tweet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TweetServiceImpl implements TweetService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    TweetRepository tweetRepository;

    @Override
    public Tweet postTweet(Tweet tweet, String loginid) {
        System.out.println("path variable loginid:"+loginid);
        System.out.println(("loginid Inside request body:"+ tweet.getUserLoginId()));
//        tweet.setpostedOn(java.time.LocalDate.now());
        tweet.setUserLoginId(loginid);
        Optional<User> user=userRepository.findById(tweet.getUserLoginId());
        if( tweet.getMessage().equals(null)){ //tweet.getUserLoginId().equals(null) ||
            System.out.println("Some details are  missing");
            throw new RecordNotFoundException("Provide all the details");
        }
        else if(user.isPresent()){
            System.out.println("User is valid,can post a tweet");
            Random random=new Random();
//            tweet.setTweetId(random.nextInt(50));
            Date date = new Date(System.currentTimeMillis());
            String value=date.toString();
            tweet.setPostedOn(value);
//            tweet.setLikes(likes);
           return tweetRepository.save(tweet);
            //return "Posted a tweet!!!";
        }
        else {
            throw new RecordNotFoundException("User is not valid:"+loginid + " Login with valid credentials");
        }

    }

    @Override
    public List<Tweet> getAllTweets() {
        return null;
    }

    @Override
    public String editTweet(Tweet tweet, Integer tweetid) {
        Tweet oldTweet=tweetRepository.findById(tweetid).get();
        oldTweet.setMessage(tweet.getMessage());
        Date date = new Date(System.currentTimeMillis());
        String value=date.toString();
        oldTweet.setPostedOn(value);
        oldTweet.setLikes(tweet.getLikes());
        tweetRepository.save(oldTweet);
        System.out.println(oldTweet.getLikes());
        return "Tweet Updated successfully";
    }

    @Override
    public List<Tweet> getUserTweets(String loginid) {
//       Optional<User> user= userRepository.findById(loginid);
//       if (user.isPresent()){
       return findByLoginId(loginid);

    }

    @Override
    public Tweet getTweetByTweetId(int tweetid) {

       Optional<Tweet> optionalTweet=tweetRepository.findById(tweetid);

         Tweet tweet = new Tweet(optionalTweet.get().getTweetId(),optionalTweet.get().getUserLoginId(),
                    optionalTweet.get().getMessage(),optionalTweet.get().getPostedOn(),optionalTweet.get().getLikes());
            //tweet.setTweetId(optionalTweet.get().getTweetId());
            return tweet;


    }

    private List<Tweet> findByLoginId(String loginid) {
        int index=0;
        List<Tweet> allTweets=tweetRepository.findAll();
        //index=allTweets.size();

        List<Tweet> userTweets = new ArrayList<Tweet>();
        for(Tweet tweet:allTweets){

            if (loginid.equals(allTweets.get(index).getUserLoginId())){
                System.out.println(loginid);
                System.out.println(allTweets.get(index).getUserLoginId());
                userTweets.add(allTweets.get(index));
                index=index+1;
                System.out.println("userTweets:"+ userTweets);
            }
            else {
                index=index+1;
            }
            //return userTweets;
        }
        return userTweets;
    }

	@Override
	public int updateLike(int tweetid) {
		// TODO Auto-generated method stub
		Tweet tweet=tweetRepository.findById(tweetid).get();
		int likes=tweet.getLikes();
		likes++;
		tweet.setLikes(likes);
		tweetRepository.save(tweet);
		return tweet.getLikes();
		
	}

//    @Override
//    public List<Tweet> getUserTweets(String loginid) {
//        Optional<User> user=userRepository.findById(loginid);
//        Optional<Tweet> allTweets=tweetRepository.findAllById(Iterable<loginid>);
//        if (user.isPresent()){
//            for(Tweet tweet:allTweets){
//
//            }
//        }
//
//    }
}