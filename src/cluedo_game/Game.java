package cluedo_game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * sets up the game (initialising players and dealing them their hand of cards,
 * selects winning combination of cards etc) and then manages the turn order of
 * the game.
 * 
 * acts as an interface between the players' desired moves and the boards that
 * the game is played on.
 * 
 * prompts users for input and receives that input on their turn
 * 
 * !!! this class synopsis should be reworded a lil
 * 
 * @author Max
 *
 */
public class Game {

	// the board that contains a 2d array of the tiles in the game
	private final Board board;
	// list of players in the game
	private ArrayList<Player> players = new ArrayList<>();
	// the "deck" of cards which is always the same
	private final ArrayList<Card> deck;

	/**
	 * carries out a few setup tasks for the game -puts all the cards in the
	 * deck -prompts players to enter their names/choose character -deals the
	 * players their cards from the main deck -creates a new board from a file
	 * and saves it in a field -establishes player order -finally calls runGame
	 */
	Game(){
		//put cards in deck
		deck = new ArrayList<Card>(Arrays.asList(new CharacterCard(CharacterCard.Character.COLONELMUSTARD), new  CharacterCard(CharacterCard.Character.MISSSCARLET), new  CharacterCard(CharacterCard.Character.MRGREEN), new CharacterCard(CharacterCard.Character.MRSPEACOCK), new CharacterCard(CharacterCard.Character.MRSWHITE), new CharacterCard(CharacterCard.Character.PROFESSORPLUM), new WeaponCard(WeaponCard.Weapon.AXE), new WeaponCard(WeaponCard.Weapon.CANLESTICK), new WeaponCard(WeaponCard.Weapon.KNIFE), new WeaponCard(WeaponCard.Weapon.LEADPIPE), new WeaponCard(WeaponCard.Weapon.POISON), new WeaponCard(WeaponCard.Weapon.REVOLVER), new WeaponCard(WeaponCard.Weapon.ROPE), new WeaponCard(WeaponCard.Weapon.WRENCH), new RoomCard(RoomCard.Room.BALLROOM), new RoomCard(RoomCard.Room.BILLIARDROOM), new RoomCard(RoomCard.Room.CONSERVATORY), new RoomCard(RoomCard.Room.DININGROOM), new RoomCard(RoomCard.Room.HALL), new RoomCard(RoomCard.Room.KITCHEN), new RoomCard(RoomCard.Room.LIBRARY), new RoomCard(RoomCard.Room.LOUNGE), new RoomCard(RoomCard.Room.STUDY)));
		assert(deck.size() == (9 + 8 + 6)): "made an error when forming deck its the worng size";
		//create players with correct names/characters
		Scanner keyboard = new Scanner(System.in);
		System.out.println("ENTER PLAYER AMOUNT \n"); 
		int playerAmount = keyboard.nextInt();
		for(int i = 0; i < playerAmount; i++){
			//get this player's name
			System.out.println("Player " + (i + 1) + " please enter your name \n"); 
			String name = keyboard.next();
			//let this player choose a character
			System.out.println(name + " please choose your character by entering the corresponding number: \n 1 = miss scarlet \n 2 = mrs. white \n 3 = mrs peacock \n 4 = professor plum \n 5 = mr green \n 6 = colonel mustard"); 
			int character = keyboard.nextInt(); //TODO: manage exception/check that input is int. CONSIDER A SMALL HELPER METHOD OR WHILE LOOP THAT CHECKS THAT IT IS INT AND : WHILE NEXT NOT INT, PROMPT USER TO ENTER ANOTHER THING
			//create this character's tile and then create this player's Player object
			CharacterCard.Character playersChar = CharacterCard.intToCharacter.get(character);
			PlayerTile tile = new PlayerTile(playersChar);//!!! the playertile constructor should return a tile that is dependent on the character given as an argument
			//finally create the actual Player with the tile/name and add it to player list
			Player currentPlayer = new Player(name, tile);
			this.players.add(currentPlayer);
		}
		//deal cards from deck to players
		//essentially we traverse the deck of cards and proceed to a new player for each card
		// because there are more cards than players, we need to "wrap around" to the start of the list
		//of players when we reach its end
		int playerToReceive = 0;
		int lastPlayer = this.players.size() - 1;
		for(int i = 0; i < deck.size(); i++){
			//give current card to current player
			this.players.get(playerToReceive).receiveCard(deck.get(i));
			//if we just dealt to the "last player" wrap the player counter around
			//else just increment it
			if(playerToReceive == lastPlayer){
				playerToReceive = 0;
			}else{
				playerToReceive ++;
			}	
		}
		//create board from specified file and the players that we have now made
		this.board = new Board(new File("board.txt"), this.players); ///!!! need to think about how this file directory system is gonna work
		
		//we have set up the players, given them their cards and tile and set up the board 
		//now we are ready to run the game
		runGame();
	}

	/**
	 * goes through the list of players prompting them for their moves/guesses
	 * until the game is won/finished.
	 * 
	 * interfaces with the board when the player attempts to move, both to make
	 * sure that an attempted/desired move is legal AND to update the visual
	 * representation of the game.
	 * 
	 * interfaces with the players when a guess has been made and we need to
	 * check possible refutations from the other players.
	 */
	private void runGame() {
		boolean gameOver = false;
		//just loop through the players' turns until the game is over
		while(!gameOver){
			for(Player eachPlayer: this.players){
				//first roll the dice
				int diceRoll = 
				//pass diceroll to player's haveMove method which passes moves through
				//this class' sendMove method
				
				
				//after haveMove finally returns then ask the player if they want to guess
				//STILL NEED TO THINK ABOUT HOW THIS SHOULD WORK:
				//1) CALL A MAKEGUESS() METHOD ON EACH PLAYER IN LIST
				//
				//IMO BEST TO CALL A MAKEGUESS() METHOD INSIDE THE PLAYER'S CLASS THAT RETURNS A GUESS
				//TO THIS METHOD WHICH THEN GOES THROUGH ALL OF OTHER PLAYERS IN THE PLAYER LIST AND 
				//CHECKS THE GUESS AGAINST THE CARDS THEY HAVE USING THEIR CHECK GUESS METHODS
				//COULD MAYBE HAVE THAT METHOD RETURN A NULL OR EQUIVALENT IF THE PLAYER ELECTS TO NOT MAKE A
				//GUESS
				//I THINK THAT WAY IS BEST BECAUSE THEN THE ONLY INPUT THAT IS COMING INTO THE GAME IS THROUGH THE PLAYER/CONTROLLER
				//APART FROM THE SETUP INPUT WHICH CAN BE THOUGHT OF AS THE "GAME MASTER" OR S/T
				
				//pass the guess to the board so the weapon can be drawn on the board
				
				
				//process the guess through all of the players checking if they can refute etc
				
				
				//pass the guess to the board so the weapon can be erased from the board
				
				
				
				
				
				
			}
		}
		System.out.println("GAME OVER");
	}

	// !!!REMEMBER THAT THERE ARE 2 GAME OVER CONDITIONS
	// 1) A PLAYER GUESSES CORRECT CARD COMBINATION
	// 2) ALL PLAYERS HAVE GUESSED WRONGLY
	
	
	/**
	 * when a player pushes a key to move somewhere, move is sent to this method
	 * . This method checks with the board to see if the move is valid. IF it not, this method returns to the decideMove
	 * method the same tile as before. If it is valid, it moves the tile on the board and returns this
	 * new version of the tile to the player
	 * @param movingTile the tile of the player who is attempting to move
	 * @param dir the direction that the player is trying to move in
	 * @return the player's tile after this attempted move
	 */
	public PlayerTile sendMove(PlayerTile movingTile, Board.Direction dir){
		//check with the canmove whether the desired move is valid
		if(!this.board.canMove(movingTile, dir)){
			//in the case that we can't make the move because of e.g. a wall
			return movingTile;
		}else{//in the case that requested move is valid
			return this.board.move(movingTile, dir);
		}
	}
	
	/**
	 * when a player makes a guess or an accusation it is passed to here
	 * so that it can be given to all the players so that they can potentially
	 * refute this guess. From here, the players 
	 * @param guess
	 */
	public void checkGuess(Guess guess){
		
		
		//get the first player in the list who can refute
		Player refuter = null;
		for(Player eachPlayer: this.players){
			if(eachPlayer.canRefute(guess)){
				refuter = eachPlayer;
				break;
			}
		}
		//deal with case where guess was correct/nobody can refute
		if(refuter == null){
			if(guess.isFinal){//then the guessing player just won the game
				win game
			}else{
				System.out.println("no players can refute this combination");
			}
		}else{//in likely case that at least one player can refute the guess
			//get the cards that they could refute with
			HashSet<? extends Card> refutationCards = refuter.getPotentialRefutationCards(guess);
			need to decide here whether should print out possible refutes here or pass the refutation cards to
			some new method in the refuting player so that input is done there and then passed back to here
			then imo just insert the method calls (all the game logic is now delegated to these hlper method) into
			the rungame method and start working on all the github issues then start debug
		}
		
	}
	
	

	public static void main(String[] args) {
		System.out.println("making a new game...");
		new Game();
	}
}
