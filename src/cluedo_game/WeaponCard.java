package cluedo_game;

/**
 * each instance represents a different one of the weapon cards. The actual weapon
 * name is stored as a string in an enum that is defined within this class.
 * 
 * @author Max
 *
 */
public class WeaponCard implements Card {
	// the weapon of this WeaponCard
	public final WeaponCard.Weapon weapon;

	WeaponCard(WeaponCard.Weapon weapon) {
		this.weapon = weapon;
	}


	
	// ENUM FOR UNIQUE WEAPONS
	public enum Weapon {
		CANLESTICK("candlestick"), WRENCH("wrench"), ROPE(
				"rope"), LEADPIPE("lead pipe"), KNIFE("knife"), REVOLVER(
				"revolver"), AXE("axe"), POISON("poison");
		
		//each weapon card has one of these enums as an identifier of which weapon it actually is. These enums also hold a string which is 
		//just a more readable/user friendly name for that enum value. This is stored in the weaponName String field.
		private String weaponName;

		private Weapon(String weapon) {
			this.weaponName = weapon;
		}

		public String getString() {
			return this.weaponName;
		}
	}

}