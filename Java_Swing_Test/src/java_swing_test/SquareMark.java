/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algie_counter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author sockand
 */
public class SquareMark {

    byte[][][] mapa;
    ArrayList<Integer> pointsX = new ArrayList<Integer>();
    ArrayList<Integer> pointsY = new ArrayList<Integer>();
    ArrayList<Point> points = new ArrayList<Point>();
    ArrayList<Point> squares = new ArrayList<Point>();
    ArrayList<Point_Value> points_count = new ArrayList<Point_Value>();
    
    public SquareMark(){
        
        System.out.print("Funciona?");
    }
    
    public void auto_detectSquare(byte[][][] mapa2){
        mapa = mapa2;
        
        for(int i = 0; i < mapa.length; i++){
            for(int j = 0; j < mapa[0].length; j++){
                int red = mapa[i][j][0];
                int green = mapa[i][j][1];
                int blue = mapa[i][j][2];
                float[] hsv = new float[3];
                Color.RGBtoHSB(red,green,blue,hsv);
                //System.out.println("AZULADO " + hsv[0] +  " " + hsv[1] +  " " + hsv[2] +  " ");
                
            //if(blue>red&&blue>green){
            if(hsv[1]>0){
                Point pointing = new Point(i,j);
                points.add(pointing);
                pointsX.add(i);
                pointsY.add(j);
            }
            }}
        System.out.print("Hola de nuevo" + points.size());
        /* Optimizar para que no necesite tantos recursos 
        Calcula en un area de mapa si hay muchos puntos rojos*/
        for(int i = 0; i < mapa.length; i += (mapa.length/128)){
            for(int j = 0; j < mapa[0].length; j += (mapa[0].length/128)){
                int pixelnumber_max = mapa.length/128 * mapa.length/128;
                int pixelnumber = 0;
                
                for(Point p: points){
                  if(p.posX> i && p.posX< i+(mapa.length/128) && p.posY> j && p.posY< j+(mapa[0].length/128)){
                      pixelnumber++;
                  }
                }
                
                if(pixelnumber*Gvar.square_sensibility > pixelnumber_max){
                    squares.add(new Point(i + (mapa.length/128)/2, j + (mapa[0].length/128)/2));
                }
            }
        }
        //points_count
        
    }
    
    public void pruebas (){
        for(int i = 0; i < mapa.length; i++){
            for(int j = 0; j < mapa[0].length; j++){
                for(Point p: points){
                    boolean detected;
                    for(Point q: points){
                        if(q.posX==p.posX&&q.posY==p.posY){} 
                        else if(q.posX==p.posX&&q.posY==p.posY){} 
                        else if(q.posX==p.posX&&q.posY==p.posY){} 
                        else if(q.posX==p.posX&&q.posY==p.posY){} 
                    }
                }
            }
        }
    }
    
    public void draw (Graphics g){
        for(int i =0;i<points.size();i++){
            g.setColor(Color.RED);
            System.out.println("AZULADO " + pointsY.get(i) +  " " + pointsX.get(i));
            g.drawLine(points.get(i).posX +20, points.get(i).posY +20, points.get(i).posX +20, points.get(i).posY+20); 
            g.setColor(Color.BLACK);
        }
        for(int i =0;i<squares.size();i++){
            g.setColor(Color.BLUE);
            System.out.println("AZULADO " + pointsY.get(i) +  " " + pointsX.get(i));
            g.drawLine(squares.get(i).posX -20, squares.get(i).posY, squares.get(i).posX +20, squares.get(i).posY); 
            g.drawLine(squares.get(i).posX, squares.get(i).posY -20, squares.get(i).posX, squares.get(i).posY + 20); 
            g.setColor(Color.BLACK);
        }
    }
}
