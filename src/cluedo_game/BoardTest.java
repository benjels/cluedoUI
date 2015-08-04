package cluedo_game;

import java.io.File;
import java.util.ArrayList;

import cluedo_game.Board.Direction;

public class BoardTest {

	/**
	 * Test the canMove method in board.
	 * 
	 * Mrs. White starts at x=10, y=0
	 * She should be able to move SOUTH only.
	 * 
	 */
	public static void moveCheckTest(Board b){
		Tile start = new PlayerTile("Mrs White", "MW", 10, 0);
		System.out.println("Testing movement");
		if(b.canMove(start, Direction.NORTH)){
			System.out.println("Shouldn't be able to move north.");
		}
		if(b.canMove(start, Direction.EAST)){
			System.out.println("Shouldn't be able to move east.");
		}
		if(b.canMove(start, Direction.SOUTH)){
			System.out.println("Can move South, correct!");
		}
		if(b.canMove(start, Direction.WEST)){
			System.out.println("Shouldn't be able to move west.");
		}
	}
	
	public static void tileDirectionTest(Board b){
		Tile start = new PlayerTile("Mrs White", "MW", 10, 1);
		Tile end = b.getTileInDirection(start, Direction.WEST);
		System.out.println("Old Tile: " + start.getX() + " , " + start.getY() + "   Direction: " + Direction.WEST 
				+ "    New Tile: " + end.getX() + " , " + end.getY());
	}
	
	public static void moveTest(Board b, int x, int y){
		Tile start = new PlayerTile("Mrs White", "MW", x, y);
		if(b.canMove(start, Direction.SOUTH)){
			b.move(start, Direction.SOUTH);
		}
	}
	
	public static void test(){
		Board b = new Board(new File("board.txt"), new ArrayList<Player>());
		//moveCheckTest(b);
		//tileDirectionTest(b);
		b.draw();
		//System.out.println("\nMoving MW south\n");
		//moveTest(b, 13, 17);
		//b.draw();
		System.out.println("\nMoving MW south\n");
		//moveTest(b, 13, 18);
		//b.draw();
		//System.out.println(b.getRoom(new HallTile(13, 19)));
	}
	
	public static void main(String[] args){
		test();
	}
	
}
