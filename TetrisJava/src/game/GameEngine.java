package game;

import objects.shapes.*;
import objects.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameEngine implements ActionListener {

    private static final int REPAINT_TIME = 50;

    private Random rand;
    private Game game;
    private int dropTime;
    private Shape activeShape;
    private Shape nextShape;
    private Timer shapeUpdateTimer;
    private Timer repaintTimer;
    private AtomicBoolean sync;
    private boolean finished;
    private boolean selected;
    private int gameIndex;

    GameEngine(Game gm, AtomicBoolean synchronizer, int gameIndex) {
        this.game = gm;
        this.sync = synchronizer;
        this.gameIndex = gameIndex;
        dropTime = 1000 / gm.level;
        this.shapeUpdateTimer = new Timer(dropTime, this);
        shapeUpdateTimer.setRepeats(true);
        this.repaintTimer = new Timer(REPAINT_TIME, actionEvent -> game.getGamePanel().getParent().repaint());
        repaintTimer.setRepeats(true);
        this.rand = new Random();
    }

    private Shape randomShape(int initX, int initY){
        int next = rand.nextInt(7);
        return createShape(next, initX, initY);
    }
    private Shape createShape(int number, int initX, int initY) {
        int maxX = game.getGameField().getColumns() - 1;
        int maxY = game.getGameField().getRows() - 1;
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

    public void start() {
        activeShape = randomShape(5, 0);
        game.addShape(activeShape);
        nextShape = randomShape(5, 0);
        shapeUpdateTimer.start();
        repaintTimer.start();
        setSelected(gameIndex == 1);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!activeShape.update(game.getGameField())) {
            game.incrementShapeCounter();
            game.getGameField().updateGrid(activeShape.getPositions(), game);
            activeShape = nextShape;
            game.addShape(activeShape);
            nextShape = randomShape(5, 0);
            for (Point p : activeShape.getPositions()) {
                if (game.getGameField().isOccupied(p.x, p.y)) {
                    shapeUpdateTimer.stop();
                    sync.set(true);
                    finished = true;

                    return;
                }

            }
        }
    }

    void moveActiveShape(Direction d) {
        activeShape.move(d, game.getGameField());
    }
    void rotateActiveShape(){
        activeShape.rotateRight(game.getGameField());
    }
    void speedUpActiveShape(){
            shapeUpdateTimer.setDelay(dropTime / 8);
    }
    void speedDownActiveShape(){
        shapeUpdateTimer.setDelay(dropTime);
    }

    public Shape getNextShape() {
        return nextShape;
    }

    public boolean isFinished() {
        return finished;
    }
    public boolean isSelected() {
        return selected;
    }

    void setSelected(boolean selected) {
        this.selected = selected;
    }

    int getGameIndex() {
        return gameIndex;
    }
}
