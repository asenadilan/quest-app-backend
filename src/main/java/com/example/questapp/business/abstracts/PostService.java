package com.example.questapp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.questapp.business.requests.posts.PostCreateRequest;
import com.example.questapp.business.requests.posts.PostUpdateRequest;
import com.example.questapp.business.responses.posts.PostResponse;

public interface PostService {

	List<PostResponse> getAll(Optional<Long> userId);

	PostResponse getById(Long id);

	PostResponse create(PostCreateRequest newPost);

	PostResponse update(Long id, PostUpdateRequest newPost);

	void delete(Long id);

}
