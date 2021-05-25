package com.authorize.main.exception;

public class LoginCredentialNotValid extends Exception {

    private static final long serialVersionUID = 1L;

    // Passing Exception to Exception Constructor
    public LoginCredentialNotValid(String exceptionMessage) {
        super(exceptionMessage);
	}
    
}
