import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;



//janelas


public class SplashScreen extends JWindow{
    
        private int duration;
        
        public SplashScreen(int d){
            
            duration = d;
        }
        
   
    
    public void showSplash(){
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.white);
        
        int width = 450;
        int height = 115;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);
        
        JLabel label = new JLabel (new ImageIcon("img/splash.gif"));
        JLabel copyrt = new JLabel ("Copyright 2020, André Sousa", JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        content.add(label, BorderLayout.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);
        Color oraRed = new Color(156,20,20,255);
        content.setBorder(BorderFactory.createLineBorder(oraRed, 10));
        
        this.setVisible(true);
        
        
        try{
            Thread.sleep(duration); 
        }catch(Exception erro){
            setVisible(true);
        }
        
        
        }
    
    public void showSplashAndExit(){
        showSplash();
        
        try{
            Principal objTela = new Principal();
                   
        }catch(Exception erro){
            setVisible(false);
        }
    }
    
    
            
         
    }