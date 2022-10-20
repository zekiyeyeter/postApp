package com.app.post.servicies;

import com.app.post.entities.Post;
import com.app.post.entities.User;
import com.app.post.repos.PostRepository;
import com.app.post.requests.PostCreateRequest;
import com.app.post.requests.PostUpdateRequest;
import com.app.post.responses.LikeResponse;
import com.app.post.responses.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
   private final UserService userService;
    private final PostRepository postRepository;

    public List<Post> getAllPosts(Optional<Long> userId) {

        if (userId.isPresent()) {

             return postRepository.findByUserId(userId.get());
        } else

           return postRepository.findAll();

    }
 /*   public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> postList;

        if (userId.isPresent()) {
            postList = postRepository.findByUserId(userId.get());
        } else
            postList = postRepository.findAll();

        return postList.stream().map(post -> {
            List<LikeResponse> likesList = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(post.getId()));
            return new PostResponse(post, likesList);}).collect(Collectors.toList());
    }*/

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
    public PostResponse getOnePostByIdWithLikes(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        return new PostResponse(post, post.getLikes());
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
      User user=  userService.getOneUserByUserId(newPostRequest.getUserId());
        if (user ==null)
            return  null;
        Post toSave= new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
       Optional<Post> post= postRepository.findById(postId);
       if(post.isPresent()){
           Post toUpdate=post.get();
           toUpdate.setTitle(updatePost.getTitle());
           toUpdate.setText(updatePost.getText());
           postRepository.save(toUpdate);
           return toUpdate;

       }
       return  null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
