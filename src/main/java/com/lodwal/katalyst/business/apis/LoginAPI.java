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
      String encryotedUserInputPassowrd = PasswordAPI.getSecuredPassword(decodedPassword, dbUser.getSalt());
      if (!encryotedUserInputPassowrd.equals(dbUser.getPassword()))
        throw new ApplicationException(ApplicationErrorCode.INVALID_CREDENTIALS, ApplicationErrorCode.INVALID_CREDENTIALS.getMessage());
      // First clear all existing application tokens
      applicationTokenAPI.clearAllApplicationTokens(dbUser.getUserId());
      ApplicationToken applicationToken = this.applicationTokenAPI.createApplicationToken(dbUser.getUserId());
      return new LoginResult(UserService.convert(dbUser), applicationToken);
    } catch (Exception exception) {
      throw ApplicationException.analyzeException(exception);
    }
  }
}