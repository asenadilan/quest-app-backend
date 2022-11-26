package com.example.questapp.webAPI.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.questapp.business.abstracts.LikeService;
import com.example.questapp.business.requests.likes.LikeCreateRequest;
import com.example.questapp.business.responses.likes.LikeResponse;

@RestController
@RequestMapping("api/likes")
public class LikeController {

	private LikeService _likeService;

	public LikeController(LikeService _likeService) {
		super();
		this._likeService = _likeService;
	}

	@GetMapping
	public List<LikeResponse> getAll(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
		return _likeService.getAll(userId, postId);
	}

	@PostMapping("/create")
	public LikeResponse create(@RequestBody LikeCreateRequest newLike) {
		return _likeService.create(newLike);

	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		_likeService.delete(id);
	}

}
