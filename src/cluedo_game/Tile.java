package cluedo_game;

/**
 * A simple abstract Tile class that represents an x and y coordinate
 * @author Tim King (300282037)
 */
public abstract class Tile {

	private int x,y;
	
	public Tile(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public abstract String getIcon();
	
}
