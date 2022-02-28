package com.asaulyuk;

import com.asaulyuk.controller.GameController;
import com.asaulyuk.model.Placement;
import com.asaulyuk.model.QuoridorGameLogic;
import com.asaulyuk.model.Vershina;
import com.asaulyuk.view.GameView;
import org.apache.commons.graph.MutableGraph;

import javax.swing.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        QuoridorGameLogic gameLogic = new QuoridorGameLogic();
        GameView gameView = new GameView(gameLogic);
        GameController gameController = new GameController(gameLogic, gameView);
        gameView.setGameController(gameController);
        //JOptionPane.showMessageDialog(null, "Hello, world!");

/*
        Scanner s = new Scanner(System.in);
        Integer personCount = 0;
        while(personCount<1 || personCount>2) {
            System.out.println("Chose mode:");
            System.out.println("1 - for Single user and PC");
            System.out.println("2 - for two users");
            personCount = s.nextInt();
        }
        gameLogic.InitializeGame(personCount);

        String choseColor="";
        while(!gameLogic.startGame(choseColor)) {
            System.out.println("Choose color 'white' or 'black':");
             choseColor = s.next();
        }
*/
        gameLogic.initializeGame(2);
        gameLogic.startGame("white");

        gameView.initialize();

        System.out.println(gameLogic.getCurrentPlayerColor());
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



    }

    public static Vershina getVershinaByXandY(Integer x, Integer y, MutableGraph graph){
        for(Vershina v: (List<Vershina>)graph.getVertices()) {
            if (v.getX().equals(x) && v.getY().equals(y)) {
                return v;
            }
        }

        return null;
    }

}
