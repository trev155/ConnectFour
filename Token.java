package ConnectFour;

/*
A Token represents a single space in the GameBoard.
If occupied is true, then the space has been filled by a player.
Otherwise, the token is empty, meaning it represents an empty space on the GameBoard.
Tokens by default start off as unoccupied.
*/
class Token {
    private int row;
    private int col;
    private String symbol;
    private boolean occupied;

    Token(int row, int col) {
        this.row = row;
        this.col = col;
        this.symbol = "O";  // "O" representing an empty space
        this.occupied = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean getOccupied() {
        return occupied;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setOccupied() {
        this.occupied = true;
    }

    public void clearOccupied() {
        this.occupied = false;
    }
}
