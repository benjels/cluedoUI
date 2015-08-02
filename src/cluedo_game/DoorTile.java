package cluedo_game;

/**
 * 
 * @author Tim
 *
 *Represents a Door in cluedo;  Acts as a tile in all ways except that it is a door.
 *
 */
public class DoorTile extends Tile {
	
	public DoorTile(int x, int y) {
		super(x, y);
	}

	@Override
	public String getIcon() {
		return " ";
	}

}
