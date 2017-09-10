package userInter;

import java.awt.EventQueue;

import javax.swing.JFrame;

import FileMoving.DownloadsWatcher;
import FileMoving.FileMover;
import FileMoving.MoverTray;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;

public class TrayMenu {

  private JFrame frmDownloadsfilemover;
  private boolean mp3 = false;
  private boolean pdf = false;
  private boolean word = false;
  
  public TrayMenu() { 
    initialize();
  }
  
  
  public static void main (String [] args) { 
    TrayMenu.startUpMenu();
  }
  
  /**
   * Launch the application.
   */
  public static void startUpMenu() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          TrayMenu window = new TrayMenu();
          window.frmDownloadsfilemover.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
  
  
  
  /**
   * Helper method to make a String array that checks mp3, pdf and 
   * word fields and compile it into a String array for use by FileMover.java and 
   * DownloadsWatcher.java.
   * @return a String array
   */
  public String[] makeStringArray() { 
    String [] strArr = new String[4]; 
    if (mp3)
      strArr[0] = ".mp3";
    if (pdf) 
      strArr[1] = ".pdf"; 
    if (word) {
      strArr[2] = ".docx";
      strArr[3] = ".doc";
    }
    return strArr;
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmDownloadsfilemover = new JFrame();
    frmDownloadsfilemover.setTitle("autoSort options");
    frmDownloadsfilemover.setBounds(100, 100, 500, 300);
    frmDownloadsfilemover.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmDownloadsfilemover.getContentPane().setLayout(null);
    
    JLabel label = new JLabel("Select Files to move from Downloads");
    label.setFont(new Font("Tahoma", Font.PLAIN, 22));
    label.setBounds(15, 16, 415, 33);
    frmDownloadsfilemover.getContentPane().add(label);
    
    // MP3 Files Check box
    JCheckBox mp3CheckBox = new JCheckBox("MP3 Files");
    mp3CheckBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
    mp3CheckBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mp3 = (mp3CheckBox.isSelected()) ? true : false; 
      }
    });
    mp3CheckBox.setBounds(69, 66, 139, 29);
    frmDownloadsfilemover.getContentPane().add(mp3CheckBox);
    
    // PDF Files Check box
    JCheckBox pdfCheckBox = new JCheckBox("PDF files");
    pdfCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
    pdfCheckBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pdf = (pdfCheckBox.isSelected()) ? true : false; 
      }
    });
    pdfCheckBox.setBounds(69, 104, 139, 29);
    frmDownloadsfilemover.getContentPane().add(pdfCheckBox);
    
    // Microsoft Word Files Check Box
    JCheckBox wordCheckBox = new JCheckBox("Microsoft Word files");
    wordCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
    wordCheckBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        word = (wordCheckBox.isSelected()) ? true : false; 
      }
    });
    wordCheckBox.setBounds(69, 141, 216, 29);
    frmDownloadsfilemover.getContentPane().add(wordCheckBox);
    
    // Move files from downloads folder immediately using FileMover.java
    JButton scanDownloadsBtn = new JButton("Move from Downloads");
    scanDownloadsBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
    scanDownloadsBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        FileMover.main(makeStringArray());
      }
    });
    scanDownloadsBtn.setBounds(15, 199, 216, 29);
    frmDownloadsfilemover.getContentPane().add(scanDownloadsBtn);
    
    // Run the program in the background using DownloadsWatcher.java
    JButton backgroundProgramBtn = new JButton("Run in Background");
    backgroundProgramBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
    backgroundProgramBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //MoverTray moveT = new MoverTray(); 
        frmDownloadsfilemover.setVisible(false);
        DownloadsWatcher dlw = new DownloadsWatcher();
      }
    });
    backgroundProgramBtn.setBounds(252, 199, 211, 29);
    frmDownloadsfilemover.getContentPane().add(backgroundProgramBtn);
  }
  
}
