package FileMoving;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class DownloadsWatcher {
  static boolean keepRunning = true;
  
  
  public static void main(String[] args) throws IOException {
    Path dir = Paths.get(System.getProperty("user.home") + "/Downloads/");
    WatchService watcher = FileSystems.getDefault().newWatchService();
    WatchKey watchKey = dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);
    
    while(keepRunning) { 
      for (WatchEvent<?> event: watchKey.pollEvents()) { 
        Path path = dir.resolve((Path) event.context());
        Path fileName = (Path) event.context();
        newDownload(path, fileName);
      }
    }
  }
  
  public static void newDownload(Path sourcePath, Path fileName) { 
    if (fileName.toString().endsWith(".txt")) { 
      //Create the folder if it doesn't exist
      FileMover.createFolder(System.getProperty("user.home"));
      System.out.println(sourcePath);
      // Where the new copied file will go
      Path targetPath = Paths.get(System.getProperty("user.home") + "/Desktop/File_Mover", 
          fileName.toString());
      
      try { 
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        Files.delete(sourcePath);
      } catch (NoSuchFileException nsfe) { 
        System.out.println("No Such file for deletion, watcher");
      } catch (IOException iox) { 
        System.out.println("Problem with copying file, watcher");
      } 
    } 
  }
  
  public static void stopApp() { 
    keepRunning = false;
  }

}
