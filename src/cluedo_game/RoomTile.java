package cluedo_game;

/**
 * @author Tim King (300282037)
 * 
 * Represents a tile in a given room.  
 *
 */
public class RoomTile extends Tile {

	private String room;
	private String icon;
	
	/**
	 * 
	 * @param room The room the tile is in e.g "Kitchen", "Dining room", "Study"
	 * @param icon
	 * @param x
	 * @param y
	 */
	public RoomTile(String room, String icon, int x, int y) {
		super(x, y);
		this.room=room;
		this.icon=icon;
	}

	public String getRoomName(){
		return room;
	}
	
	@Override
	public String getIcon() {
		
		return icon;
	}

}
