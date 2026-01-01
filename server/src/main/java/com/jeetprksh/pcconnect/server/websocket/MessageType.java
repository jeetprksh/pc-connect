package com.jeetprksh.pcconnect.server.websocket;

/*
 * @author Jeet Prakash
 * */
public enum MessageType {

  ONLINE("ONLINE"),
  OFFLINE("OFFLINE");

  private final String messageType;

  MessageType(String messageType) {
    this.messageType = messageType;
  }

  public String getType() {
    return messageType;
  }
}
