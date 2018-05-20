package ConnectFour;

/*
A class representing a player in the game.
*/
public class Player {
    private String username;
    private String symbol;

    Player(String username, String symbol) {
        this.username = username;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getUsername() {
        return username;
    }
}
