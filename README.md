### TwoPlayerGame
The ```TwoPlayerGame``` class provides the fields and methods required for your sub-classes to work. As this class will never be instantiated (only its sub-classes will), it really should be an abstract class. You must convert it to an abstract class, and at least one of the methods must be abstract. In reality, more than one method should be abstract.

### TicTacToe
The first couple of moves in the code should look like this:
```
Choose a two player game to play by entering the corresponding number.
  1) TicTacToe
  2) Guessing Game
  3) Other Game

Your choice: 
1
Instructions:
The starting player places an 'x' in one of the locations on the grid.
The second player then places a 'o' on one of the grid locations, but
not one that was previously chosen. This continues until either all the
grid locations have been filled, or until a player wins. A player wins
by placing their "pieces" in three grid locations that either share
the same row, column, or diagonal.

1|2|3
-+-+-
4|5|6
-+-+-
7|8|9

Player 1 (x) choose your location: 
1
x|2|3
-+-+-
4|5|6
-+-+-
7|8|9

Player 2 (o) choose your location: 
2
x|o|3
-+-+-
4|5|6
-+-+-
7|8|9
```

### GuessingGame
This game will be an extension of the random number game. The game will secretly select a random integer between 1 and 100 (inclusive). In the random number game, the user would guess a number and the program would respond with whether the secret number was higher or lower than the guessed number. In the two player version that you will implement, each player will make a guess, and then the program will respond with which player was closest to the secret number (if they are equally far away it will respond saying that both player 1 and player 2 are equally far away). This will go on until one (or both in the case of a tie) of the players guess the secret number.

This guessing game will actually pose some issues with the format prescribed by this project. 

The first couple of moves in the code should look like this:
```
Choose a two player game to play by entering the corresponding number.
  1) TicTacToe
  2) Guessing Game
  3) Other Game

Your choice: 
2
Instructions:
When the game starts the computer will generate a random number between 1
and 100 (including both 1 and 100). The game will prompt each player for a
a guess. Once both players have submitted a valid guess, the game will tell
you which player is closer to the secret number. Once a player has guessed
the secret number, they win and the game is over. If both players correctly
guess the secret number, the game will end in a tie.


Player 1, make your guess: 
1

Player 2, make your guess: 
100

Player 1 is closer.

Player 1, make your guess: 
15

Player 2, make your guess: 
17

Player 1 and 2 are equally far way.
```

### OtherGame
This game will be one of my choosing. It will implement an existing two player game (a few examples would be hangman, connect 4, and mancala).