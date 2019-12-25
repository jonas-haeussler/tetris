package gameobjects.shapes;

import java.awt.*;

public class ShapeJ extends Shape {

    public ShapeJ(int maxX, int maxY) {
        super(maxX, maxY);
        color = Color.CYAN;
        positions[0] = new Point(4, 0);
        positions[1] = new Point(5, 0);
        positions[2] = new Point(6, 0);
        positions[3] = new Point(6, 1);
        this.rotationInit = new int[][]{{1, -1}, {0, 0}, {-1, 1}, {-2, 0}};

    }


}
