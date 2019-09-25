package com.lodwal.katalyst.utils;

import com.lodwal.katalyst.annotations.MaxLength;
import com.lodwal.katalyst.annotations.Pattern;
import com.lodwal.katalyst.exception.ApplicationErrorCode;
import com.lodwal.katalyst.exception.ApplicationException;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Matcher;

public class ObjectValidator {

  public static void validate(Object object, String objectName) throws ApplicationException {
    if (object == null)
      throw new ApplicationException(ApplicationErrorCode.INVALID_ATTRIBUTE_VALUE, objectName + " can't be null or empty");
    try {
      Field[] fields = object.getClass().getDeclaredFields();
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
              throw new ApplicationException(ApplicationErrorCode.INVALID_ATTRIBUTE_VALUE, fieldName + " attribute value can't be null for " + objectName);
            if (fieldClass.equals(String.class)) {
              String fieldStringValue = (String) fieldValue;
              if (field.isAnnotationPresent(com.lodwal.katalyst.annotations.NotEmpty.class) && StringUtils.isBlank(fieldStringValue))
                throw new ApplicationException(ApplicationErrorCode.INVALID_ATTRIBUTE_VALUE, fieldName + " attribute value can't be empty for " + objectName);
              if (field.isAnnotationPresent(com.lodwal.katalyst.annotations.MaxLength.class) && !StringUtils.isBlank(fieldStringValue)) {
                MaxLength maxLength = field.getDeclaredAnnotation(MaxLength.class);
                if (fieldStringValue.trim().length() > maxLength.value())
                  throw new ApplicationException(ApplicationErrorCode.INVALID_ATTRIBUTE_VALUE, fieldName + " attribute value length can't be more than " + maxLength.value());
              }
              if (field.isAnnotationPresent(com.lodwal.katalyst.annotations.Pattern.class) && !StringUtils.isBlank(fieldStringValue)) {
                Pattern pattern = field.getDeclaredAnnotation(Pattern.class);
                if (!StringUtils.isBlank(pattern.value())) {
                  java.util.regex.Pattern patternMatch = java.util.regex.Pattern.compile(pattern.value());
                  Matcher matcher = patternMatch.matcher(fieldStringValue.trim());
                  if (!matcher.matches())
                    throw new ApplicationException(ApplicationErrorCode.INVALID_ATTRIBUTE_VALUE, fieldName + " attribute value is not valid");
                }
              }
            }
          }
        }
      }
    } catch (Exception exception) {
      throw new ApplicationException(ApplicationErrorCode.INVALID_ATTRIBUTE_VALUE, exception.getMessage(), exception);
    }
  }
}