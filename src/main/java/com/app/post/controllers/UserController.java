package com.app.post.controllers;

import com.app.post.entities.User;
import com.app.post.repos.UserRepository;
import com.app.post.requests.UserUpdateRequest;
import com.app.post.responses.UserResponse;
import com.app.post.servicies.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public  UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping
    public User createUser(@RequestBody User newUser){// request body deki bilgitiyi user objesine maple
        return userService.save(newUser);
    }
    @PutMapping("/{userId}")
    public User updateOneUser( @PathVariable Long userId, @RequestBody User newUser ){
        return userService.updateOneUser(userId,newUser);
    }
    @GetMapping("/{userId}")
    public UserResponse getOneUserById(@PathVariable Long userId){
        return new UserResponse(userService.getOneUserByUserId(userId));
    }
    @DeleteMapping("/{userId}")
    public void deleteOneUserByUserId(@PathVariable Long userId){
         userService.deleteOneUserByUserId(userId);
    }
    @GetMapping("/activity/{userId}")
    public List<Object> getUserActivity(@PathVariable Long userId){
       return userService.getUserActivity(userId);
    }




}


