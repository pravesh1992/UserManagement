package com.lodwal.katalyst.business.apis;

import com.lodwal.katalyst.business.objects.User;
import com.lodwal.katalyst.exception.ApplicationErrorCode;
import com.lodwal.katalyst.exception.ApplicationException;
import com.lodwal.katalyst.persistence.objects.DBUser;
import com.lodwal.katalyst.persistence.repository.UserJpaRepository;
import com.lodwal.katalyst.utils.ObjectValidator;
import com.lodwal.katalyst.utils.Utility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class UserService {

  private static SimpleDateFormat simpleDateFormat;

  private static String pattern = "dd-MM-yyyy";

  static {
    simpleDateFormat = new SimpleDateFormat(pattern);
  }

  @Autowired
  UserJpaRepository userJpaRepository;

  public User register(User user) throws ApplicationException {
    ObjectValidator.validate(user, "user");
    try {
      DBUser dbUserExisting = this.userJpaRepository.findByEmailId(user.getEmailId());
      if (dbUserExisting != null)
        throw new ApplicationException(ApplicationErrorCode.INTERNAL_SERVER_ERROR, "user already exists in system with email id:" + user.getEmailId());
      byte[] salt = PasswordAPI.getSalt();
      String encryptedPassword = PasswordAPI.getSecuredPassword(user.getPassword(), salt);
      DBUser dbUser = convert(user);
      dbUser.setSalt(salt);
      dbUser.setPassword(encryptedPassword);
      dbUser = this.userJpaRepository.saveAndFlush(dbUser);
      return convert(dbUser);
    } catch (Exception exception) {
      throw ApplicationException.analyzeException(exception);
    }
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
    return user;
  }

  public static DBUser convert(User user) throws ApplicationException {
    String userId = DBUser.CLASS_CODE + "_" + Utility.GUID("");
    if (!StringUtils.isBlank(user.getUserId()) && !StringUtils.isEmpty(user.getUserId())) {
      userId = user.getUserId();
    }
    DBUser dbUser = new DBUser();
    dbUser.setUserId(userId);
    dbUser.setFirstName(user.getFirstName());
    dbUser.setLastName(user.getLastName());
    dbUser.setEmailId(user.getEmailId());
    dbUser.setAddress(user.getAddress());
    dbUser.setMobileNo(user.getMobileNo());
    if (!StringUtils.isBlank(user.getDob()) && !StringUtils.isEmpty(user.getDob())) {
      try {
        dbUser.setDob(new Date(simpleDateFormat.parse(user.getDob()).getTime()));
      } catch (ParseException parseException) {
        throw new ApplicationException(ApplicationErrorCode.INVALID_ATTRIBUTE_VALUE, "Dob is not valid :" + user.getDob());
      }
    }
    return dbUser;
  }
}
