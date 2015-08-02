package cluedo_game;

/**
 * A Tile representation of a player.  Should be passed an icon unique to the players character
 * @author Tim
 *
 */
public class PlayerTile extends Tile {

	private String icon;
	
	public PlayerTile(String icon, int x, int y) {
		super(x, y);
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}
}
