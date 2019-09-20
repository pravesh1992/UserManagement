package com.lodwal.katalyst.business.apis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class PasswordAPI {

  public static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
    SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
    byte[] salt = new byte[16];
    secureRandom.nextBytes(salt);
    return salt;
  }

  public static String getSecuredPassword(String passwordToHash, byte[] salt) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(salt);
    byte[] bytes = md.digest(passwordToHash.getBytes());
    //Convert it to hexadecimal format
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();
  }
}