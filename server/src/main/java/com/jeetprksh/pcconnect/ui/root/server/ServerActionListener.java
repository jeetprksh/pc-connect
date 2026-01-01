package com.jeetprksh.pcconnect.ui.root.server;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * @author Jeet Prakash
 * */
interface ServerActionListener extends ActionListener {

  default boolean isNumber(String port) {
    try {
      Integer.parseInt(port);
    } catch (Exception ex) {
      return false;
    }
    return true;
  }

  default void showError(JPanel panel, String message) {
    JOptionPane.showMessageDialog(panel, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

}
