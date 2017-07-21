package com.codekata.repository;

import com.codekata.model.Coordinate;
import com.codekata.model.Direction;
import com.codekata.model.Rover;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoverRepository {
    Rover get(Integer id);

    List<Rover> get();

    Rover add(Coordinate startingPoint, Direction initialDirection);
}
