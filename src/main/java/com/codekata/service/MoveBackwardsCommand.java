package com.codekata.service;

import com.codekata.model.Rover;

public class MoveBackwardsCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.moveBackwards();
    }
}
