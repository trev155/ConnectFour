package ConnectFour;

import java.util.ArrayList;

public class GameBoard {
    private int numRows;
    private int numCols;
    private ArrayList<ArrayList<Token>> board;

    GameBoard(int r, int c) {
        this.numRows = r;
        this.numCols = c;
        initializeGameBoard();

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
    Insert a Token into this GameBoard at the specified column.
     */
    void insertToken(Token token, int column) {

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
                System.out.printf("%s ", this.board.get(i).get(j).getSymbol());
            }
            System.out.printf("\n");
        }
        System.out.printf("____________________________\n");
    }

    public int getNumRows() {
        return this.numRows;
    }

    public int getNumCols() {
        return this.numCols;
    }
}
