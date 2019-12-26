package gameobjects.shapes;

import java.awt.*;

public class ShapeT extends Shape {

    public ShapeT(int maxX, int maxY, int initX, int initY) {
        super(maxX, maxY);
        color = Color.DARK_GRAY;
        relativeLocations[0] = new Point(-1, 0);
        relativeLocations[1] = new Point(0, 1);
        relativeLocations[2] = new Point(0, 0);
        relativeLocations[3] = new Point(1, 0);
        updatePosition(initX, initY);
    }

}
