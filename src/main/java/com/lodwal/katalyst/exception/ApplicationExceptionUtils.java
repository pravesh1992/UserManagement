package com.lodwal.katalyst.exception;

import org.springframework.http.HttpStatus;

public class ApplicationExceptionUtils {

  static HttpStatus getHttpStatusCode(ApplicationException applicationException) {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    if (applicationException != null) {
      switch (applicationException.errorCode) {
        case TOKEN_EXPIRED:
        case ERROR_INVALID_TOKEN_ID:
        case INVALID_CREDENTIALS:
          httpStatus = HttpStatus.FORBIDDEN;
          break;
        case ERROR_INVALID_ID:
          httpStatus = HttpStatus.BAD_REQUEST;
          break;
        case OBJECT_ALREADY_EXISTS:
          httpStatus = HttpStatus.LOCKED;
          break;
        default:
          httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      }
    }
    return httpStatus;
  }
}