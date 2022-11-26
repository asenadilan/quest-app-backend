package com.example.questapp.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.questapp.business.abstracts.LikeService;
import com.example.questapp.business.requests.likes.LikeCreateRequest;
import com.example.questapp.business.responses.likes.LikeResponse;
import com.example.questapp.dataAccess.abstracts.LikeRepository;
import com.example.questapp.dataAccess.abstracts.PostRepository;
import com.example.questapp.dataAccess.abstracts.UserRepository;
import com.example.questapp.entities.concretes.Like;
import com.example.questapp.entities.concretes.Post;
import com.example.questapp.entities.concretes.User;

@Service
public class LikeManager implements LikeService {

	private LikeRepository _likeRepository;
	private UserRepository _userRepository;
	private PostRepository _postRepository;


	public LikeManager(LikeRepository _likeRepository, UserRepository _userRepository, PostRepository _postRepository) {
		super();
		this._likeRepository = _likeRepository;
		this._userRepository = _userRepository;
		this._postRepository = _postRepository;
	}

	@Override
	public List<LikeResponse> getAll(Optional<Long> userId, Optional<Long> postId) {
		List<Like> likes;
		List<LikeResponse> returnData = new ArrayList<>();
		if (userId.isPresent()) {
			likes = _likeRepository.findByUserId(userId.get());
		} else if (postId.isPresent()) {
			likes = _likeRepository.findByPostId(postId.get());
		} else {
			likes = _likeRepository.findAll();
		}
		for (Like like : likes) {
			Post post = like.getPost();
			User user= like.getUser();
			LikeResponse likeResponse = new LikeResponse();
			likeResponse.setPostTitle(post.getTitle());
			likeResponse.setUserName(user.getUsername());
			returnData.add(likeResponse);
			
		}
		return returnData;

	}

	@Override
	public LikeResponse create(LikeCreateRequest newLike) {
			Optional<User> o_user = _userRepository.findById(newLike.getUserId());
			Optional<Post> o_post = _postRepository.findById(newLike.getPostId());
			if(o_post.isPresent() && o_user.isPresent()) {
				Like like = new Like();
				Post post= o_post.get();
				User user = o_user.get();
				like.setPost(post);
				like.setUser(user);
				_likeRepository.save(like);
				LikeResponse likeResponse = new LikeResponse();
				likeResponse.setPostTitle(post.getTitle());
				likeResponse.setUserName(user.getUsername());
				return likeResponse;
			}
			return null;
	}

	@Override
	public void delete(Long id) {
		_likeRepository.deleteById(id);		
	}

}
