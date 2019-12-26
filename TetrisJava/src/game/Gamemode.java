package game;

import gameobjects.GameField;
import gameobjects.shapes.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Gamemode {
    private GameField gameField;
    private ArrayList<Shape> shapes;
    int level = 2;
    private GameEngine gameEngine;
    private GameHandler gameHandler;
    private Container gamePanel;
    private int points;
    private int shapesNumber;
    private int previewOffset;


    public Gamemode(AtomicBoolean synchronizer){
        gameField = new GameField();
        shapes = new ArrayList<>();
        gameEngine = new GameEngine(this, synchronizer);
        gameHandler = new GameHandler(gameEngine);
        previewOffset = (int) (0.5 * gameField.getFieldWidth());
    }

    public void drawShapes(Graphics g){
        for(Shape shape : shapes){
            g.setColor(shape.getColor());
            for (Point p : shape.getPositions()) {
                if(p != null)
                    g.fill3DRect(previewOffset + p.x * gameField.getGridWidth(), p.y * gameField.getGridHeight(), gameField.getGridWidth(), gameField.getGridHeight(), true);
            }
        }
    }

    public void drawField(Graphics g){
        for (int i = 0; i < gameField.getRows(); i++) {
            for (int j = 0; j < gameField.getColumns(); j++) {
                g.setColor(Color.WHITE);
                g.fill3DRect(previewOffset + j * gameField.getGridWidth(), i * gameField.getGridHeight(), gameField.getGridWidth(), gameField.getGridHeight(), true);
            }
        }

    }

    public void drawPreview(Graphics g, Shape s){
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, previewOffset, previewOffset);
        g.setColor(s.getColor());
        for (Point p : s.getPositions()) {
            if(p != null)
                g.fill3DRect( (int) ((p.x - 2.5) * gameField.getGridWidth()), (p.y + 2) * gameField.getGridHeight(), gameField.getGridWidth(), gameField.getGridHeight(), true);
        }
    }

    public void deleteRow(int row){
        points += 10;
        for(Shape s : shapes){
            for (int i = 0; i < s.getPositions().length; i++) {
                if (s.getPositions()[i] != null) {
                    if (s.getPositions()[i].y == row) {
                        s.getPositions()[i] = null;
                    } else if (s.getPositions()[i].y < row) {
                        s.getPositions()[i].y += 1;
                    }
                }
            }
        }
    }

    public GameField getGameField() {
        return gameField;
    }
    void addShape(Shape s){
        shapes.add(s);
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    Container getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(Container gamePanel) {
        this.gamePanel = gamePanel;
    }

    public int getPoints() {
        return points;
    }

    public int getShapesNumber() {
        return shapesNumber;
    }
    
    void incrementShapeCounter(){
        shapesNumber++;
    }

    public int getPreviewOffset() {
        return previewOffset;
    }
}
