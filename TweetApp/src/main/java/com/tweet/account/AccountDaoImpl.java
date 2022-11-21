package com.tweet.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tweet.entity.LoginDetails;
import com.tweet.entity.UserDetails;
import com.tweet.exception.TweetAppException;
import com.tweet.repositorys.LoginRepo;
import com.tweet.repositorys.UserDetailsRepo;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private UserDetailsRepo userRepo;
	@Autowired
	private LoginRepo loginRepo;

	@Override
	public UserDetails getDetailsByEmailId(String emailId) throws TweetAppException {
		try {
			return userRepo.getDetailsByEmailId(emailId);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while fetching data from userdetails ", e);
		}
	}

	@Override
	public UserDetails insertIntoUserDatails(UserDetails userDetails) throws TweetAppException {
		try {
			return userRepo.save(userDetails);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while inserting data into userdetails ", e);
		}
	}

	@Override
	public LoginDetails getOtpDetailsByEmail(String emailId) throws TweetAppException {
		try {
			return loginRepo.getOtpDetailsByEmail(emailId);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while fetching details from user otp", e);
		}
	}

	@Override
	public LoginDetails insertIntoUserOtps(LoginDetails toDb) throws TweetAppException {
		try {
			return loginRepo.save(toDb);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while inserting details to user otp", e);
		}
	}

}
