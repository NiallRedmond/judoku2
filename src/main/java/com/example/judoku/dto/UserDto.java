package com.example.judoku.dto;

public class UserDto {

	private long userId;
	private String userName;
	private boolean weighedIn;
	private String message;
	
	public UserDto() {
		super();
	}
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isWeighedIn() {
		return weighedIn;
	}
	public void setWeighedIn(boolean weighedIn) {
		this.weighedIn = weighedIn;
	}
	
	
	
	
	
	
	
}
