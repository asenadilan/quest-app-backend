package com.example.questapp.webAPI.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.questapp.business.abstracts.PostService;
import com.example.questapp.business.requests.posts.PostCreateRequest;
import com.example.questapp.business.requests.posts.PostUpdateRequest;
import com.example.questapp.business.responses.posts.PostResponse;

@RestController
@RequestMapping("api/posts")
public class PostController {
	private PostService _postService;
	

	public PostController(PostService _postService) {
		super();
		this._postService = _postService;
	}

//@RequestParam => urli parçala ve parametrelere ata
	@GetMapping
	public List<PostResponse> getAll(@RequestParam Optional<Long> userId) {
		return _postService.getAll(userId);
	}

	@PostMapping("/create")
	public PostResponse create(@RequestBody PostCreateRequest newPost) {
		return _postService.create(newPost);
	}
	
	@GetMapping("/{id}")
	public PostResponse getById(@PathVariable Long id) {
		return _postService.getById(id);
	}
	@PutMapping("/{id}")
	public PostResponse update(@PathVariable Long id,@RequestBody PostUpdateRequest newPost) {
		return _postService.update(id,newPost);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		_postService.delete(id);
	}
	
}

