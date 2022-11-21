package com.tweet.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweet.constants.EmailResponse;
import com.tweet.constants.ResponseConstants;
import com.tweet.entity.BaseResponse;
import com.tweet.entity.LoginDetails;
import com.tweet.entity.LoginResponse;
import com.tweet.entity.UserDetails;
import com.tweet.exception.TweetAppException;
import com.tweet.mailsender.Email;
import com.tweet.utils.PasswordEncDec;
import com.tweet.utils.UtilMethods;

@Service("accountService")
public class AccountService {
	Logger logger = LoggerFactory.getLogger(AccountService.class);
	@Autowired
	private AccountDao dao;

	public BaseResponse<Void> registerUser(UserDetails userDetails) throws TweetAppException {

		try {
			if (userDetails.isUpdateProfile()) {
				UserDetails fromDb = dao.getDetailsByEmailId(userDetails.getEmailId());
				if (fromDb != null) {
					fromDb.setFirstName(userDetails.getFirstName());
					fromDb.setLastName(userDetails.getLastName());
					fromDb.setDataOfBirth(userDetails.getDataOfBirth());
					fromDb.setProfileImage(userDetails.getProfileImage());
					fromDb.setUpdateProfile(true);
					userDetails.setProfileUpdatedTime(System.currentTimeMillis());
					if (dao.insertIntoUserDatails(fromDb) != null)
						return new BaseResponse<Void>(ResponseConstants.UPDATE_SUCCESS, ResponseConstants.STATUS200);
					else
						return new BaseResponse<Void>(ResponseConstants.FAIL, ResponseConstants.STATUS500);
				} else
					return new BaseResponse<Void>(ResponseConstants.EMAIL_EXIST, ResponseConstants.STATUS404);
			} else if (dao.getDetailsByEmailId(userDetails.getEmailId()) == null) {
				String firstName = userDetails.getFirstName().isBlank() ? "" : userDetails.getFirstName() + " ";
				String lastName = userDetails.getLastName().isBlank() ? "" : userDetails.getLastName();
				userDetails.setFullName(firstName + lastName);
				userDetails.setRegisterdTimeStamp(System.currentTimeMillis());
				userDetails.setPassword(PasswordEncDec.encryptingPassword(userDetails.getPassword()));
				if (dao.insertIntoUserDatails(userDetails) != null)
					Email.sendMail(EmailResponse.REGISTRATION_SUCCESS,
							String.format(EmailResponse.REGISTRATION_DESCRIPTION, userDetails.getFullName()),
							userDetails.getEmailId());
			} else
				return new BaseResponse<Void>(ResponseConstants.EMAIL_EXIST, ResponseConstants.STATUS404);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while registering the user ", e);
		}
		return new BaseResponse<Void>(ResponseConstants.REGISTRATION_SUCESS, ResponseConstants.STATUS201);
	}

	public LoginResponse login(LoginDetails loginDetails) throws TweetAppException {
		try {
			UserDetails fromDb = dao.getDetailsByEmailId(loginDetails.getEmailId());
			if (fromDb != null) {
				// create sessionId
				return PasswordEncDec.bCrypter(loginDetails.getPassword(), fromDb.getPassword())
						? new LoginResponse(ResponseConstants.LOGIN_SUCCESS, ResponseConstants.STATUS200, "sessionId",
								fromDb)
						: new LoginResponse(ResponseConstants.WRONG_PASSWORD, ResponseConstants.STATUS404);
			} else
				return new LoginResponse(ResponseConstants.EMAIL_DOESNT_EXIST, ResponseConstants.STATUS404);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while login the user ", e);
		}
	}

	public BaseResponse<Void> generateOtpForUser(String emailId) throws TweetAppException {
		try {
			UserDetails fromDb = dao.getDetailsByEmailId(emailId);
			if (fromDb != null) {
				long otp = UtilMethods.generateOtp();
				otp = otp > 0 ? otp : 6789;
				if (Email.sendMail(EmailResponse.OTP_SEND_SUCCESS,
						String.format(EmailResponse.OTP_DESCRIPTION, fromDb.getFirstName(), otp),
						fromDb.getEmailId())) {
					LoginDetails toDb = dao.getOtpDetailsByEmail(emailId);
					toDb = toDb == null ? new LoginDetails() : toDb;
					toDb.setProfileId(fromDb.getProfileId());
					toDb.setEmailId(emailId);
					toDb.setOtp(otp);
					toDb.setExpireTime(System.currentTimeMillis() + 300000);
					if (dao.insertIntoUserOtps(toDb) == null)
						return new BaseResponse<Void>(ResponseConstants.FAIL, ResponseConstants.STATUS500);
				}
			} else
				return new BaseResponse<Void>(ResponseConstants.EMAIL_DOESNT_EXIST, ResponseConstants.STATUS404);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while generating otp for login the user ", e);
		}
		return new BaseResponse<Void>(ResponseConstants.OTP_SUCCESS, ResponseConstants.STATUS200);
	}

	public LoginResponse loginUsingOTP(String emailId, long otp) throws TweetAppException {
		LoginResponse login = null;
		try {
			// create sessionId
			login = verifyOtp(emailId, otp);
			login.setSessionId("empty");
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while fetching data from userotps the user ", e);
		}
		return login;
	}

	public BaseResponse<Void> forgetPassword(LoginDetails loginDetails) throws TweetAppException {
		UserDetails fromDb = null;
		LoginResponse login = null;
		try {
			login = verifyOtp(loginDetails.getEmailId(), loginDetails.getOtp());
			if (login != null && login.getStatusCode() == ResponseConstants.STATUS200) {
				fromDb = login.getUserDetails();
				fromDb.setPassword(PasswordEncDec.encryptingPassword(loginDetails.getPassword()));
				fromDb.setUpdateProfile(true);
				fromDb.setProfileUpdatedTime(System.currentTimeMillis());
				if (dao.insertIntoUserDatails(fromDb) != null)
					Email.sendMail(EmailResponse.PASSWORD_CHANGE_SUCCESS,
							String.format(EmailResponse.PASSWORD_CHANGE_DESCRIPTION, fromDb.getFullName()),
							fromDb.getEmailId());
				login.setStatusMessage(ResponseConstants.PASSWORD_CHANGED_SUCCESSFULLY);
			}
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while checking otp for forgetpassword the user ", e);
		}
		return new BaseResponse<Void>(login.getStatusMessage(), login.getStatusCode());
	}

	public LoginResponse verifyOtp(String emailId, long otp) throws TweetAppException {
		UserDetails fromDb = null;
		try {
			fromDb = dao.getDetailsByEmailId(emailId);
			if (fromDb != null) {
				LoginDetails otpFromDb = dao.getOtpDetailsByEmail(emailId);
				if (otpFromDb != null) {
					logger.info(otpFromDb.getExpireTime() + " -->" + System.currentTimeMillis());
					if (otpFromDb.getExpireTime() < System.currentTimeMillis())
						return new LoginResponse(ResponseConstants.OTP_EXPIRED, ResponseConstants.STATUS404);
					if (otpFromDb.getOtp() != otp)
						return new LoginResponse(ResponseConstants.WRONG_OTP, ResponseConstants.STATUS404);
				} else
					return new LoginResponse(ResponseConstants.NO_OTP, ResponseConstants.STATUS404);
			} else
				return new LoginResponse(ResponseConstants.EMAIL_DOESNT_EXIST, ResponseConstants.STATUS404);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while verifying userotps the user ", e);
		}
		return new LoginResponse(ResponseConstants.LOGIN_SUCCESS, ResponseConstants.STATUS200, "sessionId", fromDb);
	}

	public BaseResponse<Void> changePassword(LoginDetails loginDetails) throws TweetAppException {
		UserDetails fromDb = null;
		try {
			fromDb = dao.getDetailsByEmailId(loginDetails.getEmailId());
			if (fromDb != null) {
				if (PasswordEncDec.bCrypter(loginDetails.getOldPassword(), fromDb.getPassword())) {
					fromDb.setPassword(PasswordEncDec.encryptingPassword(loginDetails.getPassword()));
					fromDb.setUpdateProfile(true);
					fromDb.setProfileUpdatedTime(System.currentTimeMillis());
					if (dao.insertIntoUserDatails(fromDb) != null)
						Email.sendMail(EmailResponse.PASSWORD_CHANGE_SUCCESS,
								String.format(EmailResponse.PASSWORD_CHANGE_DESCRIPTION, fromDb.getFullName()),
								fromDb.getEmailId());
				} else
					return new BaseResponse<Void>(ResponseConstants.OLD_PASSWORD_WRONG, ResponseConstants.STATUS200);
			} else
				return new BaseResponse<Void>(ResponseConstants.EMAIL_DOESNT_EXIST, ResponseConstants.STATUS200);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while changing password the user ", e);
		}
		return new BaseResponse<Void>(ResponseConstants.PASSWORD_CHANGED_SUCCESSFULLY, ResponseConstants.STATUS200);
	}
}
