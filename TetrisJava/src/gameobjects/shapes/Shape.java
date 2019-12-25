package gameobjects.shapes;

import game.Direction;
import gameobjects.GameField;

import java.awt.*;

public abstract class Shape {

    private Alignment alignment = Alignment.LEFT;
    Point[] positions;
    int[][] rotationInit;
    private int[][] rotationVariables;
    private int maxX;
    private int maxY;
    Color color;

    public boolean rotateRight(GameField gameField) {
        Alignment oldAlign = alignment;
        switch(alignment){
            case LEFT :
                for (int i = 0; i < rotationVariables.length; i++) {
                    rotationVariables[i][0] = rotationInit[i][0];
                    rotationVariables[i][1] = rotationInit[i][1];
                }
                alignment = Alignment.UP; break;
            case UP :
                for (int i = 0; i < rotationVariables.length; i++) {
                    rotationVariables[i][0] = - rotationInit[i][1];
                    rotationVariables[i][1] = rotationInit[i][0];
                }
                alignment = Alignment.RIGHT; break;
            case RIGHT :
                for (int i = 0; i < rotationVariables.length; i++) {
                    rotationVariables[i][0] = - rotationInit[i][0];
                    rotationVariables[i][1] = - rotationInit[i][1];
                }
                alignment = Alignment.DOWN; break;
            case DOWN :
                for (int i = 0; i < rotationVariables.length; i++) {
                    rotationVariables[i][0] = rotationInit[i][1];
                    rotationVariables[i][1] = - rotationInit[i][0];
                }
                alignment = Alignment.LEFT; break;
        }
        for (int i = 0; i < positions.length; i++) {
            int newX = positions[i].x + rotationVariables[i][0];
            int newY = positions[i].y + rotationVariables[i][1];
            if(newX < 0 || newX > maxX || newY < 0 || newY > maxY || gameField.isOccupied(newX, newY)){
                alignment = oldAlign;
                return false;
            }
        }
        for (int i = 0; i < positions.length; i++) {
            positions[i].x += rotationVariables[i][0];
            positions[i].y += rotationVariables[i][1];
        }
        return true;
    }

    public Color getColor() {
        return color;
    }

    public Shape(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.positions = new Point[4];
        this.rotationVariables = new int[4][2];
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
