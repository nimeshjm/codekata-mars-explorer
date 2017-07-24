package com.codekata.service;

import com.codekata.command.*;
import com.codekata.config.Config;
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
        Config config = mock(Config.class);
        when(config.getHeight()).thenReturn(10);
        when(config.getWidth()).thenReturn(10);
        when(config.getObstacles()).thenReturn(Arrays.asList(new Coordinate(3,4), new Coordinate(5,3)));

        roverRepository = mock(RoverRepository.class);
        CommandParser commandParser = new CommandParser(Arrays.asList(new MoveForwardCommand(config), new MoveBackwardsCommand(config), new TurnRightCommand(), new TurnLeftCommand()));
        sut = new MarsRoverService(roverRepository, commandParser);
    }

    @Test
    public void createIsSuccessful() throws Exception {
        when(roverRepository.add(any(), any()))
                .thenReturn(new Rover(1, new Coordinate(1,1), Direction.E));

        Rover actual = sut.create(any(), any());

        assertEquals((Integer)1, actual.getId());
        assertEquals(new Coordinate(1, 1), actual.getPosition());
        assertThat(actual.getDirection(), instanceOf(Direction.class));

        verify(roverRepository).add(any(), any());
    }

    @Test
    public void getAllOnEmptyDatabaseReturnsEmptyList() throws Exception {
        List<Rover> actual = sut.get();

        assertEquals(0, actual.size());
    }

    @Test
    public void getAllReturnsListOfRovers() throws Exception {
        when(roverRepository.get())
                .thenReturn(Arrays.asList(
                                    new Rover(1, new Coordinate(1,1), Direction.N),
                                    new Rover(1, new Coordinate(1,1), Direction.W)));

        List<Rover> actual = sut.get();

        assertEquals(2, actual.size());
    }

    @Test
    public void runCommandOnUnknownRoverReturnsMissingRover() throws Exception, InvalidCommandException {
        Rover actual = sut.run(1, any());
        assertNull(actual);
    }

    @Test
    public void runCommandOnRoverReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,1), Direction.N));
        char[] commands = {'f'};

        Rover actual = sut.run(1, commands);

        assertNotNull(actual);
    }

    @Test
    public void runCommandForwardOnRoverReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,1), Direction.N));
        char[] commands = {'f'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(1, 2);
        assertEquals(expected, actual.getPosition());
    }

    @Test
    public void runCommandBackwardsOnRoverReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,1), Direction.W));
        char[] commands = {'b'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(2, 1);
        assertEquals(expected, actual.getPosition());
    }

    @Test
    public void runCommandTurnRightOnRoverReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,1), Direction.S));
        char[] commands = {'r'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(1, 1);
        assertEquals(expected, actual.getPosition());
        assertEquals(Direction.W, actual.getDirection());
    }

    @Test
    public void runCommandTurnLeftOnRoverReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,1), Direction.W));
        char[] commands = {'l'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(1, 1);
        assertEquals(expected, actual.getPosition());
        assertEquals(Direction.S, actual.getDirection());
    }

    @Test
    public void runBackwardsCommandRoverReachesTopEdgeReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,9), Direction.S));
        char[] commands = {'b'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(1, 0);
        assertEquals(expected, actual.getPosition());
        assertEquals(Direction.S, actual.getDirection());
    }

    @Test
    public void runBackwardsCommandRoverReachesBottomEdgeReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,0), Direction.N));
        char[] commands = {'b'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(1, 9);
        assertEquals(expected, actual.getPosition());
        assertEquals(Direction.N, actual.getDirection());
    }

    @Test
    public void runBackwardsCommandRoverReachesLeftEdgeReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(0,3), Direction.E));
        char[] commands = {'b'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(9, 3);
        assertEquals(expected, actual.getPosition());
        assertEquals(Direction.E, actual.getDirection());
    }

    @Test
    public void runBackwardsCommandRoverReachesRightEdgeReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(9,3), Direction.W));
        char[] commands = {'b'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(0, 3);
        assertEquals(expected, actual.getPosition());
        assertEquals(Direction.W, actual.getDirection());
    }

    @Test
    public void runCommandRoverReachesTopEdgeReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,9), Direction.N));
        char[] commands = {'f'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(1, 0);
        assertEquals(expected, actual.getPosition());
        assertEquals(Direction.N, actual.getDirection());
    }

    @Test
    public void runCommandRoverReachesBottomEdgeReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,0), Direction.S));
        char[] commands = {'f'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(1, 9);
        assertEquals(expected, actual.getPosition());
        assertEquals(Direction.S, actual.getDirection());
    }

    @Test
    public void runCommandRoverReachesLeftEdgeReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(0,3), Direction.W));
        char[] commands = {'f'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(9, 3);
        assertEquals(expected, actual.getPosition());
        assertEquals(Direction.W, actual.getDirection());
    }

    @Test
    public void runCommandRoverReachesRightEdgeReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(9,3), Direction.E));
        char[] commands = {'f'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(0, 3);
        assertEquals(expected, actual.getPosition());
        assertEquals(Direction.E, actual.getDirection());
    }

    @Test
    public void runCommandRoverHitsObstacleReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(3,3), Direction.N));
        char[] commands = {'f', 'b'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(3, 3);
        assertEquals(expected, actual.getPosition());
        assertEquals(Direction.N, actual.getDirection());
        assertTrue(actual.isCrashed());
    }

    @Test
    public void runBackwardsCommandRoverHitsObstacleReturnsRover() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(5,4), Direction.N));
        char[] commands = {'b'};

        Rover actual = sut.run(1, commands);

        Coordinate expected = new Coordinate(5, 4);
        assertEquals(expected, actual.getPosition());
        assertEquals(Direction.N, actual.getDirection());
        assertTrue(actual.isCrashed());
    }

    @Test(expected = InvalidCommandException.class)
    public void runUnknownCommandOnRoverThrows() throws Exception, InvalidCommandException {
        when(roverRepository.get(any())).thenReturn(new Rover(1, new Coordinate(1,1), Direction.N));
        char[] commands = {'a'};

        sut.run(1, commands);
    }
}