package com.app.post.repos;

import com.app.post.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface CommentRepository extends JpaRepository<Comment,Long> {

        Optional<Comment> findById(Long commentId);

        void deleteById(Long commentId);

        List<Comment> findByUserIdAndPostId(Long userId, Long postId);

        List<Comment> findByUserId(Long userId);

        List<Comment> findByPostId(Long postId);

        List<Comment> findAll();

        Comment save(Comment commentToSave);

        @Query(value = "select 'commented on', c.post_id, u.avatar, u.user_name from "
                + "comment c left join user u on u.id = c.user_id "
                + "where c.post_id in :postIds limit 5", nativeQuery = true)
        List<Object> findUserCommentsByPostId(@Param("postIds") List<Long> postIds);
}
