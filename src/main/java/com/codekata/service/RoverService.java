package com.codekata.service;

import com.codekata.exception.InvalidCommandException;
import com.codekata.model.Coordinate;
import com.codekata.model.Direction;
import com.codekata.model.Rover;

import java.util.List;

public interface RoverService {
    Rover Create(Coordinate Position, Direction initialDirection);

    List<Rover> GetAll();

    Rover Get(Integer id);

    Rover Run(Integer id, char[] commands) throws InvalidCommandException;
}
