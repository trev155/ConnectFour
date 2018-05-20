package ConnectFour;

import java.io.IOException;

/*
A simple implementation of Connect Four.

This Main class is the primary entry point for the program.
*/
public class Main {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }
}
