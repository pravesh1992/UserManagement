package com.lodwal.katalyst.exception;

/**
 * ApplicationException wraps all checked & unchecked standard exception and enriches them with a custom error code.
 * You can use this code to retrieve localized error messages and to link to our online documentation.
 *
 * @author Pravesh Lodwal
 * @since 2.2.0
 */
public class ApplicationException extends Exception {

  /**
   * @link {ApplicationErrorCode} - Mapped error codes
   */
  ApplicationErrorCode errorCode;

  /**
   * Short error message
   */
  String errorMessage;


  /**
   * This is constructor with error code and error message
   *
   * @param errorCode    - @{link ApplicationErrorCode}
   * @param errorMessage - Error message
   */
  public ApplicationException(ApplicationErrorCode errorCode, String errorMessage) {
    super(errorMessage);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  /**
   * This is constructor with error code and error message along with actual error
   *
   * @param errorCode    - @{link ApplicationErrorCode}
   * @param errorMessage - Error message
   * @param throwable    - @{link {@link Throwable}}
   */
  public ApplicationException(ApplicationErrorCode errorCode, String errorMessage, Throwable throwable) {
    super(errorMessage, throwable);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public static ApplicationException analyzeException(Exception exception) {
    return new ApplicationException(ApplicationErrorCode.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
  }
}