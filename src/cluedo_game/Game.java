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
	private final HashSet<Card> deck;

	/**
	 * carries out a few setup tasks for the game -puts all the cards in the
	 * deck -prompts players to enter their names/choose character -deals the
	 * players their cards from the main deck -creates a new board from a file
	 * and saves it in a field -establishes player order -finally calls runGame
	 */
	Game(){
		//put cards in deck
		deck = new HashSet<Card>(Arrays.asList(new CharacterCard(CharacterCard.Character.COLONELMUSTARD), new  CharacterCard(CharacterCard.Character.MISSSCARLET), new  CharacterCard(CharacterCard.Character.MRGREEN), new CharacterCard(CharacterCard.Character.MRSPEACOCK), new CharacterCard(CharacterCard.Character.MRSWHITE), new CharacterCard(CharacterCard.Character.PROFESSORPLUM), new WeaponCard(WeaponCard.Weapon.AXE), new WeaponCard(WeaponCard.Weapon.CANLESTICK), new WeaponCard(WeaponCard.Weapon.KNIFE), new WeaponCard(WeaponCard.Weapon.LEADPIPE), new WeaponCard(WeaponCard.Weapon.POISON), new WeaponCard(WeaponCard.Weapon.REVOLVER), new WeaponCard(WeaponCard.Weapon.ROPE), new WeaponCard(WeaponCard.Weapon.WRENCH), new RoomCard(RoomCard.Room.BALLROOM), new RoomCard(RoomCard.Room.BILLIARDROOM), new RoomCard(RoomCard.Room.CONSERVATORY), new RoomCard(RoomCard.Room.DININGROOM), new RoomCard(RoomCard.Room.HALL), new RoomCard(RoomCard.Room.KITCHEN), new RoomCard(RoomCard.Room.LIBRARY), new RoomCard(RoomCard.Room.LOUNGE), new RoomCard(RoomCard.Room.STUDY)));
		assert(deck.size() == (9 + 8 + 6)): "made an error when forming deck its the worng size";
		//create board from specified file
		this.board = new Board(new File("board.txt")); ///!!! need to think about how this file directory system is gonna work
		//create players with correct names/characters
		Scanner keyboard = new Scanner(System.in);
		System.out.println("ENTER PLAYER AMOUNT"); 
		int playerAmount = keyboard.nextInt();
		for(int i = 0; i < playerAmount; i++){
			System.out.println("Player " + (i + 1) + " please enter your name"); 
			String name = keyboard.next();
			System.out.println(name + " please choose your character"); 
			String character = keyboard.next(); need to talk about how we are going to do this. imo best is to always just print out options and then let the user choose an option by typing in a number, Do movement this way too but maybe givea arrowkey support via a keylistener (dont worry about key listener for now th)
			
		}
		//deal cards from deck to players
		
		
		//call rungame method
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

	}

	// !!!REMEMBER THAT THERE ARE 2 GAME OVER CONDITIONS
	// 1) A PLAYER GUESSES CORRECT CARD COMBINATION
	// 2) ALL PLAYERS HAVE GUESSED WRONGLY

	public static void main(String[] args) {
		System.out.println("making a new game...");
		new Game();
	}
}
