package cluedo_game;

import cluedo_game.RoomCard.Room;

/**
 * @author Tim King (300282037)
 * 
 * Represents a tile in a given room
 *
 */
public class RoomTile extends Tile {

	private Room room;
	private String icon;
	
	/**
	 * 
	 * @param room The room the tile is in e.g "Kitchen", "Dining room", "Study"
	 * @param icon
	 * @param x
	 * @param y
	 */
	public RoomTile(Room room, String icon, int x, int y) {
		super(x, y);
		this.room=room;
		this.icon=icon;
	}
	
	@Override
	public String getIcon() {
		
		return icon;
	}

	public Room getRoom() {
		return room;
	}

}
