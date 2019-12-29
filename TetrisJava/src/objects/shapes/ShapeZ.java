package objects.shapes;

import java.awt.*;

public class ShapeZ extends Shape {

    public ShapeZ(int maxX, int maxY, int initX, int initY) {
        super(maxX, maxY);
        color = Color.PINK;
        relativeLocations[0] = new Point(-1, 0);
        relativeLocations[1] = new Point(0, 1);
        relativeLocations[2] = new Point(0, 0);
        relativeLocations[3] = new Point(1, 1);
        updatePosition(initX, initY);
    }


}
