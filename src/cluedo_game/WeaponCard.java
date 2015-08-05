package cluedo_game;

import java.util.HashMap;

/**
 * each instance represents a different one of the weapon cards. The actual weapon
 * name is stored as a string in an enum that is defined within this class.
 * 
 * @author Max
 *
 */
public class WeaponCard implements Card {
	// the weapon of this WeaponCard. It is ok to have this enum field publically accessible because our Weapon enum object is immutable and the field is declared as final.
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

	
	
	//HASHMAP FROM INT --> WEAPON ENUM
	//this is useful as it lets the user just enter an integer
	//to show what they want. Much easier to type in a single char
	//number than a string.
	public static final HashMap<Integer, WeaponCard.Weapon> intToWeapon = new HashMap<>();
	static {
	    intToWeapon.put(1,WeaponCard.Weapon.AXE);
	    intToWeapon.put(2,WeaponCard.Weapon.CANLESTICK);
	    intToWeapon.put(3,WeaponCard.Weapon.KNIFE);
	    intToWeapon.put(4,WeaponCard.Weapon.LEADPIPE);
	    intToWeapon.put(5,WeaponCard.Weapon.POISON);
	    intToWeapon.put(6,WeaponCard.Weapon.REVOLVER);
	    intToWeapon.put(7,WeaponCard.Weapon.ROPE);
	    intToWeapon.put(8,WeaponCard.Weapon.WRENCH);

	}
}