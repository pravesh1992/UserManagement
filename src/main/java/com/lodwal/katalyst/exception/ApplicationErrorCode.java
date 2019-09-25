package com.lodwal.katalyst.exception;

public enum ApplicationErrorCode {

  INTERNAL_SERVER_ERROR(2001, "Internal Server Error"),
  INVALID_PARAMETER_VALUE(2002, "Invalid Parameter Value"),
  INVALID_ATTRIBUTE_VALUE(2003, "Invalid Attribute Value"),
  ERROR_INVALID_ID(2004, "Invalid Id"),
  INVALID_CREDENTIALS(2005, "Invalid Credentials"),
  OBJECT_ALREADY_EXISTS(2006, "Object Already Exists");

  int code;

  String message;

  ApplicationErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}