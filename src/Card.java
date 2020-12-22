public class Card {

    private char suit;
    private char rank;
    public static String SUIT = "hdcs";
    public static String RANKS = "AKQJT98765432";

    public Card(char rank, char suit) {
        this.suit = suit;
        this.rank = rank;
    }

    public char getSuit() {
        return suit;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    public char getRank() {
        return rank;
    }

    public void setRank(char rank) {
        this.rank = rank;
    }

    public int compareCard(Card secondCard){
        if(this.compareCardRank(secondCard) == 1)
            return 1;
        else if(this.compareCardRank(secondCard) == 0){
            return this.compareCardSuit(secondCard);
        }
        else return -1;
    }

    public int compareCardRank(Card secondCard){
        return Integer.compare(RANKS.indexOf(secondCard.rank), RANKS.indexOf(this.rank));
    }

    public int compareCardSuit(Card secondCard){
        return Integer.compare(SUIT.indexOf(secondCard.suit), SUIT.indexOf(this.suit));
    }

    public String toString(){
        return ""+rank+suit;
    }
}
