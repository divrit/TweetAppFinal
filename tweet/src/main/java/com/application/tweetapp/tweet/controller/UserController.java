package com.application.tweetapp.tweet.controller;

import com.application.tweetapp.tweet.document.User;
import com.application.tweetapp.tweet.model.Divrit;
import com.application.tweetapp.tweet.repository.DUserRepository;
import com.application.tweetapp.tweet.repository.UserRepository;
import com.application.tweetapp.tweet.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "User Operations", description = "user can do following stuff")
@RestController
@RequestMapping("/api/v1/user")
//@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DUserRepository userRepository;
 
    @PostMapping("/register")
    @ApiOperation(value="User Registration", response=String.class)
    public String createUser(@RequestBody User user)
    {
    	userRepository.newUserRegistration(user);
        return "User inserted successfully";
    }


    @GetMapping("/{loginid}")
    @ApiOperation(value="Gets User details by Loginid", response=User.class)
    public User getUserByLoginid(@PathVariable String loginid){
        return userRepository.searchUserByLogin(loginid);
    }

//    @PutMapping("/resetPassword")
//    @ApiOperation(value="Password will be reset",response=String.class)
//    public String resetPassword(@RequestBody User user){
//       User usernew= userService.resetPassword(user);
//        Optional<User> old=userRepository.findById(user.getLoginid());
//        if(old.get().getPassword()!= usernew.getPassword()) {
//            return "password updated successfully";
//        }
//        else if (old.get().getPassword() == usernew.getPassword()){
//            return "Pasword should not be same";
//        }
//        else return "pasword call";
//
//    }

    @PostMapping("/login")
    @ApiOperation(value="Validate user login",response=String.class)
    public String userLogin(@RequestBody User user){
        return userService.userLogin(user);

    }

    @DeleteMapping("/delete/{loginid}")
    @ApiOperation(value="Deletes user by provided LoginId",response=String.class)
    public String deleteUser(@PathVariable String loginid){
       return userService.deleteById(loginid);
    }
}
