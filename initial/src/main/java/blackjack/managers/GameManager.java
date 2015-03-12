package blackjack.managers;

import blackjack.model.ActionResult;
import blackjack.model.Game;
import blackjack.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by alsingr on 12/03/15.
 */
public class GameManager extends HashMap<String, Game> {

    private final AtomicLong playerCounter = new AtomicLong();
    private final AtomicLong gameCounter = new AtomicLong();


    HashMap<String, Game> games = new HashMap<>();

    private void newGame(String roomName)
    {
        Game game = new Game() ;
        game.setId(String.valueOf(gameCounter.incrementAndGet()));
        game.setPlayers(new ArrayList<Player>());

        games.put(roomName, game);
    }

    private Player newPlayer()
    {
        Player player = new Player();
        player.setId(String.valueOf(playerCounter.incrementAndGet()));
        return player ;
    }

    public Player getPlayer(String roomName, String playerId)
    {
        Player player = null ;

        List<Player> players = getPlayers(roomName) ;

        for(Player p  : players)
        {
            if(p.getId().equals(playerId))
            {
                return p ;
            }
        }

        return player ;

    }

    public List<Player> getPlayers(String roomName)
    {
        Game game ;
        return (game = games.get(roomName)) == null ? null : game.getPlayers();
    }

    public Player addPlayerToRoom(String roomName)
    {
        Player player = newPlayer() ;

        if(!games.entrySet().contains(roomName))
        {
            newGame(roomName);
        }
        games.get(roomName).getPlayers().add(player);

        return player ;

    }

    public ActionResult doAction(String Action, String playerID, String room){
        return null;
    }

}
