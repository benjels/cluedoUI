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
		MISSSCARLET("miss scarlet", 1), MRSWHITE("mrs. white", 2), MRSPEACOCK(
				"mrs peacock", 3), PROFESSORPLUM("professor plum", 4), MRGREEN("mr green", 5), COLONELMUSTARD(
				"colonel mustard", 6);
		
		//each character card has one of these enums as an identifier of which weapon it actually is. These enums also hold a string which is 
		//just a more readable/user friendly name for that enum value. This is stored in the characterName String field.
		private String characterName;
		//each character card needs a code so that we can have the user input a number and have that correspond to a certain character
		//easier to input a number than to type in a string exactly correct
		private int code;

		private Character(String character, int code) {
			this.characterName = character;
			this.code = code;
		}

		public String getString() {
			return this.characterName;
		}
		
		public int getCode(){
			return this.code;
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