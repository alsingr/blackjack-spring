package blackjack.managers;

import blackjack.model.ActionResult;
import blackjack.model.ActionValue;
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
    private int indexOfPlayerWhoCanPlay;


    public void createRoomIfNotExist(String roomName){
        if(!games.containsKey(roomName))
            newGame(roomName);
    }

    private void newGame(String roomName)
    {
        Game game = new Game() ;
        game.setId(String.valueOf(gameCounter.incrementAndGet()));
        game.setPlayers(new ArrayList<Player>());
        //Correspond au croupier
        newPlayer();

        games.put(roomName, game);
    }

    private Player newPlayer()
    {
        //TODO vérifier si la partie est terminer
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

    public ActionResult doAction(String actionName, String playerID, String roomName){
        Player player = getPlayer(roomName, playerID);
        ActionValue action = ActionValue.valueOf(actionName);
        if(action == null && player == null)
            return null;
        //Mets à jour, l'état du joueur si il n'a pas d'état
        //TODO pour chaque action rajouter l'exuction de l'etat
        if(player.getEtat().equals(ActionValue.NONE)){
            if(ActionValue.Card.equals(action)){
                player.setEtat(action);
            } else if(ActionValue.Split.equals(action)){
                if(player.havePair()) player.setEtat(action);
            } else if(ActionValue.Abort.equals(action)){
                if(player.getScore() <= 21) player.setEtat(ActionValue.Stop);
                else player.setEtat(action);
            } else if(ActionValue.Stop.equals(action)){
                player.setEtat(action);
            } else if(ActionValue.Double.equals(action)) {
                //todo a faire
            }
        }
        //TODO vérifier si tous les joueurs ont joué sinon vérifier si ils sont pas dépassé le temps

        //TODO faire jouer le croupier si tous les jouersu ont jouer, puis remettre les joueurs à NONE
        return null;
    }

}
