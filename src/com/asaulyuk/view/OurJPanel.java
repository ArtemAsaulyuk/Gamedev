package com.asaulyuk.view;

import com.asaulyuk.model.Player;

import javax.swing.*;
import java.awt.*;

public class OurJPanel extends JPanel {

    Player whiPlayer;
    Player blackPlayer;

    public OurJPanel(Player whiPlayer, Player blackPlayer) {
        this.whiPlayer = whiPlayer;
        this.blackPlayer = blackPlayer;
        this.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(whiPlayer.getX()*50, whiPlayer.getY()*50, 25,25);
        g.setColor(Color.GREEN);
        g.fillOval(blackPlayer.getX()*50, blackPlayer.getY()*50, 25,25);
    }
}
