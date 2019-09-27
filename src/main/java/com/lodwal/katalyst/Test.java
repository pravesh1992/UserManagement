package com.lodwal.katalyst;

import com.lodwal.katalyst.utils.Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
  public static void main(String[] args) throws Exception {
    String EMAIL_REGEX = "^[0-3]?[0-9]-[0-3]?[0-9]-(?:[0-9]{2})?[0-9]{2}$";
    Pattern pattern = Pattern.compile(EMAIL_REGEX);
    String emailId = "1-7-1992";
    Matcher matcher = pattern.matcher(emailId);
    System.out.println(emailId + " : " + matcher.matches());
    String decodedString = "datametica";
    String decodedString1 = "datametica2";
    System.out.println("Encoded String for " + decodedString + " is :" + Utility.encode(decodedString));
    System.out.println("Encoded String for " + decodedString1 + " is :" + Utility.encode(decodedString1));

  }
}