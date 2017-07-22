package com.codekata.command;

import com.codekata.model.Rover;

public interface Filter {
    boolean canExecute(char command);
    void execute(Rover rover);
}
