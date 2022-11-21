package com.tweet.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweet.constants.ResponseConstants;
import com.tweet.entity.BaseResponse;
import com.tweet.entity.LoginDetails;
import com.tweet.entity.LoginResponse;
import com.tweet.entity.UserDetails;

@RestController
@RequestMapping("/account")
public class AccountController {

	private final static Logger logger = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private AccountService service;

	@PostMapping(value = "/registeruser", headers = "Accept=application/json")
	public BaseResponse<Void> registerUser(@RequestBody UserDetails userDetails) {
		BaseResponse<Void> customResponse = null;
		try {
			customResponse = service.registerUser(userDetails);
		} catch (Exception e) {
			logger.info("Exception occured while registering the user controller", e);
		}
		return customResponse;
	}

	@PostMapping(value = "/login", headers = "Accept=application/json")
	public LoginResponse login(@RequestBody LoginDetails loginDetails) {

		LoginResponse userDetails = null;
		try {
			userDetails = service.login(loginDetails);
		} catch (Exception e) {
			logger.info("Exception occured while login using password the user controller", e);
		}
		return userDetails;
	}

	@GetMapping(value = "/generateotp", headers = "Accept=application/json")
	public BaseResponse<Void> generateOtpForUser(@RequestParam String emailId) {
		try {
			return service.generateOtpForUser(emailId);
		} catch (Exception e) {
			logger.info("Exception occured while generating otp user controller", e);
		}
		return new BaseResponse<Void>(ResponseConstants.FAIL, ResponseConstants.STATUS500);
	}

	@GetMapping(value = "/loginthroughOTP", headers = "Accept=application/json")
	public LoginResponse loginUsingOTP(@RequestParam String emailId, @RequestParam long otp) {
		try {
			return service.loginUsingOTP(emailId, otp);
		} catch (Exception e) {
			logger.info("Exception occured while login using otp the user controller", e);
		}
		return new LoginResponse(ResponseConstants.FAIL, ResponseConstants.STATUS500);
	}

	@PostMapping("/forgetandresetpassword")
	public BaseResponse<Void> forgetPassword(@RequestBody LoginDetails loginDetails) {
		try {
			return service.forgetPassword(loginDetails);
		} catch (Exception e) {
			logger.info("Exception occured while checking forget password user controller", e);
		}
		return new BaseResponse<Void>(ResponseConstants.FAIL, ResponseConstants.STATUS500);
	}

	@PostMapping("/secure/changePassword")
	public BaseResponse<Void> changePassword(@RequestBody LoginDetails loginDetails) {
		try {
			// old and new password must be different
			return service.changePassword(loginDetails);
		} catch (Exception e) {
			logger.info("Exception occured while changing password user controller", e);
		}
		return new BaseResponse<Void>(ResponseConstants.FAIL, ResponseConstants.STATUS500);
	}

	@GetMapping(value = "/testing", headers = "Accept=application/json")
	public String testing(@RequestParam String name) {
		return "this is ur name " + name;
	}

}
