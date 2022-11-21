package com.tweet.tweets;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweet.entity.BaseResponse;
import com.tweet.entity.Followers;
import com.tweet.entity.PostDetails;
import com.tweet.entity.UserDetails;

@RestController
@RequestMapping("/tweets")
public class TweetsController {

	private final static Logger logger = LoggerFactory.getLogger(TweetsController.class);
	@Autowired
	private TweetsService service;

	@PostMapping("/post")
	public BaseResponse<Void> publishUserPost(@RequestBody PostDetails postDetails) {
		try {
			return service.publishUserPost(postDetails);
		} catch (Exception e) {
			logger.info("Exception occured while pulishing the post for user ", e);
		}
		return null;
	}

	@GetMapping("/getposts")
	public List<PostDetails> getPosts(@RequestParam(value = "from", required = false, defaultValue = "0") int from,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "profileid", required = false, defaultValue = "0") int profileId) {
		try {
			return service.getPosts(profileId, from, size);
		} catch (Exception e) {
			logger.info("Exception occured while getting posts for user ", e);
		}
		return new ArrayList<PostDetails>();
	}

	@GetMapping("/getusers")
	public List<UserDetails> getUsers(@RequestParam(value = "from", required = false, defaultValue = "0") int from,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "profileId", required = false, defaultValue = "0") int profileId) {
		try {
			return service.getUsers(from, size);
		} catch (Exception e) {
			logger.info("Exception occured while getting posts for user ", e);
		}
		return new ArrayList<UserDetails>();
	}

	@GetMapping("/getfollowers")
	public List<Followers> getFollowers(@RequestParam(value = "from", required = false, defaultValue = "0") int from,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "profileid", required = false, defaultValue = "0") int profileId) {
		try {
			return service.getFollowers(profileId, from, size);
		} catch (Exception e) {
			logger.info("Exception occured while getting posts for user ", e);
		}
		return new ArrayList<Followers>();
	}
}
