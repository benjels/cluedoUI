package cluedo_game;

public class PortalTile extends Tile {

	private String icon;
	
	public PortalTile(String icon, int x, int y) {
		super(x, y);
		this.icon=icon;
	}

	@Override
	public String getIcon() {
		return icon;
	}

}