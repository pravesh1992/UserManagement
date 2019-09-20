package com.lodwal.katalyst.business.apis;

import com.lodwal.katalyst.business.objects.User;
import com.lodwal.katalyst.persistence.objects.DBUser;

public class UserAPI {

  public static User convert(DBUser dbUser) {
    User user = new User();
    user.setUserId(dbUser.getUserId());
    user.setFirstName(dbUser.getFirstName());
    user.setLastName(dbUser.getLastName());
    user.setEmailId(dbUser.getEmailId());
    user.setAddress(dbUser.getAddress());
    user.setMobileNo(dbUser.getMobileNo());
    user.setDob(dbUser.getDob().toString());
    user.setProfileImage(dbUser.getProfileImage());
    return user;
  }
}
