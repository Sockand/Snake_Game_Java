/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_snake_game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java_snake_game.Sounds.SoundEffect;

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
private Snake snake;

private int score;
private static final Font SMALL_FONT = new Font("Times New Roman", Font.BOLD, 20);
private static final Font FONT_LARGE = new Font("Times New Roman", Font.BOLD, 40);

private boolean gameOver;



/*
 * Creates a new Engine instance.
 * @param canvas The canvas instance we're drawing onto.
 */
Engine(Canvas canvas) {
 this.canvas = canvas;
 gameBoard = new GameBoard();
 gameBoard.setTile(3, TileType.FRUIT);
  snake = new Snake(gameBoard);
  resetGame();
 
 this.canvas.addKeyListener(this);


}


/*
 * The number of times to update the game per second.
 */
protected static int UPDATES_PER_SECOND = 5;
protected static int TIME_FAST = 30;
protected static boolean FAST_ACTIVE = false;

protected static int time_left = 10;
protected static int contador = 0;


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
   * Set the time that the loop finished. Discounts the extra time to reach this line.
   */
  sleepDuration = (1000L / UPDATES_PER_SECOND) - (System.currentTimeMillis() - start);
   
  /*
   * If the sleep duration is greater than 0 milliseconds, attempt to sleep.
   */
  if(sleepDuration > 0) {
   try {
       contador++;
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
 if(gameOver || !canvas.hasFocus()) {
  return;
 }
 TileType snakeTile = snake.updateSnake();
 if(snakeTile == null || snakeTile.equals(TileType.SNAKE) || snakeTile.equals(TileType.SNAKE_HEAD) 
         || snakeTile.equals(TileType.WALL)) {
  gameOver = true;
 } else if(snakeTile.equals(TileType.FRUIT)) {
     time_left=10;
  score += 10;
  spawnAny(TileType.FRUIT);
  int RNG = (int)(Math.random()*3);
  if(RNG==0){
  spawnAny(TileType.WALL);
  } else if(RNG==1){
  spawnAny(TileType.WALL);
  } else if(RNG==2){
  spawnAny(TileType.GIRO);
  } else {
  spawnAny(TileType.GIRO);
  }
 }
 
 if(FAST_ACTIVE){
        TIME_FAST--;
     }
 if(TIME_FAST<1){
        TIME_FAST=30;
        FAST_ACTIVE=false;
     }
 
 if(contador%5==0){
     time_left--;
 }
 if(time_left<1){
     gameOver = true;
 }
}


/*
 * Draw the game onto the graphics object.
 */
private void render(Graphics2D g) {
 //Empty for now, we'll fill this in later.
 
 // System.out.println("RENDERIZADO");
 //gameBoard = new GameBoard();
 gameBoard.draw(g);
  g.setColor(Color.WHITE);

 if(gameOver) {
  g.setFont(FONT_LARGE);
  String message = new String("Final Score: " + score);
  g.drawString(message, canvas.getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2), 250);
  g.setFont(SMALL_FONT);
  message = new String("Press Enter to Restart");
  g.drawString(message, canvas.getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2), 350);
 } else {
     g.setColor(Color.BLACK);
  g.setFont(SMALL_FONT);
  g.drawString("Score: " + score, 10, 20);
  g.setColor(Color.WHITE);
  g.setFont(FONT_LARGE);
  String message = new String("Time left: " + time_left);
  g.drawString(message, canvas.getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2), canvas.getHeight() - 50);
 }

}



public static enum TileType {
 /*
  * Snake Tiles will kill us if we run into them.
  */
 SNAKE(Color.GREEN),
 
 /*
  * Snake head. Main tile in control.
  */
 SNAKE_HEAD(Color.YELLOW),
 
 /*
  * Fruit Tiles will give us points when we run into them.
  */
 FRUIT(Color.RED),
 /* Wall Tiles will kill us when hits them.*/
 WALL(Color.MAGENTA),
 
 /* Giro Tiles will turn on our direction.*/
 GIRO(Color.ORANGE),
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
if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
 snake.setDirection(Direction.UP);
}
if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
 snake.setDirection(Direction.DOWN);
}
if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
 snake.setDirection(Direction.LEFT);
 //System.out.println("TECLEADO");
 //for(int i = 0; i < gameBoard.getTileSize() - 1; i++){
     
     //gameBoard.setTile(i, gameBoard.getTile(i+1));
 //}
}
if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
 snake.setDirection(Direction.RIGHT);
 //for(int i = gameBoard.getTileSize() -1; i > 1; i--){
     //gameBoard.setTile(i, gameBoard.getTile(i-1));
 //}
}

/* Consume one tail piece to double speed. */
if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
    if(snake.tailSize()>1){
    snake.deleteTail();
    FAST_ACTIVE = true;
    SoundEffect.SCREAM.play();
    }
 //for(int i = gameBoard.getTileSize() -1; i > 1; i--){
     //gameBoard.setTile(i, gameBoard.getTile(i-1));
 //}
}

if(e.getKeyCode() == KeyEvent.VK_ENTER && gameOver) {
			resetGame();
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

private void spawnAny(TileType tiletipe) {
 int random = (int)(Math.random() * ((GameBoard.MAP_SIZE * GameBoard.MAP_SIZE)
- snake.getSnakeLength()));
  
 int emptyFound = 0;
 int index = 0;
 while(emptyFound < random) {
  index++;
  if(gameBoard.getTile(index % GameBoard.MAP_SIZE, index / GameBoard.MAP_SIZE)
.equals(TileType.EMPTY)) {
   emptyFound++;
  }
 }
 gameBoard.setTile(index % GameBoard.MAP_SIZE, index / GameBoard.MAP_SIZE, tiletipe);
}


private void resetGame() {
 gameBoard.resetBoard();
 snake.resetSnake();
 score = 0;
 gameOver = false;
 time_left = 10;
 spawnAny(TileType.FRUIT);
}


}
