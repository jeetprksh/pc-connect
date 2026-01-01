package com.jeetprksh.pcconnect.server.entity;

/*
 * @author Jeet Prakash
 * */
public class VerifyCode {

  private String name;
  private String code;

  public VerifyCode(String name, String code) {
    this.name = name;
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }
}
