package com.example.security.config;

public interface TokenVerifier {
	 public boolean verify(String jti);
}
