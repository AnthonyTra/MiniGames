public abstract class TwoPlayerGame
{
    static String name = "Two Player Game";
    protected int currentPlayer = 1;
    protected int winningPlayer = 0;

    /**
     * Default constructor
     */
    public TwoPlayerGame()
    {
    }

    /**
     * Returns the game's instructions.
     *
     * @return a string of the game's instructions.
     */
    public String getInstructions()
    {
        return "";
    }

    /**
     * Checks if the game has been won or not.
     *
     * @return a boolean value of the game's winning state.
     */
    public boolean isGameWon()
    {
        return false;
    }

    /**
     * Checks if the game is tied or not.
     *
     * @return a boolean value of the game's tied state.
     */
    public boolean isGameTied()
    {
        return false;
    }

    /**
     * Get's the current game status.
     *
     * @return a string of what is the current game status.
     */
    public String getCurrentGameState()
    {
        return "";
    }

    /**
     * Prompts the current player to enter their input.
     *
     * @return a string asking the current player to enter their input.
     */
    public String getCurrentPlayerPrompt()
    {
        return "";
    }

    /**
     * Updates and validates the current game state with the player's input.
     *
     * @param input a string containing the player's input.
     */
    public void processCurrentPlayerInput(String input) { }

    /**
     * Returns the winning player of the game.
     *
     * @return an integer value depending on which player won the game.
     */
    public int getWinningPlayer()
    {
        return winningPlayer;
    }
}
