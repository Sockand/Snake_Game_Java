/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algie_counter;

/**
 *
 * @author sockand
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SimpleMenuEx extends JFrame {

    Image image;
    BitMap bitMap;
    SquareMark squaremark;
    
    public SimpleMenuEx() {

        initBasic();
        
        bitMap = new BitMap();
        setImage(bitMap.getImage());
        
        initUI();
    }

    private void initBasic() {

        setTitle("ALGIE COUNTER");
        setSize(800, 600);
        setPreferredSize(new Dimension(800,600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.print("Hola");
      
    }
    
    private void initUI() {
        
        //createPanels();
        createMenuBar();
        createPanel_options();
    }


    private void createLabels() {
        JLabel textLabel = new JLabel("I'm a label in the window",
                SwingConstants.CENTER); textLabel.setPreferredSize(new Dimension(300, 100));
         this.getContentPane().add(textLabel, BorderLayout.CENTER); 
   
    }
    
    private void createPanel_options() {
        //JLabel textLabel = new JLabel("I'm a label in the window",
                //SwingConstants.CENTER); textLabel.setPreferredSize(new Dimension(300, 100));
         //this.getContentPane().add(textLabel, BorderLayout.CENTER); 
         //Add the login button
        JButton login = new JButton("Login");
        login.setBounds(0, 342, 150, 30);

        //Create login label
        JLabel loginlabel = new JLabel("Login Area");

        //Create login panel
        JPanel loginpanel = new JPanel();
        loginpanel.setLayout(new BorderLayout());
        loginpanel.setBounds(0, 0, 150, 400);
        loginpanel.setBackground(Color.blue);
        loginpanel.add(login);
        loginpanel.add(loginlabel);         

        this.add(loginpanel);
    }
    
    private void createPanels() {
        //JLabel textLabel = new JLabel("I'm a label in the window",
                //SwingConstants.CENTER); textLabel.setPreferredSize(new Dimension(300, 100));
         //this.getContentPane().add(textLabel, BorderLayout.CENTER); 
         //Add the login button
        JButton login = new JButton("Login");
        login.setBounds(0, 342, 150, 30);

        //Create login label
        JLabel loginlabel = new JLabel("Login Area");

        //Create login panel
        JPanel loginpanel = new JPanel();
        loginpanel.setLayout(new BorderLayout());
        loginpanel.setBounds(0, 0, 150, 400);
        loginpanel.setBackground(Color.blue);
        loginpanel.add(login);
        loginpanel.add(loginlabel);       

        this.add(loginpanel);
    }
    
    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("exit.png");

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("Exit", icon);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        file.add(eMenuItem);

        menubar.add(file);
        
        JMenu edit = new JMenu("Edit");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem copiar = new JMenuItem("Copiar", icon);
        copiar.setMnemonic(KeyEvent.VK_E);
        copiar.setToolTipText("Exit application");
        copiar.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        edit.add(copiar);
        menubar.add(edit);

        JMenu adjust = new JMenu("Adjust");
        adjust.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem detection_sensibility = new JMenuItem("Detection sensibility", icon);
        detection_sensibility.setMnemonic(KeyEvent.VK_E);
        detection_sensibility.setToolTipText("Exit application");
        detection_sensibility.addActionListener((ActionEvent event) -> {
            createPanel_options();
        });
        
        adjust.add(detection_sensibility);
        menubar.add(adjust);
        
        setJMenuBar(menubar);
    }
    
    public void setImage (Image image1)
    {
       image = image1;
       System.out.println("ALTURAS " + image.getHeight(null));
       squaremark = new SquareMark();
       squaremark.auto_detectSquare(bitMap.mapa);
       System.out.print("Funciona?3");
       repaint();
    }
    
    public void paint (Graphics g)
    {
        super.paint(g);
        Toolkit t = Toolkit.getDefaultToolkit ();
       
        //Image imagen10 = t.getImage ("flechaizq.png");
        if(image!=null){
        g.drawImage (image, 20, 20, this);
        }
        if(squaremark!=null){
        squaremark.draw(g);
        }
    }

}