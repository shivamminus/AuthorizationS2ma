package com.authorize.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.authorize.main.dto.VaildatingDTO;
import com.authorize.main.exception.LoginCredentialNotValid;
import com.authorize.main.exception.LoginException;
import com.authorize.main.model.AuthenticationRequest;
import com.authorize.main.model.AuthenticationResponse;
import com.authorize.main.service.AuthorizationService;
import com.authorize.main.util.JwtUtil;

@RestController
public class AuthorizationController {

	@Autowired
	private AuthorizationService userDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;

	private VaildatingDTO vaildatingDTO = new VaildatingDTO();

	@PostMapping("/login")
	public ResponseEntity<Object> createAuthorizationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws LoginException, LoginCredentialNotValid {
		System.out.println(authenticationRequest);
		if (authenticationRequest == null || authenticationRequest.getUserName() == null
				|| authenticationRequest.getPassword() == null) {
			System.out.println("ok");
			throw new LoginCredentialNotValid("Login Details are not provided as per Requirement");
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		System.out.println("{} " + userDetails);
		if (userDetails.getPassword().equals(authenticationRequest.getPassword())) {
			System.out.println("END - [login(customerLoginCredentials)]");
			return new ResponseEntity<>(new AuthenticationResponse(authenticationRequest.getUserName(),
					jwtTokenUtil.generateToken(userDetails)), HttpStatus.OK);
		} else {
			System.out.println("END - [login(customerLoginCredentials)]");
			throw new LoginException("Invalid Username or Password");
		}
	}

	@GetMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VaildatingDTO> validatingAuthorizationToken(
			@RequestHeader(name = "Authorization") String tokenDup) {
		String token = tokenDup.substring(7);
		try {
			UserDetails user = userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(token));
			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(token, user))) {
				vaildatingDTO.setValidStatus(true);
				return new ResponseEntity<>(vaildatingDTO, HttpStatus.OK);
			} else {
				vaildatingDTO.setValidStatus(false);
				return new ResponseEntity<>(vaildatingDTO, HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			vaildatingDTO.setValidStatus(false);
			return new ResponseEntity<>(vaildatingDTO, HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping(path = "/insert")
	public ResponseEntity<String> insertUser(@RequestBody List<AuthenticationRequest> insertUserList) {
		return new ResponseEntity<String>(userDetailsService.createUser(insertUserList), HttpStatus.CREATED);

	}

	@GetMapping(path = "/health-check")
	public ResponseEntity<String> healthCheck() {
		System.out.println("Authorization Microservice is Up and Running....");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
}