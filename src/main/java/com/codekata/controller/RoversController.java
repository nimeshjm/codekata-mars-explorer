package com.codekata.controller;

import com.codekata.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoversController {

    private Config configuration;

    @Autowired
    public RoversController(Config configuration) {
        this.configuration = configuration;
    }

    @RequestMapping("/rovers")
    public String Rovers() {
        return configuration.getHeight().toString() + configuration.getWidth() + configuration.getObstacles();
    }
}