package com.jeetprksh.pcconnect.server.config;

import com.jeetprksh.pcconnect.server.entity.http.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

/*
 * @author Jeet Prakash
 * */
@ControllerAdvice
public class CommonControllerAdvise {

  private final Logger logger = Logger.getLogger(CommonControllerAdvise.class.getName());

  @ExceptionHandler(Exception.class)
  public ResponseEntity<? extends Response> exception(Exception ex) {
    logger.severe("Error while serving the request: " + ex.getLocalizedMessage());
    if (ex.getMessage().equalsIgnoreCase("Invalid Token"))
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
          .body(new Response(false, ex.getMessage(), null));
    else if (ex.getMessage().equalsIgnoreCase("Invalid Code"))
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
              .body(new Response(false, ex.getMessage(), null));
    else
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response(false, ex.getMessage(), null));
  }

}
