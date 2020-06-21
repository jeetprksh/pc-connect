package com.jeetprksh.pcconnect.server.websocket;

import com.jeetprksh.pcconnect.server.entity.User;
import com.jeetprksh.pcconnect.server.service.AuthService;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class WebSocketHandler extends TextWebSocketHandler {

  private final Logger logger = Logger.getLogger(WebSocketHandler.class.getName());

  private final Map<User, WebSocketSession> userSessions = new ConcurrentHashMap<>();

  private final AuthService authService;

  public WebSocketHandler(AuthService authService) {
    this.authService = authService;
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
    logger.info("Sending message: " + message.getPayload() + " to " + userSessions.size() + " sessions.");
    for(WebSocketSession webSocketSession : userSessions.values()) {
      webSocketSession.sendMessage(message);
    }
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    try {
      String token = session.getHandshakeHeaders().get("token").get(0);
      User user = authService.verifyToken(token);
      userSessions.put(user, session);
      logger.info("Added WebSocket session for " + user);
    } catch (Exception ex) {
      ex.printStackTrace();
      session.close(CloseStatus.BAD_DATA);
    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    String token = session.getHandshakeHeaders().get("token").get(0);
    User user = authService.verifyToken(token);
    userSessions.remove(user);
    logger.info("Removed WebSocket session for " + user.getName() + " " + user.getId());
  }

  private enum MessageType {

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
}