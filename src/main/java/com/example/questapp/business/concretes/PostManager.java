package com.example.questapp.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.questapp.business.abstracts.PostService;
import com.example.questapp.business.requests.posts.PostCreateRequest;
import com.example.questapp.business.requests.posts.PostUpdateRequest;
import com.example.questapp.business.responses.posts.PostResponse;
import com.example.questapp.dataAccess.abstracts.PostRepository;
import com.example.questapp.dataAccess.abstracts.UserRepository;
import com.example.questapp.entities.concretes.Post;
import com.example.questapp.entities.concretes.User;

@Service
public class PostManager implements PostService {
	private PostRepository _postRepository;
	private UserRepository _userRepository;

	public PostManager(PostRepository _postRepository, UserRepository _userRepository) {
		super();
		this._postRepository = _postRepository;
		this._userRepository = _userRepository;
	}

	@Override
	public List<PostResponse> getAll(Optional<Long> userId) {
		List<Post> posts;
		List<PostResponse> returnData = new ArrayList<>();
		if (userId.isPresent()) {
			posts = _postRepository.findByUserId(userId.get());
		} else {
			posts = _postRepository.findAll();
		}
		for (Post post : posts) {
			PostResponse postResponse = new PostResponse();
			User user = post.getUser();
			postResponse.setText(post.getText());
			postResponse.setTitle(post.getTitle());
			postResponse.setUserName(user.getUsername());
			returnData.add(postResponse);

		}
		return returnData;
	}

	@Override
	public PostResponse getById(Long id) {
		Optional<Post> o_post = _postRepository.findById(id);
		if (o_post.isPresent()) {
			Post post = o_post.get();
			User user = post.getUser();
			PostResponse postResponse = new PostResponse();
			postResponse.setText(post.getText());
			postResponse.setTitle(post.getTitle());
			postResponse.setUserName(user.getUsername());
			return postResponse;
		}
		return null;
	}

	@Override
	public PostResponse create(PostCreateRequest newPost) {
		Optional<User> o_user = _userRepository.findById(newPost.getUserId());
		if (o_user.isPresent()) {
			Post post = new Post();
			PostResponse postResponse = new PostResponse();
			User user = o_user.get();
			post.setText(newPost.getText());
			post.setTitle(newPost.getTitle());
			post.setUser(user);
			_postRepository.save(post);
			postResponse.setText(post.getText());
			postResponse.setTitle(post.getTitle());
			postResponse.setUserName(user.getUsername());
			return postResponse;
		}
		return null;

	}

	@Override
	public PostResponse update(Long id, PostUpdateRequest newPost) {
		Optional<Post> o_post = _postRepository.findById(id);
		if(o_post.isPresent()) {
			Post post = o_post.get();
			post.setText(newPost.getTitle());
			post.setTitle(newPost.getText());
			_postRepository.save(post);
			PostResponse postResponse = new PostResponse();
			User user= post.getUser();
			postResponse.setText(post.getText());
			postResponse.setTitle(post.getTitle());
			postResponse.setUserName(user.getUsername());
			return postResponse;
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		_postRepository.deleteById(id);
		
	}

}
