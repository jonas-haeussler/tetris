package objects.shapes;

import objects.GameField;

import java.awt.*;

public class ShapeO extends Shape {

    public ShapeO(int maxX, int maxY, int initX, int initY) {
        super(maxX, maxY);
        color = Color.RED;
        relativeLocations[0] = new Point(-1, 0);
        relativeLocations[1] = new Point(-1, 1);
        relativeLocations[2] = new Point(0, 0);
        relativeLocations[3] = new Point(0, 1);
        updatePosition(initX, initY);
    }

    /* Do not rotate this shape */
    @Override
    public boolean rotateRight(GameField gameField){
        return true;
    }
}
