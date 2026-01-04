package com.ngk.authen.model.api.register;

import com.ngk.authen.validate.DynamicPattern;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {
	@NotEmpty(message = "Username la bat buoc")
	private String username;
	@NotEmpty(message = "Email la bat buoc")
	@Email(message = "Dinh email khong hop le")
	private String email;
	@NotEmpty(message = "So dien thoai la bat buoc")
	@DynamicPattern(key = "user.phone.regex", message = "Dinh dang so dien thoai khong hop le")
	private String phone;
	@NotEmpty(message = "Password la bat buoc")
	private String password;
}
