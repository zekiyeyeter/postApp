package com.app.post.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {
    Long id;
    String text;
    Long userId;
    Long postId;

}
