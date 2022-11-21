package com.tweet.exception;

public class TweetAppException extends Exception {

	private static final long serialVersionUID = -3965140316789517795L;

	public TweetAppException() {

	}

	public TweetAppException(String message) {
		super(message);
	}

	public TweetAppException(Throwable e) {
		super(e);
	}

	public TweetAppException(String message, Throwable e) {
		super(message, e);
	}

}
