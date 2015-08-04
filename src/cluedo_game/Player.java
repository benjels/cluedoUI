package cluedo_game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

/**
 * represents a player in the cluedo game NOT A CHARACTER!. Player's have a name, the cards that they are dealt at the start of the game
 * and a PlayerTile object which is their game token. Information about this player's character (which character  it is, what its location is on the board) is stored in this
 * CharacterTile object. A Player instance is also responsible for checking whether/how it can refute a given guess by another player in the game.
 * @author Max
 *
 */
public class Player implements KeyListener{
	
	
	// each player has a set for each of the three kinds of cards (essentially their "hand"). This could have been implemented as a some public final fields, but i
	//will use the getter/setter model instead because it is easier to add the cards after the player has been constructed
	private HashSet<WeaponCard> weaponCards = new HashSet<>();
	private HashSet<RoomCard> roomCards = new HashSet<>();
	private HashSet<CharacterCard> characterCards = new HashSet<>();
	
	//each player has a name (the name of the person playing the game). This is set while the game is being set up and string is immutable so we use the public final pattern.
	public final String playerName;
	
	//each player has a tile which holds information about their character in the game
	private PlayerTile playerTile;
	
	//if a player is able to make a move, this is set to true and the key listener is "allowed"
	private boolean canMove = false;
	
	
	//SETUP
	
	Player(String name, PlayerTile tile){
		this.playerName = name;
		this.playerTile = tile;
	}
	
	/**
	 * method that decides which list of cards the card
	 * dealt to this player belongs in. Just check what dynamic type the
	 * card is and assign it appropriately
	 * @param card the card dealt by the setup of the game
	 */
	void receiveCard(Card card){
		if(card instanceof WeaponCard){
			this.weaponCards.add((WeaponCard) card);
		}else if(card instanceof RoomCard){
			this.roomCards.add((RoomCard) card);
		}else if(card instanceof CharacterCard){
			this.characterCards.add((CharacterCard) card);
		}else{
			throw new RuntimeException("dealt card must be one of the specified types");
		}
	}
	
	
	
	
	//MOVEMENT:
	/**
	 * manages the player's movement for a certain dice roll move distance.
	 * ensures that the player only moves the correct distance and only on that
	 * player's turn. To accomplish this, this method needs to set the canMove boolean 
	 * field to true when the keyListener should be temporarily "allowed".
	 * The method is called from Game initially when the dice is rolled for that player's turn but
	 * this method also makes a recursive call to itself decrementing the distanceRemaining counter
	 * by one for every iteration. Essentially: each call of this method represents an attempt to move
	 * by one tile.
	 * @param distanceRemaining the amount of tiles that the player must still move by
	 */
	public void haveMove(int distanceRemaining){
		//set canMove to true
		this.canMove = true;
		//wait until canMove set to false by keyReleased when a move key pressed
		while(!this.canMove){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//if there is still distance remaining in this move, recursively call this method
		if(distanceRemaining == 1){
			return;
		}else{//in case that we didn't just use the final move
			haveMove(distanceRemaining - 1);
			return;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//NOT USED BUT NECESSARY TO OVERRID THIS METHOD WHEN IMPLEMENTING KEYLISTENER
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//NOT USED BUT NECESSARY TO OVERRID THIS METHOD WHEN IMPLEMENTING KEYLISTENER
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		//depending on which key is pressed AND whether haveMove is set to true,
		//send a direction and this player's tile to the makeMove methodin Game which in turn calls checkmove/move in board MVC yee
		//MUST SET this.canMove to false after one move sent to Game
		//TODO: make this method --> makeMove -->checkmove/move mvc chain thing that passes a tile down the chain and then back
		int code = e.getKeyCode();
		if((this.canMove) && (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT)) {			
			System.out.println("just moved up one space or attempted to");
		} else if((this.canMove) && (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT)) {
			System.out.println("just moved left space or attempted to");
		} else if((this.canMove) && (code == KeyEvent.VK_UP)) {
			System.out.println("just moved right space or attempted to");
		} else if((this.canMove) && (code == KeyEvent.VK_DOWN)) {
			System.out.println("just moved down one space or attempted to");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	//GUESSING:
	
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
