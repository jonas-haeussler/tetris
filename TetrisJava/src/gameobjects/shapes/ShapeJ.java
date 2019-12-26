package gameobjects.shapes;

import java.awt.*;

public class ShapeJ extends Shape {

    public ShapeJ(int maxX, int maxY, int initX, int initY) {
        super(maxX, maxY);
        color = Color.CYAN;
        relativeLocations[0] = new Point(1, 1);
        relativeLocations[1] = new Point(1, 0);
        relativeLocations[2] = new Point(0, 0);
        relativeLocations[3] = new Point(-1, 0);
        updatePosition(initX, initY);
    }

}
