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
  final ResponseEntity<ApplicationErrorResponse> handleApplicationException(ApplicationException applicationException) {
    HttpStatus httpStatus = ApplicationExceptionUtils.getHttpStatusCode(applicationException);
    ApplicationErrorResponse applicationErrorResponse = new ApplicationErrorResponse(applicationException, httpStatus);
    return new ResponseEntity<>(applicationErrorResponse, httpStatus);
  }

  /**
   * This method handle error from all those method which throws Exception.class type of errors while processing of that API
   *
   * @param exception - {@link Exception}
   * @return {@link ApplicationErrorResponse}
   */
  @ExceptionHandler(Exception.class)
  final ResponseEntity<ApplicationErrorResponse> handleException(Exception exception) {
    ApplicationException applicationException = ApplicationException.analyzeException(exception);
    HttpStatus httpStatus = ApplicationExceptionUtils.getHttpStatusCode(applicationException);
    ApplicationErrorResponse applicationErrorResponse = new ApplicationErrorResponse(applicationException, httpStatus);
    return new ResponseEntity<>(applicationErrorResponse, httpStatus);
  }
}