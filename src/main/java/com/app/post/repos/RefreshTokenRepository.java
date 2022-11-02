package com.app.post.repos;


import com.app.post.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

    RefreshToken findByUserId(Long userId);

}