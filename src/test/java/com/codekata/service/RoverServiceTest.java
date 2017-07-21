package com.codekata.service;

import com.codekata.model.Coordinate;
import com.codekata.model.Direction;
import com.codekata.model.Rover;
import com.codekata.repository.RoverRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class RoverServiceTest {
    private RoverService sut;
    private RoverRepository roverRepository;

    @Before
    public void setUp() throws Exception {
        roverRepository = mock(RoverRepository.class);
        sut = new RoverService(roverRepository);
    }

    @Test
    public void createIsSuccessful() throws Exception {
        // arrange
        when(roverRepository.add(any(), any()))
                .thenReturn(new Rover(1, new Coordinate(1,1), Direction.E));

        // act
        Rover rover = sut.Create(any(), any());

        // assert
        assertEquals((Integer)1, rover.getId());
        assertEquals(new Coordinate(1, 1), rover.getStartingPoint());
        assertEquals(Direction.E, rover.getDirection());

        verify(roverRepository).add(any(), any());
    }

    @Test
    public void getAllOnEmptyDatabaseReturnsEmptyList() throws Exception {
        List<Rover> rovers = sut.GetAll();

        assertEquals(0, rovers.size());
    }

    @Test
    public void getAllReturnsListOfRovers() throws Exception {
        when(roverRepository.get())
                .thenReturn(Arrays.asList(
                                    new Rover(1, new Coordinate(1,1), Direction.E),
                                    new Rover(1, new Coordinate(1,1), Direction.E)));

        List<Rover> rovers = sut.GetAll();

        assertEquals(2, rovers.size());
    }

}