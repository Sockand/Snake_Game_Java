/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_snake_game;

import java.awt.Color;
import java.awt.Graphics2D;
import java_snake_game.Engine.TileType;

/**
 *
 * @author sockand
 */
public class GameBoard {
    
    /*
 * The number of pixels each tile is along the x and y axis.
 */
public static final int TILE_SIZE = 25;

/*
 * The number of tiles the map is along the x and y axis.
 */
public static final int MAP_SIZE = 20;


/**
 * Stores an array that tracks the type of tile.
 */
private TileType[] tiles;

/**
 * Creates a new instance of the GameBoard class, and initialises
 * all of the tiles to EMPTY.
 */
public GameBoard() {
 tiles = new TileType[(MAP_SIZE * MAP_SIZE) + MAP_SIZE];
 
 resetBoard();
 tiles[5] = TileType.FRUIT;
    tiles[36] = TileType.FRUIT;
    tiles[77] = TileType.FRUIT;
    
 //resetBoard();
}

/**
 * Reset all of the tiles to EMPTY.
 */
public void resetBoard() {
 for(int i = 0; i < tiles.length; i++) {
  tiles[i] = TileType.EMPTY;
 }
}

/**
 * Sets the tile at the given coordinates.
 * @param x The x coordinate of the tile.
 * @param y The y coordinate of the tile.
 * @param type The type of tile.
 */
public void setTile(int x, int y, TileType type) {
 tiles[y * MAP_SIZE + x] = type;
}

/**
 * Sets the tile at the given coordinates.
 * @param i The i index of the tile.
 * @param type The type of tile.
 */
public void setTile(int i, TileType type) {
 tiles[i] = type;
}

/**
 * Gets the tile at the given coordinates.
 * @param x The x coordinate of the tile.
 * @param y The y coordinate of the tile.
 * @return The type of tile.
 */
public TileType getTile(int x, int y) {
 return tiles[y * MAP_SIZE + x];
}

/**
 * Gets the tile at the given coordinates.
 * @param i The i index of the tile.
 * @return The type of tile.
 */
public TileType getTile(int i) {
 return tiles[i];
}

/**
 * Gets the tile at the given coordinates.
 * @param i The i index of the tile.
 * @return The type of tile.
 */
public int getTileSize() {
 return tiles.length;
}

/**
 * Draws the game board.
 * @param g The graphics object to draw to.
 */
public void draw(Graphics2D g) {
 
  
      /* White background. The snake parts splits up.*/
      g.setColor(Color.WHITE);
  g.fillRect(0, 0, TILE_SIZE * MAP_SIZE, TILE_SIZE * 5);
   
 /*
  * Set the color of the tile to the snake color.
  */
 g.setColor(TileType.SNAKE.getColor());
 
 /*
  * Loop through all of the tiles.
  */
 for(int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
  
  /*
   * Calculate the x and y coordinates of the tile.
   */
  int x = i % MAP_SIZE;
  int y = i / MAP_SIZE;
  
  //System.out.println(" X " + x + " Y " + y + " tipo " + tiles[i]);
  
  /*
   * If the tile is empty, so there is no need to render it.
   */
  if(tiles[i].equals(TileType.EMPTY)) {
   continue;
  }
  
  /*
   * If the tile is fruit, we set the color to red before rendering it.
   */
  if(tiles[i].equals(TileType.FRUIT)) {
   g.setColor(TileType.FRUIT.getColor());
   g.fillOval(x * TILE_SIZE + 4, y * TILE_SIZE + 4, TILE_SIZE - 8, TILE_SIZE - 8);
  } else if(tiles[i].equals(TileType.SNAKE)) {
      g.setColor(TileType.SNAKE.getColor());
      
    /*
    * Th drawing of the snake depending on the connections.
    */
    int UP_SIZE =x * TILE_SIZE + 2;
    int DOWN_SIZE =TILE_SIZE - 4;
    int LEFT_SIZE = y * TILE_SIZE + 2;
    int RIGHT_SIZE =TILE_SIZE - 4;
    
     if(i - MAP_SIZE*5 > 0){
    if(tiles[i-1]==Engine.TileType.SNAKE||tiles[i-1]==Engine.TileType.SNAKE_HEAD){
          UP_SIZE = x * TILE_SIZE;
      } 
    if(tiles[i+1]==Engine.TileType.SNAKE||tiles[i+1]==Engine.TileType.SNAKE_HEAD){
          DOWN_SIZE =TILE_SIZE;
      }
    if(tiles[i-MAP_SIZE]==Engine.TileType.SNAKE||tiles[i-MAP_SIZE]==Engine.TileType.SNAKE_HEAD){
          LEFT_SIZE = y * TILE_SIZE;
      }
    if(tiles[i+MAP_SIZE]==Engine.TileType.SNAKE||tiles[i+MAP_SIZE]==Engine.TileType.SNAKE_HEAD){
          RIGHT_SIZE =TILE_SIZE;
      }
     }
          g.fillRect(UP_SIZE, LEFT_SIZE, DOWN_SIZE, RIGHT_SIZE);
   
  } else if(tiles[i].equals(TileType.SNAKE_HEAD)) {
      g.setColor(TileType.SNAKE_HEAD.getColor());
   //g.fillRect(x * TILE_SIZE + 1, y * TILE_SIZE + 1, TILE_SIZE - 2, TILE_SIZE - 2);
   g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
  }
 }
}



}
