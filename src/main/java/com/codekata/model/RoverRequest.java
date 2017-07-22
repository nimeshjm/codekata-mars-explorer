package com.codekata.model;

public class RoverRequest {
    private Coordinate position;
    private Direction direction;

    public RoverRequest() {
    }

    RoverRequest(Coordinate Position, Direction direction) {
        this.position = Position;
        this.direction = direction;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate Position) {
        this.position = Position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
