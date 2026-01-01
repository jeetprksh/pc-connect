package com.jeetprksh.pcconnect.server.entity;

/*
 * @author Jeet Prakash
 * */
public class VerifiedUser {

  private final String userId;
  private final String userName;
  private final String token;

  public VerifiedUser(String userId, String userName, String token) {
    this.userId = userId;
    this.userName = userName;
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public String getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    VerifiedUser that = (VerifiedUser) o;

    if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
    return userName != null ? userName.equals(that.userName) : that.userName == null;
  }

  @Override
  public int hashCode() {
    int result = userId != null ? userId.hashCode() : 0;
    result = 31 * result + (userName != null ? userName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "VerifiedUser{" +
            "userId='" + userId + '\'' +
            ", userName='" + userName + '\'' +
            '}';
  }
}
