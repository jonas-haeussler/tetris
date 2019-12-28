import game.Game;
import gui.MainFrame;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        var synchronizer = new AtomicBoolean(false);
        int numberOfGames = 1;
        int level = 1;
        for (String s : args) {
            if(s.startsWith("-g=")) {
                try {
                    numberOfGames = Integer.parseInt(s.substring(3));
                    if(numberOfGames > 3){
                        LOGGER.log(Level.WARNING, "Mehr als 3 Spiele gleichzeitig sind leider nicht möglich.");
                        System.exit(1);
                    }
                } catch(NumberFormatException e){
                    LOGGER.log(Level.WARNING, "Die Option \"-g\" muss eine Zahl enthalten.", e);
                    System.exit(1);
                }
            }
            else if(s.startsWith("-l=")){
                try {
                    level = Integer.parseInt(s.substring(3));
                    if(level > 4){
                        LOGGER.log(Level.WARNING,"Es gibt leider kein solches Level.");
                        System.exit(1);
                    }
                } catch(NumberFormatException e){
                    LOGGER.log(Level.WARNING, "Die Option \"-l\" muss eine Zahl enthalten.", e);
                    System.exit(1);
                }
            }
        }
        var gList = new LinkedList<Game>();
        for (int i = 0; i < numberOfGames; i++) {
            var g = new Game(synchronizer, i + 1, level);
            gList.add(g);
        }
        new MainFrame(gList);
        for(Game g : gList) {
            new Thread(g.getGameEngine()).start();
        }
    }
}
