package ConnectFour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
A Game object contains everything that is needed to run the game.
A Game object can be started and stopped. It can initialize a GameBoard and play the game.
Basically, anything related to a Game instance is contained here.
*/
public class Game {
    private static final String PLAYER_ONE_SYMBOL = "A";
    private static final String PLAYER_TWO_SYMBOL = "B";
    private static final int GAME_ROWS = 6;
    private static final int GAME_COLS = 7;

    private GameBoard gameBoard;
    private boolean isGameRunning;
    private Player playerOne;
    private Player playerTwo;
    private BufferedReader reader;

    Game() {
        this.gameBoard = new GameBoard(GAME_ROWS, GAME_COLS);
        this.isGameRunning = false;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /*
    Preamble to the main game loop.
    Initialization of the Players.
    */
    public void gameSetup() throws IOException {
        String playerOneName, playerTwoName;

        System.out.println("Enter name of Player One:");
        playerOneName = reader.readLine();
        System.out.println("Enter name of Player Two:");
        playerTwoName = reader.readLine();

        this.playerOne = new Player(playerOneName, PLAYER_ONE_SYMBOL);
        this.playerTwo = new Player(playerTwoName, PLAYER_TWO_SYMBOL);

        System.out.println("Game Board:");
        gameBoard.printGameBoard();

        gameLoop(playerOne, playerTwo);

        // TODO when gameLoop() ends, make sure to cleanup and close the reader
        tearDown();
    }

    /*
    Main game loop.
    */
    private void gameLoop(Player p1, Player p2) throws IOException {
        this.isGameRunning = true;
        int curPlayerIndex = 1;     // 1 if p1, 2 if p2

        while (true) {
            System.out.println("Enter a column to insert at.");

            // Read input from user. Verify that it is a number, and verify the number is in proper range.
            int columnInput;
            try {
                columnInput = Integer.valueOf(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a number!");
                continue;
            }

            if (columnInput < 0 || columnInput >= gameBoard.getNumCols()) {
                System.out.println("Invalid column value");
                continue;
            }

            // At this point, the input is validated. Insert into the GameBoard.
            Player curPlayer;
            if (curPlayerIndex == 1) {
                curPlayer = p1;
            } else {
                curPlayer = p2;
            }

            int rv  = insertToken(columnInput, curPlayer);
            if (rv < 0) {
                System.out.println("Column is full, cannot insert token at this column");
                continue;
            }

            if (curPlayerIndex == 1) {
                curPlayerIndex = 2;
            } else {
                curPlayerIndex = 1;
            }

            // After inserting into the GameBoard, check if the game is over (tie or victory)
            int rvGame = isGameOver(curPlayer);
            if (rvGame >= 0) {
                System.out.println("Game over!");
                // TODO Print who won or if tie
                gameBoard.printGameBoard();
                gameBoard.clearBoard();
                this.isGameRunning = false;
                break;
            }

            // Output the GameBoard to the user.
            gameBoard.printGameBoard();
        }
    }

    /*
    Insert a Token into this GameBoard at the specified column.
    Returns 0 on success, -1 if failure.
    */
    public int insertToken(int col, Player player) {
        int row = gameBoard.findNextAvailableRowInColumn(col);
        if (row < 0) {
            return -1;
        }
        gameBoard.setToken(row, col, player.getSymbol());
        return 0;
    }

    /*
    Check if this Game is over.
    This involves checking either if the GameBoard is full, in which case we have a tie, or if Player p has won.
    Note that after some Player p makes a move, we only need to check if Player p has won. The other player
    is irrelevant. Therefore, this function should be called for a specific player, after they have made a move.

    Return -1 if this Game is NOT over.
    Return 0 if tie.
    Return 1 if player p has won.
    */
    public int isGameOver(Player p) {
        if (gameBoard.checkVictory(p)) {
            return 1;
        }
        if (gameBoard.checkTie()) {
            return 0;
        }
        return -1;
    }

    /*
    Tear down this Game instance. Involves things like closing the input reader.
    */
    public void tearDown() throws IOException {
        this.reader.close();

    }

    public GameBoard getGameBoard() {
        return this.gameBoard;
    }

}
