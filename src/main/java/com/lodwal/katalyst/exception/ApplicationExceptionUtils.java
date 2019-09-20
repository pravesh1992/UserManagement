package com.lodwal.katalyst.exception;

import org.springframework.http.HttpStatus;

public class ApplicationExceptionUtils {

  static HttpStatus getHttpStatusCode(ApplicationException applicationException) {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    if (applicationException != null) {
      switch (applicationException.errorCode) {
        default:
          httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      }
    }
    return httpStatus;
  }
}
