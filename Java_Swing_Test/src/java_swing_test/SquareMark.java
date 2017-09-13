/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_swing_test;

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
    boolean [][] squares_on = new boolean[128][128];
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
                int pixelnumber_max = mapa.length/128 * mapa[0].length/128;
                int pixelnumber = 0;
                
                for(Point p: points){
                  if(p.posX> i && p.posX< i+(mapa.length/128) && p.posY> j && p.posY< j+(mapa[0].length/128)){
                      pixelnumber++;
                  }
                }
                
                /* Sensitivity multiplicates the square detection */
                if(pixelnumber*Gvar.square_sensibility > pixelnumber_max){
                    squares.add(new Point(i + (mapa.length/128)/2, j + (mapa[0].length/128)/2));
                    System.out.println("I " + i + "J " + j);
                    if(i/(mapa.length/128)>=0&&j/(mapa[0].length/128)>=0&&i/(mapa.length/128)<squares_on.length&&j/(mapa[0].length/128)<squares_on.length){
                    squares_on[i/(mapa.length/128)][j/(mapa.length/128)]=true;
                    System.out.println("SQUARE_SISS " + i/(mapa.length/128) +  " " + j/(mapa[0].length/128));
                    }
                }
                
                
            }
        }
        
        //See if is only one square, unless it then deletes other ones
        squares.clear();
                for(int i = 0; i < 128; i += 1){
                for(int j = 0; j < 128; j += 1){
                    // !!!OJO! Si esta en un costado no va a hacer el calculo
                    if(i>=1&&j>=1&&i<squares_on.length-1&&j<squares_on.length-1){
                        System.out.println("LLEGO ");
                    if(squares_on[i][j]&&!squares_on[i+1][j]&&!squares_on[i-1][j]&&!squares_on[i][j-1]&&!squares_on[i][j+1]){
                         System.out.println("ESTOY ");
                        squares.add(new Point(i + (mapa.length/128), j + (mapa[0].length/128)));
                    } else {
                        squares_on[i][j]=false;
                    }
                    }
                }
                }
                //Convertir squares_on en int
                int squares_on_int[][] = new int [128][128];
                for(int i = 0; i < 128; i += 1){
                for(int j = 0; j < 128; j += 1){
                    if(squares_on[i][j]==true){
                        System.out.println("SQUARE_SI " + i +  " " + j);
                    squares_on_int[i][j]=1;
                    }
                }
                }
                squares.clear();
                Islands islands = new Islands(squares_on_int);
                squares_on_int = islands.countIslands(squares_on_int);
                squares.add(new Point(2 , 0));
                squares.add(new Point(2 , 1));
                squares.add(new Point(2 , 2));
                squares.add(new Point(2 , 3));
                squares.add(new Point(2 , 4));
                for(int i = 0; i < 128; i += 1){
                for(int j = 0; j < 128; j += 1){
                    if(squares_on_int[i][j]==1){
                   squares.add(new Point(i , j));
                    }
                }
                }
                
       
        Gvar.celulas=squares.size();
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
            System.out.println("ROJIZO " + pointsY.get(i) +  " " + pointsX.get(i));
            g.drawLine((int)(Gvar.zoomX*(points.get(i).posX)) + Gvar.desv_derecha, (int)(Gvar.zoomY*(points.get(i).posY)) + Gvar.desv_arriba, (int)(Gvar.zoomX*(points.get(i).posX)) + Gvar.desv_derecha, (int)(Gvar.zoomY*(points.get(i).posY))+Gvar.desv_arriba); 
            g.setColor(Color.BLACK);
        }
        for(int i =0;i<squares.size();i++){
            g.setColor(Color.BLUE);
            System.out.println("AZULADO " + squares.get(i).posX +  " " + squares.get(i).posY);
            g.drawLine((int)(Gvar.zoomX*((squares.get(i).posX)*(mapa.length/128))) -20 + Gvar.desv_derecha, (int)(Gvar.zoomY*((squares.get(i).posY)*(mapa.length/128))) + Gvar.desv_arriba, (int)(Gvar.zoomX*((squares.get(i).posX)*(mapa.length/128))) +20 + Gvar.desv_derecha, (int)(Gvar.zoomY*((squares.get(i).posY)*(mapa.length/128))) + Gvar.desv_arriba); 
            g.drawLine((int)(Gvar.zoomX*((squares.get(i).posX)*(mapa.length/128))) + Gvar.desv_derecha, (int)(Gvar.zoomY*((squares.get(i).posY)*(mapa.length/128))) -20 + Gvar.desv_arriba, (int)(Gvar.zoomX*((squares.get(i).posX)*(mapa.length/128))) + Gvar.desv_derecha, (int)(Gvar.zoomY*((squares.get(i).posY)*(mapa.length/128))) + 20 + Gvar.desv_arriba); 
            g.setColor(Color.BLACK);
        }
    }
}
