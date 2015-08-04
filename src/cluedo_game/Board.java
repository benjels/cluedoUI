package cluedo_game;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import cluedo_game.RoomCard.Room;

/**
 * A board from the game Cluedo.  
 * 
 * The Board is constructed with a formatted file with a height and width of 26.  
 * 
 * This class allows movement of tiles across the board, and contains methods for checking movements.  
 * The Board is drawn using the given draw() method, which will print the board to console.
 * 
 * @author Tim
 *
 */
public class Board {

	private int w = 26, h = 26;
	
	private Tile[][] tiles;
	private Tile[][] originalBoard;

	private Board(Tile[][] tiles){
		this.tiles = tiles;
	}

	/**
	 * Constructs a board given a formatted text representation of the Cluedo board.
	 * @param f
	 */
	public Board(File f, List<Player> players){
		
		System.out.println("Generating board..");
		
		tiles = new Tile[w][h];
		originalBoard = new Tile[w][h];
		
		for(int i = 1; i <= 6; i++){
			
			for(Player player : players){
				player.getTile().getCharacter();
			}
		}
		
		
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
		* H = hallway tile
		* ' - ', dash, is a nogo tile.  These will be null in the array. 
		*/
		while(sc.hasNextLine()){
			
			String line = sc.nextLine();
			String[] lineChars = line.split(" ");
			
			for(int x = 0 ; x < lineChars.length; x++){
			
				char c = lineChars[x].charAt(0);
				Tile t = null;
				
				switch(c) {
				
				case 'A' : t = new RoomTile(Room.KITCHEN, "KI", x, y);break;
				case 'B' : t = new RoomTile(Room.BALLROOM, "BA", x, y);break;
				case 'C' : t = new RoomTile(Room.CONSERVATORY, "CO", x, y);break;
				case 'D' : t = new RoomTile(Room.BILLIARDROOM, "BI", x, y);break;
				case 'K' : t = new RoomTile(Room.LIBRARY, "LI", x, y);break;
				case 'F' : t = new RoomTile(Room.STUDY, "ST", x, y);break;
				case 'G' : t = new RoomTile(Room.HALL, "HA", x, y);break;
				case 'I' : t = new RoomTile(Room.LOUNGE, "LO", x, y);break;
				case 'J' : t = new RoomTile(Room.DININGROOM, "DI", x, y);break;
				
				
				case '1' : System.out.println("MRS WHITE: " + x + " , " + y) ;break;
				case '2' :  System.out.println("Rev. Green" + x + " , " + y);break;
				case '3' : System.out.println("Mrs. Peacock" + x + " , " + y);break;
				case '4' : System.out.println("Proffessor Plum" + x + " , " + y);break;
				case '5' :  System.out.println("Miss Scarlet" + x + " , " + y);break;
				case '6' : System.out.println("Colonel Mustard" + x + " , " + y);break;
				
				case 'N' : t = new DoorTile(Direction.NORTH, x, y) ;break;
				case 'E' : t = new DoorTile(Direction.EAST, x, y);break;
				case 'S' : t = new DoorTile(Direction.SOUTH, x, y);break;
				case 'W' : t = new DoorTile(Direction.WEST, x, y);break;
				
				case 'P' : t = new PortalTile("Po", x, y);break;
				
				case 'H' : t = new HallTile(x, y); break;
				
				case 'Z' : t = new RoomTile(null, "SP", x, y); break;
				
				
				default: t = null ;break;
				
				}
				
				tiles[x][y] = t;
				originalBoard[x][y] = t;
				
			}
			
			y++;
			
		}
		
		for(Player p : players){
			tiles[p.getTile().getX()][p.getTile().getY()] = p.getTile();
		}
		
	}

	/**
	 * Checks if a player at the given tile can move in Direction dir.  
	 * @param tile Tile to check
	 * @param dir Direction you want the tile to move in
	 * @return True if the move is possible ie if there is nothing in the way
	 */
	public boolean canMove(Tile tile, Direction dir){
		
		//First, get the tile in direction dir
		Tile dest = getTileInDirection(tile, dir);
		
		if(dest == null){
			return false;
		}
		
		if(dest instanceof RoomTile){
			//If we're standing on a door, let us into the room..
			if(originalBoard[tile.getX()][tile.getY()] instanceof DoorTile){
				return true;
			}
			
			//If not, check whether we are surrounded by atleast 3 room tiles.
			int count = 0;
			for(Tile t : getTilesSurrounding(tile)){
				if(t instanceof RoomTile){
					count++;
				}
			}
			return count >= 3;
		}
		
		if(dest instanceof HallTile){
			return true;
		}
		
		if(dest instanceof DoorTile){
			DoorTile door = (DoorTile)dest;
			if(door.getDirection().equals(dir.getOpposite())){
				return true;
			}
		}
		
		return false; 
	}

	/**
	 * Moves the specified tile in the given direction.  This method DOES NOT perform any checks to 
	 * see whether the move is legal, so #canMove should be called if you want to maintain game rules.
	 * 
	 * NOTE: Tiles are taken directly from X and Y - the tile at 'tile.x' and 'tile.y' will be moved
	 * in direction 'dir'
	 * 
	 * @param tile
	 * @param dir
	 * @return
	 */
	public Tile move(Tile tile, Direction dir){
		
		int oldX = tile.getX();
		int oldY = tile.getY();
		int destX = tile.getX()+dir.x;
		int destY = tile.getY()+dir.y;
		
		//Buffer the old tiles..
		Tile current = tiles[oldX][oldY];
		
		//Now swap them over
		tiles[destX][destY] = current;
		if(originalBoard[oldX][oldY] instanceof PlayerTile){
			tiles[oldX][oldY] = new HallTile(oldX, oldY);
		} else {
			tiles[oldX][oldY] = originalBoard[oldX][oldY];
		}
		
		return tiles[destX][destY]; //TODO
	}
	
	/**
	 * Gets all tiles surrounding Tile t
	 * @param t
	 * @return
	 */
	public Tile[] getTilesSurrounding(Tile t){
		
		int x = t.getX();
		int y = t.getY();
		
		return new Tile[] {tiles[x-1][y-1], tiles[x][y-1], tiles[x+1][y-1], tiles[x-1][y], tiles[x+1][y], tiles[x-1][y+1], tiles[x][y+1], tiles[x+1][y+1]};
	}
	
	
	/**
	 * Gets the room the given tile is in, or null if not in a room.
	 * @param loc Tile to check
	 * @return The name of the room the tile is in, in lower case.  e.g "conservatory"
	 */
	public Room getRoom(Tile loc){
		
		Tile original = originalBoard[loc.getX()][loc.getY()];
		
		if(original instanceof RoomTile){
			return ((RoomTile)original).getRoom();
		}
		
		if(original instanceof DoorTile){
			for(Tile t : getTilesSurrounding(loc)){
				if(t instanceof RoomTile){
					return ((RoomTile)t).getRoom();
				}
			}
		}
		
		return null;
		
	}
	
	/**
	 * Places a guess on the board - the Weapon of the guess is sent to the room the guess is in.
	 * @param guess
	 */
	public void placeGuess(Guess guess){
		Room r = guess.roomCard.room;
		
		//Find a tile to put the weapon in..
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[x].length; y++){
				if(tiles[x][y] instanceof RoomTile){
					RoomTile tile = (RoomTile)tiles[x][y];
					if(tile.getRoom().equals(r)){
						tiles[x][y] = new WeaponTile(guess.weaponCard.weapon, "^-", x, y);
					}
				}
			}
		}
	}
	
	/**
	 * Removes the visual component of a guess from the board.
	 * @param guess
	 */
	public void removeGuess(Guess guess){
		
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[x].length; y++){
				if(tiles[x][y] instanceof WeaponTile){
					tiles[x][y] = originalBoard[x][y];
				}
			}
		}
		
	}

	/**
	 * Gets the tile in any given direction from a start tile.
	 * @param tile Tile in direction
	 * @param dir Direction to move in for new tile
	 * @return A tile if there is one in the given direction, NULL if there is no tile there.
	 */
	public Tile getTileInDirection(Tile tile, Direction dir){
		if(tile.getX()+dir.x > w || tile.getY()+dir.y > h){
			return null;
		}
		if(tile.getX()+dir.x < 0 || tile.getY()+dir.y < 0){
			return null;
		}
	
		return tiles[(tile.getX()+dir.x)][(tile.getY()+dir.y)];
	}
	
	/**
	 * Outputs a text based drawing of the board, including rooms and characters.
	 */
	public void draw(){
		for(int y = 0; y < tiles.length; y++){
			for(int x = 0; x < tiles[y].length; x++){
				if(tiles[x][y] == null){
					System.out.print("##");
				} else {
					System.out.print(tiles[x][y].getIcon());
				}
			}
			System.out.println();
		}
	}

	/**
	 * Contains directions NORTH, EAST, SOUTH, WEST and utility methods for use with the directions.
	 * 
	 * Each Direction contains an x and y offset for use with tiles.  ie The tile in NORTH direction has newX = startX+NORTH.x
	 * @author Tim
	 *
	 */
	public enum Direction {

		NORTH(0, -1), EAST(1, 0), WEST(-1, 0), SOUTH(0, 1);

		public final int x;
		public final int y;

		private Direction opposite;

		static {
			NORTH.opposite = SOUTH;
			SOUTH.opposite = NORTH;
			EAST.opposite = WEST;
			WEST.opposite = EAST;
		    }
		
		private Direction(int x, int y){
			this.x=x;
			this.y=y;
		}

		public Direction getOpposite(){
			return opposite;
		}
		
	}

}
