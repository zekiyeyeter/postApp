package com.app.post.servicies;

import com.app.post.entities.Likes;
import com.app.post.entities.Post;
import com.app.post.entities.User;
import com.app.post.repos.LikeRepository;
import com.app.post.requests.LikeCreateRequest;
import com.app.post.responses.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final   UserService userService;
    private  final  PostService postService;





    public List<LikeResponse> getAllLikesWithParam(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        List<Likes> list;
        if (userId.isPresent() && postId.isPresent()) {
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        } else
            list = likeRepository.findAll();
        return list.stream().map(LikeResponse::new).collect(Collectors.toList());
    }

    public Likes getOneLikeById(Long likeId) {
        return  likeRepository.findById(likeId).orElse(null);
    }

    public Likes createOneLike(LikeCreateRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if(user != null && post != null) {
            Likes likeToSave = new Likes();
            likeToSave.setId(request.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        }else
            return null;
    }


    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }

}
