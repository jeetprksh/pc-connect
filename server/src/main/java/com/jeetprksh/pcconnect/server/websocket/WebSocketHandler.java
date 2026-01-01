package com.jeetprksh.pcconnect.server.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeetprksh.pcconnect.server.entity.VerifiedUser;
import com.jeetprksh.pcconnect.server.users.UserService;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/*
 * @author Jeet Prakash
 * */
public class WebSocketHandler extends TextWebSocketHandler {

  private final Logger logger = Logger.getLogger(WebSocketHandler.class.getName());

  private final Map<VerifiedUser, WebSocketSession> userSessions = new ConcurrentHashMap<>();
  private final UserService userService;
  private final ObjectMapper mapper = new ObjectMapper();

  public WebSocketHandler(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) {
    logger.info("Received message: " + message.getPayload());
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    try {
      String token = session.getHandshakeHeaders().get("token").get(0);
      VerifiedUser user = userService.verifyToken(token);
      for (Map.Entry<VerifiedUser, WebSocketSession> userSession : userSessions.entrySet()) {
        Message message = new Message(MessageType.ONLINE.getType(), user.getUserId());
        byte[] messageBytes = mapper.writeValueAsString(message).getBytes();
        userSession.getValue().sendMessage(new TextMessage(messageBytes));
      }
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
    VerifiedUser user = userService.verifyToken(token);
    userSessions.remove(user);
    userService.removeUser(token);
    logger.info("Removed WebSocket session for " + user.getUserName() + " " + user.getUserId());
  }
}
