package com.codekata.service;

import com.codekata.exception.InvalidCommandException;
import com.codekata.model.Coordinate;
import com.codekata.model.Direction;
import com.codekata.model.Rover;
import com.codekata.repository.RoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarsRoverService implements RoverService {
    private final RoverRepository roverRepository;

    @Autowired
    public MarsRoverService(RoverRepository roverRepository) {
        this.roverRepository = roverRepository;
    }

    @Override
    public Rover Create(Coordinate Position, Direction initialDirection){
        return roverRepository.add(Position, initialDirection);
    }

    @Override
    public List<Rover> GetAll() {
        return roverRepository.get();
    }

    @Override
    public Rover Get(Integer id) {
        return roverRepository.get(id);
    }

    @Override
    public Rover Run(Integer id, char[] commands) throws InvalidCommandException {
        Rover rover = Get(id);

        if (rover == null)
        {
            return null;
        }

        rover.runCommands(commands);

        return rover;
    }
}
