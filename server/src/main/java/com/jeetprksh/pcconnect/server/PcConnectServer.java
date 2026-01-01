package com.jeetprksh.pcconnect.server;

import com.jeetprksh.pcconnect.server.entity.ServerParams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/*
 * @author Jeet Prakash
 * */
@SpringBootApplication
public class PcConnectServer {

  private static final Logger logger = Logger.getLogger(PcConnectServer.class.getName());

  private static ConfigurableApplicationContext context;
  private static List<String> sharedDirectories = new ArrayList<>();

  public static void main(String[] args) {
    logger.info("Starting server at default port");
    ServerParams params = new ServerParams("8088", Arrays.asList("C:\\PCConnect"));
    start(params);
  }

  public static void start(ServerParams serverParams) {
    logger.info("Starting server at port: " + serverParams.getServerPort());
    verifyPort(serverParams.getServerPort());
    sharedDirectories = serverParams.getSharedDirectories();
    if (Objects.isNull(context) || !context.isActive()) {
      SpringApplication application = new SpringApplication(PcConnectServer.class);
      application.setDefaultProperties(Collections.singletonMap("server.port", serverParams.getServerPort()));
      context = application.run();
    }
  }

  public static void restart(ServerParams serverParams) {
    logger.info("Restarting server at port " + serverParams.getServerPort());
    verifyPort(serverParams.getServerPort());
    Thread thread = new Thread(() -> {
      stop();
      start(serverParams);
    });
    thread.setDaemon(false);
    thread.start();
  }

  public static void stop() {
    logger.info("Stopping the server");
    if (!Objects.isNull(context)) {
      context.close();
    }
  }

  public static List<String> getSharedDirectories() {
    return sharedDirectories;
  }

  private static void verifyPort(String port) {
    int intPort = Integer.parseInt(port);
    if (intPort < 0 || intPort > 65535) {
      throw new InvalidParameterException("Invalid Port Number.");
    }
  }
}
