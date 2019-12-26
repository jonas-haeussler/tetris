package gameobjects.shapes;

import game.Direction;
import gameobjects.GameField;

import java.awt.*;

public abstract class Shape {

    private Point[] positions;
    Point[] relativeLocations;
    private int maxX;
    private int maxY;
    Color color;

    public Shape(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.positions = new Point[4];
        this.relativeLocations = new Point[4];
    }

    void updatePosition(int initX, int initY){
        for (int i = 0; i < positions.length; i++) {
            positions[i] = new Point(initX, initY);
            positions[i].translate(relativeLocations[i].x, relativeLocations[i].y);
        }
    }

    public boolean rotateRight(GameField gameField) {
        for (int i = 0; i < positions.length; i++) {
            int newX = positions[2].x - relativeLocations[i].y;
            int newY = positions[2].y + relativeLocations[i].x;
            if(newX < 0 || newX > maxX || newY < 0 || newY > maxY || gameField.isOccupied(newX, newY)){
                return false;
            }
        }
        for (int i = 0; i < relativeLocations.length; i++) {
            int tempX = relativeLocations[i].x;
            relativeLocations[i].x = - relativeLocations[i].y;
            relativeLocations[i].y = tempX;
        }
        updatePosition(positions[2].x, positions[2].y);
        return true;
    }

    public Color getColor() {
        return color;
    }

    public Point[] getPositions() {
        return positions;
    }

    public boolean update(GameField gameField){
        for(Point position: positions){
            if(position.y >= maxY || gameField.isOccupied(position.x, position.y + 1)) {
                return false;
            }
        }
        for(Point position: positions) {
            position.y += 1;
        }
        return true;
    }

    public boolean move(Direction d, GameField gameField){
        for(Point position: positions){
            if(d == Direction.LEFT && (position.x <= 0 || gameField.isOccupied(position.x - 1, position.y))){
                return false;
            }
            if(d == Direction.RIGHT && (position.x >= maxX || gameField.isOccupied(position.x + 1, position.y))){
                return false;
            }
        }
        for(Point position: positions) {
            if(d == Direction.LEFT) position.x -= 1;
            else                    position.x += 1;
        }
            return true;
    }
}
