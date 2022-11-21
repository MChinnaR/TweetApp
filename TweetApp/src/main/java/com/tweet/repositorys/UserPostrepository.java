package com.tweet.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweet.constants.DataBaseConstants;
import com.tweet.entity.PostDetails;

@Repository
public interface UserPostrepository extends JpaRepository<PostDetails, Integer> {

	@Query(value = "SELECT * FROM " + DataBaseConstants.DATABASE_NAME + "." + DataBaseConstants.USER_POSTS
			+ " ORDER BY post_time LIMIT :size OFFSET :from", nativeQuery = true)
	List<PostDetails> findAllWithSize(int from, int size);

	@Query(value = "SELECT * FROM " + DataBaseConstants.DATABASE_NAME + "." + DataBaseConstants.USER_POSTS
			+ " WHERE profile_id :profileId ORDER BY post_time LIMIT :size OFFSET :from", nativeQuery = true)
	List<PostDetails> findWithProfileId(int profileId, int from, int size);

}
