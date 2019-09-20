package com.lodwal.katalyst.annotations


import java.lang.annotation.*

/**
 * @since 2.2.0*
 * @author Pravesh Lodwal
 */

@Documented
@Target(value = [ElementType.PARAMETER, ElementType.FIELD])
@Retention(RetentionPolicy.RUNTIME)
@interface NotEmpty {
}