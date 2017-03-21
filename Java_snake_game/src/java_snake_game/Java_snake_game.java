/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_snake_game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author sockand
 */
public class Java_snake_game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
  * Create a new JFrame and set it's properties up.
  */
 JFrame frame = new JFrame("Snake Game");
 
 /*
  * Create a new canvas, and set it's properties up.
  */
 Canvas canvas = new Canvas();
 canvas.setBackground(Color.BLACK);
 //canvas.setPreferredSize(new Dimension(500, 500));
 canvas.setPreferredSize(new Dimension(GameBoard.MAP_SIZE * GameBoard.TILE_SIZE,
GameBoard.MAP_SIZE * GameBoard.TILE_SIZE));
 
 /*
  * Add the canvas to the frame.
  */
 frame.add(canvas);
 
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 frame.setResizable(false);
 
 /*
  * Pack the frame, position it in the center of the screen, and then display
  * it.
  */
 frame.pack();
 frame.setLocationRelativeTo(null);
 frame.setVisible(true);
 
 //Window creation code...

 /*
  * Create the engine and start the game.
  */
 Engine engine = new Engine(canvas);
 engine.startGame();
    }
    
}
