package com.tweet.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweet.constants.DataBaseConstants;
import com.tweet.entity.UserDetails;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {

	@Query(value = "SELECT * FROM " + DataBaseConstants.DATABASE_NAME + "." + DataBaseConstants.USER_DETAILS
			+ " where email_id=:emailId", nativeQuery = true)
	UserDetails getDetailsByEmailId(String emailId);

	@Query(value = "SELECT * FROM " + DataBaseConstants.DATABASE_NAME + "." + DataBaseConstants.USER_DETAILS
			+ " ORDER BY first_name LIMIT :size OFFSET :from", nativeQuery = true)
	List<UserDetails> getUsers(int from, int size);

}
