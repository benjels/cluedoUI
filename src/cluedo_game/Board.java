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
		
		int y = 0;
		
		/* parse in text file and create board.
		* Each character is separated by a SPACE
		* A, B, C, D, K, F, G, I, J = Kitchen, Ball room, Conservatory, Billiard Room, Library, Study, Hall, Lounge, Dining room
		* 1, 2, 3, 4, 5, 6 = Spawns for Mrs White, Rev. Green, Mrs. Peacock, Professor Plum, Miss Scarlet, Colonel Mustard
		* N, S, E, W are doors facing the specified direction (north, south, easth, west)
		* Z = 'Swimming pool' ie Center room
		* P = portal - Where you go is determined by the room you are in (the room the tiles adjacent to you are in)
		* ' - ', dash, is a nogo tile.  These will be null in the array. 
		*/
		while(!sc.hasNextLine()){
			String line = sc.nextLine();

			for(int x = 0 ; x < line.length(); x++){
				char c = line.charAt(x);

				Tile t = null;
				
				switch(c) {
				
				case 'A' : t = new RoomTile("Kitchen", " KI ", x, y);break;
				case 'B' : t = new RoomTile("Ball Room", " BA ", x, y);break;
				case 'C' : t = new RoomTile("Conservatory", " CO ", x, y);break;
				case 'D' : t = new RoomTile("Billiard Room", " BI ", x, y);break;
				case 'K' : t = new RoomTile("Library", " LI ", x, y);break;
				case 'F' : t = new RoomTile("Study", " ST ", x, y);break;
				case 'G' : t = new RoomTile("Hall", " HA ", x, y);break;
				case 'I' : t = new RoomTile("Lounge", " LO ", x, y);break;
				case 'J' : t = new RoomTile("Dining Room", " DI ", x, y);break;
				
				case '1' : t = new PlayerTile("Mrs White", "MW", x, y);break;
				case '2' : t =  new PlayerTile("Rev. Green", "RG", x, y);break;
				case '3' : t = new PlayerTile("Mrs. Peacock", "MP", x, y);break;
				case '4' : t = new PlayerTile("Proffessor Plum", "PP", x, y);break;
				case '5' : t = new PlayerTile("Miss Scarlet", "MS", x, y);break;
				case '6' : t = new PlayerTile("Colonel Mustard", "CM", x, y);break;
				
				case 'N' : t = new DoorTile(Direction.NORTH, x, y) ;break;
				case 'E' : t = new DoorTile(Direction.EAST, x, y);break;
				case 'S' : t = new DoorTile(Direction.SOUTH, x, y);break;
				case 'W' : t = new DoorTile(Direction.WEST, x, y);break;
				
				case 'P' : t = new PortalTile("Po", x, y);break;
				
				default: t = null ;break;
				
				}
				
				if(c == 'A'){
					//tiles[x][y] = new HallTile();
				} else if(c == 'B'){
					//tiles[x][y] = new RoomTile("Kitchen");
				} else if(c == 'C'){
					//tiles[x][y] = new PortalTile();
				} else if(c == 'D'){
					//tiles[x][y] = new DoorTile();
				}
				if(c == 'E'){
					
				}
				if(c == 'F'){
					
				}
				if(c == 'G'){
					
				}
				if(c == 'I'){
					
				}
				if(c == 'J'){
					
				}
			}
			
			y++;
			
		}
		
		for(int x = 0; x < tiles.length; x++){
			for(y = 0; y < tiles[x].length; y++){
				System.out.print(tiles[x][y]);
			}
			System.out.println();
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
