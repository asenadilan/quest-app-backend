package com.example.questapp.webAPI.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.questapp.business.abstracts.CommentService;
import com.example.questapp.business.requests.comments.CommentCreateRequest;
import com.example.questapp.business.requests.comments.CommentUpdateRequest;
import com.example.questapp.business.responses.comments.CommentResponse;

@RestController
@RequestMapping
public class CommentController {

	private CommentService _commentService;

	public CommentController(CommentService _commentService) {
		super();
		this._commentService = _commentService;
	}

	@GetMapping
	public List<CommentResponse> getAll(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
		return _commentService.getAll(userId, postId);
	}

	@PostMapping("/create")
	public CommentResponse create(@RequestBody CommentCreateRequest newComment) {
		return _commentService.create(newComment);
	}

	@PutMapping("/{id}")
	public CommentResponse update(@PathVariable Long id, @RequestBody CommentUpdateRequest newComment) {
		return _commentService.update(id,newComment);
	}
}
