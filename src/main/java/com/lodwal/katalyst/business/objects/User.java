package com.lodwal.katalyst.business.objects;

import com.lodwal.katalyst.annotations.NotEmpty;
import com.lodwal.katalyst.annotations.NotNull;

import java.io.Serializable;

public class User implements Serializable {

  private String userId;

  @NotNull
  @NotEmpty
  private String firstName;

  @NotNull
  @NotEmpty
  private String lastName;

  private String dob;

  @NotNull
  @NotEmpty
  private String mobileNo;

  private String address;

  @NotNull
  @NotEmpty
  private String emailId;

  @NotNull
  @NotEmpty
  private String password;

  private byte[] profileImage;

  public String getUserId() {
    return userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getDob() {
    return dob;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public String getEmailId() {
    return emailId;
  }

  public String getPassword() {
    return password;
  }

  public byte[] getProfileImage() {
    return profileImage;
  }

  public String getAddress() {
    return address;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setProfileImage(byte[] profileImage) {
    this.profileImage = profileImage;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
