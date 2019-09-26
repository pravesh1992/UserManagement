package com.lodwal.katalyst.business.objects;

import java.io.Serializable;

public class LoginResult implements Serializable {

  private User user;

  private ApplicationToken applicationToken;

  public LoginResult(User user, ApplicationToken applicationToken) {
    this.user = user;
    this.applicationToken = applicationToken;
  }

  public User getUser() {
    return user;
  }

  public ApplicationToken getApplicationToken() {
    return applicationToken;
  }
}