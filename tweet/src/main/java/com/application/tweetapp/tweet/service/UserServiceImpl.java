package com.application.tweetapp.tweet.service;

import com.application.tweetapp.tweet.document.User;
import com.application.tweetapp.tweet.exception.RecordNotFoundException;
import com.application.tweetapp.tweet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        System.out.println("inside serviceimpl");
        if (userRepository.findById(user.getLoginid()).isPresent()) {

            throw new RecordNotFoundException("User is already exists with the entered loginid");
        }
         if(user.getPassword().isEmpty() || user.getLoginid().isEmpty() || user.getFirstname().isEmpty() || user.getLastname().isEmpty() || user.getEmail().isEmpty() || user.getContact().equals(null)){
            System.out.println("register check");
             try {
                 throw new Exception("Enter all the required details:"+ user);
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
        else {
            userRepository.save(user);
            System.out.println("user saved successfully");
        }
        return user;
    }

    @Override
    public User getUserByLoginid(String loginid) {
        Optional< User > userdb = this.userRepository.findById(loginid);

        if (userdb.isPresent()) {
            return userdb.get();
        } else {
            throw new RecordNotFoundException("User not found with loginid : " + loginid);
        }

    }

    @Override
    public User resetPassword(User user) {
        Optional<User> olduser=userRepository.findById(user.getLoginid());
        if(olduser.isPresent()){
            System.out.println(olduser.get().getPassword());
            olduser.get().setPassword(user.getPassword());
            userRepository.save(olduser.get());
            return olduser.get();
        }
        else {
            throw new RecordNotFoundException("User not found with Loginid:" +user.getLoginid());
        }
    }

    @Override
    public String userLogin(User user) {
        Optional<User> user1=userRepository.findById(user.getLoginid());
        if(!user1.isPresent()){
            throw new RecordNotFoundException("User not found");
//            return user1.get();
        }
        if (!user1.get().getPassword().equals(user.getPassword())){
            throw new RecordNotFoundException("Invalid password");
//            return user1.get();
        }
        else
            return "User is valid!!!";

    }

    @Override
    public String deleteById(String loginid) {
        Optional<User> user1=userRepository.findById(loginid);
        if(!user1.isPresent()){
            throw new RecordNotFoundException("User not found with loginid:" + loginid);
//            return user1.get();
        }
        else {
            userRepository.deleteById(loginid);
            return "user deleted successfully";
        }
//        return null;
    }

}
