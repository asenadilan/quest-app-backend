package com.example.questapp.business.requests.likes;

import lombok.Data;

@Data
public class LikeCreateRequest {
	Long userId;
	Long postId;
}
