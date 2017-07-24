package com.codekata.service;

import com.codekata.command.CommandParser;
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
    private final CommandParser commandParser;

    @Autowired
    public MarsRoverService(RoverRepository roverRepository, CommandParser commandParser) {
        this.roverRepository = roverRepository;
        this.commandParser = commandParser;
    }

    @Override
    public Rover create(Coordinate Position, Direction initialDirection){
        return roverRepository.add(Position, initialDirection);
    }

    @Override
    public List<Rover> get() {
        return roverRepository.get();
    }

    @Override
    public Rover get(Integer id) {
        return roverRepository.get(id);
    }

    @Override
    public Rover run(Integer id, char[] commands) throws InvalidCommandException {
        Rover rover = get(id);

        if (rover == null)
        {
            return null;
        }

        rover.runCommands(commandParser, commands);

        return rover;
    }
}
