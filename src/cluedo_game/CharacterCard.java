package cluedo_game;

import java.util.Arrays;
import java.util.HashMap;

/**
 * each instance represents a different one of the character cards. The actual character
 * name is stored as a string in an enum that is defined within this class.
 * 
 * @author Max
 *
 */
public class CharacterCard implements Card {
	// the character of this WeaponCard It is ok to have this enum field publically accessible because our Weapon enum object is immutable and the field is declared as final.
	public final CharacterCard.Character character;

	CharacterCard(CharacterCard.Character character) {
		this.character = character;
	}


	
	// ENUM FOR UNIQUE CHARACTERS
	public enum Character {
		MISSSCARLET("miss scarlet", 8, 24, 's'), MRSWHITE("mrs. white", 10, 0, 'w'), MRSPEACOCK(
				"mrs peacock", 24, 6, 'm'), PROFESSORPLUM("professor plum", 24, 19, 'p'), MRGREEN("mr green", 15, 0, 'g'), COLONELMUSTARD(
				"colonel mustard", 1, 17, 'm');
		
		//each character card has one of these enums as an identifier of which weapon it actually is. These enums also hold a string which is 
		//just a more readable/user friendly name for that enum value. This is stored in the characterName String field.
		private String characterName;
		//we give each character a starting x and a starting y so we know where to place them on the board
		private int startX;
		private int startY;
		//we give each character a unique ascii char to represent this character on the game board
		private char icon;
	
		private Character(String character, int x, int y, char icon) {
			this.characterName = character;
			this.startX = x;
			this.startY = y;
			this.icon = icon;
		}

		public String getString() {
			return this.characterName;
		}
		
		public int getXStart() {
			return this.startX;
		}
	
		public int getYStart() {
			return this.startY;
		}
	
		public char getIcon() {
			return this.icon;
		}
	
		
		
		
	}
	//HASHMAP FROM INT --> CHARACTER ENUM
	//this is useful as it lets the user just enter an integer
	//to show what they want. Much easier to type in a single char
	//number than a string.
	public static final HashMap<Integer, CharacterCard.Character> intToCharacter = new HashMap<>();
	static {
	    intToCharacter.put(1,CharacterCard.Character.MISSSCARLET);
	    intToCharacter.put(2,CharacterCard.Character.MRSWHITE);
	    intToCharacter.put(3,CharacterCard.Character.MRSPEACOCK);
	    intToCharacter.put(4,CharacterCard.Character.PROFESSORPLUM);
	    intToCharacter.put(5,CharacterCard.Character.MRGREEN);
	    intToCharacter.put(6,CharacterCard.Character.COLONELMUSTARD);
	   
	}
}