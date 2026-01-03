package com.ngk.authen.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DynamicPatternValidator implements ConstraintValidator<DynamicPattern, String> {

	private String configKey;

	@Autowired
	private Environment env; // Sử dụng Environment để truy cập properties

	@Override
	public void initialize(DynamicPattern constraintAnnotation) {
		this.configKey = constraintAnnotation.key();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// 1. Nếu giá trị truyền vào là null, mặc định cho qua (để @NotNull lo việc của
		// nó)
		if (value == null || value.isEmpty()) {
			return true;
		}

		// 2. Lấy Regex từ application.properties dựa trên configKey
		// (Giống như việc bạn tra từ điển để tìm luật lệ vậy)
		String regex = env.getProperty(configKey);

		// 3. Nếu không tìm thấy Regex trong file cấu hình, coi như không có luật (hợp
		// lệ)
		// Hoặc bạn có thể return false tùy vào yêu cầu hệ thống
		if (regex == null || regex.isEmpty()) {
			return true;
		}

		// 4. So khớp giá trị với Regex vừa lấy được
		return value.matches(regex);
	}
}