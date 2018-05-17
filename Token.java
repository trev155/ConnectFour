package ConnectFour;

abstract class Token {
    int row;
    int col;
    String symbol;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getSymbol() {
        return symbol;
    }
}
