package com.lodwal.katalyst;

import com.lodwal.katalyst.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

  private final static Logger logger = LoggerFactory.getLogger(Test.class);

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
    logger.info("This is an {} message.", "info");
    logger.warn("This is a warn message.");
    logger.error("This is an error message.");
    logger.debug("This is a debug message.");
  }
}