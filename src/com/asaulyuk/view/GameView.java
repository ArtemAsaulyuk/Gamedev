package com.asaulyuk.view;

import com.asaulyuk.controller.GameController;
import com.asaulyuk.model.Player;
import com.asaulyuk.model.QuoridorGameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.JOptionPane.DEFAULT_OPTION;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;

public class GameView implements MouseListener {
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
        frame.setSize(new Dimension(480,550));
        frame.setResizable(false);
        JPanel topPanel = new JPanel(new GridLayout(1,5));
        JButton starGameButton = new JButton("Start Game");
        starGameButton.setBackground(Color.BLUE);
        starGameButton.addActionListener(gameController);
        starGameButton.setActionCommand(GameController.START_GAME);
        topPanel.add(new JLabel("Red:"));
        whiteInfo = new JLabel("info1");
        topPanel.add(whiteInfo);
        topPanel.add(starGameButton);
        topPanel.add(new JLabel("Green"));
        blackInfo = new JLabel("info2");
        topPanel.add(blackInfo);
        frame.setTitle("Quoridor by Asaulyuk");
        this.buildField();

//        frame.add(topPanel, BorderLayout.PAGE_START);
        JPanel pageStartPanel = new JPanel();
        pageStartPanel.setLayout(new GridLayout(2,1));
        JPanel topCoord = new JPanel();
        topCoord.setLayout(new GridLayout(1,9));
        for (int i=65; i< 65 + QuoridorGameLogic.MATRIX_SIZE_X; i++) {
            topCoord.add(new JLabel(Character.toString((char)i), SwingConstants.CENTER));
        }
        pageStartPanel.add(topPanel);
        pageStartPanel.add(topCoord);

        frame.add(pageStartPanel, BorderLayout.PAGE_START);
        fieldPanel.addMouseListener(this);
        fieldPanel.setCurrentPlayer(gameLogic.getCurrentPlayer());
        JPanel leftCoord = new JPanel();
        leftCoord.setLayout(new GridLayout(QuoridorGameLogic.MATRIX_SIZE_Y, 1));
        for(Integer i=1; i< QuoridorGameLogic.MATRIX_SIZE_Y+1; i++) {
            leftCoord.add(new JLabel(" "+i.toString(), SwingConstants.CENTER));
        }
        frame.add(leftCoord, BorderLayout.WEST);
        frame.add(fieldPanel );
        frame.setVisible(true);

    }

    private Button buildPlayer(Player player) {
        String color = player.playerColor.equals(Player.PlayerColor.WHITE) ? "white" : "black";
        Button button = new Button(color);
        button.setLocation(player.getX()*10, player.getY()*10);
        return button;

    }

    private void buildField() {
        fieldPanel = new OurJPanel(gameLogic.getWhitePlayer(),
                gameLogic.getBlackPlayer(),
                QuoridorGameLogic.MATRIX_SIZE_X,
                QuoridorGameLogic.MATRIX_SIZE_Y,
                gameLogic.getWallMatrix());
//        gameLogic.getWallMatrix().length


    }

    public void refreshInfo() {
        fieldPanel.setCurrentPlayer(gameLogic.getCurrentPlayer());
        fieldPanel.setWallMatrix(gameLogic.getWallMatrix());
        whiteInfo.setText("X="+gameLogic.getWhitePlayer().getX()+" Y="+gameLogic.getWhitePlayer().getY());
        blackInfo.setText("X="+gameLogic.getBlackPlayer().getX()+" Y="+gameLogic.getBlackPlayer().getY());
        this.fieldPanel.repaint();
    }

    public int choseColor() {
        String[] options = {"Red","Green"};
        return JOptionPane.showOptionDialog(frame,"Chose your color","Chose color",DEFAULT_OPTION, QUESTION_MESSAGE, null, options, 0);
    }

    public int choseMode() {
        String[] options = {"One player","Two players"};
        return JOptionPane.showOptionDialog(frame,"Chose number of players","Mode",DEFAULT_OPTION, QUESTION_MESSAGE, null, options, 0);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() + " " + e.getY());
        ViewSupportData data = fieldPanel.calculateWallEdgeCoordinates(e.getX(), e.getY());
        System.out.println(data.selectedType+" x:"+data.x+" y:"+data.y);

        ActionEvent event;
        if (data.selectedType.equals(ViewSupportData.BoxType.CELL)) {
            event = new ActionEvent(data, ActionEvent.ACTION_FIRST, GameController.MOVE_COMMAND);
            gameController.actionPerformed(event);
        } else {
            event = new ActionEvent(data, ActionEvent.ACTION_FIRST, GameController.WALL_COMMAND);
            gameController.actionPerformed(event);
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
