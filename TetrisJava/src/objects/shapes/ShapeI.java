package objects.shapes;

import java.awt.*;

public class ShapeI extends Shape {


    public ShapeI(int maxX, int maxY, int initX, int initY) {
        super(maxX, maxY);
        color = Color.MAGENTA;
        relativeLocations[0] = new Point(-2, 0);
        relativeLocations[1] = new Point(-1, 0);
        relativeLocations[2] = new Point(0, 0);
        relativeLocations[3] = new Point(1, 0);
        updatePosition(initX, initY);
    }
}
