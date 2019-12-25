package gameobjects.shapes;

import java.awt.*;

public class ShapeI extends Shape {


    public ShapeI(int maxX, int maxY) {
        super(maxX, maxY);
        color = Color.MAGENTA;
        positions[0] = new Point(4, 0);
        positions[1] = new Point(5, 0);
        positions[2] = new Point(6, 0);
        positions[3] = new Point(7, 0);
        this.rotationInit = new int[][]{{2, -2}, {1, -1}, {0, 0}, {-1, 1}};
    }


}
