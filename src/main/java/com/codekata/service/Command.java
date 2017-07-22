package com.codekata.service;

import com.codekata.model.Rover;

public interface Command {
    void execute(Rover rover);
}
