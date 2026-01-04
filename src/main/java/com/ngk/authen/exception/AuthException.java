package com.ngk.authen.exception;

public class AuthException extends RuntimeException {
 private final String authDetail;

 public AuthException(String message, String authDetail) {
     super(message);
     this.authDetail = authDetail;
 }

 public String getAuthDetail() {
     return authDetail;
 }
}