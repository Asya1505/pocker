import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class Pocker {

    public static void main(String[] args) {
        try {
            ArrayList<String> games = (ArrayList<String>) Files.lines(Paths.get(args[0]), StandardCharsets.UTF_8)
                                                                .collect(Collectors.toList());
            for (String gameDate: games) {
                Game game = new Game(gameDate);
                for (Hand playersHand : game.playersHand) {
                    game.straightFlush(playersHand);
                    game.straight(playersHand);
                    game.repeats(playersHand);

                }
                ArrayList<Hand> resultGame = (ArrayList<Hand>) game.playersHand.stream()
                        .sorted(Hand::compareHand)
                        .collect(Collectors.toList());

                for (int i = resultGame.size()-1; i >=0; i--){
                    System.out.print(resultGame.get(i).toString());
                    if(i != 0 && resultGame.get(i).getHightCard().compareCardRank(resultGame.get(i-1).getHightCard())==0){
                        System.out.print("=");
                    }else System.out.print(" ");
                }
                System.out.print("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
