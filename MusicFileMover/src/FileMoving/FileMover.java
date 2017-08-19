package FileMoving;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class FileMover {

  public static void main (String [] args) { 
    moveLister();
  }
  
  public static void moveLister() { 
    // Store list of files
    String home = System.getProperty("user.home");
    File file = new File(home + "/Downloads/");
    String [] files = file.list(); 
    
    // Create folder on desktop
    createFolder(home);

    // Copy .mp3 files
    for (String fileName: files) { 
      if (fileName.endsWith(".mp3")) { 
        File source = new File (home + "/Downloads/" + fileName); 
        System.out.println(source.toPath());
        moveFolder(source);
      }
    }
    
  }
  
  /**
   * Checks desktop to see if folder named "File Mover" has been created. 
   * If it has, do nothing. If it hasn't, create it."
   * @param homeDir home directory of user running the code
   */
  public static void createFolder(String homeDir) { 
    File dir = new File(homeDir + "/Desktop/File_Mover"); 
    Path path = dir.toPath();
    
    if (Files.exists(path) == false) { 
      dir.mkdir(); 
    }
    
  }

  public static void moveFolder(File source) { 
    Path sourcePath = source.toPath();
    String fileName = source.getName(); 
    String exitPath = System.getProperty("user.home") + "/Desktop/File_Mover";
    Path targetPath = Paths.get(exitPath, fileName);
    
    try { 
    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException io) { 
      io.printStackTrace();
    }
    
  }
  
  

}
