package blackjack.services;

import blackjack.managers.GameManager;
import blackjack.model.Player;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 *
 *GamesService
 * Resource controller
 *
 * */


@RestController
public class GameService {

    private final AtomicLong counter = new AtomicLong();

    private final GameManager gameManager = new GameManager() ;

    @RequestMapping("/play/{room}/players")
    List<Player> players(@PathVariable String room)
    {
        System.out.println("\n[URL]" + "/play/" + room + "/players");
        //System.out.println(gameManager.getPlayers(room).size());
        return gameManager.getPlayers(room);
    }

    //The @RequestMapping annotation ensures that HTTP requests to /play/roomName/me are mapped to the vegetable() method.
    @RequestMapping("/play/{room}/me")
    Player playerInfo(@RequestParam(value = "id", defaultValue = "0") String playerId, @PathVariable String room)
    {
        //Returning a new instance of the Vegetable class
        //The object data will be written directly to the HTTP response as JSON.
        //you don’t need to do this conversion manually
        System.out.println("\n[URL]" + "/play/" + room + "/me");

        return gameManager.getPlayer(room, playerId);
    }

    /**
     * Subcribe a player to a game
     * @param room
     * @return Player
     */

    @RequestMapping("/play/{room}/subscribe")
    Player addPlayer( @PathVariable String room)
    {
        System.out.println("\n[URL]" + "/play/" + room + "/subscribe");
        return gameManager.addPlayerToRoom(room);
    }

    @RequestMapping("/play/{room}/actions")
    Player doAction( @RequestParam(value = "id", defaultValue = "0") String playerId,
                      @RequestParam(value = "actions", defaultValue = "NONE") String action,
                      @PathVariable String room)
    {
        System.out.println("\n[URL]" + "/play/" + room + "/actions");
        System.out.println("[Parameter] id :" + playerId);
        System.out.println("[Parameter] actions :" + action);
        return gameManager.doAction(action, playerId, room);
    }

    @RequestMapping("/play/{room}/bet")
    Player playerBet(@RequestParam(value="bet", defaultValue = "0") float bet,
                     @RequestParam(value = "id", defaultValue = "0") String playerId,
                     @PathVariable String room){
        System.out.println("\n[URL]" + "/play/" + room + "/bet");
        System.out.println("[Parameter] id :" + playerId);
        System.out.println("[Parameter] bet :" + bet);
        return gameManager.playerBet(room, playerId, bet);
    }
}
