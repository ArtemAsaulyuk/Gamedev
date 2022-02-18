package com.asaulyuk.model;

public interface Moving {
    Integer moveCount = 0;

    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();

    Integer getMoveCount();


}
