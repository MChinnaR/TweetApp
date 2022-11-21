package com.tweet.constants;

public class ResponseConstants {

	// FOR SUCCESS CODES
	public static final int STATUS200 = 200; // OK
	public static final int STATUS201 = 201; // created successfully
	public static final int STATUS401 = 401; // Unauthorized
	public static final int STATUS403 = 403; // Forbidden
	public static final int STATUS404 = 404; // Not Found
	public static final int STATUS405 = 405; // Method Not Allowed
	public static final int STATUS409 = 409;// Advice already active Or Conflict
	public static final int STATUS500 = 500; // Internal Server Error
	public static final int STATUS503 = 503;// Service Unavailable
	public static final int STATUS524 = 524;// A Timeout Occurred;

	// FOR STATS MESSAGES
	public static final String SUCESS = "Sucessfull!!";
	public static final String FAIL = "Something went wrong plz try again!!";
	public static final String REGISTRATION_SUCESS = "You have registered Sucessfully, Tnks for joing to our family Njoy....!!";
	public static final String EMAIL_EXIST = "This emailId is already registered";
	public static final String UPDATE_SUCCESS = "Profile updated successfully";
	public static final String LOGIN_SUCCESS = "Login Successful";
	public static final String EMAIL_DOESNT_EXIST = "This emailId doesn't exist or Email entered is wrong";
	public static final String WRONG_PASSWORD = "You have entered wrong password";
	public static final String OTP_SUCCESS = "OTP send successfully to your mailId";
	public static final String NO_OTP = "There is no OTP found on your emailId, Plz GENERATE OTP and come back again";
	public static final String OTP_EXPIRED = "OTP has been expired, Generate new OTP and try again";
	public static final String WRONG_OTP = "OTP entered by you is Wrong";
	public static final String PASSWORD_CHANGED_SUCCESSFULLY = "Password changed successfully";
	public static final String OLD_PASSWORD_WRONG = "Old Password you have entered wrong";

}
