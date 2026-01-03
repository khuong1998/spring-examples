package com.ngk.authen.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ngk.authen.model.api.register.UserDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@RestController
public class UserRegisterController {
	@Autowired
    private Validator validator;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> register() {
		UserDTO dto = new UserDTO();
		dto.setName("asd");
		dto.setEmail("asd");
		dto.setPhone(null);
		try {
			Set<ConstraintViolation<UserDTO>> violations = validator.validate(dto);
			violations.stream().forEach(e -> System.out.println(e.getMessage()));
		} catch (Exception e) {
			ResponseEntity<UserDTO> resp = ResponseEntity.internalServerError().body(dto);
			return resp;
		}
		ResponseEntity<UserDTO> resp = ResponseEntity.badRequest().body(dto);
		return resp;
	}
	void testUser(@Validated UserDTO u) {
		
	}
}
