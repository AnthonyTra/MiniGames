import java.util.Scanner;
public class OtherGame extends TwoPlayerGame {

    private char[][] counter = new char[6][7];
    private char[][] grid = initializeArray(counter); // Initializes the array by making all the elements empty.
    private char playerColor = 'R';
    private int move = 1;
    private int winningPlayer;
    static String name = "Other Game";
    Scanner in = new Scanner(System.in);
    Scanner sc = new Scanner(System.in);

    /**
     * Constructor
     *
     * Calls the super class constructor.
     */
    public OtherGame() {
        super();
    }

    /**
     * Returns the Connect 4 instructions
     *
     * @return a string of the Connect 4 instructions.
     */
    @Override
    public String getInstructions() {
        return "Each player will try to build a row of four checkers while keeping your opponent from doing the same."
                + "\n" + "If a player gets 4 checkers in a row they win! The checkers can match up vertically, "
                + "\n" + "diagonally or horizontally. If both players fill the board up without either one getting"
                + "\n" + "4 checkers in a row, then both players tie.";
    }

    /**
     * Checks if game is won horizontally/vertically/diagonally.
     *
     * @return a boolean value of the game's winning state.
     */
    @Override
    public boolean isGameWon() {
        // Checks 4 across.
        for (char[] chars : grid) { // Loops through each row
            for (int col = 0; col < grid[0].length - 3; col++) { // Loops through each column of that row.
                if (chars[col] == playerColor && chars[col + 1] == playerColor &&
                        chars[col + 2] == playerColor && chars[col + 3] == playerColor) {
                    winningPlayer = currentPlayer;
                    return true; // If there are 4 letters consecutively across from each other, then return true.
                }
            }
        }
        //Checks for 4 up and down
        for(int row = 0; row < grid.length - 3; row++) { // Loops through each row
            for(int col = 0; col < grid[0].length; col++) { // Loops through each column of that row.
                if (grid[row][col] == playerColor && grid[row+1][col] == playerColor &&
                    grid[row+2][col] == playerColor && grid[row+3][col] == playerColor) {
                    winningPlayer = currentPlayer;
                    return true; // If there are 4 letters consecutively vertical from each other, then return true.
                }
            }
        }
        //Check upward diagonal
        for(int row = 3; row < grid.length; row++) { // Loops through each row
            for(int col = 0; col < grid[0].length - 3; col++) { // Loops through each column of that row.
                if (grid[row][col] == playerColor && grid[row-1][col+1] == playerColor &&
                    grid[row-2][col+2] == playerColor && grid[row-3][col+3] == playerColor) {
                    winningPlayer = currentPlayer;
                    return true; // If there are 4 letters consecutively diagonal from each other, then return true.
                }
            }
        }
        //Checks downward diagonal
        for(int row = 0; row < grid.length - 3; row++) { // Loops through each row
            for(int col = 0; col < grid[0].length - 3; col++){ // Loops through each column of that row.
                if (grid[row][col] == playerColor && grid[row+1][col+1] == playerColor &&
                    grid[row+2][col+2] == playerColor && grid[row+3][col+3] == playerColor) {
                    winningPlayer = currentPlayer;
                    return true; // If there are 4 letters consecutively diagonal from each other, then return true.
                }
            }
        }
        return false; // If none of the conditions are met, then return false.
    }

    /**
     * Checks if the game is tied or not.
     *
     * @return a boolean value of the game's tied state.
     */
   @Override
   public boolean isGameTied() {
       return move >= 42 && !isGameWon(); // If all the spots on the board are filled up and game is not won yet, then return true.
   }

    /**
     * Puts the current game board into a string.
     *
     * @return a string of the current game board.
     */
   @Override
   public String getCurrentGameState() {
        String board = " 0 1 2 3 4 5 6";
       for (char[] chars : grid) {
           board += "\n" + "---------------" + "\n" + "|"; // Separates each row with a line and outside border.
           for (int col = 0; col < grid[0].length; col++) {
               board += chars[col] + "|" ; // Prints out the spaces that were filled and inside lines of the board.
           }
       }
        board += "\n" + "---------------" + "\n" + " 0 1 2 3 4 5 6"; // Prints out the bottom of the board.
        return board; // Returns the current state of the board.
    }

    /**
     * Prompts the current player to enter their input.
     *
     * @return a string asking the current player to enter their input.
     */
   @Override
   public String getCurrentPlayerPrompt() {
        if (currentPlayer == 1) {
            return "Player 1 (R), choose a column: "; // Prompts player 1 for input.
        } else {
            return "Player 2 (B), choose a column: "; // Prompts player 2 for input.
        }
    }

    /**
     * Updates and validates the current game state with the player's input.
     *
     * @param input a string containing the player's input.
     */
   @Override
   public void processCurrentPlayerInput(String input) {
        if (!isInteger(input)) {
            while (!isInteger(input)) { // If it is a invalid integer then it re prompts the user for input.
                System.out.println("Please re enter a valid integer value (0-6): ");
                input = in.nextLine();
            }
        }
        int column = Integer.parseInt(input); // Turns string into integer.
        column = validateRange(column); // Validates the integer input.
        while (!validateMove(column, grid)) { // Calls the method to determine if it is a valid method on the board.
            System.out.println("Please re enter a valid column placement: ");
            column = sc.nextInt();
        }
        // Drops the checker
       for (int row = grid.length - 1; row >= 0; row--) {
           if (grid[row][column] == ' ') { // If the spot is empty then replace that spot with the player's color.
               grid[row][column] = playerColor;
               break; // Break loop after the spot has been replaced with the person's color.
           }
       }
       move += 1; // Increment the move after each player's turn.
       switchPlayers(); // Then call the method to switch player's color.
   }

    /**
     * Returns the winning player of the game.
     *
     * @return an integer value 1 or 2, depending on which player won the game.
     */
    @Override
    public int getWinningPlayer() {
        return winningPlayer; // Return the winning player of the game.
   }

    /**
     * Validates if the current player's input is a valid move in the game.
     *
     * @param column an integer of the player's move.
     * @param grid a 2d array of the current game board.
     *
     * @return a boolean value if it is a valid input or not.
     */
    public boolean validateMove(int column, char[][] grid) {
        // Tests if it is a valid column.
        if (column < 0 || column > grid[0].length) {
            return false; // If it is not on the board, then return false.
        }
        // Tests if it is a full column
        if (grid[0][column] != ' '){
            return false; // If it is a full column return false.
        }
        return true; // If it is not a full column, then return true.
    }

    /**
     * Initializes the array by making all the elements empty.
     *
     * @param board a 2d array with no elements
     *
     * @return a 2d board with empty spaces.
     */
    public char[][] initializeArray(char[][] board) {
        for(int row = 0; row < board.length; row++){
            for (int col = 0; col < board[0].length; col++){
                board[row][col] = ' '; // Assign each row and column to an empty spot.
            }
        }
        return board; // Return the empty board.
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
     * Switches player's and colors after the end of the current player's turn.
     */
    public void switchPlayers() {
        if(!isGameWon()) {
            if(currentPlayer == 1) {
                playerColor = 'B'; // Switch color to blue after player one's turn is done.
                currentPlayer = 2; // Switch to player two.
            } else {
                playerColor = 'R'; // Switch color to red after player two's turn is done.
                currentPlayer = 1; // Switch to player one.
            }
        }
    }

    /**
     * Validates if the integer input is within the board's limits.
     *
     * @param input an integer value of the user's input.
     *
     * @return an integer of a validated input.
     */
    public int validateRange(int input) {
        while(input >= 7 || input < 0) {
            System.out.println("Please re enter a valid range from (0-6): ");
            input = sc.nextInt(); // If the player's input is not within the range, then re prompt the user
        }
        return input; // Return the validated integer.
    }
}
