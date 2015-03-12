package blackjack.managers;

import blackjack.model.Game;
import blackjack.model.Player;

import java.util.HashMap;
import java.util.List;

/**
 * Created by alsingr on 12/03/15.
 */
public class GameManager extends HashMap<String, Game> {

    HashMap<String, Game> games = new HashMap<>();

    private void newGame(String roomName)
    {

    }

    public List<Player> getPlayers(String roomName)
    {
        return  games.get(roomName).getPlayers();
    }



}
