package com.ngk.authen.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngk.authen.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUsernameOrEmailOrPhone(String username, String email, String phone);
}
