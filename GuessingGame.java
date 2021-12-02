import java.util.Random;
import java.util.Scanner;
public class GuessingGame extends TwoPlayerGame {

    Random rand = new Random ();
    Scanner in = new Scanner(System.in);
    Scanner sc = new Scanner(System.in);
    private int playerOneNumber;
    private int playerTwoNumber;
    private final int answer = rand.nextInt(100) + 1;
    private boolean counter = false;
    static String name = "Guessing Game";

    /**
     * Constructor
     *
     * Calls the super class constructor.
     */
    public GuessingGame() { // Constructor
        super();
    }

    /**
     * Returns the guessing game's instructions.
     *
     * @return a string of the guessing game's instructions.
     */
    @Override
    public String getInstructions() {
        return "When the game starts the computer will generate a random number between 1 " + "\n" +
                "and 100 (including both 1 and 100). The game will prompt each player for a " + "\n" +
                "a guess. Once both players have submitted a valid guess, the game will tell " + "\n" +
                "you which player is closer to the secret number. Once a player has guessed " + "\n" +
                "the secret number, they win and the game is over. If both players correctly " + "\n" +
                "guess the secret number, the game will end in a tie. " + "\n";
    }

    /**
     * Checks if the number is guessed or not.
     *
     * @return a boolean value of the game's winning state.
     */
    @Override
    public boolean isGameWon() {
        if(!counter) {
            return false; // If it is still player one's turn, then return false
        }
        else {
            if (playerOneNumber == answer && playerTwoNumber != answer) {
                winningPlayer = 1;
                return true; // If player one's answer equals the answer then player one wins!
            }
            else if (playerTwoNumber == answer && playerOneNumber != answer) {
                winningPlayer = 2;
                return true; // If player two's answer equals the answer then player one wins!
            }
        }
        return false; // If no one won then return false.
    }

    /**
     * Checks if the game is tied or not.
     *
     * @return a boolean value of the game's tied state.
     */
    @Override
    public boolean isGameTied() {
        if(!counter) {
            return false; // If it is still player one's turn, then return false.
        }
        else {
            // Return the boolean value if it is a tied game
            return playerOneNumber == answer && playerTwoNumber == answer;
        }
    }

    /**
     * Determines which player's number is closer the correct answer.
     *
     * @return a string of which player is closer to the number or if they are equally close.
     */
    @Override
    public String getCurrentGameState() {
        if(!counter) {
            return ""; // Return nothing if it is still player one's turn.
        }
        else {
            if(isGameTied() || isGameWon()) {
                return ""; // If it is tied or the game is finished, then return nothing.
            }
            else if (differences(answer, playerOneNumber, playerTwoNumber) == 1) {
                return "Player 1 is closer."; // If the player one's difference is smaller then return that player one is closer.
            }
            else if (differences(answer, playerOneNumber, playerTwoNumber) == 2) {
                return "Player 2 is closer."; // If the player two's difference is smaller then return that player one is closer.
            }
            else if(differences(answer, playerOneNumber, playerTwoNumber) == 3) {
                return "Player 1 and 2 are equally close."; // Return that they are equal if they have the same difference.
            }
        }
        return ""; // Return nothing if nothing is true.
    }

    /**
     * Prompts the current player to enter their input.
     *
     * @return a string asking the current player to enter their input.
     */
    @Override
    public String getCurrentPlayerPrompt() {
        return "Player " + currentPlayer + ", make your guess:"; // Returns the current player prompt.
    }

    /**
     * Updates and validates the current game state with the player's input.
     *
     * @param input a string containing the player's input.
     */
    @Override
    public void processCurrentPlayerInput(String input) {
        if(currentPlayer == 1) {
            if (!isInteger(input)) {
                while (!isInteger(input)) { // If it is a invalid integer then it re prompts the user for input.
                    System.out.println("Please re enter a valid integer value (1-100): ");
                    input = in.nextLine();
                }
            }
            playerOneNumber = Integer.parseInt(input); // Turns string into integer.
            playerOneNumber = validateInput(playerOneNumber); // Validates the integer input.
            counter = false; // Set to false when it is player one's turn
            currentPlayer = 2; // Switches players at the end of player one's turn.
        }
        else {
            if (!isInteger(input)) {
                while (!isInteger(input)) { // If it is a invalid integer then it re prompts the user for input.
                    System.out.println("Please re enter a valid integer value (1-100): ");
                    input = in.nextLine();
                }
            }
            playerTwoNumber = Integer.parseInt(input); // Turns string into integer.
            playerTwoNumber = validateInput(playerTwoNumber); // Validates the integer input.
            counter = true; // Set to false when it is player one's turn
            currentPlayer = 1; // Switches players at the end of player one's turn.
        }
    }

    /**
     * Returns the winning player of the game.
     *
     * @return an integer value 1 or 2, depending on which player won the game.
     */
    @Override
    public int getWinningPlayer() {
        return winningPlayer; // Returns the winning player.
    }

    /**
     * Determines which player is closer or if they are equally close.
     *
     * @param answer an integer of the answer.
     * @param playerOne an integer of player one's number.
     * @param playerTwo an integer of player one's number.
     *
     * @return an integer value 1, 2, or 3 depending on the difference of the numbers.
     */
    public int differences (int answer, int playerOne, int playerTwo) {
        int counterOne = 0; // Counter variable for player one's number
        int counterTwo = 0; // Counter variable for player two's number.

        if (answer >= playerOne) { // If answer is bigger than the player's number then we subtract answer first.
            counterOne = answer - playerOne;
        }
        else if(answer < playerOne) { // If answer is smaller than the player's number then we subtract the player's # first.
            counterOne = playerOne - answer;
        }
        if (answer >= playerTwo) { // If answer is bigger than the player's number then we subtract answer first.
            counterTwo = answer - playerTwo;
        }
        else if(answer < playerTwo) { // If answer is smaller than the player's number then we subtract the player's # first.
            counterTwo = playerTwo - answer;
        }

        if(counterOne < counterTwo) { // If player one's difference is smaller, then return 1.
            return 1;
        }
        else if (counterTwo < counterOne) { // If player two's difference is smaller, then return 2.
            return 2;
        }
        else {
            return 3; // If player one's difference and player two's difference, then return 3.
        }
    }

    /**
     * Returns if the user's string input can be converted into an integer.
     *
     * @param input a string of the user's input.
     *
     * @return a boolean value if the string can be converted into an integer or not.
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input); // If the string can turn into an integer then return true.
            return true;
        }
        catch(Exception e) { // If the string cannot turn into an integer, then return false.
            return false;
        }
    }

    /**
     * Checks if the user's input is within the boarders of the board.
     *
     * @param input an integer value of the user's input.
     *
     * @return a validated integer range on the board.
     */
    public int validateInput(int input) {
        int counter = input;
        while(counter > 100 || counter <= 0) { // If the player's input is not within the range, then re prompt the user
            System.out.println("Please re enter a valid integer value (1-100): ");
            counter = sc.nextInt();
        }
        return counter; // Return the validated integer.
    }
}