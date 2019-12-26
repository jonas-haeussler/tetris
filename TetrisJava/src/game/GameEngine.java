package game;

import gameobjects.shapes.*;
import gameobjects.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameEngine implements ActionListener, Runnable {

    private Random rand;
    private Gamemode gamemode;
    private int dropTime;
    private Shape activeShape;
    private Shape nextShape;
    private Timer timer;
    private AtomicBoolean sync;

    GameEngine(Gamemode gm, AtomicBoolean synchronizer) {
        this.gamemode = gm;
        this.sync = synchronizer;
        dropTime = 1000 / gm.level;
        this.timer = new Timer(dropTime, this);
        timer.setRepeats(true);
        this.rand = new Random();
    }

    private Shape randomShape(int initX, int initY){
        int next = rand.nextInt(7);
        return createShape(next, initX, initY);
    }
    private Shape createShape(int number, int initX, int initY) {
        int maxX = gamemode.getGameField().getColumns() - 1;
        int maxY = gamemode.getGameField().getRows() - 1;
        Shape shape = null;
        switch (number) {
            case 0:
                shape = new ShapeI(maxX, maxY, initX, initY);
                break;
            case 1:
                shape = new ShapeJ(maxX, maxY, initX, initY);
                break;
            case 2:
                shape = new ShapeL(maxX, maxY, initX, initY);
                break;
            case 3:
                shape = new ShapeO(maxX, maxY, initX, initY);
                break;
            case 4:
                shape = new ShapeS(maxX, maxY, initX, initY);
                break;
            case 5:
                shape = new ShapeT(maxX, maxY, initX, initY);
                break;
            case 6:
                shape = new ShapeZ(maxX, maxY, initX, initY);
                break;
            default:
                break;
        }
        return shape;
    }

    @Override
    public void run() {
        activeShape = randomShape(5, 0);
        gamemode.addShape(activeShape);
        nextShape = randomShape(5, 0);
        timer.start();
        while(!sync.get()){
            gamemode.getGamePanel().getParent().repaint();
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!activeShape.update(gamemode.getGameField())){
            gamemode.incrementShapeCounter();
            gamemode.getGameField().updateGrid(activeShape.getPositions(), gamemode);
            activeShape = nextShape;
            gamemode.addShape(activeShape);
            nextShape = randomShape(5, 0);
            for(Point p : activeShape.getPositions()){
                if(gamemode.getGameField().isOccupied(p.x, p.y)) {
                    timer.stop();
                    sync.set(true);

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

    public Shape getNextShape() {
        return nextShape;
    }
}
