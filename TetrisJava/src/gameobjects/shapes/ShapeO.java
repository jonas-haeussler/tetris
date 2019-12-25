package gameobjects.shapes;

import java.awt.*;

public class ShapeO extends Shape {

    public ShapeO(int maxX, int maxY) {
        super(maxX, maxY);
        color = Color.RED;
        positions[0] = new Point(5, 1);
        positions[1] = new Point(5, 0);
        positions[2] = new Point(6, 1);
        positions[3] = new Point(6, 0);
        this.rotationInit = new int[][]{{0, -1}, {1, 0}, {-1, 0}, {0, 1}};
    }


}
