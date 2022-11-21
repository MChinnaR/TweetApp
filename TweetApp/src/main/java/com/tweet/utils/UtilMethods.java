package com.tweet.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilMethods {
	private static final Logger logger = LoggerFactory.getLogger(UtilMethods.class);

	public static long generateOtp() {
		int num = 0;
		try {
			int min = 111111;
			int max = 999999;
			num = (int) (Math.random() * (max - min + 1) + min);
		} catch (Exception e) {
			logger.info("Exception occured while generating the otp number", e);
		}
		return num;
	}

}
