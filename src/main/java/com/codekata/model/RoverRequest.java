package com.codekata.model;

public class RoverRequest {
    public Coordinate getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Coordinate startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private Coordinate startingPoint;
    private Direction direction;

    public RoverRequest() {
    }

    RoverRequest(Coordinate startingPoint, Direction direction) {
        this.startingPoint = startingPoint;
        this.direction = direction;
    }
}
