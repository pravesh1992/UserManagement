package com.lodwal.katalyst.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandlerControllerAdvice {

  /**
   * This method handle error from all those method which throws FlamingoException.class type of errors while processing of that API
   *
   * @param applicationException - {@link ApplicationException}
   * @return {@link ApplicationErrorResponse}
   */
  @ExceptionHandler(ApplicationException.class)
  final ResponseEntity<ApplicationErrorResponse> handleFlamingoException(ApplicationException applicationException) {
    HttpStatus httpStatus = ApplicationExceptionUtils.getHttpStatusCode(applicationException);
    ApplicationErrorResponse flamingoErrorResponse = new ApplicationErrorResponse(applicationException, httpStatus);
    return new ResponseEntity<>(flamingoErrorResponse, httpStatus);
  }
}