package com.codekata.model;

import com.codekata.exception.InvalidCommandException;
import com.codekata.service.Command;
import com.codekata.service.CommandParser;

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

    public void runCommands(char[] commands) throws InvalidCommandException {
        for (char c : commands) {
            Command command = CommandParser.Parse(c);
            if (command == null) {
                throw new InvalidCommandException(String.valueOf(c));
            }

            command.execute(this);
        }
    }

    public void Forward() {
        Coordinate position = getPosition();
        Direction direction = getDirection();

        int x = position.getX() + direction.getX();
        int y = position.getY() + direction.getY();

        setPosition(new Coordinate(x, y));
    }
}
