package com.lodwal.katalyst.business.apis;

import com.lodwal.katalyst.business.objects.ApplicationToken;
import com.lodwal.katalyst.business.objects.LoginResult;
import com.lodwal.katalyst.exception.ApplicationErrorCode;
import com.lodwal.katalyst.exception.ApplicationException;
import com.lodwal.katalyst.persistence.objects.DBUser;
import com.lodwal.katalyst.persistence.repository.UserJpaRepository;
import com.lodwal.katalyst.utils.Utility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginAPI {

  @Autowired
  UserJpaRepository userJpaRepository;

  @Autowired
  ApplicationTokenAPI applicationTokenAPI;

  public LoginResult login(final String emailId, final String securedPassword) throws ApplicationException {
    if (StringUtils.isEmpty(emailId))
      throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, ApplicationErrorCode.INVALID_PARAMETER_VALUE.getMessage() + " emailId:" + emailId);
    if (StringUtils.isEmpty(securedPassword))
      throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, ApplicationErrorCode.INVALID_PARAMETER_VALUE.getMessage() + " of securedPassword");
    try {
      String decodedPassword = Utility.decode(securedPassword);
      DBUser dbUser = this.userJpaRepository.findByEmailId(emailId);
      if (dbUser == null)
        throw new ApplicationException(ApplicationErrorCode.ERROR_INVALID_ID, "Email id doesn't exists, emailId:" + emailId);
      String userPassword = PasswordAPI.getSecuredPassword(decodedPassword, dbUser.getSalt());
      if (!userPassword.equals(dbUser.getPassword()))
        throw new ApplicationException(ApplicationErrorCode.INVALID_CREDENTIALS, ApplicationErrorCode.INVALID_CREDENTIALS.getMessage());
      // First clear all existing application tokens
      applicationTokenAPI.clearAllApplicationTokens(dbUser.getUserId());
      ApplicationToken applicationToken = this.applicationTokenAPI.createApplicationToken(dbUser.getUserId());
      return new LoginResult(UserService.convert(dbUser), applicationToken);
    } catch (Exception exception) {
      throw ApplicationException.analyzeException(exception);
    }
  }

  public void changePassword(final String emailId, final String oldPassword, final String newPassword) throws ApplicationException {
    if (StringUtils.isEmpty(emailId))
      throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, ApplicationErrorCode.INVALID_PARAMETER_VALUE.getMessage() + " emailId:" + emailId);
    if (StringUtils.isEmpty(oldPassword))
      throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, ApplicationErrorCode.INVALID_PARAMETER_VALUE.getMessage() + " of oldPassword");
    if (StringUtils.isEmpty(newPassword))
      throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, ApplicationErrorCode.INVALID_PARAMETER_VALUE.getMessage() + " of newPassword");
    try {
      DBUser dbUser = this.userJpaRepository.findByEmailId(emailId);
      if (dbUser == null)
        throw new ApplicationException(ApplicationErrorCode.ERROR_INVALID_ID, "Email id doesn't exists, emailId:" + emailId);
      String oldDecodedPassword = Utility.decode(oldPassword);
      String userOldPassword = PasswordAPI.getSecuredPassword(oldDecodedPassword, dbUser.getSalt());
      if (!userOldPassword.equals(dbUser.getPassword()))
        throw new ApplicationException(ApplicationErrorCode.INVALID_CREDENTIALS, "Given old password is not last password, try again with correct password");
      String newDecodedPassword = Utility.decode(newPassword);
      if (newDecodedPassword.equals(oldDecodedPassword))
        throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, "New password can't be same old password");
      byte[] newSalt = PasswordAPI.getSalt();
      String encryptedNewPassword = PasswordAPI.getSecuredPassword(newDecodedPassword, newSalt);
      dbUser.setSalt(newSalt);
      dbUser.setPassword(encryptedNewPassword);
      this.userJpaRepository.save(dbUser);
      this.applicationTokenAPI.clearAllApplicationTokens(dbUser.getUserId());
    } catch (
      Exception exception) {
      throw ApplicationException.analyzeException(exception);
    }
  }
}