package com.jeetprksh.pcconnect.server.users;

import com.jeetprksh.pcconnect.server.entity.OnlineUser;
import com.jeetprksh.pcconnect.server.entity.VerifiedUser;
import com.jeetprksh.pcconnect.server.entity.VerifyCode;
import com.jeetprksh.pcconnect.server.entity.http.Response;
import com.jeetprksh.pcconnect.server.users.UserService;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * @author Jeet Prakash
 * */
@CrossOrigin
@RestController()
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/user/code/verify")
  public ResponseEntity<? extends Response> verifyCode(
      @RequestParam(value = "name") String name,
      @RequestParam(value = "encoded") String encoded) throws Exception {
    VerifiedUser verifiedUser = userService.validateCode(new VerifyCode(name, new String(Base64.decodeBase64(encoded))));
    return ResponseEntity.ok().body(new Response(true, "User Verified", verifiedUser));
  }

  @GetMapping("/user/online")
  public ResponseEntity<? extends Response> getOnlineUsers(
          @RequestHeader("token") String token) throws Exception {
    userService.verifyToken(token);
    List<OnlineUser> onlineUsers = userService.getOnlineUsers();
    return ResponseEntity.ok().body(new Response(true, "Online Users", onlineUsers));
  }

}
