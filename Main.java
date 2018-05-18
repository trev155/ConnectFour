package ConnectFour;

/*
A simple implementation of Connect Four.

This Main class is the primary entry point for the program.
*/
public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        game.gameLoop("A");

        /*
        game.getGameBoard().printGameBoard();

        game.insertToken(1, "A");
        game.insertToken(1, "B");
        game.insertToken(6, "A");

        game.getGameBoard().printGameBoard();
        */
    }
}
