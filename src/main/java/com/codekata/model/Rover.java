package com.codekata.model;

public class Rover extends RoverRequest {
    private Integer id;

    public Rover(Integer id, Coordinate startingPoint, Direction direction) {
        super(startingPoint, direction);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Coordinate getStartingPoint() {
        return super.getStartingPoint();
    }

    public Direction getDirection() {
        return super.getDirection();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStartingPoint(Coordinate startingPoint) {
        super.setStartingPoint(startingPoint);
    }

    public void setDirection(Direction direction) {
        super.setDirection(direction);
    }

    public static Rover Null() {
        return new Rover(-1, Coordinate.Null(), Direction.UNSPECIFIED);
    }
}
