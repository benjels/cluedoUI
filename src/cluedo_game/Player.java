package cluedo_game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * represents a player in the cluedo game NOT A CHARACTER!. Player's have a name, the cards that they are dealt at the start of the game
 * and a PlayerTile object which is their game token. Information about this player's character (which character  it is, what its location is on the board) is stored in this
 * CharacterTile object. A Player instance is also responsible for checking whether/how it can refute a given guess by another player in the game.
 * @author Max
 *
 */
public class Player{
	
	
	// each player has a set for each of the three kinds of cards (essentially their "hand"). This could have been implemented as a some public final fields, but i
	//will use the getter/setter model instead because it is easier to add the cards after the player has been constructed
	private HashSet<WeaponCard> weaponCards = new HashSet<>();
	private HashSet<RoomCard> roomCards = new HashSet<>();
	private HashSet<CharacterCard> characterCards = new HashSet<>();
	
	//each player has a name (the name of the person playing the game). This is set while the game is being set up and string is immutable so we use the public final pattern.
	public final String playerName;
	
	//each player has a tile which holds information about their character in the game
	private PlayerTile playerTile;
	
	//we need to store the game because we need to pass moves through it
	private final Game game;
	//we need to know whether the player is still in the game
	private boolean hasLost = false;
	
	
	//SETUP
	
	Player(String name, PlayerTile tile, Game game){
		this.playerName = name;
		this.playerTile = tile;
		this.game = game;
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
	public void decideMove(int distanceRemaining){
		System.out.println(this.playerName + ": enter one of the directions to move (up, down, left, right). Your piece is: " + this.playerTile.getIcon() + " you have : " + distanceRemaining + " moves remaining");
		//wait until canMove set to false by keyReleased when a move key pressed
		Scanner keyBoard = new Scanner(System.in);
		String temp = keyBoard.next();
		if(temp.equals("up")){
			System.out.println("DEBUG from player");
			this.playerTile = this.game.sendMove(this.playerTile, Board.Direction.NORTH);
		}else if(temp.equals("down")){
			this.playerTile = this.game.sendMove(this.playerTile, Board.Direction.SOUTH);
		}else if(temp.equals("left")){
			this.playerTile = this.game.sendMove(this.playerTile, Board.Direction.WEST);
		}else if(temp.equals("right")){
			this.playerTile = this.game.sendMove(this.playerTile, Board.Direction.EAST);
		}else{
			throw new RuntimeException("must be one of those");
		}
		//if there is still distance remaining in this move, recursively call this method
		if(distanceRemaining == 1){
			return;
		}else{//in case that we didn't just use the final move
			decideMove(distanceRemaining - 1);
			return;
		}
	}
	

	
	
	
	
	
	
	
	
	
	
	

	
	//GUESSING:
	/**
	 * manages a player's guess/accusation
	 * a player can either make no guess or accusation, make a guess, or make an accusation.
	 * THe player's guess is then passed back to the runGame method so that the weapon token from
	 * this guess can be displayed on the board and the guess can be checked against the cards of 
	 * the other players
	 * @return the guess that this player has made OR NULL IF NO GUESS MADE
	 */
	public Guess decideGuess(RoomCard.Room roomGuesserIn){
		//give the player their options
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Your moving is over and now you can elect to make a guess or an accusation... \n");
		System.out.println("press 1 for no guess, 2 for guess and 3 for accusation");
		//decide which kind of guess
		boolean isAccusation = false;
		int temp = keyboard.nextInt();
		if(temp == 1){
			return null; //!!! null as a value
		}else if(temp == 2){
			if(roomGuesserIn == null){
				return null;//!!! they cant guess if they are in the hallway
			}
			isAccusation = false;
		}else if(temp == 3){
			isAccusation = true;
		}else{
			throw new RuntimeException("that input is not an allowed type of accusation");
		}
		//prompt the player to guess which three "suspects" they wish to guess
		//build a card from each suspect enum that is chosen
		//get guessed character
		System.out.println("enter a number to choose which character to guess: 1 for miss scarlet, 2 for mrs white, 3 for mrs peacock, 4 for professor plum, 5 for mr green, 6 for colonel mustard");
		CharacterCard.Character guessedChar = CharacterCard.intToCharacter.get(keyboard.nextInt());
		System.out.println("you chose " + guessedChar.getString());
		CharacterCard guessedCharCard = new CharacterCard(guessedChar);
		//get guessed location
		System.out.println("enter a number to choose which room to guess: 1 for ballroom, 2 for billiard rooom, 3 for conservatory, 4 for dining room, 5 for hall, 6 for kitchen, 7 for library, 8 for lounge, 9 for study");
		RoomCard.Room guessedRoom = RoomCard.intToRoom.get(keyboard.nextInt());
		System.out.println("you chose " + guessedRoom.getString());
		RoomCard guessedRoomCard = new RoomCard(guessedRoom);
		//get guessed weapon
		System.out.println("enter a number to choose which weapon to guess: 1 for axe, 2 for candlestick, 3 for knife, 4 for lead pipe, 5 for poison, 6 for revolver, 7 for rope, 8 for wrench");
		WeaponCard.Weapon guessedWeapon = WeaponCard.intToWeapon.get(keyboard.nextInt());
		System.out.println("you chose " + guessedWeapon.getString());
		WeaponCard guessedWeaponCard = new WeaponCard(guessedWeapon);
		//create a guess/final guess with the guessed cards provided
		//pass the guess back to the runGame method so that its weapon can be drawn on the board and
		//it can be checked against the other players' cards
		return new Guess(guessedWeaponCard, guessedRoomCard, guessedCharCard, isAccusation, this);
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
	 * called when this player is chosen by the game as the refuter and
	 * we know that this player has card/s which allows him/her to refute
	 * @param guess the guess that is being refuted
	 */
	public void refuteGuess(Guess guess) {
		//use a helper method to find the cards that we can use to refute the guess
		ArrayList<? extends Card> refutationCards = getPotentialRefutationCards(guess);
		//now print the potential cards out and let the user choose
		//which one they want to refute with
		Scanner keyboard = new Scanner(System.in);
		for(int i = 0; i < refutationCards.size(); i ++){
			System.out.println(this.playerName + ": to refute with : " + refutationCards.get(i).toString() + " enter the number " + i );
		}
		//now get the user's input to decide which card we will refute with
		Card cardRefute = refutationCards.get(keyboard.nextInt());
		//we shoould print thisout to everyone from inside this method rather than
		//sending it back to the game class because this player is making an announcement to the others
		System.out.println("chose to refute with the card: " + cardRefute.toString());
	}

	
	
	/**
	 * gets a set of the cards that this player can use to refute a given Guess.
	 * Only use this method when it is established that this player CAN refute the guess
	 * @param guess the combination of cards that were guessed
	 * @return the subset of cards that are in this player's hand AND the supplied Guess
	 */
	private ArrayList<? extends Card> getPotentialRefutationCards(Guess guess){
		ArrayList<Card> refutationCards = new ArrayList<>();
		
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





//UTILITY
/**
 * used to get the tile of this player which
 * has the player's current position on the board a well as
 * who their character is
 * @return the player's tile
 */
public PlayerTile getTile(){
	return this.playerTile;
}

public void loseGame(){
	this.hasLost = true;
}

public boolean hasLost(){
	return this.hasLost;
}
	
	
}
