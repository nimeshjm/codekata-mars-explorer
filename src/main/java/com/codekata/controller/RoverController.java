package com.codekata.controller;

import com.codekata.model.Rover;
import com.codekata.model.RoverRequest;
import com.codekata.service.RoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoverController {

    private RoverService roverService;

    @Autowired
    public RoverController(RoverService roverService) {
        this.roverService = roverService;
    }

    @RequestMapping(value = "/rovers", method = RequestMethod.GET)
    public List<Rover> ListRovers() {
        return roverService.GetAll();
    }

    @RequestMapping(value = "/rovers/{id}", method = RequestMethod.GET)
    public ResponseEntity<Rover> GetRover(@PathVariable Integer id) {
        return Optional.ofNullable(roverService.Get(id))
                .map(r -> ResponseEntity.ok().body(r))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/rovers", method = RequestMethod.POST)
    public ResponseEntity<Rover> CreateRover(@RequestBody RoverRequest request) {
        Rover rover = roverService.Create(request.getStartingPoint(), request.getDirection());
        return new ResponseEntity<>(rover, HttpStatus.CREATED);
    }
}