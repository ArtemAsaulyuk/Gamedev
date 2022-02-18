package com.asaulyuk.model;

import org.apache.commons.graph.Vertex;

public class Vershina implements Vertex {
    Integer x;
    Integer y;


    public Vershina(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
