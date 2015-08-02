package cluedo_game;

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

}
