package cluedo_game;

import java.io.File;
import java.util.ArrayList;

import cluedo_game.Board.Direction;
import cluedo_game.CharacterCard.Character;

public class Board {

	public enum Direction {

	}

	public Board(File file, ArrayList<Player> players) {
		// TODO Auto-generated constructor stub
	}



	public int charToX(Character playersChar) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int charToY(Character playersChar) {
		// TODO Auto-generated method stub
		return 0;
	}



	public boolean canMove(PlayerTile movingTile, Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}



	public PlayerTile move(PlayerTile movingTile, Direction dir) {
		// TODO Auto-generated method stub
		return null;
	}



	public void placeGuess(Guess playerGuess) {
		// TODO Auto-generated method stub
		
	}



	public void removeGuess(Guess playerGuess) {
		// TODO Auto-generated method stub
		
	}

}
