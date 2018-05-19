package ConnectFour;

/*
A wrapper for a position in a GameBoard. Essentially, an (x, y) pair.
*/
public class Pair {
    private int row;
    private int col;

    Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
