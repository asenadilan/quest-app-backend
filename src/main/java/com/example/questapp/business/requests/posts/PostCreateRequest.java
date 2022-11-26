package com.example.questapp.business.requests.posts;

import lombok.Data;

@Data
public class PostCreateRequest {
	String text;
	String title;
	Long userId;
}
