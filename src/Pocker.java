import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class Pocker {

    public static void main(String[] args) {
        try {
            ArrayList<String> games = (ArrayList<String>) Files.lines(Paths.get(args[0]), StandardCharsets.UTF_8)
                    .collect(Collectors.toList());
            ArrayList<String> result = new ArrayList<>();
            for (String gameDate: games) {
                StringBuilder newLine = new StringBuilder();
                Game game = new Game(gameDate);
                if (game.mainHand==null){
                    newLine.append("Error: type no texas-holdem");
                }else{
                    for (Hand playersHand : game.playersHand) {
                        game.straightFlush(playersHand);
                        game.straight(playersHand);
                        game.repeats(playersHand);

                    }
                    ArrayList<Hand> resultGame = (ArrayList<Hand>) game.playersHand.stream()
                            .sorted(Hand::compareHand)
                            .collect(Collectors.toList());

                    for (int i = resultGame.size()-1; i >=0; i--){
                        newLine.append(resultGame.get(i).toString());

                        if(i != 0 && resultGame.get(i).getHightCard().compareCardRank(resultGame.get(i-1).getHightCard())==0){
                            newLine.append("=");
                        }else {
                            newLine.append(" ");
                        }
                    }
                }
                result.add(newLine.toString());
            }
            Files.write(Paths.get(args[1]), result, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
