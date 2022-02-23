package com.asaulyuk.view;

import com.asaulyuk.controller.GameController;
import com.asaulyuk.model.Player;
import com.asaulyuk.model.QuoridorGameLogic;


import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.DEFAULT_OPTION;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;

public class GameView {
    QuoridorGameLogic gameLogic;
    GameController gameController;
    JFrame frame;
    JLabel whiteInfo;
    JLabel blackInfo;
    OurJPanel fieldPanel;

    public GameView(QuoridorGameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.gameLogic = gameLogic;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void initialize(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600,600));
        JPanel topPanel = new JPanel(new GridLayout(1,5));
        //        GridLayout test= new GridLayout(5,5);
        frame.setLayout(new GridLayout(2,1));
        JButton starGameButton = new JButton("Start Game");
        starGameButton.setBackground(Color.BLUE);
        starGameButton.addActionListener(gameController);
        starGameButton.setActionCommand(GameController.START_GAME);
        topPanel.add(new JLabel("White:"));
        whiteInfo = new JLabel("info1");
        topPanel.add(whiteInfo);
        topPanel.add(starGameButton);
        topPanel.add(new JLabel("Black"));
        blackInfo = new JLabel("info2");
        topPanel.add(blackInfo);
        frame.add(topPanel);
        frame.setTitle("Quoridor by Asaulyuk");
        this.buildField();
        frame.add(fieldPanel);
        frame.setVisible(true);
//        frame.add(buildPlayer(gameLogic.getBlackPlayer()));
//        frame.add(buildPlayer(gameLogic.getWhitePlayer()));

    }

    private Button buildPlayer(Player player) {
        String color = player.playerColor.equals(Player.PlayerColor.WHITE) ? "white" : "black";
        Button button = new Button(color);
        button.setLocation(player.getX()*10, player.getY()*10);
        return button;

    }

    private void buildField() {
        fieldPanel = new OurJPanel(gameLogic.getWhitePlayer(), gameLogic.getBlackPlayer());
//        gameLogic.getWallMatrix().length


    }

    public void refreshInfo() {
        this.fieldPanel.repaint();
        whiteInfo.setText("X="+gameLogic.getWhitePlayer().getX()+" Y="+gameLogic.getWhitePlayer().getY());
        blackInfo.setText("X="+gameLogic.getBlackPlayer().getX()+" Y="+gameLogic.getBlackPlayer().getY());
    }

    public int choseColor() {
        String[] options = {"White","Black"};
        return JOptionPane.showOptionDialog(frame,"Chose your color","Chose color",DEFAULT_OPTION, QUESTION_MESSAGE, null, options, 0);
    }

    public int choseMode() {
        String[] options = {"One player","Two players"};
        return JOptionPane.showOptionDialog(frame,"Chose number of players","Mode",DEFAULT_OPTION, QUESTION_MESSAGE, null, options, 0);
    }

}
