package cluedo_game;

import cluedo_game.WeaponCard.Weapon;

public class WeaponTile extends Tile {

	private Weapon weapon;
	private String icon;
	
	public WeaponTile(Weapon weapon, String icon, int x, int y) {
		super(x, y);
		this.weapon=weapon;
		this.icon=icon;
	}

	@Override
	public String getIcon() {
		
		return icon;
	}
	
	public Weapon getWeapon(){
		return weapon;
	}

}