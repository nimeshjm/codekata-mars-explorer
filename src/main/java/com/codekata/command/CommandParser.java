package com.codekata.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandParser {
    private final List<? extends Filter> filters;

    @Autowired
    public CommandParser(List<? extends Filter> filters) {
        this.filters = filters;
    }

    public Filter Parse(char command) {
        return filters.stream()
                .filter(f -> f.canExecute(command))
                .findFirst()
                .orElse(null);
    }
}
