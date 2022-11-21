package com.tweet.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "followers_following")
public class Followers {

	@Id
	private int profileId;
	private int followerProfileId;
	private String fullName;
	private String profileImage;
	private boolean followingBack;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id")
	private UserDetails details;

	public UserDetails getDetails() {
		return details;
	}

	public void setDetails(UserDetails details) {
		this.details = details;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public int getFollowerProfileId() {
		return followerProfileId;
	}

	public void setFollowerProfileId(int followerProfileId) {
		this.followerProfileId = followerProfileId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public boolean isFollowingBack() {
		return followingBack;
	}

	public void setFollowingBack(boolean followingBack) {
		this.followingBack = followingBack;
	}

}
