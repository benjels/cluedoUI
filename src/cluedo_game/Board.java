package cluedo_game;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Board {

	private int w, h;
	private Tile[][] tiles;

	private Board(Tile[][] tiles){
		this.tiles = tiles;
	}

	public Board(File f){

		Scanner sc = null;
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int x = 0;
		
		/* parse in text file and create board.
		* Each character is separated by a SPACE
		* A, B, C, D, E, F, G, I, J = Kitchen, Ball room, Conservatory, Billiard Room, Library, Study, Hall, Lounge, Dining room
		* 1, 2, 3, 4, 5, 6 = Spawns for Mrs White, Rev. Green, Mrs. Peacock, Professor Plum, Miss Scarlet, Colonel Mustard
		* N, S, E, W are doors facing the specified direction (north, south, easth, west)
		* Z = 'Swimming pool' ie Center room
		* P = portal - Where you go is determined by the room you are in (the room the tiles adjacent to you are in)
		* ' - ', dash, is a nogo tile.  These will be null in the array. 
		*/
		while(!sc.hasNextLine()){
			String line = sc.nextLine();

			for(int y = 0 ; y < line.length(); y++){
				char c = line.charAt(y);

				if(c == 'D'){
					//tiles[x][y] = new HallTile();
				}
				if(c == 'S'){
					//tiles[x][y] = new RoomTile("Kitchen");
				}
				if(c == 'F'){
					//tiles[x][y] = new PortalTile();
				}
				if(c == 'M'){
					//tiles[x][y] = new DoorTile();
				}
			}

		}

	}

	public boolean canMove(Tile tile, Direction dir){
		return false; //TODO
	}

	public Tile move(Tile tile, Direction dir){
		return null; //TODO
	}

	public Tile getTileInDirection(Tile tile, Direction dir){
		return tiles[tile.getX()+dir.x][tile.getY()+dir.y];
	}
	
	public void draw(){

	}

	public enum Room {

		KITCHEN; //whatever else TODO

	}

	public enum Direction {

		NORTH(0, 1), EAST(1, 0), WEST(-1, 0), SOUTH(0, -1);

		private final int x;
		private final int y;

		private Direction(int x, int y){
			this.x=x;
			this.y=y;
		}

	}

}
