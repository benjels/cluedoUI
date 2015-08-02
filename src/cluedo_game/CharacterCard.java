package cluedo_game;

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
		MISSSCARLET("miss scarlet"), MRSWHITE("mrs. white"), MRSPEACOCK(
				"mrs peacock"), PROFESSORPLUM("professor plum"), MRGREEN("mr green"), COLONELMUSTARD(
				"colonel mustard");
		
		//each character card has one of these enums as an identifier of which weapon it actually is. These enums also hold a string which is 
		//just a more readable/user friendly name for that enum value. This is stored in the characterName String field.
		private String characterName;

		private Character(String character) {
			this.characterName = character;
		}

		public String getString() {
			return this.characterName;
		}
	}

}