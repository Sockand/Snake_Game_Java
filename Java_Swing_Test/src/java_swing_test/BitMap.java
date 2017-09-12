/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_swing_test;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author sockand
 */
public class BitMap {
    
    int x_long=1024;
    int y_long=768;
    public byte[][][] mapa = new byte[x_long][y_long][3];
    int rgb[] = new int [3];
    BufferedImage image_buffered = null;
    
    public BitMap(String nombre){
         Image image = new ImageIcon(this.getClass().getResource("/res/KA_01.jpg")).getImage();
        
        try {
        URL url = this.getClass().getResource("/res/" + nombre);
         System.out.println("Document URL" + url);
        image_buffered = ImageIO.read(url);
        } catch (IOException e) {
         }
        
        BufferedImage newImage = new BufferedImage(
        image_buffered.getWidth(), image_buffered.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 50, 50, null);
        g.dispose();
                
         System.out.println("IMAGEN " + image_buffered.getHeight(null));
         for(int i = 0; i < image.getHeight(null); i++){
            for(int j = 0; j < image.getWidth(null); j++){
            rgb = getPixelData(image_buffered, j, i);
            mapa[j][i][0] = (byte) rgb[0];
            mapa[j][i][1] = (byte) rgb[1];
            mapa[j][i][2] = (byte) rgb[2];
            }}
            
         
    }
    
    private static int[] getPixelData(BufferedImage img, int x, int y) {
int argb = img.getRGB(x, y);

int rgb[] = new int[] {
    (argb >> 16) & 0xff, //red
    (argb >>  8) & 0xff, //green
    (argb      ) & 0xff  //blue
};

//System.out.println("rgb: " + rgb[0] + " " + rgb[1] + " " + rgb[2]);
return rgb;
}
    
    public Image getImage(){
        return image_buffered;
    }
    
    
    
}
