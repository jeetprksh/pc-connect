package com.jeetprksh.pcconnect.server.config;

import com.jeetprksh.pcconnect.server.users.UserService;
import com.jeetprksh.pcconnect.server.websocket.WebSocketHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.logging.Logger;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  private final Logger logger = Logger.getLogger(WebSocketConfig.class.getName());

  @Autowired private UserService userService;

  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    logger.info("Initializing websocket handlers");
    registry.addHandler(new WebSocketHandler(userService), "/websocket").setAllowedOrigins("*");
  }

}
