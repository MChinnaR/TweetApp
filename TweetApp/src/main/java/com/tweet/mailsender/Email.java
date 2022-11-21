package com.tweet.mailsender;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Email {
	private static final Logger logger = LoggerFactory.getLogger(Email.class);

//	Before using this we have to enable the setting in mail 
//	open gmail --> security --> Below 2 step verification App Password 
	// select others--> create password copy password past in application.proprties

//	@Autowired
//	private JavaMailSender mailSender;

	public static final String MAILID = "chinna.mondem@gmail.com";
	public static final String PASSWORD = "bpblmrvqxklsqthi";
	private static Session session = null;

	private static Session getMailSession() {
		if (session == null) {
			try {
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.host", "smtp.gmail.com");
				if ("smtp.gmail.com".equalsIgnoreCase("smtp.gmail.com")) {
					props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
				} else {
					props.put("mail.smtp.ssl.trust", "*");
				}
				session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(Email.MAILID, Email.PASSWORD);
					}
				});
			} catch (Exception e) {
				logger.info("Exception occurred while initializing mail", e);
			}
		}
		return session;
	}

	public static boolean sendMail(String subject, String description, String toEmailAddress) {

		if (toEmailAddress == null || toEmailAddress.isEmpty() || subject == null || subject.isEmpty()
				|| description == null || description.isEmpty()) {
			logger.warn("Email address not found for sending email");
			return false;
		}
		boolean bool = true;
		try {
			Session session = getMailSession();
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Email.MAILID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress.trim()));
			message.setSubject(subject);
			message.setContent(description, "text/html; charset=utf-8");
			Transport.send(message);
			logger.warn("Email sent successful to " + toEmailAddress);
		} catch (Exception e) {
			logger.error("Error occurred while sending email", e);
			bool = false;
		}
		return bool;
	}

//	public static void sendEMail(String toMail, String subject, String body) throws TweetAppException {
//
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(MAILID);
//		message.setTo(toMail);
//		message.setSubject(subject);
//		message.setText(body);
//		try {
//			mailSender.send(message);
//			logger.info("mail send success to" + toMail);
//		} catch (Exception e) {
//			throw new TweetAppException("Exception occured while sending mail to user", e);
//		}
//	}

}
