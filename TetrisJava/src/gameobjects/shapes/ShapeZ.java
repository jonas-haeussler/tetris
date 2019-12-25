package gameobjects.shapes;

import java.awt.*;

public class ShapeZ extends Shape {

    public ShapeZ(int maxX, int maxY) {
        super(maxX, maxY);
        color = Color.PINK;
        positions[0] = new Point(4, 0);
        positions[1] = new Point(5, 0);
        positions[2] = new Point(5, 1);
        positions[3] = new Point(6, 1);
        this.rotationInit = new int[][]{{2, 0}, {1, 1}, {0, 0}, {-1, 1}};

    }


}
