package cluedo_game;

/**
 * A Tile representation of a player.  Should be passed an icon unique to the players character
 * @author Tim
 *
 */
public class PlayerTile extends Tile {

	private String icon;
	private String character;
	
	public PlayerTile(String character, String icon, int x, int y) {
		super(x, y);
		this.icon = icon;
		this.setCharacter(character);
	}

	public String getIcon() {
		return icon;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}
}
