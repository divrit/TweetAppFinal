package com.application.tweetapp.tweet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.tweetapp.tweet.document.Reply;
import com.application.tweetapp.tweet.document.Tweet;
import com.application.tweetapp.tweet.document.User;
import com.application.tweetapp.tweet.exception.RecordNotFoundException;
import com.application.tweetapp.tweet.repository.ReplyRepository;
import com.application.tweetapp.tweet.repository.UserRepository;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	ReplyRepository replyRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Reply postReply(Reply reply, int tweetId) {
		// TODO Auto-generated method stub
		System.out.println("path variable tweetid:"+tweetId);
        System.out.println(("tweetId Inside request body:"+ reply.getTweetId()));
//        tweet.setpostedOn(java.time.LocalDate.now());
//        reply.setTweetId(tweetId);
        Optional<User> user=userRepository.findById(reply.getUserLoginId());
        if(reply.getComment().equals(null)){
            System.out.println("Some details are  missing");
            throw new RecordNotFoundException("Provide all the details");
        }
        else if(user.isPresent()){
            System.out.println("User is valid,can reply to a tweet");
            Random random=new Random();
//            reply.setReplyId(random.nextInt(50));
            Date date = new Date(System.currentTimeMillis());
            String value=date.toString();
            reply.setRepliedOn(value);
//            tweet.setLikes(likes);
           return replyRepository.save(reply);
            //return "Posted a tweet!!!";
        }
        else {
            throw new RecordNotFoundException("User is not valid:"+reply.getUserLoginId() + " Login with valid credentials");
        }
	}

	@Override
	public List<Reply> getRepliesByTweetId(int tweetId) {
		// TODO Auto-generated method stub
		int index=0;
        List<Reply> allReplies=replyRepository.findAll();
        //index=allTweets.size();

        List<Reply> userReplies = new ArrayList<Reply>();
        for(Reply reply:allReplies){

//            if (tweetId == allReplies.get(index).getTweetId()) {
//                System.out.println(tweetId);
//                System.out.println(allReplies.get(index).getTweetId());
//                userReplies.add(allReplies.get(index));
//                index=index+1;
//                System.out.println("userReplies:"+ userReplies);
//            }
//            else {
//                index=index+1;
//            }
            //return userTweets;
        }
        return userReplies;
	}

}
