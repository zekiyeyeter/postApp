package com.app.post.repos;
import com.app.post.entities.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LikeRepository extends JpaRepository<Likes,Long> {
    Optional<Likes> findById(Long likeId);

    void deleteById(Long likeId);

    List<Likes> findByUserIdAndPostId(Long userId, Long postId);

    List<Likes> findByUserId(Long userId);

    List<Likes> findByPostId(Long postId);

    List<Likes> findAll();

    Likes save(Likes likeToSave);
}
