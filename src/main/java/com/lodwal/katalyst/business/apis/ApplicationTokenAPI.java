package com.lodwal.katalyst.business.apis;

import com.lodwal.katalyst.business.objects.ApplicationToken;
import com.lodwal.katalyst.exception.ApplicationErrorCode;
import com.lodwal.katalyst.exception.ApplicationException;
import com.lodwal.katalyst.persistence.objects.DBApplicationToken;
import com.lodwal.katalyst.persistence.repository.ApplicationTokenJpaRepository;
import com.lodwal.katalyst.utils.Utility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ApplicationTokenAPI {

  public static final int DEFAULT_EXPIRY_SECONDS = 300;

  @Autowired
  private ApplicationTokenJpaRepository applicationTokenJpaRepository;

  public void clearAllApplicationTokens(String userId) throws ApplicationException {
    if (StringUtils.isEmpty(userId))
      throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, ApplicationErrorCode.INVALID_PARAMETER_VALUE.getMessage() + " userId:" + userId);
    List<DBApplicationToken> dbApplicationTokens = this.applicationTokenJpaRepository.findByUserId(userId);
    this.applicationTokenJpaRepository.deleteAll(dbApplicationTokens);
  }

  public ApplicationToken createApplicationToken(String userId) throws ApplicationException {
    if (StringUtils.isEmpty(userId))
      throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, ApplicationErrorCode.INVALID_PARAMETER_VALUE.getMessage() + " userId:" + userId);
    DBApplicationToken dbApplicationToken = new DBApplicationToken(DBApplicationToken.CLASS_CODE + "_" + Utility.GUID(userId));
    dbApplicationToken.setUserId(userId);
    long currentTimeStamp = System.currentTimeMillis();
    dbApplicationToken.setTokenCreationTime(new Timestamp(currentTimeStamp));
    dbApplicationToken.setExpirySeconds(DEFAULT_EXPIRY_SECONDS);
    dbApplicationToken.setTokenExpiryTime(new Timestamp(currentTimeStamp + (1000 * ApplicationTokenAPI.DEFAULT_EXPIRY_SECONDS)));
    dbApplicationToken = this.applicationTokenJpaRepository.saveAndFlush(dbApplicationToken);
    return ApplicationTokenAPI.convert(dbApplicationToken);
  }


  public static ApplicationToken convert(DBApplicationToken dbApplicationToken) {
    ApplicationToken applicationToken = new ApplicationToken();
    applicationToken.setTokenId(dbApplicationToken.getTokenId());
    applicationToken.setUserId(dbApplicationToken.getUserId());
    applicationToken.setTokenCreationTime(dbApplicationToken.getTokenCreationTime());
    applicationToken.setTokenExpiryTime(dbApplicationToken.getTokenExpiryTime());
    applicationToken.setExpirySeconds(dbApplicationToken.getExpirySeconds());
    return applicationToken;
  }
}