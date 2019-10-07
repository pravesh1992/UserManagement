package com.lodwal.katalyst.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ApplicationErrorResponse implements Serializable {
  /**
   * Return application specific error code
   */
  String applicationErrorCode = ApplicationErrorCode.INTERNAL_SERVER_ERROR.toString();

  /**
   * Represent http status code
   */
  int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

  /**
   * Http error status reason phase value
   */
  String error = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();

  /**
   * Returns a short description of exception
   */
  String message = "Internal Server Error";

  ApplicationErrorResponse(ApplicationException applicationException, HttpStatus httpStatus) {
    if (applicationException != null) {
      this.applicationErrorCode = applicationException.errorCode.toString();
      this.message = applicationException.errorMessage;
      this.status = httpStatus.value();
      this.error = httpStatus.getReasonPhrase();
    }
  }
}