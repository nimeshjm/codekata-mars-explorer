package com.codekata.service;

import com.codekata.model.Coordinate;
import com.codekata.model.Direction;
import com.codekata.model.Rover;
import com.codekata.repository.RoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoverService {
    private final RoverRepository roverRepository;

    @Autowired
    public RoverService(RoverRepository roverRepository) {
        this.roverRepository = roverRepository;
    }

    public Rover Create(Coordinate startingPoint, Direction initialDirection){
        return roverRepository.add(startingPoint, initialDirection);
    }

    public List<Rover> GetAll() {
        return roverRepository.get();
    }

    public Rover Get(Integer id) {
        return roverRepository.get(id);
    }
}
