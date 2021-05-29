package com.authorize.main.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.authorize.main.exception.LoginCredentialNotValid;
import com.authorize.main.exception.LoginException;
import com.authorize.main.model.AuthenticationRequest;
import com.authorize.main.repository.AuthRequestRepo;
import com.authorize.main.service.AuthorizationService;
import com.authorize.main.util.JwtUtil;

@SpringBootTest
class AuthorizationControllerTest {

	@InjectMocks
	AuthorizationController authController;

//	AuthenticationResponse authResponse;

//	UserDetails userdetails;

	@Mock
	JwtUtil jwtUtil;

	@Mock
	AuthorizationService authService;

	@Mock
	AuthRequestRepo authRepo;

	/* test for valid details */
	@Test
	public void validLoginTest() throws LoginException, LoginCredentialNotValid {
		AuthenticationRequest user = new AuthenticationRequest("mayur012", "test12345");
		UserDetails userDetails = new User(user.getUserName(), user.getPassword(), new ArrayList<>());
		UserDetails loadUserByUsername = authService.loadUserByUsername("mayur012");
		when(authService.loadUserByUsername("mayur012")).thenReturn(userDetails);
		when(jwtUtil.generateToken(loadUserByUsername)).thenReturn("token");
		ResponseEntity<?> login = authController.createAuthorizationToken(user);
		assertEquals(200, login.getStatusCodeValue());

	}

	/* test for invalid details */
	@Test
	public void invalidLoginTest() throws LoginException, LoginCredentialNotValid {
		AuthenticationRequest user = new AuthenticationRequest("mayur012", "test12345");
		UserDetails userDetails = new User(user.getUserName(), "wrongPassword", new ArrayList<>());
		UserDetails loadUserByUsername = authService.loadUserByUsername("mayur012");
		when(authService.loadUserByUsername("mayur012")).thenReturn(userDetails);
		when(jwtUtil.generateToken(loadUserByUsername)).thenReturn("token");
		ResponseEntity<?> login = authController.createAuthorizationToken(user);
		assertEquals(403, login.getStatusCodeValue());

	}

	/* test for not proper details */
	@Test
	public void unproperDetailsForLoginTest() throws LoginException, LoginCredentialNotValid {
		AuthenticationRequest user = null;
		UserDetails userDetails = new User("test", "test", new ArrayList<>());
		UserDetails loadUserByUsername = authService.loadUserByUsername("mayur012");
		when(authService.loadUserByUsername("mayur012")).thenReturn(userDetails);
		when(jwtUtil.generateToken(loadUserByUsername)).thenReturn("token");
		ResponseEntity<?> login = authController.createAuthorizationToken(user);
		assertEquals(400, login.getStatusCodeValue());

	}

}
