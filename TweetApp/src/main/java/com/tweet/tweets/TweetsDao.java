package com.tweet.tweets;

import java.util.List;

import com.tweet.entity.Followers;
import com.tweet.entity.PostDetails;
import com.tweet.entity.UserDetails;
import com.tweet.exception.TweetAppException;

public interface TweetsDao {

	PostDetails publishUserPost(PostDetails postDetails) throws TweetAppException;

	List<PostDetails> getPosts(int from, int size) throws TweetAppException;

	List<PostDetails> getMyPosts(int profileId, int from, int size) throws TweetAppException;

	List<Followers> getFollowers(int profileId, int from, int size) throws TweetAppException;

	List<UserDetails> getUsers(int from, int size) throws TweetAppException;

}
