package com.codekata.command;

import com.codekata.model.Rover;
import org.springframework.stereotype.Component;

@Component
public class TurnRightCommand implements Filter {
    @Override
    public boolean canExecute(char command) {
        return Character.toLowerCase(command) == 'r';
    }

    @Override
    public void execute(Rover rover) {
        rover.turnRight();
    }
}
