package com.example.questapp.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.questapp.business.abstracts.UserService;
import com.example.questapp.business.requests.users.UserRequest;
import com.example.questapp.business.responses.users.UserResponse;
import com.example.questapp.dataAccess.abstracts.UserRepository;
import com.example.questapp.entities.concretes.User;

@Service
public class UserManager implements UserService {

	private UserRepository _userRepository;

	public UserManager(UserRepository _userRepository) {
		super();
		this._userRepository = _userRepository;
	}

	@Override
	public List<UserResponse> getAll() {
		List<User> users = _userRepository.findAll();
		List<UserResponse> returndata = new ArrayList<>();
		for (User user : users) {
			UserResponse userResponse = new UserResponse();
			userResponse.setUserName(user.getUsername());
			userResponse.setPassword(user.getPassword());
			returndata.add(userResponse);
		}
		return returndata;
	}


	@Override
	public UserResponse getById(Long id) {
		Optional<User> user = _userRepository.findById(id);
		if (user.isPresent()) {
			UserResponse userResponse = new UserResponse();
			User foundUser = user.get();
			userResponse.setPassword(foundUser.getPassword());
			userResponse.setUserName(foundUser.getUsername());
			return userResponse;
		}

		return null;
	}

	@Override
	public UserResponse update(Long id, UserRequest newUser) {
		Optional<User> user = _userRepository.findById(id);
		if (user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUsername(newUser.getUserName());
			foundUser.setPassword(newUser.getPassword());
			_userRepository.save(foundUser);
			UserResponse userResponse = new UserResponse();
			userResponse.setUserName(foundUser.getUsername());
			userResponse.setPassword(foundUser.getPassword());
			return userResponse;

		}
		return null;
	}

	@Override
	public void deleteById(Long id) {
		Optional<User> user = _userRepository.findById(id);
		if (user.isPresent()) {
			_userRepository.deleteById(id);
		}
	}

	@Override
	public UserResponse create(UserRequest newUser) {
		User user = new User();
		user.setPassword(newUser.getPassword());
		user.setUsername(newUser.getUserName());
		_userRepository.save(user);
		UserResponse userResponse = new UserResponse();
		userResponse.setPassword(user.getPassword());
		userResponse.setUserName(user.getUsername());
		return userResponse;
	}


}
