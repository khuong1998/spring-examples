package com.ngk.authen.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CommonErrorResponse {
	private String message;
	private String code;
	private long timestamp;
}
