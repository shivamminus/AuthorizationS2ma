package com.authorize.main.model;

//@Data
//@NoArgsConstructor
//@Entityo
public class AuthenticationResponse {
	private String userName;
	private String jwtAuthToken;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJwtAuthToken() {
		return jwtAuthToken;
	}
	public void setJwtAuthToken(String jwtAuthToken) {
		this.jwtAuthToken = jwtAuthToken;
	}
	public AuthenticationResponse(String userName, String jwtAuthToken) {
		super();
		this.userName = userName;
		this.jwtAuthToken = jwtAuthToken;
	}
	public AuthenticationResponse() {
		super();
	}
	@Override
	public String toString() {
		return "AuthenticationResponse [userName=" + userName + ", jwtAuthToken=" + jwtAuthToken + "]";
	}
	
	
}