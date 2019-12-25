package game;

import gameobjects.shapes.*;
import gameobjects.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameEngine implements ActionListener {

    private Random rand;
    private Gamemode gamemode;
    private int dropTime;
    private Shape activeShape;
    private Timer timer;
    private boolean isPaused;


    GameEngine(Gamemode gm) {
        this.gamemode = gm;
        dropTime = 1000 / gm.level;
        this.timer = new Timer(dropTime, this);
        timer.setRepeats(true);
        this.rand = new Random();
    }

    private Shape calcRandomShape(){
        int number = rand.nextInt(7);
        int maxX = gamemode.getGameField().getColumns() - 1;
        int maxY = gamemode.getGameField().getRows() - 1;
        Shape shape = null;
        switch (number){
            case 0 : shape = new ShapeI(maxX, maxY); break;
            case 1 : shape = new ShapeJ(maxX, maxY); break;
            case 2 : shape = new ShapeL(maxX, maxY); break;
            case 3 : shape = new ShapeO(maxX, maxY); break;
            case 4 : shape = new ShapeS(maxX, maxY); break;
            case 5 : shape = new ShapeT(maxX, maxY); break;
            case 6 : shape = new ShapeZ(maxX, maxY); break;
            default: break;
        }
        gamemode.addShape(shape);
        return shape;
    }


    void start() {
        activeShape = calcRandomShape();
        timer.start();
        while(!isPaused){
            gamemode.getMainFrame().getGamePanel().repaint();
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!activeShape.update(gamemode.getGameField())){
            gamemode.getGameField().updateGrid(activeShape.getPositions(), gamemode);
            activeShape = calcRandomShape();
            for(Point p : activeShape.getPositions()){
                if(gamemode.getGameField().isOccupied(p.x, p.y)) {
                    timer.stop();
                    isPaused = true;

                    return;
                }

            }
            timer.restart();
        }
    }

    void moveActiveShape(Direction d) {
        activeShape.move(d, gamemode.getGameField());
    }
    void rotateActiveShape(){
        activeShape.rotateRight(gamemode.getGameField());
    }
    void speedUpActiveShape(){
            timer.setDelay(dropTime / 8);
    }
    void speedDownActiveShape(){
        timer.setDelay(dropTime);
    }
}
