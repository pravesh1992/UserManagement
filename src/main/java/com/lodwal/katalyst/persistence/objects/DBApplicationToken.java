package com.lodwal.katalyst.persistence.objects;

import com.lodwal.katalyst.business.apis.ApplicationTokenService;
import com.lodwal.katalyst.persistence.constants.TableNames;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = TableNames.APPLICATION_TOKEN)
public class DBApplicationToken implements Serializable {

  public static final String CLASS_CODE = "APPTOKEN";

  // Id must be initialized by flamingo server
  @Id
  @Column(name = "token_id", length = 50)
  private String tokenId;

  @Column(name = "user_id", length = 50, nullable = false)
  private String userId;

  @Column(name = "token_creation_time", nullable = false)
  private Timestamp tokenCreationTIme;

  @Column(name = "token_expiry_time", nullable = false)
  private Timestamp tokenExpiryTime;

  @Column(name = "expiry_seconds", nullable = false)
  private int expirySeconds = ApplicationTokenService.DEFAULT_EXPIRY_SECONDS;

  public DBApplicationToken() {
  }

  public DBApplicationToken(String tokenId) {
    this.tokenId = tokenId;
  }

  public String getTokenId() {
    return tokenId;
  }

  public String getUserId() {
    return userId;
  }

  public Timestamp getTokenCreationTime() {
    return tokenCreationTIme;
  }

  public Timestamp getTokenExpiryTime() {
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

  public void setTokenCreationTime(Timestamp tokenCreationTIme) {
    this.tokenCreationTIme = tokenCreationTIme;
  }

  public void setTokenExpiryTime(Timestamp tokenExpiryTime) {
    this.tokenExpiryTime = tokenExpiryTime;
  }

  public void setExpirySeconds(int expirySeconds) {
    this.expirySeconds = expirySeconds;
  }
}