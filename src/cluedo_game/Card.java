package cluedo_game;



/**interface that guarantees that an implementing class is a Card and
 * can have its real name/cardvalue gotten.
 */



public interface Card {
	//!!! at the moment the string of a certain card is retreived only via calling a getter on its enum. Maybe should have a get method in the card classes themselves for more encapsulation.
	//make this decision when i know exactly when we will actually be getting this string value
	//IMO THO: It is ok to have this enum field publically accessible because our Weapon enum object is immutable and the field is declared as final.
	
	//!!! cool to have this interface because it means that we can have all of the cards stored in a set just as "Card" at the start of the program execution and then
	// deal them out to the players, putthing them in correct set depending on what their dynamic type is
	
}
