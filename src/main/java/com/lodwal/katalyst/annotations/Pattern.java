package com.lodwal.katalyst.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Pravesh Lodwal
 * @since 2.2.0*
 */

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
public @interface Pattern {

  /**
   * The value indicate that given field/parameter value should match with this value
   *
   * @return max length count
   */
  String value() default "";
}