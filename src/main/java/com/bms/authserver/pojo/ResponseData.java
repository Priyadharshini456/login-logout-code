package com.bms.authserver.pojo;

public class ResponseData {
	
	String username;
	
	String status;
	
	int code;
	
	String message;

	
	public ResponseData(String status, int code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message =message;
	}

	public ResponseData(String username, String status, int code, String message) {
		super();
		this.username = username;
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public ResponseData() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStstus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
