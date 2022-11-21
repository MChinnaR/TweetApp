package com.tweet.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user_details")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "1000")
	private int profileId;
	private String fullName;
	private String firstName;
	private String lastName;
	private int age;
	private String emailId;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String profileImage;
	private long registerdTimeStamp;
	private boolean updateProfile;
	private long profileUpdatedTime;
	private String dataOfBirth;

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public long getRegisterdTimeStamp() {
		return registerdTimeStamp;
	}

	public void setRegisterdTimeStamp(long registerdTimeStamp) {
		this.registerdTimeStamp = registerdTimeStamp;
	}

	public boolean isUpdateProfile() {
		return updateProfile;
	}

	public void setUpdateProfile(boolean updateProfile) {
		this.updateProfile = updateProfile;
	}

	public long getProfileUpdatedTime() {
		return profileUpdatedTime;
	}

	public void setProfileUpdatedTime(long profileUpdatedTime) {
		this.profileUpdatedTime = profileUpdatedTime;
	}

	public String getDataOfBirth() {
		return dataOfBirth;
	}

	public void setDataOfBirth(String dataOfBirth) {
		this.dataOfBirth = dataOfBirth;
	}

}
