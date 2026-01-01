package com.jeetprksh.pcconnect.ui.root.server;

import com.jeetprksh.pcconnect.server.PcConnectServer;

import javax.swing.*;

/*
 * @author Jeet Prakash
 * */
public class ServerControlPanel extends JPanel {

  private JButton startServer;
  private JButton restartServer;
  private JButton stopServer;
  private JTextField serverPort;

  private final SharedDirectoriesPanel sharedDirectoriesPanel;

  public ServerControlPanel(SharedDirectoriesPanel sharedDirectoriesPanel) {
    this.sharedDirectoriesPanel = sharedDirectoriesPanel;
    createUI();
  }

  private void createUI() {
    serverPort = new JTextField(6);

    startServer = new JButton("Start");
    startServer.addActionListener(new StartServer(this));

    restartServer = new JButton("Restart");
    restartServer.addActionListener(new RestartServer(this));

    stopServer = new JButton("Stop");
    stopServer.addActionListener((event) -> PcConnectServer.stop());

    this.add(serverPort);
    this.add(startServer);
    this.add(stopServer);
    this.add(restartServer);
  }

  JTextField getServerPort() {
    return serverPort;
  }

  SharedDirectoriesPanel getSharedDirectoriesPanel() {
    return sharedDirectoriesPanel;
  }

}
