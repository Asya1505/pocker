import java.util.ArrayList;

import java.util.stream.Collectors;


public class Pocker {

    public static void main(String[] args) {
        System.out.printf("test pocker\n");
        Game game = new Game();
        game.mainHand = new Hand(args[1]);
        for (int i = 2; i < args.length; i++) {
            game.playersHand.add(new Hand(args[i]));
        }

        for (Hand playersHand : game.playersHand) {
            game.straightFlush(playersHand);
            game.straight(playersHand);
            game.repeats(playersHand);

        }
        ArrayList<Hand> resultGame = (ArrayList<Hand>) game.playersHand.stream()
                                                            .sorted(Hand::compareHand)
                                                            .collect(Collectors.toList());

        for (int i = resultGame.size()-1; i >=0; i--){
            System.out.printf(resultGame.get(i).toString());
            if(i != 0 && resultGame.get(i).getHightCard().compareCardRank(resultGame.get(i-1).getHightCard())==0){
                System.out.printf("=");
            }else System.out.printf(" ");
        }

    }
}
