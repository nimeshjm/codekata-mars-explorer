package com.codekata.model;

import java.util.Objects;

public class Coordinate {
    private Integer x;
    private Integer y;

    public Coordinate() {
    }

    public Coordinate(Integer x, Integer y) {

        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Coordinate)) {
            return false;
        }

        Coordinate coordinate = (Coordinate) o;
        return this.x.equals(coordinate.x)
                && this.y.equals(coordinate.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
