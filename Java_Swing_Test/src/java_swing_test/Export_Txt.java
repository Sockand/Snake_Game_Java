/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_swing_test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author sockand
 */
public class Export_Txt {
    
    
    public Export_Txt(){
        try {
            if(Arrays.asList(Gvar.points_limited).contains(null)){
            JOptionPane.showMessageDialog(null, "Cant save. Check all photos.");
            
            } else {
                
                FileWriter writer = new FileWriter("Archivo.txt", true);
            for(int i=0;i<Gvar.points_limited.size();i++){
            System.out.println("" + Gvar.points_limited.get(i).size());
            writer.write("  " + (i+1) + "  " + Gvar.points_limited.get(i).size());
            //writer.write("\r\n");   // write new line
            //writer.write("Good Bye!");
            }
            writer.close();
            JOptionPane.showMessageDialog(null, "Saved to textfile Archivo.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }
    
    public void guardar(String texto) throws IOException{
        String text = "Text to save to file %n yeey o ye";
        Files.write(Paths.get("./fileName.txt"), text.getBytes());
    }
    
}
    

