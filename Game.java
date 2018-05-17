package ConnectFour;

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

    public GameBoard getGameBoard() {
        return this.gameBoard;
    }

}
