package com.asaulyuk.model;

public class Player implements Moving {

    public enum PlayerColor {
        BLACK,
        WHITE
    }
    public String name;
    public Integer moveCount;
    public Boolean isUserPlayer = true;
    public PlayerColor playerColor;
    Integer x;
    Integer y;
    Vershina coordinati;

    public Player(String name, PlayerColor playerColor) {
        this.name = name;
        this.playerColor = playerColor;
        this.moveCount=0;
    }

    public void newCoordinate(Integer xCoordinate, Integer yCoordinate) {
        this.x = xCoordinate;
        this.y = yCoordinate;
        this.moveCount=0;
    }

    @Override
    public void moveUp() {
        if (y<9) {
            this.moveCount++;
            y = y + 1;
        }
    }

    @Override
    public void moveDown() {
        if (y>1) {
            this.moveCount++;
            y = y - 1;
        }
    }

    @Override
    public void moveLeft() {
        if (x>1) {
            this.moveCount++;
            x = x - 1;
        }
    }

    @Override
    public void moveRight() {
        if (x<9) {
            this.moveCount++;
            x = x + 1;
        }
    }

    @Override
    public Integer getMoveCount() {
        return this.moveCount;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
