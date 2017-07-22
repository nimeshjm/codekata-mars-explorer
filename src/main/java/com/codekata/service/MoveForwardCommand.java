package com.codekata.service;

import com.codekata.model.Rover;

public class MoveForwardCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.Forward();
    }
}
