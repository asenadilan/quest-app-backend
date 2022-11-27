package com.example.questapp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.questapp.business.requests.comments.CommentCreateRequest;
import com.example.questapp.business.requests.comments.CommentUpdateRequest;
import com.example.questapp.business.responses.comments.CommentResponse;

public interface CommentService {

	List<CommentResponse> getAll(Optional<Long> userId, Optional<Long> postId);

	CommentResponse create(CommentCreateRequest newComment);

	CommentResponse update(Long id, CommentUpdateRequest newComment);


}
