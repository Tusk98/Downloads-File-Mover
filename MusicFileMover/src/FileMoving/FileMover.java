package FileMoving;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import javax.swing.JOptionPane;

public class FileMover {

  public static void main (String [] args) { 
    for (String k : args) { 
      if (k != null) { 
        moveLister(k);
      }
    }
    JOptionPane.showMessageDialog(null, "Files have been moved");
  }
  
  /** 
   * Searches default /Downloads/ directory for all files with indicated fileEnding in parameter.
   * Then calls copyFile and deleteFile to move the matching files onto Desktop.
   * @param fileEnding String representing file suffix
   */
  public static void moveLister(String fileEnding) { 
    // Store list of files
    String home = System.getProperty("user.home");
    File file = new File(home + "/Downloads/");
    String [] files = file.list(); 
    
    // Create folder on desktop
    createFolder(home);

    // Copy .mp3 files
    for (String fileName: files) { 
      if (fileName.endsWith(fileEnding)) { 
        File source = new File (home + "/Downloads/" + fileName); 
        System.out.println(source.toPath());
        boolean copied = copyFile(source);
        
        // Delete file from Downloads if successfully copied
        if (copied) { 
          deleteFile(source); 
        } 
  
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

  /**
   * This method takes in the file source, and then copies the file to the
   * User's /Desktop/File_Mover directory.
   * @param source File to transfer
   * @return true if successful, false otherwise
   */
  public static boolean copyFile(File source) { 
    boolean success = false;
    Path sourcePath = source.toPath();
    String fileName = source.getName(); 
    String exitPath = System.getProperty("user.home") + "/Desktop/File_Mover";
    Path targetPath = Paths.get(exitPath, fileName);
    
    try { 
    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    success = true;
    } catch (IOException io) { 
      success = false;
    }
    
    return success;
  }
  
  /**
   * Deletes the file indicated in the source parameter.
   * @param source File object for the file to delete.
   */
  public static void deleteFile(File source) { 
    try { 
      Files.delete(source.toPath());
    } catch (NoSuchFileException nsfe) { 
      System.out.println("No such file exists for deletion.");
    } catch (IOException io) { 
      System.out.println(io);
    }
  }
  

}
