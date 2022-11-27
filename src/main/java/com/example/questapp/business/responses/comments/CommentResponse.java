package com.example.questapp.business.responses.comments;

import lombok.Data;

@Data
public class CommentResponse {
	String userName;
	String postTitle;
	String text;
}
