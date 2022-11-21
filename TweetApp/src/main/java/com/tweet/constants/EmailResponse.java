package com.tweet.constants;

public class EmailResponse {

	public static final String REGISTRATION_SUCCESS = "REGISTRATION_SUCCESSFUL_TWEET_APP";
	public static final String OTP_SEND_SUCCESS = "OTP_SENT_SUCCESSFULLY_FROM_TWEET_APP";
	public static final String PASSWORD_CHANGE_SUCCESS = "PASSWORD_CHANGED_SUCCESSFULLY_FOR_TWEET_APP";

	// MAIL DISCRIPTIONS

	public static final String REGISTRATION_DESCRIPTION = "Hi <b>%1$s"
			+ "</b>,<br><br>You have Successfully registered with <b>TWEET APP.</b><br>"
			+ "<h2>Share your memories with your friends and family members</h2>"
			+ "<img src=\"https://e7.pngegg.com/pngimages/52/363/png-clipart-tweeter-logo-scalable-graphics-icon-twitter-blue-logo-thumbnail.png\"alt=\"Girl in a jacket\" width=\"500\" height=\"600\">";

	public static final String OTP_DESCRIPTION = "Hi <b>%1$s</b><br><br>This is your One Time Password <b> %2$s</b>. <br>This will be valid for 5 mins only, Don't share with anyone.<img src=\"https://e7.pngegg.com/pngimages/52/363/png-clipart-tweeter-logo-scalable-graphics-icon-twitter-blue-logo-thumbnail.png\"alt=\"Girl in a jacket\" width=\"500\" height=\"600\">";

	public static final String PASSWORD_CHANGE_DESCRIPTION = "Hi <b>%1$s"
			+ "</b>,<br><br>You have Successfully changed password for <b>TWEET APP.</b><br>"
			+ "<img src=\"https://e7.pngegg.com/pngimages/52/363/png-clipart-tweeter-logo-scalable-graphics-icon-twitter-blue-logo-thumbnail.png\"alt=\"Girl in a jacket\" width=\"500\" height=\"600\">";
}
