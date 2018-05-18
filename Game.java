package ConnectFour;

import java.util.Scanner;

/*
A Game object contains everything that is needed to run the game.
A Game object can be started and stopped. It can initialize a GameBoard and play the game.
Basically, anything related to a Game instance is contained here.
*/
public class Game {
    private GameBoard gameBoard;

    Game() {
        initializeGameBoard(6, 7);
    }

    /*
    Initialize the GameBoard for this Game.
     */
    private void initializeGameBoard(int rows, int cols) {
        this.gameBoard = new GameBoard(rows, cols);
    }

    /*
    Main game loop.
    */
    public void gameLoop(String symbol) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Enter a column to insert at.");
            int columnInput = scanner.nextInt();


            // Run checks on input
            if (columnInput < 0 || columnInput >= gameBoard.getNumCols()) {
                System.out.println("Invalid column value");
                continue;
            }

            int rv  = insertToken(columnInput, "A");
            if (rv < 0) {
                System.out.println("Column is full, cannot insert token at this column");
            }

            // temp for testing - this loop should really break if the game ends
            if (columnInput == 6) {
                break;
            }

            gameBoard.printGameBoard();
        }

        scanner.close();
    }

    /*
    Insert a Token into this GameBoard at the specified column.
    Returns 0 on success, -1 if failure.
    */
    public int insertToken(int col, String symbol) {
        int row = gameBoard.findNextAvailableRowInColumn(col);
        if (row < 0) {
            return -1;
        }

        gameBoard.setToken(row, col, symbol);
        return 0;
    }

    public GameBoard getGameBoard() {
        return this.gameBoard;
    }

}
