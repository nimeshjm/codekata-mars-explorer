package com.codekata.repository;

import com.codekata.model.Coordinate;
import com.codekata.model.Direction;
import com.codekata.model.Rover;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryRoverRepository implements RoverRepository {
    private List<Rover> deployedRovers = new ArrayList<>();

    @Override
    public Rover get(Integer id){
        return deployedRovers
                .stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Rover> get() {
        return deployedRovers;
    }

    @Override
    public Rover add(Coordinate Position, Direction initialDirection){
        Rover rover = new Rover(deployedRovers.size() + 1, Position, initialDirection);
        deployedRovers.add(rover);

        return rover;
    }
}
