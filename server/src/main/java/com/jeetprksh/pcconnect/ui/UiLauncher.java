package com.jeetprksh.pcconnect.ui;

import com.jeetprksh.pcconnect.ui.root.RootFrame;

import javax.swing.*;

import java.awt.EventQueue;
import java.util.logging.Logger;

/*
 * @author Jeet Prakash
 * */
public class UiLauncher {

  private final Logger logger = Logger.getLogger(UiLauncher.class.getName());

  private final RootFrame rootFrame = new RootFrame();

  public static void main(String args[]) {
    new UiLauncher().run();
  }

  private void run() {
    logger.info("Starting Server UI.");
    EventQueue.invokeLater(() -> {
      try {
        rootFrame.setVisible(true);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception ex) {
        logger.severe("Unable to initialize UI " + ex.getLocalizedMessage());
      }
    });
  }

}
