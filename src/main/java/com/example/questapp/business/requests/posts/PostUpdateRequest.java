package com.example.questapp.business.requests.posts;

import lombok.Data;

@Data
public class PostUpdateRequest {
	String text;
	String title;
}
