package com.codekata.service;

import com.codekata.exception.InvalidCommandException;
import com.codekata.model.Coordinate;
import com.codekata.model.Direction;
import com.codekata.model.Rover;
import com.codekata.repository.RoverRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class MarsRoverServiceTest {
    private MarsRoverService sut;
    private RoverRepository roverRepository;

    @Before
    public void setUp() throws Exception {
        roverRepository = mock(RoverRepository.class);
        sut = new MarsRoverService(roverRepository);
    }

    @Test
    public void createIsSuccessful() throws Exception {
        // arrange
        when(roverRepository.add(any(), any()))
                .thenReturn(new Rover(1, new Coordinate(1,1), Direction.E));

        // act
        Rover actual = sut.Create(any(), any());

        // assert
        assertEquals((Integer)1, actual.getId());
        assertEquals(new Coordinate(1, 1), actual.getPosition());
        assertThat(actual.getDirection(), instanceOf(Direction.class));

        verify(roverRepository).add(any(), any());
    }

    @Test
    public void getAllOnEmptyDatabaseReturnsEmptyList() throws Exception {
        List<Rover> actual = sut.GetAll();

        assertEquals(0, actual.size());
    }

    @Test
    public void getAllReturnsListOfRovers() throws Exception {
        when(roverRepository.get())
                .thenReturn(Arrays.asList(
                                    new Rover(1, new Coordinate(1,1), Direction.N),
                                    new Rover(1, new Coordinate(1,1), Direction.W)));

        List<Rover> actual = sut.GetAll();

        assertEquals(2, actual.size());
    }

    @Test
    public void runCommandOnUnknownRoverReturnsMissingRover() throws Exception, InvalidCommandException {
        Rover actual = sut.Run(1, any());
        assertNull(actual);
    }

    @Test
    public void runCommandOnRoverReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,1), Direction.N));
        char[] commands = {'f'};

        Rover actual = sut.Run(1, commands);

        assertNotNull(actual);
    }

    @Test
    public void runCommandForwardOnRoverReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,1), Direction.N));
        char[] commands = {'f'};

        Rover actual = sut.Run(1, commands);

        assertEquals((Integer)2, actual.getPosition().getY());
        assertEquals((Integer)1, actual.getPosition().getX());
    }

    @Test
    public void runCommandBackwardsOnRoverReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,1), Direction.W));
        char[] commands = {'b'};

        Rover actual = sut.Run(1, commands);

        assertEquals((Integer)1, actual.getPosition().getY());
        assertEquals((Integer)0, actual.getPosition().getX());
    }

    @Test(expected = InvalidCommandException.class)
    public void runUnknownCommandOnRoverThrows() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,1), Direction.N));
        char[] commands = {'a'};

        sut.Run(1, commands);
    }
}