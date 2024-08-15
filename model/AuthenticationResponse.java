package com.society.model;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationResponse {
    private final String token;
    private final Optional<User> userDetails;

    public AuthenticationResponse(String token, Optional<User> userDetails2) {
        this.token = token;
        this.userDetails=userDetails2;
    }

	public String getToken() {
		return token;
	}

	public Optional<User> getUserDetails() {
		return userDetails;
	}

   
}
