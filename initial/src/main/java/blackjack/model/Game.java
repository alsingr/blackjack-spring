package blackjack.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *Game
 *
 * */
public class Game {
    private String id ;
    private List<Player> players = new ArrayList<Player>();

    public Player getPlayer(String playerId)
    {
        Player player = null ;
        for (Player p : players)
        {
            if(player.getId().equals(playerId))
            {
                player = p ;
            }
        }

        return player ;
    }


    @Override
    public boolean equals(Object obj) {
        return ((obj!=null) && (obj instanceof Game) && (this.id.equals(((Game) obj).getId())));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
