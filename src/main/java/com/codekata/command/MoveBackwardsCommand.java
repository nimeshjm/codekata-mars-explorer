package com.codekata.command;

import com.codekata.model.Rover;
import org.springframework.stereotype.Component;

@Component
public class MoveBackwardsCommand implements Filter {
    @Override
    public boolean canExecute(char command) {
        return Character.toLowerCase(command) == 'b';
    }

    @Override
    public void execute(Rover rover) {
        rover.moveBackwards();
    }
}
