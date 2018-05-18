package ConnectFour;

import java.util.ArrayList;

public class GameBoard {
    private int numRows;
    private int numCols;
    private int numOccupied;
    private ArrayList<ArrayList<Token>> board;

    GameBoard(int r, int c) {
        this.numRows = r;
        this.numCols = c;
        initializeGameBoard();
        this.numOccupied = 0;
    }

    /*
    Initialize the GameBoard, a 2D matrix of EmptyTokens.
    */
    private void initializeGameBoard() {
        this.board = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ArrayList<Token> row = new ArrayList<>();
            for (int j = 0; j < numCols; j++) {
                row.add(new Token(i, j));
            }
            this.board.add(row);
        }
    }

    /*
    Print out this GameBoard in a human-readable format.
    Note that row 0 refers to the top of the game board.
    */
    public void printGameBoard() {
        System.out.printf("[Top of board]----------\n");
        for (int i = 0; i < numRows; i++) {
            System.out.printf("[Row #%d]: ", i);
            for (int j = 0; j < numCols; j++) {
                System.out.printf("%s ", getTokenAtPosition(i, j).getSymbol());
            }
            System.out.printf("\n");
        }
        System.out.printf("____________________________\n");
    }

    /*
    Find the next row index that a token can be placed in this GameBoard's column.
    Returns an integer from [0, numRows - 1].
    Returns -1 if column is full.
    */
    public int findNextAvailableRowInColumn(int col) {
        for (int row = this.numRows - 1; row >= 0; row--) {
            if (!isSpaceOccupied(row, col)) {
                return row;
            }
        }
        // Caller should handle this error case.
        return -1;
    }

    /*
    Return the Token at the specified position on the GameBoard.
    */
    private Token getTokenAtPosition(int row, int col) {
        return board.get(row).get(col);
    }

    /*
    Check if the position at (row, col) in this GameBoard is occupied.
    The element at board.get(row).get(col) is a Token. Check if this Token's
    occupied field is true.
    */
    private boolean isSpaceOccupied(int row, int col) {
        return board.get(row).get(col).getOccupied();
    }

    /*
    The insertion of a token is simply just setting a Token's "occupied" to False,
    and then setting its symbol accordingly.

    Additionally, make sure to update the token counter.
    */
    public void setToken(int row, int col, String symbol) {
        Token t = getTokenAtPosition(row, col);
        t.setSymbol(symbol);
        t.setOccupied();
        this.numOccupied += 1;
    }

    /*
    Clearing the GameBoard means setting all the Tokens in the board to unoccupied, and resetting their
    symbols.
    */
    public void clearBoard() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Token t = getTokenAtPosition(row, col);
                t.clearOccupied();
                t.setSymbol("O");
            }
        }
        this.numOccupied = 0;
    }

    public int getNumRows() {
        return this.numRows;
    }

    public int getNumCols() {
        return this.numCols;
    }

    public ArrayList<ArrayList<Token>> getBoard() {
        return this.board;
    }
}
