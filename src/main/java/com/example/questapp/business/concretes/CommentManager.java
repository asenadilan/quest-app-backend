package com.example.questapp.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.questapp.business.abstracts.CommentService;
import com.example.questapp.business.requests.comments.CommentCreateRequest;
import com.example.questapp.business.requests.comments.CommentUpdateRequest;
import com.example.questapp.business.responses.comments.CommentResponse;
import com.example.questapp.dataAccess.abstracts.CommentRepository;
import com.example.questapp.dataAccess.abstracts.PostRepository;
import com.example.questapp.dataAccess.abstracts.UserRepository;
import com.example.questapp.entities.concretes.Comment;
import com.example.questapp.entities.concretes.Post;
import com.example.questapp.entities.concretes.User;

@Service
public class CommentManager implements CommentService {
	private CommentRepository _commentRepository;
	private UserRepository _userRepository;
	private PostRepository _postRepository;

	public CommentManager(CommentRepository _commentRepository, UserRepository _userRepository,
			PostRepository _postRepository) {
		super();
		this._commentRepository = _commentRepository;
		this._userRepository = _userRepository;
		this._postRepository = _postRepository;
	}

	@Override
	public List<CommentResponse> getAll(Optional<Long> userId, Optional<Long> postId) {
		List<Comment> comments;
		List<CommentResponse> returnData = new ArrayList<>();
		if (userId.isPresent()) {
			comments = _commentRepository.findByUserId(userId.get());
		} else if (postId.isPresent()) {
			comments = _commentRepository.findByPostId(postId.get());
		} else {
			comments = _commentRepository.findAll();
		}
		for (Comment comment : comments) {
			CommentResponse commentResponse = new CommentResponse();
			User user = comment.getUser();
			Post post = comment.getPost();
			commentResponse.setPostTitle(post.getTitle());
			commentResponse.setUserName(user.getUsername());
			commentResponse.setText(comment.getText());
			returnData.add(commentResponse);
		}
		return returnData;
	}

	@Override
	public CommentResponse create(CommentCreateRequest newComment) {
		Optional<User> o_user = _userRepository.findById(newComment.getUserId());
		Optional<Post> o_post = _postRepository.findById(newComment.getPostId());
		if (o_post.isPresent() && o_user.isPresent()) {
			User user = o_user.get();
			Post post = o_post.get();
			Comment comment = new Comment();
			comment.setPost(post);
			comment.setUser(user);
			comment.setText(newComment.getText());
			_commentRepository.save(comment);
			CommentResponse commentResponse = new CommentResponse();
			commentResponse.setPostTitle(post.getTitle());
			commentResponse.setUserName(user.getUsername());
			commentResponse.setText(newComment.getText());
			return commentResponse;

		}
		return null;
	}

	@Override
	public CommentResponse update(Long id, CommentUpdateRequest newComment) {
		Optional<Comment> o_comment = _commentRepository.findById(id);
		if (o_comment.isPresent()) {
			Comment comment = o_comment.get();
			comment.setText(newComment.getText());
			_commentRepository.save(comment);
			Post post = comment.getPost();
			User user = comment.getUser();
			CommentResponse commentResponse = new CommentResponse();
			commentResponse.setPostTitle(post.getTitle());
			commentResponse.setUserName(user.getUsername());
			commentResponse.setText(comment.getText());
			return commentResponse;

		}
		return null;
	}

}
