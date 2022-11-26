package com.example.questapp.business.abstracts;

import java.util.List;

import com.example.questapp.business.requests.users.UserRequest;
import com.example.questapp.business.responses.users.UserResponse;

public interface UserService {

	public List<UserResponse> getAll();

	public UserResponse create(UserRequest newUser);

	public UserResponse getById(Long id);

	public UserResponse update(Long id, UserRequest newUser);

	public void deleteById(Long id);

}
