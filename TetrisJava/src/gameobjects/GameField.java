package gameobjects;

import game.Game;

import java.awt.*;

public class GameField {
    private int fieldWidth = 400; // pixel width
    private int fieldHeight = 600; // pixel height

    private int columns = 11;
    private int rows = 20;

    private int gridWidth;
    private int gridHeight;

    private boolean[][] grid;

    public GameField(){
        this(400, 600, 11, 20);
    }

    private GameField(int fieldWidth, int fieldHeight, int columns, int rows) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.columns = columns;
        this.rows = rows;

        grid = new boolean[rows][columns];

        gridWidth = fieldWidth / columns;
        gridHeight = fieldHeight / rows;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public void updateGrid(Point[] positions, Game gm){
        for(Point position : positions){
            grid[position.y][position.x] = true;
            if(isRowFull(position.y)) {
                for (int i = position.y - 1; i >= 0; i--) {
                    System.arraycopy(grid[i], 0, grid[i + 1], 0, grid[i].length);
                }
                gm.deleteRow(position.y);
            }
        }
    }

    public boolean isOccupied(int x, int y) {
        return grid[y][x];
    }
    private boolean isRowFull(int rowNumber){
        for (int i = 0; i < grid[rowNumber].length; i++) {
            if(!grid[rowNumber][i])
                return false;
        }
        return true;
    }
}
