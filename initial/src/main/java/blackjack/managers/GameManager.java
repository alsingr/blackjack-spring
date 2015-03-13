package blackjack.managers;

import blackjack.model.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by alsingr on 12/03/15.
 */
public class GameManager extends HashMap<String, Game> {

    private final AtomicLong playerCounter = new AtomicLong();
    private final AtomicLong gameCounter = new AtomicLong();

    private static final SecureRandom random = new SecureRandom();

    private int timeToPlay = 60 * 1000; // temps de jeu : 1 min
    private Long finishTimeRound; // Temps de fin du round

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

    public Player doAction(String actionName, String playerID, String roomName){
        Player player = getPlayer(roomName, playerID);
        PlayerEtat action = PlayerEtat.valueOf(actionName);
        if(action == null && player == null)
            return null;

        //Mise à jours du compte à rebours
        if(finishTimeRound == null) finishTimeRound = new Date().getTime() + timeToPlay;

        //Indique si le round est terminer ou non
        boolean finish = isFinish(new Date().getTime());

        //Mise à jour de l'état du joueur si il n'a pas d'état
        if(!finish && player.getEtat().equals(PlayerEtat.NONE)){
            if(PlayerEtat.Card.equals(action)){
                doCard(player);
            } else if(PlayerEtat.Split.equals(action)){
                doSplit(player);
            } else if(PlayerEtat.Abort.equals(action)){
                doAbort(player);
            } else if(PlayerEtat.Stop.equals(action)){
                doStop(player);
            } else if(PlayerEtat.Double.equals(action)) {
                doDouble(player);
            }
        }

        if(isEndOfRound(roomName, finish)){
            endOfRound(roomName);
        }
        if(isEndOfGame(roomName)){
            endOfGame(roomName);
        }
        return player;
    }

    public Player playerBet(String roomName, String playerID, float bet){
        Player player = getPlayer(roomName, playerID);
        if(player != null && bet > 0 && player.getBet() == 0){
            player.setBet(bet);
        }
        return player;
    }

    private boolean isFinish(long time){
        if(finishTimeRound != null && finishTimeRound < time)
            return true;
        return false;
    }

    private void tourCroupier(Player croupier){
        if(croupier.getScore() < 17){
            croupier.getCards().add(this.getCard());
        } else {
            croupier.setEtat(PlayerEtat.Stop);
        }
    }

    private void doCard(Player player){
        player.setEtat(PlayerEtat.Card);
        player.getCards().add(this.getCard());
        if(player.getScore() == 21) {
            player.setEtat(PlayerEtat.Stop);
        } else if(player.getScore() > 21){
            player.setEtat(PlayerEtat.Abort);
        }
    }

    private void doSplit(Player player){
        if(player.havePair()){
            player.setEtat(PlayerEtat.Split);
            //TODO séparer en 2 le tas du joueurs
        }
    }

    private void doStop(Player player){
        player.setEtat(PlayerEtat.Stop);
        player.setCanPlay(false);
    }

    private void doAbort(Player player){
        if(player.getScore() <= 21) {
            doStop(player);
        }
        else {
            player.setEtat(PlayerEtat.Abort);
        }
    }

    private void doDouble(Player player){
        player.setEtat(PlayerEtat.Double);
        //TODO VOIR RÈGLE BLACKJACK
    }

    private Card getCard(){
        return new Card(randomEnum(SignOfCard.class), randomEnum(ValueOfCard.class));
    }

    private boolean isEndOfRound(String roomName, boolean finish){
        for(int i = 1 ; i < getPlayers(roomName).size() ; i++){
            if(finish && getPlayers(roomName).get(i).getEtat().equals(PlayerEtat.NONE))
                getPlayers(roomName).get(i).setEtat(PlayerEtat.Stop);
            else if(!finish && getPlayers(roomName).get(i).getEtat().equals(PlayerEtat.NONE))
                return false;
        }
        return true;
    }

    private void endOfRound(String roomName){
        tourCroupier(getPlayers(roomName).get(0));

        //mise à jours des états des joueurs sauf croupier à NONE en fonction de leurs états
        PlayerEtat etatJoueur;
        for(int i = 1 ; i < getPlayers(roomName).size() ; i++){
            etatJoueur = getPlayers(roomName).get(i).getEtat();
            if(!PlayerEtat.Abort.equals(etatJoueur) || !PlayerEtat.Stop.equals(etatJoueur))
                getPlayers(roomName).get(i).setEtat(PlayerEtat.NONE);
        }

        //mise à jours du temps
        finishTimeRound = new Date().getTime() + timeToPlay;
    }

    private boolean isEndOfGame(String roomName){
        //Fin du jeu = tous les joueurs ont arréter de jouer (etat STOP ou ABORT), croupier inclus
        PlayerEtat etatJoueur;
        for(int i = 0 ; i < getPlayers(roomName).size() ; i++){
            etatJoueur = getPlayers(roomName).get(i).getEtat();
            if(!PlayerEtat.Abort.equals(etatJoueur) || !PlayerEtat.Stop.equals(etatJoueur))
                return false;
        }
        return true;
    }

    private void endOfGame(String roomName){
        //TODO statistique partie

        Player player;
        float scorePlayer;
        Player croupier = getPlayers(roomName).get(0);
        for(int i = 1 ; i < getPlayers(roomName).size() ; i++){
            player = getPlayers(roomName).get(i);
            scorePlayer = player.getScore();
            if(scorePlayer == 21 || scorePlayer > croupier.getScore() && scorePlayer < 21){
                player.addGain(player.getBet());
                croupier.addGain(-player.getBet());
            } else {
                player.addGain(-player.getBet());
                croupier.addGain(player.getBet());
            }
            player.init();
        }
        croupier.init();
    }




    private  <T extends Enum<?>> T randomEnum(Class<T> c){
        int i = random.nextInt(c.getEnumConstants().length);
        return c.getEnumConstants()[i];
    }
}
