package blackjack.model;

/**
 *
 * Card
 *
 * */
public class Card {
    private SignOfCard sign ;
    private ValueOfCard value ;
    private int score ;

    public Card(SignOfCard sign, ValueOfCard value){
        setSign(sign);
        setValue(value);
    }

    public String getSign() {
        return sign.name();
    }

    public void setSign(SignOfCard sign) {
        this.sign = sign;
    }

    public String getValue() {
        return value.name();
    }

    public void setValue(ValueOfCard value) {
        this.value = value;
        //TODO à améliorer
        if(ValueOfCard.V2.equals(value)){ setScore(2); } else
        if(ValueOfCard.V3.equals(value)){ setScore(3); } else
        if(ValueOfCard.V4.equals(value)){ setScore(4); } else
        if(ValueOfCard.V5.equals(value)){ setScore(5); } else
        if(ValueOfCard.V6.equals(value)){ setScore(6); } else
        if(ValueOfCard.V7.equals(value)){ setScore(7); } else
        if(ValueOfCard.V8.equals(value)){ setScore(8); } else
        if(ValueOfCard.V9.equals(value)){ setScore(9); } else
        if(ValueOfCard.V10.equals(value)){ setScore(10); } else
        if(ValueOfCard.Valet.equals(value)){ setScore(10); } else
        if(ValueOfCard.Queen.equals(value)){ setScore(10); } else
        if(ValueOfCard.King.equals(value)){ setScore(10); } else
        if(ValueOfCard.As.equals(value)){ setScore(1); }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
