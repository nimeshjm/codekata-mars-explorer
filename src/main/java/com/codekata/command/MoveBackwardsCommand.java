package com.codekata.command;

import com.codekata.config.Config;
import com.codekata.model.Rover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MoveBackwardsCommand implements Filter {
    private final Config config;

    @Autowired
    public MoveBackwardsCommand(Config config) {
        this.config = config;
    }

    @Override
    public boolean canExecute(char command) {
        return Character.toLowerCase(command) == 'b';
    }

    @Override
    public void execute(Rover rover) {
        rover.moveBackwards(config.getHeight(), config.getWidth());
    }
}

