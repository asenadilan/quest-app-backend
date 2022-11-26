package com.example.questapp.webAPI.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.questapp.business.abstracts.UserService;
import com.example.questapp.business.requests.users.UserRequest;
import com.example.questapp.business.responses.users.UserResponse;

@RestController
@RequestMapping("api/users")
public class UserController {

	private UserService _userService;

	public UserController(UserService _userService) {
		super();
		this._userService = _userService;
	}

	@GetMapping()
	public List<UserResponse> getAll() {

		return _userService.getAll();
	}

	@PostMapping("/create")
	public UserResponse create(@RequestBody UserRequest newUser) {
		return _userService.create(newUser);

	}

	@GetMapping("/{id}")
	public UserResponse getById(@PathVariable Long id) {
		return _userService.getById(id);
	}

	@PutMapping("/{id}")
	public UserResponse update(@PathVariable Long id, @RequestBody UserRequest newUser) {
		return _userService.update(id, newUser);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		_userService.deleteById(id);
	}

}
