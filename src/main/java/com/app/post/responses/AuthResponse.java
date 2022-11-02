package com.app.post.responses;

import lombok.Data;

@Data
public class AuthResponse {
    String message;
    Long userId;
    String accessToken;
    String refreshToken;
}