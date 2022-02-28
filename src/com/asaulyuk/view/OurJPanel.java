package com.asaulyuk.view;

import com.asaulyuk.model.Placement;
import com.asaulyuk.model.Player;

import javax.swing.*;
import java.awt.*;

public class OurJPanel extends JPanel {
    private static int CELL_SIZE = 50;

    private static int offsetSize = 17;
    private static int playerSize = 25;
    private static int wallSize = 10;
    private ViewSupportData.Box[] verticalWallEdges;
    private ViewSupportData.Box[] horizontalWallEdges;

    Placement[][] wallMatrix;

    private Integer xSize;
    private Integer ySize;

    Player currentPlayer;
    Player whitePlayer;
    Player blackPlayer;

    public OurJPanel(Player whitePlayer, Player blackPlayer, Integer xSize, Integer ySize, Placement[][] wallMatrix) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.xSize = xSize;
        this.ySize = ySize;
        this.setBackground(Color.WHITE);
        this.wallMatrix = wallMatrix;
        this.verticalWallEdges = new ViewSupportData.Box[xSize];
        this.horizontalWallEdges = new ViewSupportData.Box[ySize];
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayers(g);
        drawGrid(g);
        drawPlacedWalls(g);
    }

    private void drawGrid(Graphics g) {
        for (int x = 1; x <= xSize; x++) {
            g.drawRect(x * CELL_SIZE, 0, wallSize, CELL_SIZE * xSize + wallSize);
            verticalWallEdges[x - 1] = new ViewSupportData.Box(x * CELL_SIZE, 0, wallSize, CELL_SIZE * xSize + wallSize);
        }
        for (int y = 1; y <= ySize; y++) {
            g.drawRect(0, y * CELL_SIZE, CELL_SIZE * ySize + wallSize, wallSize);
            horizontalWallEdges[y - 1] = new ViewSupportData.Box(0, y * CELL_SIZE, CELL_SIZE * ySize + wallSize, wallSize);
        }
    }

    private void drawPlayers(Graphics g) {
        g.setColor(Color.RED);
        if (this.currentPlayer.equals(whitePlayer)) {
            g.drawRect(whitePlayer.getX() * 50 + offsetSize, whitePlayer.getY() * 50 + offsetSize, playerSize, playerSize);
        }
        g.fillOval(whitePlayer.getX() * 50 + offsetSize, whitePlayer.getY() * 50 + offsetSize, playerSize, playerSize);
        g.setColor(Color.GREEN);

        if (this.currentPlayer.equals(blackPlayer)) {
            g.drawRect(blackPlayer.getX() * 50 + offsetSize, blackPlayer.getY() * 50 + offsetSize, playerSize, playerSize);
        }
        g.fillOval(blackPlayer.getX() * 50 + offsetSize, blackPlayer.getY() * 50 + offsetSize, playerSize, playerSize);

    }

    private void drawPlacedWalls(Graphics g) {
        for (int x = 0; x < xSize - 1; x++) {
            for (int y = 0; y < ySize - 1; y++) {
                if (wallMatrix[x] == null || wallMatrix[x][y] == null) {
                    continue;
                }
                if (wallMatrix[x][y].equals(Placement.Horizontal)) {
                    g.fillRect((x) * CELL_SIZE + wallSize, (y + 1) * CELL_SIZE, CELL_SIZE * 2 - wallSize, wallSize);
                } else if (wallMatrix[x][y].equals(Placement.Vertical)) {
                    g.fillRect((x + 1) * CELL_SIZE, (y) * CELL_SIZE + wallSize, wallSize, CELL_SIZE * 2 - wallSize);
                }
            }
        }
    }

    public ViewSupportData calculateWallEdgeCoordinates(Integer xCoordinate, Integer yCoordinate) {

        Integer lastProcessedVerticalEdgeIndex = 0;
        Integer lastProcessedHorizontalEdgeIndex = 0;
        Integer verticalEdgeIndex = -1;
        Integer horizontalEdgeIndex = -1;
        for (int x = 0; x < xSize; x++) {
            lastProcessedVerticalEdgeIndex = x;
            ViewSupportData.Box verticalEdge = verticalWallEdges[x];
            if (isInBox(xCoordinate, yCoordinate, verticalEdge)) {
                verticalEdgeIndex = x;
                break;
            } else if (xCoordinate < verticalEdge.x1) {
                break;
            }
        }
        for (int y = 0; y < ySize; y++) {
            lastProcessedHorizontalEdgeIndex = y;
            ViewSupportData.Box horizontalEdge = horizontalWallEdges[y];
            if (isInBox(xCoordinate, yCoordinate, horizontalEdge)) {
                horizontalEdgeIndex = y;
                break;
            } else if (yCoordinate < horizontalEdge.y1) {
                break;
            }
        }

        //  if Wall was selected
        if ((verticalEdgeIndex >= 0) || (horizontalEdgeIndex >= 0)) {
            if ((verticalEdgeIndex >= 0) && (horizontalEdgeIndex >= 0)) {
                return new ViewSupportData(verticalEdgeIndex, horizontalEdgeIndex, ViewSupportData.BoxType.WALL_HORIZONTAL);
            } else if (verticalEdgeIndex >= 0) {
                return new ViewSupportData(verticalEdgeIndex, lastProcessedHorizontalEdgeIndex, ViewSupportData.BoxType.WALL_VERTICAL);
            } else {
                return new ViewSupportData(lastProcessedVerticalEdgeIndex, horizontalEdgeIndex, ViewSupportData.BoxType.WALL_HORIZONTAL);
            }
        } else {
        //  cell was selected
            return new ViewSupportData(lastProcessedVerticalEdgeIndex, lastProcessedHorizontalEdgeIndex, ViewSupportData.BoxType.CELL);
        }

    }

    private Boolean isInBox(Integer x, Integer y, ViewSupportData.Box box) {
        return (x >= box.x)
                && (x <= box.x1)
                && (y >= box.y)
                && (y <= box.y1);

    }

    public void setWallMatrix(Placement[][] wallMatrix) {
        this.wallMatrix = wallMatrix;
    }

}
