package com.jeetprksh.pcconnect.ui.root.server;

import javax.swing.*;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Jeet Prakash
 * */
public class SharedDirectoriesPanel extends JPanel {

  private JList list;
  private DefaultListModel listModel;
  private JButton addDirectory;
  private JButton removeDirectory;
  private JFileChooser chooser;

  public SharedDirectoriesPanel() {
    createList();
  }

  private void createList() {
    listModel = new DefaultListModel<>();
    list = new JList(listModel);

    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    list.setFixedCellWidth(300);
    list.addListSelectionListener(event -> {
      System.out.println(event.getFirstIndex() + " " + event.getLastIndex());
    });
    list.setVisibleRowCount(5);
    JScrollPane listScrollPane = new JScrollPane(list);

    addDirectory = new JButton("Add");
    addDirectory.addActionListener(event -> {
      chooser = new JFileChooser();
      chooser.setCurrentDirectory(new java.io.File("."));
      chooser.setDialogTitle("Choose a directory to share.");
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      chooser.setAcceptAllFileFilterUsed(false);
      if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        listModel.addElement(chooser.getSelectedFile());
      }
    });

    removeDirectory = new JButton("Remove");
    removeDirectory.addActionListener(event -> {
      listModel.remove(list.getSelectedIndex());
    });

    add(listScrollPane);
    add(addDirectory);
    add(removeDirectory);
  }

  List<String> getSharedDirectories() {
    List<String> sharedDirectories = new ArrayList<>();
    for (int i = 0; i < listModel.size(); i++) {
      File file = (File) listModel.getElementAt(i);
      sharedDirectories.add(file.getPath());
    }
    return sharedDirectories;
  }
}
