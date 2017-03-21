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
 tiles = new TileType[MAP_SIZE * MAP_SIZE];
 
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
   g.setColor(TileType.SNAKE.getColor());
  } else {
   g.fillRect(x * TILE_SIZE + 1, y * TILE_SIZE + 1, TILE_SIZE - 2, TILE_SIZE - 2);
  }
 }
}



}
