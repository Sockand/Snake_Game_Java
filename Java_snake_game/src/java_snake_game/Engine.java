/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_snake_game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author sockand
 */
public class Engine extends KeyAdapter {
    /*
 * The canvas instance that we draw to.
 */
private Canvas canvas;
GameBoard gameBoard;
/*
 * Creates a new Engine instance.
 * @param canvas The canvas instance we're drawing onto.
 */
Engine(Canvas canvas) {
 this.canvas = canvas;
 this.canvas.addKeyListener(this);
 gameBoard = new GameBoard();
 
 gameBoard.setTile(3, TileType.FRUIT);
}


/*
 * The number of times to update the game per second.
 */
private static final int UPDATES_PER_SECOND = 10;

/**
 * Responsible for setting up and retrieving the rendering context, then maintaining
 * the game loop.
 */
public void startGame() {
 /*
  * Create the buffers and get the graphics.
  */
 canvas.createBufferStrategy(2);
  
 /*
  * Grab a reference to the graphics object.
  */
 Graphics2D g = (Graphics2D)canvas.getBufferStrategy().getDrawGraphics();
  
 /*
  * Create the variables that keep the loop executing at a constant framerate.
  */
 long start = 0L;
 long sleepDuration = 0L;
 
 /*
  * The game loop will continue to click until the program ends.
  */
 while(true) {

  /*
   * Set the time that the loop started.
   */
  start = System.currentTimeMillis();
  
  /*
   * Update the game.
   */
  update();
   
  /*
   * Draw the game onto the graphics object.
   */
  render(g);
  
  /*
   * Display the graphics onto the screen.
   */
  canvas.getBufferStrategy().show();

  /*
   * Clear the screen so we draw a fresh frame next time.
   */
   g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
   
  /*
   * Set the time that the loop finished.
   */
  sleepDuration = (1000L / UPDATES_PER_SECOND) - (System.currentTimeMillis() - start);
   
  /*
   * If the sleep duration is greater than 0 milliseconds, attempt to sleep.
   */
  if(sleepDuration > 0) {
   try {
    Thread.sleep(sleepDuration);
   } catch(Exception e) {
    e.printStackTrace();
   }
  }
 }
}

/*
 * Update the game's logic.
 */
private void update() {
 //Empty for now, we'll fill this in later.
}

/*
 * Draw the game onto the graphics object.
 */
private void render(Graphics2D g) {
 //Empty for now, we'll fill this in later.
 
 // System.out.println("RENDERIZADO");
 //gameBoard = new GameBoard();
 gameBoard.draw(g);
}


public static enum TileType {
 /*
  * Snake Tiles will kill us if we run into them.
  */
 SNAKE(Color.GREEN),
 
 /*
  * Fruit Tiles will give us points when we run into them.
  */
 FRUIT(Color.RED),

 /*
  * Empty Tiles do nothing when we run into them.
  */
 EMPTY(null);

 /*
  * The color of the tile.
  */
 private Color tileColor;

 /*
  * TileType constructor
  * @param color The color of this tile.
  */
 private TileType(Color color) {
  this.tileColor = color;
 }

 /*
  * @return The color of this tile.
  */
 public Color getColor() {
  return tileColor;
 }
}

@Override
public void keyPressed(KeyEvent e) {
    System.out.println("TECLEADO22");
if(e.getKeyCode() == KeyEvent.VK_W) {
 //snake.setDirection(Direction.UP);
}
if(e.getKeyCode() == KeyEvent.VK_S) {
 //snake.setDirection(Direction.DOWN);
}
if(e.getKeyCode() == KeyEvent.VK_A) {
 //snake.setDirection(Direction.LEFT);
 System.out.println("TECLEADO");
 for(int i = 0; i < gameBoard.getTileSize() - 1; i++){
     
     gameBoard.setTile(i, gameBoard.getTile(i+1));
 }
}
if(e.getKeyCode() == KeyEvent.VK_D) {
 //snake.setDirection(Direction.RIGHT);
 for(int i = gameBoard.getTileSize() -1; i > 1; i--){
     gameBoard.setTile(i, gameBoard.getTile(i-1));
 }
}

}



public static enum Direction {

 /*
  * Snake is moving up.
  */
 UP,

 /*
  * Snake is moving down.
  */
 DOWN,

 /*
  * Snake is moving left.
  */
 LEFT,

 /*
  * Snake is moving right.
  */
 RIGHT,

 /*
  * Snake is stationary.
  */
 NONE

}



}
