import java.util.Scanner;
public class TicTacToe extends TwoPlayerGame {

    private char[] row;
    private boolean game = false;
    private boolean counter = false;
    static String name = "TicTacToe";
    Scanner in = new Scanner(System.in);

    /**
     * Constructor
     *
     * Calls the super class constructor and instantiates the board with characters.
     */
    public TicTacToe() {
        super();
        row = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'}; // Initialize Array
    }

    /**
     * Returns the tic-tac-toe's instructions.
     *
     * @return a string of the tic-tac-toe's instructions.
     */
    @Override
    public String getInstructions() {
        return "The starting player places an 'x' in one of the locations on the grid. " + "\n" +
                "The second player then places a 'o' on one of the grid locations, but " + "\n" +
                "not one that was previously chosen. This continues until either all the " + "\n" +
                "grid locations have been filled, or until a player wins. A player wins " + "\n" +
                "by placing their \"pieces\" in three grid locations that either share " + "\n" +
                "the same row, column, or diagonal.";
    }

    /**
     * Checks if game is won horizontally/vertically/diagonally.
     *
     * @return a boolean value of the game's winning state.
     */
    @Override
    public boolean isGameWon() {
        if (horizontalRow() || verticalRow() || diagonalRow()) { // If the game has won in one of the three ways, then return true.
            return true;
        }
        return game; // If none of the conditions are met then return the condition of the game.
    }

    /**
     * Checks if the game is tied or not.
     *
     * @return a boolean value of the game's tied state.
     */
    @Override
    public boolean isGameTied() {
        int i = 0;
        while (i < row.length) {
            if (row[i] == 'x' || row[i] == 'o') {   // Loops through the rows to see if they are filled.
                counter = true;
            } else {
                counter = false;
                break;
            }
            i += 1;
        }
        return counter; // Returns true or false at the end.
    }

    /**
     * Puts the current game board into a string.
     *
     * @return a string of the current game board.
     */
    @Override
    public String getCurrentGameState() {
        // Prints the current state of the tic tac toe board.
        return row[0] + " | " + row[1] + " | " + row[2] + "\n" + "+-+-+-+-+-"
                + "\n" + row[3] + " | " + row[4] + " | " + row[5] +
                "\n" + "+-+-+-+-+-" + "\n" + row[6] + " | " + row[7] + " | " + row[8] + "\n";
    }

    /**
     * Prompts the current player to enter their input.
     *
     * @return a string asking the current player to enter their input.
     */
    @Override
    public String getCurrentPlayerPrompt() {
        // Returns the string asking the current player to enter their next input.
        String playerTurn;
        if(currentPlayer == 1) {
            playerTurn = "Player " + currentPlayer + "(x), please choose a number in between 1 - 9:";
        }
        else {
            playerTurn = "Player " + currentPlayer + "(o), please choose a number in between 1 - 9:";
        }
        return playerTurn; // Return the string of the current player.
    }

    /**
     * Updates and validates the current game state with the player's input.
     *
     * @param input a string containing the player's input.
     */
    @Override
    public void processCurrentPlayerInput(String input) {
        // Updates the board with user's input
        if (currentPlayer == 1 || currentPlayer == 2) {
            if (!isInteger(input)) { // Checks if the string can be converted to an integer.
                while (!isInteger(input)) {
                    System.out.println("Please re enter a valid integer value (1-9): ");
                    // If input is not an integer, ask them to re enter input.
                    input = in.nextLine();
                }
            }
        }
        int position = Integer.parseInt(input);
        // Turn user input into an integer.
            if (currentPlayer == 1) {  // Player one's turn
                updateBoard(position); // Updates the board with user's input.
                currentPlayer = 2; // Switch Players.
            } else if (currentPlayer == 2) { // Player two's turn
                updateBoard(position); // Updates the board with user's input.
                currentPlayer = 1; // Switch Players.
            }
        }

        /**
         * Returns the winning player of the game.
         *
         * @return an integer value 1 or 2, depending on which player won the game.
         */
        @Override
        public int getWinningPlayer ()
        {
            int counter;
            if (currentPlayer == 1) {
                counter = 2; // The current player is currently one on the last turn so we update to player two.
            } else {
                counter = 1; // The current player is currently two on the last turn so we update to player one.
            }
            winningPlayer = counter; // Update winning player with the counter variable.
            return winningPlayer; // Return the winning player.
        }

        /**
         * Checks if the game is won horizontally.
         *
         * @return a boolean value if the game is won horizontally or not.
         */
        public boolean horizontalRow () {
            if (row[0] == row[1] && row[1] == row[2] ||
                row[3] == row[4] && row[4] == row[5] ||
                row[6] == row[7] && row[7] == row[8]) {  // Checks if the game is won horizontally.
                return true;
            }
            return game;
        }

        /**
         * Checks if the game is won vertically.
         *
         * @return a boolean value if the game is won vertically or not.
         */
        public boolean verticalRow () {
            if (row[0] == row[3] && row[3] == row[6] ||
                row[1] == row[4] && row[4] == row[7] ||
                row[2] == row[5] && row[5] == row[8]) { // Checks if the game is won vertically.
                return true;
            }
            return game;
        }

        /**
         * Checks if the game is won diagonally.
         *
         * @return a boolean value if the game is won diagonally or not.
         */
        public boolean diagonalRow () {
            if (row[2] == row[4] && row[4] == row[6] ||
                row[0] == row[4] && row[4] == row[8]) { // Checks if the game is won diagonally.
                return true;
            }
            return game;
        }

        /**
         * Checks if the move on the board is already made.
         *
         * @param move an integer value of the user's input.
         * @param player an integer value of 1 or 2 depending on if it's player 1 or player 2's turn.
         *
         * @return a validated integer move on the board.
         */
        public int repeatedMove (int move, int player){ // Checks if the user's spot is already taken on the board.
            while (row[(move - 1)] == 'x' || row[(move - 1)] == 'o') { // Checks if the player's spot is already filled with x's and o's
                if (player == 1) {
                    System.out.println(getCurrentGameState());
                    System.out.println("Player 1(x), please choose another location to play: ");
                    move = in.nextInt(); // Asks player one to choose another number if the spot is taken.
                    move = validateRange(move, player); // Checks to see if the new input is in range from 1-9.
                } else {
                    System.out.println(getCurrentGameState());
                    System.out.println("Player 2(o), please choose another location to play: ");
                    move = in.nextInt(); // Asks player two to choose another number if the spot is taken.
                    move = validateRange(move, player); // Checks to see if the new input is in range from 1-9.
                }
            }
            return move;  // Return position at the end.
        }

        /**
         * Checks if the user's input is within the boarders of the board.
         *
         * @param move an integer value of the user's input.
         * @param player an integer value of 1 or 2 depending on if it's player 1 or player 2's turn.
         *
         * @return a validated integer range on the board.
         */
        public int validateRange (int move, int player){ // Validates if the user input is in between 1-9
            while (move >= 10 || move <= 0) {  // Checks to see if the move is in between 1-9
                if (player == 1) {
                    System.out.println(getCurrentGameState());
                    System.out.println("Player 1(x), please choose a number in between 1 - 9: ");
                    move = in.nextInt(); // Asks player one to choose another number.
                } else {
                    System.out.println(getCurrentGameState());
                    System.out.println("Player 2(o), please choose a number in between 1 - 9: ");
                    move = in.nextInt(); // Asks player two to choose another number.
                }
            }
            return move; // Return position at the end.
        }

        /**
         * Updates the current game state of the board with the user's input.
         *
         * @param position an integer of the user's validated input.
         */
        public void updateBoard (int position){
            if (currentPlayer == 1) {
                position = validateRange(position, currentPlayer); // Calls this method to validate the position.
                position = repeatedMove(position, currentPlayer); // Calls this method to validate the position.
                row[(position - 1)] = 'x';
            }
            if (currentPlayer == 2) {
                position = validateRange(position, currentPlayer); // Calls this method to validate the position.
                position = repeatedMove(position, currentPlayer); // Calls this method to validate the position.
                row[(position - 1)] = 'o';
            }
        }

        /**
         * Returns if the user's string input can be converted into an integer.
         *
         * @param input a string of the user's input.
         *
         * @return a boolean value if the string can be converted into an integer or not.
         */
        public static boolean isInteger (String input) {
            try {
                Integer.parseInt(input); // If the string can turn into an integer then return true.
                return true;
            }
            catch (Exception e) {
                return false;  // If the string cannot turn into an integer, then return false.
            }
        }
    }