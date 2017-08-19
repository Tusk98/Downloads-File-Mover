package userInter;

import java.awt.EventQueue;
import FileMoving.FileMover;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;


public class FileSelector {
  private boolean mp3 = false;
  private boolean pdf = false;
  private boolean docx = false;
  private JFrame frmFileMover;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          FileSelector window = new FileSelector();
          window.frmFileMover.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public FileSelector() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmFileMover = new JFrame();
    frmFileMover.setTitle("File Mover");
    frmFileMover.setBounds(100, 100, 400, 300);
    frmFileMover.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmFileMover.getContentPane().setLayout(null);    
    
    JButton moveFilesBtn = new JButton("Move Files");
    moveFilesBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
    moveFilesBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        
        String [] options = new String[3];
        // Filling in the document options according to whether the checkbox was clicked
        if (mp3)
          options[0] = ".mp3";
        if (pdf) 
          options[1] = ".pdf"; 
        if (docx) 
          options[2] = ".docx";
        
        FileMover.main(options);
        
      }
    });
    moveFilesBtn.setBounds(117, 199, 139, 29);
    frmFileMover.getContentPane().add(moveFilesBtn);
    
    // Check box for moving MP3 files
    JCheckBox mp3CheckBox = new JCheckBox("MP3 Files");
    mp3CheckBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
    mp3CheckBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mp3 = (mp3CheckBox.isSelected()) ? true : false;
      }
    });
    mp3CheckBox.setBounds(114, 61, 139, 29);
    frmFileMover.getContentPane().add(mp3CheckBox);
    
    // Check box for moving PDF files
    JCheckBox pdfCheckBox = new JCheckBox("PDF files");
    pdfCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
    pdfCheckBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { 
        pdf = (pdfCheckBox.isSelected()) ? true : false;
      }
    });
    pdfCheckBox.setBounds(114, 94, 139, 29);
    frmFileMover.getContentPane().add(pdfCheckBox);
    
    // Check box for moving Word(docx) files
    JCheckBox docxCheckBox = new JCheckBox("Word (docx) files");
    docxCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
    docxCheckBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        docx = (docxCheckBox.isSelected()) ? true : false; 
      }
    });
    docxCheckBox.setBounds(114, 130, 179, 29);
    frmFileMover.getContentPane().add(docxCheckBox);
    
    JLabel lblSelectFilesTo = new JLabel("Select Files to move from Downloads");
    lblSelectFilesTo.setFont(new Font("Tahoma", Font.PLAIN, 18));
    lblSelectFilesTo.setBounds(15, 16, 348, 33);
    frmFileMover.getContentPane().add(lblSelectFilesTo);
  }
}
