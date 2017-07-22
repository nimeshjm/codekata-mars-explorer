package com.codekata.model;

public enum Direction {
    N(0, 1),
    S(0, -1),
    E(1, 0),
    W(-1, 0);

    static {
        N.right = E;
        N.left= W;
        S.right = W;
        S.left= E;
        W.right = N;
        W.left= S;
        E.right = S;
        E.left= N;
    }

    private final int x;
    private final int y;
    private Direction right;
    private Direction left;

    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Direction turnRight(){
        return right;
    }

    public Direction turnLeft(){
        return left;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
