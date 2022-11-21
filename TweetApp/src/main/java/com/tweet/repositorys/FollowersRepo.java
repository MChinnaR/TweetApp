package com.tweet.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweet.constants.DataBaseConstants;
import com.tweet.entity.Followers;

@Repository
public interface FollowersRepo extends JpaRepository<Followers, Integer> {

	@Query(value = "SELECT f.follower_profile_id,f.following_back,u.profile_image,u.full_name FROM "
			+ DataBaseConstants.DATABASE_NAME + "." + DataBaseConstants.USER_FOLLOWERS + " f JOIN "
			+ DataBaseConstants.DATABASE_NAME + "." + DataBaseConstants.USER_DETAILS
			+ " u ON u.profile_id = f.follower_profile_id WHERE f.profile_id :profileId "
			+ "ORDER BY u.full_name LIMIT :size OFFSET :from", nativeQuery = true)
	List<Followers> getFollowers(int profileId, int from, int size);
}
