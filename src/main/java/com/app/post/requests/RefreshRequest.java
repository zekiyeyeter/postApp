package com.app.post.requests;

import lombok.Data;

@Data
public class RefreshRequest {

    Long userId;
    String refreshToken;
}
