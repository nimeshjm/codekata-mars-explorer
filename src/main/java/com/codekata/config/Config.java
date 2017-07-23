package com.codekata.config;

import com.codekata.model.Coordinate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix="grid")
public class Config {

    private Integer height;
    private Integer width;

    private List<Coordinate> obstacles;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public List<Coordinate> getObstacles() {
        return this.obstacles;
    }

    public void setObstacles(List<Coordinate> obstacles) {
        this.obstacles = obstacles;
    }
}