package com.ngk.authen.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ngk.authen.entities.UserEntity;
import com.ngk.authen.mapper.UserMapper;
import com.ngk.authen.model.api.register.UserDTO;
import com.ngk.authen.model.api.register.UserRegisterRequest;
import com.ngk.authen.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;

	public UserDTO registerNewUser(UserRegisterRequest request) {
		Optional<UserEntity> user = userRepo.findByUsernameOrEmailOrPhone(request.getUsername(), request.getEmail(), request.getPhone());
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
}
