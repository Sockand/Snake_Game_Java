/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_swing_test;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java_swing_test.tools.DataReader;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import sun.rmi.runtime.Log;

/**
 *
 * @author sockand
 */
public class FileChoosing extends JFileChooser{
    
JFrame jframe = new JFrame();
//In response to a button click:

public FileChoosing(JFrame jf){
    
    jframe = jf;
     System.out.println("Soy el file_chooser.");
    
}

public File[] resultados (){
    this.setMultiSelectionEnabled(true);
    int returnVal = this.showOpenDialog(jframe);
    System.out.println("Me has cliqueado.");
    if (returnVal == JFileChooser.APPROVE_OPTION) {
         File[] files = this.getSelectedFiles();
         File file = files[0];
        // What to do with the file, e.g. display it in a TextArea
        //textarea.read( new FileReader( file.getAbsolutePath() ), null );
        try{
        //System.out.println("Texto: " + new FileReader( file.getAbsolutePath() ));
        DataReader dataReader = new DataReader();
        System.out.println("Texto: " + dataReader.textRead(file));
        } catch (IOException ex) {
          System.out.println("problem accessing file"+file.getAbsolutePath());
        }
        return files;
    } else {
        System.out.println("File access cancelled by user.");
    }
    return null;
}

}

