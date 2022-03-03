package com.asaulyuk.controller;

import com.asaulyuk.model.Placement;
import com.asaulyuk.model.QuoridorGameLogic;
import com.asaulyuk.view.GameView;
import com.asaulyuk.view.ViewSupportData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener{

    public static final String START_GAME = "StartGame";
    public static final String MOVE_COMMAND = "move";
    public static final String WALL_COMMAND = "wall";
    private QuoridorGameLogic gameLogic;
    private GameView gameView;
    private PcPlayerController pcPlayerController;

    public GameController(QuoridorGameLogic gameLogic, GameView gameView) {
        this.gameLogic = gameLogic;
        pcPlayerController = new PcPlayerController(gameLogic);
        this.gameView = gameView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(START_GAME)) {
            if (processStartGame()) {
                return;

            }
        }
        boolean commandResult = false;
        if (e.getActionCommand().equals(MOVE_COMMAND)) {
            ViewSupportData data = (ViewSupportData) e.getSource();
            commandResult = gameLogic.moveOrJump(data.x, data.y);
        }
        if (e.getActionCommand().equals(WALL_COMMAND)) {
            ViewSupportData data = (ViewSupportData) e.getSource();
            if (data.selectedType.equals(ViewSupportData.BoxType.WALL_HORIZONTAL)) {
                commandResult= gameLogic.placeWall(data.x, data.y, Placement.Horizontal);
            } else {
                commandResult = gameLogic.placeWall(data.x, data.y, Placement.Vertical);
            }
        }
        gameView.refreshInfo();
        if (gameLogic.getWinner()!=null) {
            gameView.congratulateWinner(gameLogic.getWinner().name);
        }


/*
        if (!gameLogic.getCurrentPlayer().isUserPlayer) {
            pcPlayerController.doNextMove();
            gameView.refreshInfo();
            if (gameLogic.getWinner()!=null) {
                gameView.congratulateWinner(gameLogic.getWinner().name);
            }

        }
*/
        while (!gameLogic.getCurrentPlayer().isUserPlayer && gameLogic.isGameStarted()) {
            pcPlayerController.doNextMove();
            gameView.refreshInfo();
            if (gameLogic.getWinner()!=null) {
                gameView.congratulateWinner(gameLogic.getWinner().name);
            }

        }

        //System.out.println(e.paramString());
    }

    private boolean processStartGame() {
        int choseColor = gameView.choseColor();
        String firstColor;
        if (choseColor == 0) {
            firstColor="white";
        } else if (choseColor == 1) {
            firstColor="black";
        } else {
            return true;
        }
        int choseMode = gameView.choseMode();
        if (choseMode == 0 ) {
            gameLogic.initializeGame(1);
            gameLogic.getWhitePlayer().name="Red";
            gameLogic.getBlackPlayer().name="Green";
            gameLogic.startGame(firstColor);
        } else if (choseMode ==1 ) {
            gameLogic.initializeGame(2);
            gameLogic.getWhitePlayer().name="Red";
            gameLogic.getBlackPlayer().name="Green";
            gameLogic.startGame(firstColor);
        } else return true;


        //System.out.println(choseColor);
        return false;
    }

}
