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
  static String [] files = {".mp3", ".pdf", ".doc", ".docx"};
  
  public DownloadsWatcher() { 
    try { 
      createWatcher();
    } catch (IOException iox) { 
      System.out.println("Exception with createWatcher()");
    }
  }
  
  /**
   * Creates the watcher and continues the loop until it is ended.
   * @throws IOException if there is input-output exception
   */
  public void createWatcher() throws IOException {
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
  
  /**
   * This method is called when an event is triggered in the main method of this class.
   * Checks the file Path for it's extension. If it matches any in the files array, then it 
   * will be moved to a desktop folder named File_Mover.
   * @param sourcePath is the full path of the file
   * @param fileName is the path representing ONLY the name of the file
   */
  public void newDownload(Path sourcePath, Path fileName) { 
    boolean matched = false; 
    for (String i: files) { 
      if (fileName.toString().endsWith(i)) { 
        matched = true;
      }
    }
    
    if (matched) { 
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
  
  public void stopApp() { 
    System.exit(0);
    return;
  }

}
