package com.poonsak.gdxpacman.game;

import com.badlogic.gdx.math.Vector2;

public class Pacman {
	
	public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_LEFT = 4;
    public static final int DIRECTION_STILL = 0;
    public static final int SPEED = 5;
	private Vector2 position;
	private int currentDirection;
	private int nextDirection;
	private Maze maze;
	
	public Pacman(int x, int y, Maze maze) {
		position = new Vector2(x,y);
		currentDirection = DIRECTION_STILL;
		nextDirection = DIRECTION_STILL;
		this.maze = maze;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	private static final int [][] DIR_DIFF = new int [][] {
		{0,0},
		{0,-1},
		{1,0},
		{0,1},
		{-1,0}
	};
	
	
	public void move(int dir) { 
        position.x += SPEED * DIR_DIFF[dir][0];
        position.y += SPEED * DIR_DIFF[dir][1];
        
    }
	
	public void setNextDirection(int dir) {
		nextDirection = dir;
	}
	
	public void update() {
		if(isAtCenter()) {	
			if(canMoveInDirection(nextDirection)) {
				currentDirection = nextDirection;
			} else {
				currentDirection = DIRECTION_STILL;
			}
			
			int newRow = getRow() + DIR_DIFF[nextDirection][1];
			int newCol = getColumn() + DIR_DIFF[nextDirection][0];
			if(maze.hasDotAt(newRow, newCol)) {
				maze.removeDotAt(newRow, newCol);;
			}
		}
		position.x += SPEED * DIR_DIFF[currentDirection][0];
		position.y += SPEED * DIR_DIFF[currentDirection][1];
	}
	
	public boolean isAtCenter() {
		int blockSize = WorldRenderer.BLOCK_SIZE;
		
		return ((((int)position.x - blockSize/2) % blockSize) == 0) &&
                ((((int)position.y - blockSize/2) % blockSize) == 0);
	}

	
	private boolean canMoveInDirection(int dir) {
		
		int newRow = getRow() + DIR_DIFF[dir][1];
		int newCol = getColumn() + DIR_DIFF[dir][0];
				
		if (maze.hasWallAt(newRow, newCol)){
			return false;
		}
		return true;
	}
	
	private int getRow() {
		return ((int)position.y) / WorldRenderer.BLOCK_SIZE;
	}
	
	private int getColumn() {
		return ((int)position.x) / WorldRenderer.BLOCK_SIZE;
	}
}