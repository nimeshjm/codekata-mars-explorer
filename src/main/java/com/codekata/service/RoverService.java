package com.codekata.service;

import com.codekata.exception.InvalidCommandException;
import com.codekata.model.Coordinate;
import com.codekata.model.Direction;
import com.codekata.model.Rover;

import java.util.List;

public interface RoverService {
    Rover create(Coordinate Position, Direction initialDirection);

    List<Rover> get();

    Rover get(Integer id);

    Rover run(Integer id, char[] commands) throws InvalidCommandException;
}
