package ConnectFour;

import java.io.IOException;

/*
A simple implementation of Connect Four.

This Main class is the primary entry point for the program.
*/
public class Main {
    public static void main(String[] args) throws IOException {
        Game game = new Game();

        game.gameSetup();

        /*
        game.getGameBoard().printGameBoard();

        game.insertToken(1, "A");
        game.insertToken(1, "B");
        game.insertToken(6, "A");

        game.getGameBoard().printGameBoard();
        */
    }
}
