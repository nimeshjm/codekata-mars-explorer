package com.codekata.repository;

import com.codekata.model.Coordinate;
import com.codekata.model.Direction;
import com.codekata.model.Rover;

import java.util.List;

public interface RoverRepository {
    Rover get(Integer id);

    List<Rover> get();

    Rover add(Coordinate Position, Direction initialDirection);
}
