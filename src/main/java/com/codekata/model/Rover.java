package com.codekata.model;

import com.codekata.command.CommandParser;
import com.codekata.command.Filter;
import com.codekata.exception.InvalidCommandException;

import java.util.List;

public class Rover extends RoverRequest {
    private Integer id;
    private boolean crashed;

    public Rover(Integer id, Coordinate Position, Direction direction) {
        super(Position, direction);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void runCommands(CommandParser commandParser, char[] commands) throws InvalidCommandException {
        crashed = false;
        int i = 0;
        while (i < commands.length && !isCrashed()) {
            char c = commands[i];
            Filter command = commandParser.Parse(c);
            if (command == null) {
                throw new InvalidCommandException(String.valueOf(c));
            }

            command.execute(this);
            i++;
        }
    }

    public void moveForward(Integer height, Integer width, List<Coordinate> obstacles) {
        Coordinate position = getPosition();
        Direction direction = getDirection();

        int x = wrap(position.getX() + direction.getX(), width);
        int y = wrap(position.getY() + direction.getY(), height);
        Coordinate nextPosition = new Coordinate(x, y);

        move(obstacles, nextPosition);
    }

    public void moveBackwards(Integer height, Integer width, List<Coordinate> obstacles) {
        Coordinate position = getPosition();
        Direction direction = getDirection();

        int x = wrap(position.getX() - direction.getX(), width);
        int y = wrap(position.getY() - direction.getY(), height);
        Coordinate nextPosition = new Coordinate(x, y);

        move(obstacles, nextPosition);
    }

    private void move(List<Coordinate> obstacles, Coordinate nextPosition) {
        boolean foundObstacle = obstacles.stream().anyMatch(o -> o.equals(nextPosition));

        if (foundObstacle) {
            crashed = true;
        }
        else {
            setPosition(nextPosition);
        }
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

    public boolean isCrashed() {
        return crashed;
    }
}
