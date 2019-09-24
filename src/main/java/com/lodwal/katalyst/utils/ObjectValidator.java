package com.lodwal.katalyst.utils;

import com.lodwal.katalyst.exception.ApplicationErrorCode;
import com.lodwal.katalyst.exception.ApplicationException;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ObjectValidator {

  public static void validate(Object object, String objectName) throws ApplicationException {
    if (object == null)
      throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, objectName + " can't be null or empty");
    try {
      Field[] fields = object.getClass().getFields();
      if (fields != null && fields.length > 0) {
        for (int i = 0; i < fields.length; i++) {
          Field field = fields[i];
          Class<?> fieldClass = field.getType();
          field.setAccessible(true);
          String fieldName = field.getName();
          Object fieldValue = fields[i].get(object);
          Annotation[] annotations = field.getDeclaredAnnotations();
          if (annotations != null && annotations.length > 0) {
            if (field.isAnnotationPresent(com.lodwal.katalyst.annotations.NotNull.class) && fieldValue == null)
              throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, fieldName + " attribute value  can't be null for " + objectName);
            if (fieldClass.equals(String.class) && field.isAnnotationPresent(com.lodwal.katalyst.annotations.NotEmpty.class)) {
              String objectStirngValue = (String) fieldValue;
              if (StringUtils.isEmpty(objectStirngValue))
                throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, fieldName + " attribute value  can't be empty for " + objectName);
            }
          }
        }
      }
    } catch (Exception exception) {
      throw new ApplicationException(ApplicationErrorCode.INVALID_PARAMETER_VALUE, exception.getMessage(), exception);
    }
  }
}