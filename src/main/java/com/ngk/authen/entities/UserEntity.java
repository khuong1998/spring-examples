package com.ngk.authen.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "appuser")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true, nullable = false)
	private String username;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private String phone;
	@Column(nullable = false)
	private String password;
}
