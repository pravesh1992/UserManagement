package com.lodwal.katalyst.business.objects;

import com.lodwal.katalyst.annotations.MaxLength;
import com.lodwal.katalyst.annotations.NotEmpty;
import com.lodwal.katalyst.annotations.NotNull;
import com.lodwal.katalyst.annotations.Pattern;

import java.io.Serializable;

public class User implements Serializable {

  private String userId;

  @NotNull
  @NotEmpty
  @MaxLength(value = 50)
  private String firstName;

  @NotNull
  @NotEmpty
  @MaxLength(value = 50)
  private String lastName;

  @Pattern(value = "^[0-3]?[0-9]-[0-3]?[0-9]-(?:[0-9]{2})?[0-9]{2}$")
  private String dob;

  @NotNull
  @NotEmpty
  @Pattern(value = "(0/91)?[7-9][0-9]{9}")
  private String mobileNo;

  @MaxLength
  private String address;

  @NotNull
  @NotEmpty
  @Pattern(value = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")
  @MaxLength
  private String emailId;

  @NotNull
  @NotEmpty
  @MaxLength
  private String password;

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

  public void setAddress(String address) {
    this.address = address;
  }
}
