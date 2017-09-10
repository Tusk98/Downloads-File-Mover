package FileMoving;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;

import userInter.TrayMenu;
import FileMoving.DownloadsWatcher;

public class MoverTray {
  static TrayIcon trayIcon;
  static DownloadsWatcher dlw; 
  static TrayMenu tm;
  
  public MoverTray() { 
    showTrayIcon();
    //TrayMenu.main(null);
  }
  
  /**
   * Creating the Tray Icon, firstly checks if System Tray is supported. 
   * Then creates 2 buttons and adding custom Photo to tray icon.
   */
  public void showTrayIcon() { 
    if (!SystemTray.isSupported()) { 
      System.out.println("System tray not supported.");
      System.exit(0);
      return;
    }
    //dlw = new DownloadsWatcher();
    
    //Creating the tray icon
    trayIcon = new TrayIcon(createIcon("/Images/icon.png", "Tray Icon"));
    final SystemTray tray = SystemTray.getSystemTray();
    trayIcon.setToolTip("Downloads-File-Mover\nVersion 0.1");
    
    // Add Pop up menu when you hover over icon and adding components/menu items
    final PopupMenu popup = new PopupMenu();
    MenuItem fileChoice = new MenuItem("File Moving Options");
    popup.add(fileChoice);
    popup.addSeparator();
    MenuItem endProgram = new MenuItem("End Program");
    popup.add(endProgram);
    
    // Adding actions to the pop up buttons
    fileChoice.addActionListener(new ActionListener() { 
      @Override 
      public void actionPerformed(ActionEvent e) { 
        tm = new TrayMenu();
      }
    });
    
    // Ends Downloads program and exits
    endProgram.addActionListener(new ActionListener() { 
      @Override
      public void actionPerformed(ActionEvent e) { 
        System.exit(0);
      }
    });
    
    // Adding the trayIcon
    trayIcon.setPopupMenu(popup);
    try { 
      tray.add(trayIcon);
    } catch (AWTException ae) { 
      System.out.println("Tray Exception");
    }
  }
  
  /**
   * Helper method for Tray icon picture
   * @param path is path of designated picture in String
   * @param desc is short description of the photo
   * @return and Image Object at designated path
   */
  protected static Image createIcon(String path, String desc) { 
    URL imageURL = MoverTray.class.getResource(path);
    return (new ImageIcon(imageURL, desc)).getImage();
  } 
  
  /*public static void main (String [] args) { 
    MoverTray moveTee = new MoverTray();
  }*/
  
}
