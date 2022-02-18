package com.asaulyuk;

import java.awt.*;

public class SuperPlayer extends Player {

    boolean isAdmin;
    public SuperPlayer(String name, Color color, boolean isAdmin) {
        super(name, color);
        this.isAdmin = isAdmin;
    }

    @Override
    public void moveUp() {

        super.moveUp();
        this.y= y+2;
    }

    @Override
    public void moveDown() {
        this.y=y-3;
    }
}
