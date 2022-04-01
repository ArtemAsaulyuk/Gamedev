package com.asaulyuk;

import com.asaulyuk.controller.GameController;
import com.asaulyuk.model.Placement;
import com.asaulyuk.model.QuoridorGameLogic;
import com.asaulyuk.view.GameView;


public class Main {

    public static void main(String[] args) {
        QuoridorGameLogic gameLogic = new QuoridorGameLogic();
        GameView gameView = new GameView(gameLogic);
        GameController gameController = new GameController(gameLogic, gameView);
        gameView.setGameController(gameController);

        gameLogic.initializeGame(2);
        gameView.initialize();

/*
        gameLogic.startGame("white");
        Boolean wallResult = gameLogic.placeWall(3,0, Placement.Horizontal);
        gameView.refreshInfo();
        Thread.sleep(200);

        gameLogic.placeWall(1,5, Placement.Vertical);
        gameView.refreshInfo();
        Thread.sleep(200);

        gameLogic.placeWall(2,7, Placement.Horizontal);
        gameView.refreshInfo();
        Thread.sleep(200);
        System.out.println("Wall result:" + wallResult);
        System.out.println(gameLogic.getCurrentPlayerColor());
        gameView.refreshInfo();
        Thread.sleep(200);

        System.out.println("Move result:" + gameLogic.move(4,7));
        System.out.println(gameLogic.getCurrentPlayerColor());
        gameView.refreshInfo();
        Thread.sleep(200);

        System.out.println("Move result:" + gameLogic.move(5,0));
        System.out.println(gameLogic.getCurrentPlayerColor());
        gameView.refreshInfo();
        Thread.sleep(200);

        System.out.println("Move result:" + gameLogic.move(4,6));
        System.out.println(gameLogic.getCurrentPlayerColor());
        gameView.refreshInfo();
        Thread.sleep(200);

        System.out.println("Move result:" + gameLogic.move(5,1));
        System.out.println(gameLogic.getCurrentPlayerColor());
        gameView.refreshInfo();
        Thread.sleep(200);

        System.out.println("Move result:" + gameLogic.move(4,5));
        System.out.println(gameLogic.getCurrentPlayerColor());
        gameView.refreshInfo();
        Thread.sleep(200);

        System.out.println("Move result:" + gameLogic.move(5,2));
        System.out.println(gameLogic.getCurrentPlayerColor());
        gameView.refreshInfo();
        Thread.sleep(200);

        System.out.println("Move result:" + gameLogic.move(5,5));
        System.out.println(gameLogic.getCurrentPlayerColor());
        gameView.refreshInfo();
        Thread.sleep(200);

        System.out.println("Move result:" + gameLogic.move(5,3));
        System.out.println(gameLogic.getCurrentPlayerColor());
        gameView.refreshInfo();
        Thread.sleep(200);

        System.out.println("Move result:" + gameLogic.move(5,4));
        System.out.println(gameLogic.getCurrentPlayerColor());
        gameView.refreshInfo();
        Thread.sleep(200);

        System.out.println("Move result:" + gameLogic.move(5,4));
        System.out.println(gameLogic.getCurrentPlayerColor());
        gameView.refreshInfo();
        Thread.sleep(200);

        System.out.println("Move result:" + gameLogic.jump(5,5));
        System.out.println(gameLogic.getCurrentPlayerColor());
        gameView.refreshInfo();
        Thread.sleep(200);
*/
    }
}
