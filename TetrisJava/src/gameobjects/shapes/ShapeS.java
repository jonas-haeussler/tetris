package gameobjects.shapes;

import java.awt.*;

public class ShapeS extends Shape {

    public ShapeS(int maxX, int maxY) {
        super(maxX, maxY);
        color = Color.GREEN;
        positions[0] = new Point(4, 1);
        positions[1] = new Point(5, 1);
        positions[2] = new Point(5, 0);
        positions[3] = new Point(6, 0);
        this.rotationInit = new int[][]{{0, -2}, {-1, -1}, {0, 0}, {-1, 1}};


    }

}
