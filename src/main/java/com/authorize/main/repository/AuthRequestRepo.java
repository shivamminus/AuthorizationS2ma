package com.authorize.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authorize.main.model.AuthenticationRequest;

@Repository
public interface AuthRequestRepo extends JpaRepository<AuthenticationRequest, String> {

	// This will find the AuthenticationRequest from DB from username
	// @params String username
	// @return AuthenticationRequest authenticationRequestObj
	public AuthenticationRequest findByUserName(String username);
}