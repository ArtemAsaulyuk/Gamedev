package com.asaulyuk.controller;

import com.asaulyuk.model.QuoridorGameLogic;
import com.asaulyuk.view.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener{

    public static final String START_GAME = "StartGame";
    private QuoridorGameLogic gameLogic;
    private GameView gameView;

    public GameController(QuoridorGameLogic gameLogic, GameView gameView) {
        this.gameLogic = gameLogic;
        this.gameView = gameView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(START_GAME)) {
            int choseColor = gameView.choseColor();
            String firstColor;
            if (choseColor == 0) {
                firstColor="white";
            } else if (choseColor == 1) {
                firstColor="black";
            } else {
                return;
            }
            int choseMode = gameView.choseMode();
            if (choseMode == 0 ) {
                gameLogic.initializeGame(1);
                gameLogic.startGame(firstColor);
            } else if (choseMode ==1 ) {
                gameLogic.initializeGame(2);
                gameLogic.startGame(firstColor);
            } else return;

            System.out.println(choseColor);
        }
        System.out.println(e.paramString());
    }
}
