package com.jeetprksh.pcconnect.server.healthcheck;

import com.jeetprksh.pcconnect.server.entity.Test;
import com.jeetprksh.pcconnect.server.entity.http.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author Jeet Prakash
 * */
@CrossOrigin
@RestController()
public class HealthCheckController {

  @GetMapping("/connect/ping")
  public ResponseEntity<? extends Response> test() {
    Response res = new Response(true, "Pong.", new Test("I am OK!"));
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(res);
  }

}
