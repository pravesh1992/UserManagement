package com.lodwal.katalyst;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
  public static void main(String[] args) {
     String EMAIL_REGEX = "(0/91)?[7-9][0-9]{9}";

    Pattern pattern = Pattern.compile(EMAIL_REGEX);
    String emailId = "8237714335";
    Matcher matcher = pattern.matcher(emailId);
    System.out.println(emailId +" : "+ matcher.matches());
  }
}
