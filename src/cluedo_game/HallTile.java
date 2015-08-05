package cluedo_game;

/**
 * @author Tim King (300282037)
 * Represents a location in the hallway.
 */
public class HallTile extends Tile {

	public HallTile(int x, int y){
		super(x,y);
	}

	public String getIcon() {
		
		return "  ";
	}
	
}
