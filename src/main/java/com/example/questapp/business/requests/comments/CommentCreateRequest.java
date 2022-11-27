package com.example.questapp.business.requests.comments;

import lombok.Data;

@Data
public class CommentCreateRequest {
	Long userId;
	Long postId;
	String text;
}
