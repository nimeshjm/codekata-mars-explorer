package com.codekata.controller;

import com.codekata.model.Coordinate;
import com.codekata.model.Direction;
import com.codekata.model.Rover;
import com.codekata.repository.RoverRepository;
import com.codekata.service.MarsRoverService;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class RoverControllerTest {
    private final RoverRepository roverRepository = mock(RoverRepository.class);
    private final MarsRoverService roverService = new MarsRoverService(roverRepository);
    private final RoverController sut = new RoverController(roverService);

    private final MockMvc mockMvc = standaloneSetup(this.sut).build();

    @Test
    public void unknownEndpointIs404() throws Exception {
        mockMvc.perform(get("/someresource"))
                .andExpect(status().is(404));
    }

    @Test
    public void listRoversEndpointIsOk() throws Exception {
        mockMvc.perform(get("/rovers"))
                .andExpect(status().isOk());
    }

    @Test
    public void listRoversOnEmptyPlanetIsEmpty() throws Exception {
        mockMvc.perform(get("/rovers"))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void listRoversOnPlanetReturnsCorrectNumberofRovers() throws Exception {
        List<Rover> rovers = new ArrayList<>();
        rovers.add(new Rover(1, new Coordinate(1, 1), Direction.N));
        when(roverRepository.get()).thenReturn(rovers);
        mockMvc.perform(get("/rovers"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    public void getRoverOnPlanetReturnsCorrectRover() throws Exception {
        Rover rover = new Rover(1, new Coordinate(1, 1), Direction.S);
        when(roverRepository.get(any())).thenReturn(rover);

        mockMvc.perform(get("/rovers/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUnknownRoverOnPlanetReturnsMissingRover() throws Exception {
        mockMvc.perform(get("/rovers/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createRoverWithEmptyBodyIsaBadRequest() throws Exception {
        mockMvc.perform(post("/rovers"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createRoverIsSuccesful() throws Exception {
        mockMvc.perform(post("/rovers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"Position\":{\"x\":1,\"y\":1},\"Direction\":\"W\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void moveRoverOnUnknownRoverThrowsError() throws Exception {
        mockMvc.perform(post("/rovers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"commands\": [\"f\"]}"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void moveRoverForward() throws Exception {
        Rover rover = new Rover(1, new Coordinate(1, 1), Direction.N);
        when(roverRepository.get(any())).thenReturn(rover);

        mockMvc.perform(post("/rovers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"commands\": [\"f\"]}"))
                .andExpect(status().isOk());
    }

    @Test
    public void moveRoverBackwards() throws Exception {
        Rover rover = new Rover(1, new Coordinate(1, 1), Direction.N);
        when(roverRepository.get(any())).thenReturn(rover);

        mockMvc.perform(post("/rovers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"commands\": [\"b\"]}"))
                .andExpect(status().isOk());
    }

    @Test
    public void moveRoverInvalidDirectionReturnsBadRequest() throws Exception {
        Rover rover = new Rover(1, new Coordinate(1, 1), Direction.N);
        when(roverRepository.get(any())).thenReturn(rover);

        mockMvc.perform(post("/rovers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"commands\": [\"a\"]}"))
                .andExpect(status().isBadRequest());
    }
}