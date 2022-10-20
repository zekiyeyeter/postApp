package com.app.post.responses;

import com.app.post.entities.Likes;
import lombok.Data;

@Data
public class LikeResponse {
    Long id;
    Long userId;
    Long postId;


    public LikeResponse(Likes entity){
        this.id=entity.getId();
        this.userId=entity.getUser().getId();
        this.postId=entity.getPost().getId();

    }
}
