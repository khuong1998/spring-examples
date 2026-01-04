package com.ngk.authen.exceptionhandler;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ngk.authen.exception.AppServiceException;
import com.ngk.authen.exception.AuthException;
import com.ngk.authen.model.api.CommonErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AppServiceException.class)
	public ResponseEntity<CommonErrorResponse> handleServiceException(AppServiceException ex) {
		CommonErrorResponse error = new CommonErrorResponse(ex.getMessage(), ex.getErrorCode(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(AuthException.class)
	public ResponseEntity<CommonErrorResponse> handleAuthException(AuthException ex) {
		CommonErrorResponse error = new CommonErrorResponse(ex.getMessage(), "AUTH_" + ex.getAuthDetail(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CommonErrorResponse> handleArgumentValidateException(MethodArgumentNotValidException ex) {
		String detailedMessage = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.joining("; "));

		CommonErrorResponse errorResponse = new CommonErrorResponse("Dữ liệu không hợp lệ: " + detailedMessage,
				"VALIDATION_ERROR", System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CommonErrorResponse> handleGeneralException(Exception ex) {
		ex.printStackTrace();
		CommonErrorResponse error = new CommonErrorResponse("Đã có lỗi hệ thống xảy ra", "SERVER_ERROR",
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}