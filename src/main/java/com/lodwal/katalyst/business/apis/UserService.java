package com.lodwal.katalyst.business.apis;

import com.lodwal.katalyst.business.objects.User;
import com.lodwal.katalyst.exception.ApplicationErrorCode;
import com.lodwal.katalyst.exception.ApplicationException;
import com.lodwal.katalyst.persistence.objects.DBUser;
import org.springframework.stereotype.Service;

@Service
public class UserService {


  public User register(User user) throws ApplicationException {
    if (user == null)
      throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, "user can't be null or empty");


    return null;
  }

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
