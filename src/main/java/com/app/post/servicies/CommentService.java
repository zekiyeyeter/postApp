package com.app.post.servicies;

import com.app.post.entities.Comment;
import com.app.post.entities.Post;
import com.app.post.entities.User;
import com.app.post.repos.CommentRepository;
import com.app.post.requests.CommentCreateRequest;
import com.app.post.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;


@Service
public class CommentService {


    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService,
                          PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentsWithParam(@RequestParam  Optional<Long> userId, @RequestParam Optional<Long> postId) {
      //  List<Comment> comments;
        if(userId.isPresent() && postId.isPresent()) {
           return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()) {
            return  commentRepository.findByPostId(postId.get());
        }else
            return  commentRepository.findAll();

                //comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
    }

    public Comment getOneCommentById(Long commentId) {
        return  commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if(user != null && post != null) {
            Comment commentToSave = new Comment();
            commentToSave.setId(request.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(request.getText());
            return commentRepository.save(commentToSave);
        }else
            return null;
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
        Optional<Object> comment = Optional.ofNullable(commentRepository.findById(commentId));
        if(comment.isPresent()) {
            Comment commentToUpdate = (Comment) comment.get();
            commentToUpdate.setText(request.getText());
            return commentRepository.save(commentToUpdate);
        }else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }



}
