package com.ngk.authen.model.api.register;

import com.ngk.authen.validate.DynamicPattern;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private long id;
	@NotNull
	private String name;
	@NotNull
	@Email(message = "Email is not valid")
	private String email;
	@NotNull(message = "{user.phone.regex}")
	@DynamicPattern(key = "user.phone.regex", message = "Dinh dang so dien thoai khong hop le")
	private String phone;
}
