/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_snake_game;

import java.awt.Point;
import java.util.LinkedList;
import java_snake_game.Engine.Direction;
import static java_snake_game.Engine.FAST_ACTIVE;
import java_snake_game.Engine.TileType;

/**
 *
 * @author sockand
 */
public class Snake {
    

/*
 * The GameBoard instance.
 */
private GameBoard board;

/*
 * Create the list that contains the points of the snake.
 */
private LinkedList<Point> points;

/*
 * The current direction that the snake is moving.
 */
private Direction currentDirection;

/*
 * The last direction pressed on the keyboard, this will be explained in the next lesson.
 */
private Direction temporaryDirection;

/*
 * Creates a new Snake instance.
 * @param board The GameBoard instance.
 */
public Snake(GameBoard board) {
 this.board = board;
 this.points = new LinkedList<Point>();
}

public void resetSnake() {
 this.currentDirection = Direction.NONE;
 this.temporaryDirection = Direction.NONE;

 Point head = new Point(GameBoard.MAP_SIZE / 2, GameBoard.MAP_SIZE / 2);
 points.clear();
 points.add(head);
 board.setTile(head.x, head.y, TileType.SNAKE);
}

public void deleteTail() {
Point last = points.removeLast();
  board.setTile(last.x, last.y, TileType.EMPTY);
}

public int tailSize() {
return points.size();
}



public TileType updateSnake() {

 /*
  * Polls the snake's direction from the keyboard input, explained later.
  */
 this.currentDirection = temporaryDirection;
 
 /*
  * Gets the location of the head of the snake.
  */
 Point head = points.getFirst(); 
   
 System.out.println("Y " + head.y + "X " + head.x);
 
 /*
  * Gets the current direction of the snake, and returns false if the direction leads
  * us into a wall. Otherwise, it pushes a new point onto the first position of the
  * list (the head) in the new position of the head.
  */
 switch(currentDirection) {
 
 case UP:
  if(head.y <= 0) {
   return null;
  }
  points.push(new Point(head.x, head.y - 1));
  break;
  
 case DOWN:
  if(head.y >= GameBoard.MAP_SIZE - 1) {
   return null;
  }
  points.push(new Point(head.x, head.y + 1));
  break;
  
 case LEFT:
  if(head.x <= 0) {
   return null;
  }
  points.push(new Point(head.x - 1, head.y));
  break;
  
 case RIGHT:
  if(head.x >= GameBoard.MAP_SIZE - 1) {
   return null;
  }
  points.push(new Point(head.x + 1, head.y));
  break;
  
 case NONE:
  return TileType.EMPTY;
 }
 
 /*
  * Updates the head variable with the position of the new head.
  */
 head = points.getFirst();
 Point neck = points.get(1); 
 
 /*
  * If the snake head is on the white area, goes double speed.
  */
 if(head.y>4){
     Engine.UPDATES_PER_SECOND = 5;
     if(FAST_ACTIVE){
        Engine.UPDATES_PER_SECOND = 10; 
     }
 } else {
     Engine.UPDATES_PER_SECOND = 10;
     if(FAST_ACTIVE){
        Engine.UPDATES_PER_SECOND = 20; 
     }
 }
 
 for(int i = 0; i < points.size() - 1; i++){
     System.out.println("POINT " + board.getTile(points.get(i).x, points.get(i).y));
 }

 /*
  * Gets the type of tile that was at the head location before we updated.
  * If the tile type was not a fruit, we remove the last point from the snake
  * and update the board to reflect this.
  */
 TileType oldType = board.getTile(head.x, head.y);
 if(!oldType.equals(TileType.FRUIT)) {
  Point last = points.removeLast();
  board.setTile(last.x, last.y, TileType.EMPTY);
  oldType = board.getTile(head.x, head.y);
 }

 /*
  * Set the tile at the head location to a snake tile.
  */
 board.setTile(head.x, head.y, TileType.SNAKE_HEAD);
 if(points.size()>1){
 board.setTile(neck.x, neck.y, TileType.SNAKE);
 }
 
 /*
  * Return the tile type.
  */
 return oldType;
}

public int getSnakeLength() {
 return points.size();
}

public void setDirection(Direction direction) {
 if(direction.equals(Direction.UP) && currentDirection.equals(Direction.DOWN)) {
  return;
 } else if(direction.equals(Direction.DOWN) && currentDirection.equals(Direction.UP)) {
  return;
 } else if(direction.equals(Direction.LEFT) && currentDirection.equals(Direction.RIGHT)) {
  return;
 } else if(direction.equals(Direction.RIGHT) && currentDirection.equals(Direction.LEFT)) {
  return;
 }
 this.temporaryDirection = direction;
}


}
