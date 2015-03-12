package blackjack.services;

import blackjack.managers.GameManager;
import blackjack.model.Game;
import blackjack.model.Player;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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
    private ArrayList<Player> players = new ArrayList<Player>() ;
    private HashMap<String, Game> games;

    @RequestMapping("/play/{room}/players")
    List<Player> players(@PathVariable String room)
    {
      return gameManager.getPlayers(room);
    }

    //The @RequestMapping annotation ensures that HTTP requests to /play/roomName/me are mapped to the vegetable() method.
    @RequestMapping("/play/{room}/me")
    Player playerInfo(@RequestParam(value = "id", defaultValue = "0") String playerId, @PathVariable String room)
    {
        //Returning a new instance of the Vegetable class
        //The object data will be written directly to the HTTP response as JSON.
        //you don’t need to do this conversion manually
        return players.get(Integer.valueOf(playerId));
    }

}
