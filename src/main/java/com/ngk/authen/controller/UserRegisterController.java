package com.ngk.authen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ngk.authen.exception.AppServiceException;
import com.ngk.authen.model.api.login.UserLoginRequest;
import com.ngk.authen.model.api.register.UserDTO;
import com.ngk.authen.model.api.register.UserRegisterRequest;
import com.ngk.authen.services.UserService;

import jakarta.validation.Valid;

@RestController
public class UserRegisterController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> register(@Valid @RequestBody UserRegisterRequest request) {
//		UserRegisterRequest body = request.getBody();
		UserDTO userResponse = userService.registerNewUser(request);
		if (userResponse == null) {
			throw new AppServiceException("Error while create user please check if information valid",
					"ERR_USER_CREATE_ERROR");
		}
		return ResponseEntity.ok(userResponse);
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> login(@Valid @RequestBody UserLoginRequest request) {
		UserDTO userResponse = userService.login(request);
		return ResponseEntity.ok(userResponse);
	}
}
