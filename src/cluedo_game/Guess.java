package cluedo_game;

/**
 * represents a guess made by one of the players in the game as to what the
 * murderer/weapon/location were. Contains one card from each of the three
 * groups of cards (character, weapons, locations). if this guess is a player's
 * final guess / "accusation", that is specified by a boolean field. Instances
 * will be constructed in the game class and then checked against the players'
 * cards/winning combination of cards to check who can refute an
 * accusation/whether this is the correct combination of cards.
 * 
 * @author Max
 *
 */
public class Guess {

	// these fields are the constituent cards of this guess. We can make them
	// public because they are declared as final and the state of card objects
	// is immutable
	public final WeaponCard weaponCard;
	public final RoomCard roomCard;
	public final CharacterCard characterCard;

	// an instance of this class is either an ordinary guess (the kind a player
	// can make every turn) or a final guess (which a player can elect to make
	// only once per game)
	public final boolean isFinal;
	
	
	
	Guess(WeaponCard weapon, RoomCard room,
			CharacterCard character, boolean isFinal) {
		this.weaponCard = weapon;
		this.roomCard = room;
		this.characterCard = character;
		this.isFinal = isFinal;
	}

}
