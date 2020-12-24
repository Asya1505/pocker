import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {
    public Hand mainHand;
    public List<Hand> playersHand = new ArrayList<>();
    public static char [] RANKS= new char[]{'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};


    public Game(String cards) {
        String[] subStr = cards.split(" ");
        if(subStr[0].equals("texas-holdem")) {
            mainHand = new Hand(subStr[1]);

            for (int i = 2; i < subStr.length; i++) {
                playersHand.add(new Hand(subStr[i]));
            }
        }
    }

    public void repeats(Hand hand){

        ArrayList<Card> listCards = new ArrayList<>();
        listCards.addAll(hand.getCards());
        listCards.addAll(this.mainHand.getCards());
        listCards = (ArrayList<Card>) listCards.stream().sorted(Card::compareCard).
                collect(Collectors.toList());

        ArrayList<Card> sortedlistCards = new ArrayList<>();

        for (int i = listCards.size()-1; i >=0 ; i--) {
            sortedlistCards.add(listCards.get(i));
        }

        int count=0;
        for (char rank: RANKS ) {
            count = (int) sortedlistCards.stream()
                    .filter(s->s.getRank() == rank)
                    .count();

            if(count>0) {

                if (count == 4) {
                    hand.setHandValues(HandValues.FOUROFKIND);
                    hand.setHightCard(sortedlistCards.stream().filter(s -> s.getRank() == rank).findFirst().get());
                    return;
                } else if (count >= 2) {
                    int count1 = 0;
                    for (char rank1 : RANKS) {
                        count1 = (int) sortedlistCards.stream()
                                .filter(s -> s.getRank() == rank1 && s.getRank() != rank)
                                .count();

                        if ((count == 3 && count1 == 2) || (count == 2 && count1 == 3)) {
                            hand.setHightCard(sortedlistCards.stream().filter(s -> s.getRank() == rank).findFirst().get());
                            hand.setSecondHightCard(sortedlistCards.stream().filter(s -> s.getRank() == rank1).findFirst().get());
                            hand.setHandValues(HandValues.FULLHOUSE);
                            return;
                        } else if (hand.getHandValues().i > 5 && count == 2 && count1 == 2) {
                            hand.setHightCard(sortedlistCards.stream().filter(s -> s.getRank() == rank).findFirst().get());
                            hand.setSecondHightCard(sortedlistCards.stream().filter(s -> s.getRank() == rank1).findFirst().get());
                            hand.setHandValues(HandValues.TWOPAIRS);
                            return;
                        }

                    }

                    if (hand.getHandValues().i > 5 && count == 3) {
                        hand.setHightCard(sortedlistCards.stream().filter(s -> s.getRank() == rank).findFirst().get());
                        hand.setHandValues(HandValues.THREEOFKIND);
                        return;
                    } else if (hand.getHandValues().i > 5 && count == 2) {
                        hand.setHightCard(sortedlistCards.stream().filter(s -> s.getRank() == rank).findFirst().get());
                        hand.setHandValues(HandValues.PAIRS);
                        return;
                    }
                }
            }
        }

    }

    public void straight(Hand hand){
        ArrayList<Card> listCards = new ArrayList<>();
        listCards.addAll(hand.getCards());
        listCards.addAll(this.mainHand.getCards());
        List<Card> newlistCards = listCards.stream().sorted(Card::compareCard).
                collect(Collectors.toList());

        ArrayList<Card> sortedlistCards = new ArrayList<>();
        for (int i = newlistCards.size()-1; i >=0 ; i--) {
            sortedlistCards.add(newlistCards.get(i));
        }

        for (int i = 0; i < 3; i++) {

            int k = Rank.valueOf("RANK_"+sortedlistCards.get(i).getRank()).i;
            ArrayList<Card> streat = new ArrayList<>();
            streat.add(sortedlistCards.get(i));

            for (int j = i+1; j < sortedlistCards.size(); j++) {
                k++;

                if(sortedlistCards.get(j).getRank()==RANKS[k]){
                    streat.add(sortedlistCards.get(j));
                    if(streat.size()==5){
                        hand.setHightCard(streat.get(0));
                        hand.setHandValues(HandValues.STRAIGHT);
                        return;
                    }
                }else if(sortedlistCards.get(j).getRank()==RANKS[k-1]){
                    k--;
                }else{
                    break;
                }

            }

        }

    }

    public void straightFlush(Hand hand){
        ArrayList<Card> listCards = new ArrayList<>();
        listCards.addAll(hand.getCards());
        listCards.addAll(this.mainHand.getCards());

        int count =0;
        for (Suit suit: Suit.values() ) {
            count = (int) listCards.stream()
                    .filter(s->s.getSuit() == suit.h)
                    .count();

            if(count>=5) {

                ArrayList<Card> cards = (ArrayList<Card>) listCards.stream().filter(s -> s.getSuit() == suit.h).
                        collect(Collectors.toList());
                Hand newHand = new Hand(cards);
                this.straight(newHand);
                if(newHand.getHandValues() == HandValues.STRAIGHT) {
                    hand.setHightCard(cards.get(0));
                    hand.setHandValues(HandValues.STRAIGHTFLUSH);
                    return;
                }else{
                    hand.setHightCard(cards.get(0));
                    hand.setHandValues(HandValues.FLUSH);
                    return;
                }
            }
        }
    }

}
