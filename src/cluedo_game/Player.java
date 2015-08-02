package cluedo_game;

import java.util.HashSet;

/**
 * represents a player in the cluedo game NOT A CHARACTER!. Player's have a name, the cards that they are dealt at the start of the game
 * and a PlayerTile object which is their game token. Information about this player's character (which character  it is, what its location is on the board) is stored in this
 * CharacterTile object. A Player instance is also responsible for checking whether/how it can refute a given guess by another player in the game.
 * @author Max
 *
 */
public class Player {
	
	
	// each player has a set for each of the three kinds of cards (essentially their "hand"). This could have been implemented as a some public final fields, but i
	//will use the getter/setter model instead because it is easier to add the cards after the player has been constructed
	private HashSet<WeaponCard> weaponCards = new HashSet<>();
	private HashSet<RoomCard> roomCards = new HashSet<>();
	private HashSet<CharacterCard> characterCards = new HashSet<>();
	
	//each player has a name (the name of the person playing the game). This is set while the game is being set up and string is immutable so we use the public final pattern.
	public final String playerName;
	
	//each player has a tile which holds information about their character in the game
	private PlayerTile playerTile;
	
	Player(String name, PlayerTile tile){
		this.playerName = name;
		this.playerTile = tile;
	}
	

	
	
	
	
	/**
	 * checks whether this Player, given a certain Guess, is able to refute that Guess
	 * with the cards in his/her hand
	 * @param guess the combination of cards that were guessed
	 * @return true if this Player has at least one of the guessed cards, else false.
	 */
	public boolean canRefute(Guess guess){
		if(this.weaponCards.contains(guess.weaponCard)){
			return true;
		}
		if(this.roomCards.contains(guess.roomCard)){
			return true;
		}
		if(this.characterCards.contains(guess.characterCard)){
			return true;
		}
		//this is only reached/we only return false if none of the guessed cards can be found in their respective sets
		return false;
	}
	
	
	/**
	 * gets a set of the cards that this player can use to refute a given Guess.
	 * Only use this method when it is established that this player CAN refute the guess
	 * @param guess the combination of cards that were guessed
	 * @return the subset of cards that are in this player's hand AND the supplied Guess
	 */
	public HashSet<? extends Card> getPotentialRefutationCards(Guess guess){
		HashSet<Card> refutationCards = new HashSet<>();
		
		//traverse the sets of cards in this player's hand and when we find a card that is also present in the 
		//guess, add it to the set of cards that we will return
		for(WeaponCard eachCard: this.weaponCards){
			if(guess.weaponCard.equals(eachCard)){
				refutationCards.add(eachCard);
			}
		}
		for(RoomCard eachCard: this.roomCards){
			if(guess.roomCard.equals(eachCard)){
				refutationCards.add(eachCard);
			}
		}
		for(CharacterCard eachCard: this.characterCards){
			if(guess.characterCard.equals(eachCard)){
				refutationCards.add(eachCard);
			}
		}
		
		assert(!refutationCards.isEmpty()):"we should not be returning an empty set here. this method should only be called on a player that CAN refute a guess somehow.";
		return refutationCards;
	}
	
	
}
