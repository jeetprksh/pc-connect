package com.jeetprksh.pcconnect.server.entity;

/*
 * @author Jeet Prakash
 * */
public class OnlineUser {

  private final String userId;
  private final String userName;

  public OnlineUser(String userId, String userName) {
    this.userId = userId;
    this.userName = userName;
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

    OnlineUser that = (OnlineUser) o;

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
