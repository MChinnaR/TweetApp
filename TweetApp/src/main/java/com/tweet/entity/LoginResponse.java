package com.tweet.entity;

public class LoginResponse {

	private String statusMessage;
	private int statusCode;
	private String sessionId;
	private UserDetails UserDetails;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public UserDetails getUserDetails() {
		return UserDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		UserDetails = userDetails;
	}

	public LoginResponse(String statusMessage, int statusCode, String sessionId,
			com.tweet.entity.UserDetails userDetails) {
		super();
		this.statusMessage = statusMessage;
		this.statusCode = statusCode;
		this.sessionId = sessionId;
		UserDetails = userDetails;
	}

	public LoginResponse(String statusMessage, int statusCode) {
		super();
		this.statusMessage = statusMessage;
		this.statusCode = statusCode;
	}

	public LoginResponse() {
	}
}
