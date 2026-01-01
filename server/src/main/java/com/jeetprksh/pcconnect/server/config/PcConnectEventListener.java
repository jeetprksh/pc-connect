package com.jeetprksh.pcconnect.server.config;

import com.jeetprksh.pcconnect.server.users.UserService;
import com.jeetprksh.pcconnect.server.items.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/*
 * @author Jeet Prakash
 * */
@Component
public class PcConnectEventListener {

  private final Logger logger = Logger.getLogger(PcConnectEventListener.class.getName());

  private final ItemService itemService;
  private final UserService userService;

  @Autowired
  public PcConnectEventListener(ItemService itemService, UserService userService) {
    this.itemService = itemService;
    this.userService = userService;
  }

  @EventListener(ContextRefreshedEvent.class)
  public void initSharedRootDir() {
    logger.info("Initializing shared root directories.");
    itemService.initSharedRootDir();
    String code = userService.generateCode();
    logger.info("Access code for Users :: " + code);
  }
}
