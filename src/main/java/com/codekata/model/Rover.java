package com.codekata.model;

import com.codekata.command.CommandParser;
import com.codekata.command.Filter;
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

    public void moveForward(Integer height, Integer width) {
        Coordinate position = getPosition();
        Direction direction = getDirection();

        int x = wrap(position.getX() + direction.getX(), width);
        int y = wrap(position.getY() + direction.getY(), height);

        setPosition(new Coordinate(x, y));
    }

    public void moveBackwards(Integer height, Integer width) {
        Coordinate position = getPosition();
        Direction direction = getDirection();

        int x = wrap(position.getX() - direction.getX(), width);
        int y = wrap(position.getY() - direction.getY(), height);

        setPosition(new Coordinate(x, y));
    }

    public void turnRight() {
        setDirection(getDirection().turnRight());
    }

    public void turnLeft() {
        setDirection(getDirection().turnLeft());
    }

    private Integer wrap(Integer currentPosition, Integer maxPosition){
        int pos = currentPosition % maxPosition;

        if (pos < 0){
            pos = maxPosition - 1;
        }

        return pos;
    }
}
