package com.jeetprksh.pcconnect.server.entity;

/*
 * @author Jeet Prakash
 * */
public class Item {

  private String name;
  private boolean isDirectory;
  private boolean isRoot;
  private String rootAlias;
  private String path;

  public Item(String name, boolean isDirectory, boolean isRoot, String rootAlias, String path) {
    this.name = name;
    this.isDirectory = isDirectory;
    this.isRoot = isRoot;
    this.rootAlias = rootAlias;
    this.path = path;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isDirectory() {
    return isDirectory;
  }

  public void setDirectory(boolean directory) {
    isDirectory = directory;
  }

  public boolean isRoot() {
    return isRoot;
  }

  public void setRoot(boolean root) {
    isRoot = root;
  }

  public String getRootAlias() {
    return rootAlias;
  }

  public void setRootAlias(String rootAlias) {
    this.rootAlias = rootAlias;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
