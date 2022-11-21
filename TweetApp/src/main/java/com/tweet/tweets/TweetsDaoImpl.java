package com.tweet.tweets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tweet.entity.Followers;
import com.tweet.entity.PostDetails;
import com.tweet.entity.UserDetails;
import com.tweet.exception.TweetAppException;
import com.tweet.repositorys.FollowersRepo;
import com.tweet.repositorys.UserDetailsRepo;
import com.tweet.repositorys.UserPostrepository;

@Repository("tweetsdao")
public class TweetsDaoImpl implements TweetsDao {

	@Autowired
	private UserPostrepository postrepo;
	@Autowired
	private UserDetailsRepo userrepo;
	@Autowired
	private FollowersRepo followRepo;

	@Override
	public PostDetails publishUserPost(PostDetails postDetails) throws TweetAppException {

		try {
			return postrepo.save(postDetails);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while pulishing the post for user daoimpl ", e);
		}
	}

	@Override
	public List<PostDetails> getPosts(int from, int size) throws TweetAppException {
		try {
			return postrepo.findAllWithSize(from, size);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while fetching the post for user daoimpl ", e);
		}
	}

	@Override
	public List<PostDetails> getMyPosts(int profileId, int from, int size) throws TweetAppException {
		try {
			return postrepo.findWithProfileId(profileId, from, size);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while fetching the post for user daoimpl ", e);
		}
	}

	@Override
	public List<Followers> getFollowers(int profileId, int from, int size) throws TweetAppException {
		try {
			return followRepo.getFollowers(profileId, from, size);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while fetching the post for user daoimpl ", e);
		}
	}

	@Override
	public List<UserDetails> getUsers(int from, int size) throws TweetAppException {
		try {
			return userrepo.getUsers(from, size);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while fetching the post for user daoimpl ", e);
		}
	}

}
