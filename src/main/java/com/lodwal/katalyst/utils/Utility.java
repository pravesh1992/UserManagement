package com.lodwal.katalyst.utils;


import com.lodwal.katalyst.exception.ApplicationErrorCode;
import com.lodwal.katalyst.exception.ApplicationException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Utility class contains utility functions for flamingo server application
 *
 * @author Pravesh Lodwal
 * @since 2.2.0
 */

public class Utility {

  private static Base64 base64;

  static {
    base64 = new Base64();
  }

  public static String GUID(final String string) {
    if (StringUtils.isEmpty(string))
      return UUID.randomUUID().toString();
    else
      return UUID.nameUUIDFromBytes(string.getBytes()).toString();
  }

  public static String decode(final String value) throws ApplicationException {
    if (StringUtils.isEmpty(value))
      throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, ApplicationErrorCode.INVALID_PARAMETER_VALUE.getMessage() + " value:" + value);
    return new String(base64.decode(value));
  }
}