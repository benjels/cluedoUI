package cluedo_game;

import java.util.HashMap;

/**
 * each instance represents a different one of the room cards The actual room
 * name is stored as a string in an enum that is defined within this class.
 * 
 * @author Max
 *
 */
public class RoomCard implements Card {
	// the Room of this RoomCard It is ok to have this enum field publically accessible because our Weapon enum object is immutable and the field is declared as final.
	public final RoomCard.Room room;

	RoomCard(RoomCard.Room room) {
		this.room = room;
	}


	
	// ENUM FOR UNIQUE ROOMS
	public enum Room {
		BALLROOM("ball room"), CONSERVATORY("conservatory"), BILLIARDROOM(
				"billiard room"), LIBRARY("library"), STUDY("study"), HALL(
				"hall"), LOUNGE("lounge"), DININGROOM("dining room"), KITCHEN(
				"kitchen");
		
		//each room card has one of these enums as an identifier of which room it actually is. These enums also hold a string which is 
		//just a more readable/user friendly name for that enum value. This is stored in the roomName String field.
		private String roomName;

		private Room(String room) {
			this.roomName = room;
		}

		public String getString() {
			return this.roomName;
		}
	}
	
	
	//HASHMAP FROM INT --> ROOM ENUM
		//this is useful as it lets the user just enter an integer
		//to show what they want. Much easier to type in a single char
		//number than a string.
		public static final HashMap<Integer, RoomCard.Room> intToRoom = new HashMap<>();
		static {
		    intToRoom.put(1,RoomCard.Room.BALLROOM);
		    intToRoom.put(2,RoomCard.Room.BILLIARDROOM);
		    intToRoom.put(3,RoomCard.Room.CONSERVATORY);
		    intToRoom.put(4,RoomCard.Room.DININGROOM);
		    intToRoom.put(5,RoomCard.Room.HALL);
		    intToRoom.put(6,RoomCard.Room.KITCHEN);
		    intToRoom.put(7,RoomCard.Room.LIBRARY);
		    intToRoom.put(8,RoomCard.Room.LOUNGE);
		    intToRoom.put(9,RoomCard.Room.STUDY);
		}

}
