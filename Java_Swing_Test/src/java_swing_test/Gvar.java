/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_swing_test;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author sockand
 */
public class Gvar {
    //Conteo
    static int celulas = 0;
    
    static int square_sensibility = 8;
    static int desv_arriba = 20;
    static int desv_derecha = 0;
    static double zoomX = 0.75;
    static double zoomY = 0.75;
    //Repaint has to repaint image too.
    static boolean image_paint = true;

            
    static boolean squaring = false;
    static boolean pressed = false;
    
    static int squaring_first_x = 0;
    static int squaring_first_y = 0;
    static int squaring_final_x = 0;
    static int squaring_final_y = 0;
    
    static SortedMap<Integer, ArrayList<Point>> points = new TreeMap<Integer, ArrayList<Point>>();
    static SortedMap<Integer, ArrayList<Point>> points_limited = new TreeMap<Integer, ArrayList<Point>>();

}
