package com.authorize.main.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authorize.main.model.AuthenticationRequest;
import com.authorize.main.repository.AuthRequestRepo;

@Service
public class AuthorizationService implements UserDetailsService {

	@Autowired
	AuthRequestRepo authRequestRepo;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		try {
			AuthenticationRequest authenticationRequest = authRequestRepo.findByUserName(s);
			UserDetails user = new User(authenticationRequest.getUserName(), authenticationRequest.getPassword(),
					new ArrayList<>());
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

}
