package ConnectFour;

import java.util.ArrayList;

public class GameBoard {
    private int numRows;
    private int numCols;
    private int numOccupied;
    private ArrayList<ArrayList<Token>> board;
    private ArrayList<ArrayList<Pair>> allSequences;

    GameBoard(int r, int c) {
        this.numRows = r;
        this.numCols = c;
        initializeGameBoard();
        this.numOccupied = 0;
        this.allSequences = generateSequences();
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
        System.out.println("");
        for (int i = 0; i < numRows; i++) {
            System.out.printf("[Row #%d]: ", i);
            for (int j = 0; j < numCols; j++) {
                System.out.printf("%s ", getTokenAtPosition(i, j).getSymbol());
            }
            System.out.println("");
        }
        System.out.println("____________________________");
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

    /*
    Checks if the GameBoard is completely occupied.
    Returns a boolean, true if tie, false if not a tie.
    */
    public boolean checkTie() {
        return getNumOccupied() == getNumRows() * getNumCols();
    }

    /*
    Check for a victory, for this player.
    Involves checking all the possible sequences of positions that could contain a line of 4 Tokens in a row.
    These sequences are precomputed and initialized in the constructor.

    Return false if no victory. Return true if victory.
    */
    public boolean checkVictory(Player p) {
        for (ArrayList<Pair> sequence : allSequences) {
            if (checkSequence(sequence, p)) {
                return true;
            }
        }
        return false;
    }

    /*
    Generate a list of sequences that will be checked every time a Game is started. This sequence list only
    needs to be generated once, as a Game instance always has the same size GameBoard, and there will be
    a finite number of sequences to check.
    */
    private ArrayList<ArrayList<Pair>> generateSequences() {
        ArrayList<ArrayList<Pair>> allSequences = new ArrayList<>();

        // Row sequences
        for (int r = 0; r < numRows; r++) {
            ArrayList<Pair> sequence = new ArrayList<>();
            for (int c = 0; c < numCols; c++) {
                Pair position = new Pair(r, c);
                sequence.add(position);
            }
            allSequences.add(sequence);
        }

        // Column sequences
        for (int c = 0; c < numCols; c++) {
            ArrayList<Pair> sequence = new ArrayList<>();
            for (int r = 0; r < numRows; r++) {
                Pair position = new Pair(r, c);
                sequence.add(position);
            }
            allSequences.add(sequence);
        }

        // Diagonal sequences
        // left side, up right direction
        for (int r = 3; r < numRows; r++) {
            ArrayList<Pair> sequence = new ArrayList<>();
            int row = r;
            int col = 0;
            while (row >= 0 && col < numCols) {
                Pair position = new Pair(row, col);
                sequence.add(position);
                row -= 1;
                col += 1;
            }
            allSequences.add(sequence);
        }

        // bottom side, up right direction (exclude the corner)
        for (int c = 1; c < numCols - 3; c++) {
            ArrayList<Pair> sequence = new ArrayList<>();
            int row = numRows - 1;
            int col = c;
            while (row >= 0 && col < numCols) {
                Pair position = new Pair(row, col);
                sequence.add(position);
                row -= 1;
                col += 1;
            }
            allSequences.add(sequence);
        }

        // left side, bottom right direction
        for (int r = 0; r < numRows - 3; r++) {
            ArrayList<Pair> sequence = new ArrayList<>();
            int row = r;
            int col = 0;
            while (row < numRows & col < numCols) {
                Pair position = new Pair(row, col);
                sequence.add(position);
                row += 1;
                col += 1;
            }
            allSequences.add(sequence);
        }

        // top side, bottom right direction
        for (int c = 0; c < numCols - 3; c++) {
            ArrayList<Pair> sequence = new ArrayList<>();
            int row = 0;
            int col = c;
            while (row < numRows & col < numCols) {
                Pair position = new Pair(row, col);
                sequence.add(position);
                row += 1;
                col += 1;
            }
            allSequences.add(sequence);
        }

        return allSequences;
    }

    /*
    Check if there is a sequence of 4 Tokens that are occupied by Player p, from the list of positions provided.
    Returns true if so, false otherwise.
    */
    private boolean checkSequence(ArrayList<Pair> positions, Player p) {
        int streak = 0;
        for (Pair pos : positions) {
            int row = pos.getRow();
            int col = pos.getCol();

            if (getTokenAtPosition(row, col).getSymbol().equals(p.getSymbol())) {
                streak += 1;
            } else {
                streak = 0;
            }

            if (streak == 4) {
                return true;
            }
        }
        return false;
    }

    public int getNumRows() {
        return this.numRows;
    }

    public int getNumCols() {
        return this.numCols;
    }

    public int getNumOccupied() {
        return this.numOccupied;
    }

    public ArrayList<ArrayList<Token>> getBoard() {
        return this.board;
    }
}
