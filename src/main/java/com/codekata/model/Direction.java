package com.codekata.model;

public enum Direction {
    N(0, 1),
    S(0, -1),
    E(-1, 0),
    W(1, 0);

    private int x;
    private int y;

    private Direction(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
