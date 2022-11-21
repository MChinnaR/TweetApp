package com.tweet.tweets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweet.constants.ResponseConstants;
import com.tweet.entity.BaseResponse;
import com.tweet.entity.Followers;
import com.tweet.entity.PostDetails;
import com.tweet.entity.UserDetails;
import com.tweet.exception.TweetAppException;

@Service("tweetsservice")
public class TweetsService {

	@Autowired
	private TweetsDao dao;

	public BaseResponse<Void> publishUserPost(PostDetails postDetails) throws TweetAppException {
		try {
			postDetails.setPostTime(System.currentTimeMillis());
			if (dao.publishUserPost(postDetails) == null) {
				return new BaseResponse<Void>(ResponseConstants.FAIL, ResponseConstants.STATUS500);
			}
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while pulishing the post for user ", e);
		}
		return new BaseResponse<Void>(ResponseConstants.SUCESS, ResponseConstants.STATUS200);
	}

	public List<PostDetails> getPosts(int profileId, int from, int size) throws TweetAppException {
		try {
			List<PostDetails> list = profileId > 0 ? dao.getMyPosts(profileId, from, size) : dao.getPosts(from, size);
			return list;
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while fetching the post for user ", e);
		}
	}

	public List<UserDetails> getUsers(int from, int size) throws TweetAppException {
		try {
			return dao.getUsers(from, size);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while fetching the users for user ", e);
		}
	}

	public List<Followers> getFollowers(int profileId, int from, int size) throws TweetAppException {
		try {
			return dao.getFollowers(profileId, from, size);
		} catch (Exception e) {
			throw new TweetAppException("Exception occured while fetching the users for user ", e);
		}
	}

}
