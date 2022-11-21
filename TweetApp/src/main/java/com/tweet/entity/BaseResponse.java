package com.tweet.entity;

import java.io.Serializable;

/**
 * This is a base response class to send the status of a web service request
 * 
 * The code is used to send the success/error code.
 * 
 * The message variable is used to send the message
 *
 */
public class BaseResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String statusMessage;
	private int statusCode;

	private T data;

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public BaseResponse() {
	}

	public BaseResponse(String statusMessage, int statusCode) {
		this.statusMessage = statusMessage;
		this.statusCode = statusCode;
	}

	public BaseResponse(String statusMessage, int statusCode, T data) {
		this.statusMessage = statusMessage;
		this.statusCode = statusCode;
		this.data = data;
	}

}
