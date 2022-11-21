package com.tweet.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tweet.constants.DataBaseConstants;
import com.tweet.entity.LoginDetails;

public interface LoginRepo extends JpaRepository<LoginDetails, Integer> {

	@Query(value = "SELECT * FROM " + DataBaseConstants.DATABASE_NAME + "." + DataBaseConstants.USER_OTPS
			+ " where email_id=:emailId", nativeQuery = true)
	LoginDetails getOtpDetailsByEmail(String emailId);

}
