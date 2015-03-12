package blackjack.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Player
 *
 * */
public class Player {

    private String id ;
    private int position ;
    private long timeout ;
    private boolean canPlay ;
    private int score ;
    private float bet ;
    private List<Card> cards ;

    private ActionValue etat;

    public Player(){
        cards = new ArrayList<>();
        etat = ActionValue.NONE;
    }

    public ActionValue getEtat() {
        return etat;
    }

    public void setEtat(ActionValue etat) {
        this.etat = etat;
    }

    public int calculateScore()
    {
        int countAs = 0, score = 0;
        // Calcul le score de toutes les cartes sans les as
        for(int i = 0 ; i < cards.size() ; i++){
            if(cards.get(i).getValue().equals("As")){
               countAs++;
            } else {
                score += cards.get(i).getScore();
            }
        }
        // Pour chaque as on calcul le meilleur score en fonction de la valeur de l'as (1 ou 11)
        for(int i = 0 ; i < countAs ; i++){
            if(score + 11 <= 21){
                score += 11;
            } else {
                score++;
            }
        }
        return score ;
    }

    public boolean havePair(){
        if(cards.size() != 2)
            return false;
        if(!cards.get(0).getValue().equals(cards.get(1).getValue()))
            return false;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return ((obj!=null) && (obj instanceof Player) && (this.id.equals(((Player) obj).getId())));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public boolean isCanPlay() {
        return canPlay;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getBet() {
        return bet;
    }

    public void setBet(float bet) {
        this.bet = bet;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
