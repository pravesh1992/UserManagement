package com.lodwal.katalyst.business.objects;

import java.io.Serializable;
import java.util.Date;

public class ApplicationToken implements Serializable {

  private String tokenId;

  private String userId;

  private Date tokenCreationTime;

  private Date tokenExpiryTime;

  private int expirySeconds;

  public String getTokenId() {
    return tokenId;
  }

  public String getUserId() {
    return userId;
  }

  public Date getTokenCreationTime() {
    return tokenCreationTime;
  }

  public Date getTokenExpiryTime() {
    return tokenExpiryTime;
  }

  public int getExpirySeconds() {
    return expirySeconds;
  }

  public void setTokenId(String tokenId) {
    this.tokenId = tokenId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setTokenCreationTime(Date tokenCreationTime) {
    this.tokenCreationTime = tokenCreationTime;
  }

  public void setTokenExpiryTime(Date tokenExpiryTime) {
    this.tokenExpiryTime = tokenExpiryTime;
  }

  public void setExpirySeconds(int expirySeconds) {
    this.expirySeconds = expirySeconds;
  }
}