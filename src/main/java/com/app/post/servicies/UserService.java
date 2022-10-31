package com.app.post.servicies;

import com.app.post.entities.User;
import com.app.post.repos.CommentRepository;
import com.app.post.repos.LikeRepository;
import com.app.post.repos.PostRepository;
import com.app.post.repos.UserRepository;
import com.app.post.responses.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    LikeRepository likeRepository;
    CommentRepository commentRepository;
    PostRepository postRepository;

    public UserService(PostRepository postRepository,UserRepository userRepository,LikeRepository likeRepository,CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.likeRepository=likeRepository;
        this.commentRepository=commentRepository;
        this.postRepository=postRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }


    public User updateOneUser(Long userId, User newUser) {

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser= user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            foundUser.setAvatar(newUser.getAvatar());
            userRepository.save(foundUser);
            return foundUser;
        }else
            return null;
    }

    public User getOneUserByUserId(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteOneUserByUserId(Long userId) {
        userRepository.deleteById(userId);
    }


    public User getOneUserById(Long userId) {
       return userRepository.findById(userId).orElse(null);
    }

    public User getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public List<Object> getUserActivity(Long userId) {
       List<Long> postIds= postRepository.findTopByUserId(userId);
       if(postIds.isEmpty())
           return null;
        List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
        List<Object> likes = likeRepository.findUserLikesByPostId(postIds);
        List<Object> result = new ArrayList<>();
        result.addAll(comments);
        result.addAll(likes);
        return result;


    }
}
