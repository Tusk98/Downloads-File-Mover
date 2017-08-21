package FileMoving;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;

import javax.swing.ImageIcon;

public class MoverTray {
  static TrayIcon trayIcon;

  public MoverTray() { 
    showTrayIcon();
  }
  
  public static void showTrayIcon() { 
    if (!SystemTray.isSupported()) { 
      System.out.println("System tray not supported.");
      System.exit(0);
      return;
    }
    
    trayIcon = new TrayIcon(createIcon("/Images/icon.png", "Tray Icon"));
    final SystemTray tray = SystemTray.getSystemTray();
    
    try { 
      tray.add(trayIcon);
    } catch (AWTException ae) { 
      System.out.println("Tray Exception");
    }
  }
  
  protected static Image createIcon(String path, String desc) { 
    URL imageURL = MoverTray.class.getResource(path);
    return (new ImageIcon(imageURL, desc)).getImage();
  } 
}
