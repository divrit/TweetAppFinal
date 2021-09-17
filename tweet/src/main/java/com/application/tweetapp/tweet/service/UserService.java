package com.application.tweetapp.tweet.service;

import com.application.tweetapp.tweet.document.User;

public interface UserService {
    public User saveUser(User user);

    public User getUserByLoginid(String loginid);

    public User resetPassword(User user);

    public String userLogin(User user);

    public String deleteById(String loginid);
}
