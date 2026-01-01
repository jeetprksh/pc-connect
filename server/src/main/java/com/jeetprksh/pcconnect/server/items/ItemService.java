package com.jeetprksh.pcconnect.server.items;

import com.jeetprksh.pcconnect.server.PcConnectServer;
import com.jeetprksh.pcconnect.server.entity.Item;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*
 * @author Jeet Prakash
 * */
@Component
public class ItemService {

  private final Logger logger = Logger.getLogger(ItemService.class.getName());

  private final Map<String, File> rootMaps = new HashMap<>();

  public List<Item> getRootItems() {
    logger.info("Getting the root item that are shared");
    return rootMaps
        .entrySet().stream()
        .map(e -> new Item(e.getValue().getName(), e.getValue().isDirectory(), true, e.getKey(), ""))
        .collect(Collectors.toList());
  }

  public List<Item> getItems(String root, String path) throws Exception {
    logger.info("Getting items from path " + path);
    String requestedPath = createRequestedPath(root, path);
    File file = new File(requestedPath);

    if (!file.exists()) throw new Exception("Invalid path");
    if (!file.isDirectory()) throw new Exception("Specified path not a directory");

    return Arrays.stream(file.listFiles())
        .filter(f -> !f.isHidden())
        .map(f -> new Item(f.getName(),
            f.isDirectory(),
            false,
            root,
            path + "/" + f.getName()))
        .collect(Collectors.toList());
  }

  public File downloadItem(String root, String path) throws Exception {
    logger.info("Downloading the item on path " + path);
    String requestedPath = createRequestedPath(root, path);
    File file = new File(requestedPath);

    if (!file.exists() || file.isHidden()) throw new Exception("Invalid path.");
    if (!file.isFile()) throw new Exception("Not a file, only a file can be downloaded.");

    return file;
  }

  public void uploadItem(String root, String path, MultipartFile file) throws Exception {
    logger.info("Uploading the item on path " + path);
    try {
      String filePath = createRequestedPath(root, path);
      File diskFile = new File(filePath + "/" + file.getOriginalFilename());
      file.transferTo(diskFile);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new Exception(ex.getLocalizedMessage());
    }
  }

  public void initSharedRootDir() {
    List<String> directories = PcConnectServer.getSharedDirectories();
    logger.info("Initializing shared root directories: " + directories);
    directories.forEach(dir -> rootMaps.put(Integer.toString(Objects.hashCode(dir)), new File(dir)));
  }

  private String createRequestedPath(String root, String path) {
    return rootMaps.get(root).getAbsolutePath() + "/" + path;
  }
}
