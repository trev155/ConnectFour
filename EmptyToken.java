package ConnectFour;

/*
Representation of an empty space in the game board.
*/
class EmptyToken extends Token {
    EmptyToken(int row, int col) {
        this.row = row;
        this.col = col;
        this.symbol = "O";
    }

}
