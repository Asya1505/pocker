import java.util.ArrayList;

public class Hand {
    private final ArrayList<Card> cards = new ArrayList<>();
    private HandValues handValues = HandValues.HIGHTCARD;
    private Card hightCard;
    private Card secondHightCard;


    public Hand(String cardsStr) {

        for (int i = 0; i < cardsStr.length()-1; i=i+2) {
            cards.add(new Card(cardsStr.charAt(i),cardsStr.charAt(i+1)));
        }

    }
    public Hand(ArrayList<Card> cardsList) {
        cards.addAll(cardsList);
    }

    public int compareHand(Hand secondHand){

        if(this.getHandValues().i > secondHand.getHandValues().i)
            return 1;
        else if(this.getHandValues().i < secondHand.getHandValues().i)
            return -1;
        else if(this.getHandValues() == HandValues.HIGHTCARD){
            Card thisMaxCard = hightCard(cards);
            Card secondMaxCard = hightCard(secondHand.cards);
            return thisMaxCard.compareCard(secondMaxCard);
        }
        else if(this.getHandValues() == HandValues.FULLHOUSE || this.getHandValues() == HandValues.TWOPAIRS){
            int res = secondHand.hightCard.compareCard(hightCard);
            if(res != 0){
                return res;
            }else{
                return secondHand.secondHightCard.compareCard(secondHightCard);
            }
        }
        else return secondHand.hightCard.compareCardRank(hightCard);

    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public HandValues getHandValues() {
        return handValues;
    }

    public void setHandValues(HandValues handValues) {
        this.handValues = handValues;
    }

    public Card hightCard(ArrayList<Card> listCards){

        Card maxCard = listCards.get(0);
        for (int i = 1; i < listCards.size(); i++) {
            if(maxCard.compareCard(listCards.get(i)) == -1 )
                maxCard = listCards.get(i);
        }

        return maxCard;
    }

    public Card getSecondHightCard() {
        return secondHightCard;
    }

    public void setSecondHightCard(Card secondHightCard) {
        this.secondHightCard = secondHightCard;
    }

    public Card getHightCard() {
        return hightCard;
    }

    public void setHightCard(Card hightCard) {
        this.hightCard = hightCard;
    }

    public String toString(){
        StringBuilder r = new StringBuilder();
        cards.forEach(s-> r.append(s.toString()));
        return r.toString();
    }
}
