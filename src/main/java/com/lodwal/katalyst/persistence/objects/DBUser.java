package com.lodwal.katalyst.persistence.objects;

import com.lodwal.katalyst.persistence.constants.TableNames;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = TableNames.USER)
public class DBUser implements Serializable {

  public static final String CLASS_CODE = "USER";

  // Id must be initialized by flamingo server
  @Id
  @Column(name = "user_id", length = 50)
  private String userId;

  @Column(name = "first_name", length = 50, nullable = false)
  private String firstName;

  @Column(name = "last_name", length = 50, nullable = false)
  private String lastName;

  @Column(name = "dob")
  private Date dob;

  @Column(name = "mobile_no", nullable = false)
  private String mobileNo;

  @Column(name = "address")
  private String address;

  @Column(name = "email_id", unique = true, nullable = false)
  private String emailId;

  @Column(name = "password", nullable = false)
  private String password;

  @Lob
  @Column(name = "salt", length = 65535)
  private byte[] salt;

  @Lob
  @Column(name = "profile_image", length = 10000000)
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

  public Date getDob() {
    return dob;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public String getAddress() {
    return address;
  }

  public String getEmailId() {
    return emailId;
  }

  public String getPassword() {
    return password;
  }

  public byte[] getSalt() {
    return salt;
  }

  public byte[] getProfileImage() {
    return profileImage;
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

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setSalt(byte[] salt) {
    this.salt = salt;
  }

  public void setProfileImage(byte[] profileImage) {
    this.profileImage = profileImage;
  }
}