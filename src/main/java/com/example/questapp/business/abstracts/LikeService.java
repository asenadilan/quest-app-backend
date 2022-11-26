package com.example.questapp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.questapp.business.requests.likes.LikeCreateRequest;
import com.example.questapp.business.responses.likes.LikeResponse;

public interface LikeService {

	List<LikeResponse> getAll(Optional<Long> userId, Optional<Long> postId);

	LikeResponse create(LikeCreateRequest newLike);

	void delete(Long id);
	
}
