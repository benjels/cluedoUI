package cluedo_game;

import cluedo_game.Board.Direction;

/**
 * 
 * @author Tim
 *
 *Represents a Door in cluedo;  Acts as a tile in all ways except that it is a door.
 *
 */
public class DoorTile extends Tile {
	
	private Direction dir;
	
	public DoorTile(Direction dir, int x, int y) {
		super(x, y);
		this.setDirection(dir);
	}

	@Override
	public String getIcon() {
		switch(dir){
		case NORTH : return "__";
		case EAST : return "| ";
		case WEST : return " |";
		case SOUTH : return "__";
		}
		return "__";
	}

	public Direction getDirection() {
		return dir;
	}

	public void setDirection(Direction dir) {
		this.dir = dir;
	}

}
