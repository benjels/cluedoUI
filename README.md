# cluedo_game
text based implementation of the cluedo boardgame




Note On Single Display:

There is a problem caused by playing a game in which it is important for players to conceal their own information/knowledge on a single screen. If all the players are always watching the screen, they will be able to see what cards all the other players have and thereby deduce what the correct solution is to the game. The simple (albeit crude) solution is to instruct all of the players in the game to only look at the screen when input from them is required.


Note On Guessing Rules:

I have allowed players as much freedom as possible in the guesses that they can make. Players can repeat already made guesses, guess with suspects that are in their own hand etc because there are strategic reasons for doing these things.

Note On Use Of Cards In Program:

in this program cards do not exist in exactly the same way that they would in the physical version of the game. When a guess is made, we make a three card objects for the suspects that make up that guess. So we can have two instances of the "same" card.

Note On Guesses/Accusations in a turn:

after a player has moved, they may elect to either make a guess or an accusation. If a player could make a guess and an accusation in the same turn, there would be no need to ever make an accusation without first verifying it with an ordinary guess. That would remove depth from the game.
