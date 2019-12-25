package game;

import gameobjects.GameField;
import gameobjects.shapes.Shape;
import gui.MainFrame;

import java.awt.*;
import java.util.ArrayList;

public class Gamemode {
    private GameField gameField;
    private ArrayList<Shape> shapes;
    private MainFrame mainFrame;
    int level = 2;


    public Gamemode(){
        gameField = new GameField();
        shapes = new ArrayList<>();
        GameEngine gameEngine = new GameEngine(this);
        GameHandler gameHandler = new GameHandler(gameEngine);
        mainFrame = new MainFrame(this, gameField, gameHandler);
        gameEngine.start();


    }

    public void drawShapes(Graphics g){
        for(Shape shape : shapes){
            g.setColor(shape.getColor());
            for (Point p : shape.getPositions()) {
                if(p != null)
                    g.fill3DRect(p.x * gameField.getGridWidth(), p.y * gameField.getGridHeight(), gameField.getGridWidth(), gameField.getGridHeight(), true);
            }
        }
    }

    public void drawField(Graphics g){
        for (int i = 0; i < gameField.getRows(); i++) {
            for (int j = 0; j < gameField.getColumns(); j++) {
                g.setColor(Color.WHITE);
                g.fill3DRect(j * gameField.getGridWidth(), i * gameField.getGridHeight(), gameField.getGridWidth(), gameField.getGridHeight(), true);
            }
        }

    }

    public void deleteRow(int row){
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

    GameField getGameField() {
        return gameField;
    }
    void addShape(Shape s){
        shapes.add(s);
    }

    MainFrame getMainFrame() {
        return mainFrame;
    }
}
