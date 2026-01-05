package com.ngk.authen.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ngk.authen.entities.UserEntity;
import com.ngk.authen.exception.AuthException;
import com.ngk.authen.mapper.UserMapper;
import com.ngk.authen.model.api.login.UserLoginRequest;
import com.ngk.authen.model.api.register.UserDTO;
import com.ngk.authen.model.api.register.UserRegisterRequest;
import com.ngk.authen.repositories.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;

	public UserDTO registerNewUser(UserRegisterRequest request) {
		Optional<UserEntity> user = userRepo.findByUsernameOrEmailOrPhone(request.getUsername(), request.getEmail(),
				request.getPhone());
		if (user.isPresent()) {
			return null;
		}
		UserEntity entity = UserMapper.MAPPER.toUserEntity(request);
		String encoded = passwordEncoder.encode(request.getPassword());
		entity.setPassword(encoded);
		userRepo.save(entity);

		UserDTO resp = UserMapper.MAPPER.toUserRegisterResponse(entity);

		return resp;
	}

	public UserDTO login(@Valid UserLoginRequest request) throws AuthException {
		Optional<UserEntity> user = userRepo.findByUsernameOrEmailOrPhone(request.getLoginID(), request.getLoginID(),
				request.getLoginID());
		if (!user.isPresent()) {
			throw new AuthException("Unauthorized", "");
		}
		UserEntity userEntity = user.get();
		boolean match = passwordEncoder.matches(request.getPassword(), userEntity.getPassword());
		if (!match) {
			throw new AuthException("Unauthorized", "");
		}
		UserDTO resp = UserMapper.MAPPER.toUserRegisterResponse(userEntity);
		return resp;
	}
}
