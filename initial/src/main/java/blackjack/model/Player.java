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
    private boolean canPlay ;
    private int score ;
    private float bet ;
    private List<Card> cards ;
    private float gain;

    private String message;

    private PlayerEtat etat;

    public Player(){
        cards = new ArrayList<>();
        etat = PlayerEtat.NONE;
    }

    public PlayerEtat getEtat() {
        return etat;
    }

    public void setEtat(PlayerEtat etat) {
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

    public void init(){
        setBet(0);
        setCanPlay(true);
        setCards(new ArrayList<Card>());
        setEtat(PlayerEtat.NONE);
        setMessage(null);
        setScore(0);
    }

    public boolean isCanPlay() {
        return canPlay;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

    public int getScore() {
        return calculateScore();
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getGain() {
        return gain;
    }

    public void setGain(float gain) {
        this.gain = gain;
    }

    public void addGain(float gain) { this.gain += gain; }
}
