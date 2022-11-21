package com.tweet.account;

import com.tweet.entity.LoginDetails;
import com.tweet.entity.UserDetails;
import com.tweet.exception.TweetAppException;

public interface AccountDao {

	UserDetails getDetailsByEmailId(String emailId) throws TweetAppException;

	UserDetails insertIntoUserDatails(UserDetails userDetails) throws TweetAppException;

	LoginDetails getOtpDetailsByEmail(String emailId) throws TweetAppException;

	LoginDetails insertIntoUserOtps(LoginDetails toDb) throws TweetAppException;
}
