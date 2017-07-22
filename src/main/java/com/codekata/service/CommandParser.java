package com.codekata.service;

public class CommandParser {
    public static Command Parse(char command) {
        if (command == 'f')
            return new MoveForwardCommand();
        if (command == 'b')
            return new MoveBackwardsCommand();
        return null;
    }
}
