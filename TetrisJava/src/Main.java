import game.Gamemode;
import gui.MainFrame;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {
        var synchronizer = new AtomicBoolean(false);
        var gm1 = new Gamemode(synchronizer);
        var gm2 = new Gamemode(synchronizer);
        var gm3 = new Gamemode(synchronizer);
        var gmList = new LinkedList<Gamemode>();
        gmList.add(gm1);
        // gmList.add(gm2);
        // gmList.add(gm3);
        new MainFrame(gmList);
        for(Gamemode g : gmList) {
            new Thread(g.getGameEngine()).start();
        }

    }
}
