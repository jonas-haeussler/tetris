package gameobjects.shapes;

import java.awt.*;

public class ShapeT extends Shape {

    public ShapeT(int maxX, int maxY) {
        super(maxX, maxY);
        color = Color.DARK_GRAY;
        positions[0] = new Point(4, 0);
        positions[1] = new Point(5, 1);
        positions[2] = new Point(5, 0);
        positions[3] = new Point(6, 0);
        this.rotationInit = new int[][]{{1, -1}, {-1, -1}, {0, 0}, {-1, 1}};

    }

}
