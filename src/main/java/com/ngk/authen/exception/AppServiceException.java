package com.ngk.authen.exception;

public class AppServiceException extends RuntimeException {
 private final String errorCode;

 public AppServiceException(String message, String errorCode) {
     super(message);
     this.errorCode = errorCode;
 }

 public String getErrorCode() {
     return errorCode;
 }
}