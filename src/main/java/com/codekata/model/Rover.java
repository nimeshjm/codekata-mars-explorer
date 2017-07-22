package com.codekata.model;

import com.codekata.command.Filter;
import com.codekata.command.CommandParser;
import com.codekata.exception.InvalidCommandException;

public class Rover extends RoverRequest {
    private Integer id;

    public Rover(Integer id, Coordinate Position, Direction direction) {
        super(Position, direction);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Coordinate getPosition() {
        return super.getPosition();
    }

    public Direction getDirection() {
        return super.getDirection();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPosition(Coordinate Position) {
        super.setPosition(Position);
    }

    public void setDirection(Direction direction) {
        super.setDirection(direction);
    }

    public void runCommands(CommandParser commandParser, char[] commands) throws InvalidCommandException {
        for (char c : commands) {
            Filter command = commandParser.Parse(c);
            if (command == null) {
                throw new InvalidCommandException(String.valueOf(c));
            }

            command.execute(this);
        }
    }

    public void moveForward() {
        Coordinate position = getPosition();
        Direction direction = getDirection();

        int x = position.getX() + direction.getX();
        int y = position.getY() + direction.getY();

        setPosition(new Coordinate(x, y));
    }

    public void moveBackwards() {
        Coordinate position = getPosition();
        Direction direction = getDirection();

        int x = position.getX() - direction.getX();
        int y = position.getY() - direction.getY();

        setPosition(new Coordinate(x, y));
    }

    public void turnRight() {
        setDirection(getDirection().turnRight());
    }

    public void turnLeft() {
        setDirection(getDirection().turnLeft());
    }
}
