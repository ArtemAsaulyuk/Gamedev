package com.asaulyuk.model;

import org.apache.commons.graph.MutableGraph;
import org.apache.commons.graph.domain.basic.UndirectedGraphImpl;

import java.awt.Color;
import java.util.List;
import java.util.Set;


public class QuoridorGameLogic {
    private static final Integer MATRIX_SIZE_X = 9;
    private static final Integer MATRIX_SIZE_Y = 9;


    Player blackPlayer;
    Player whitePlayer;

    MutableGraph graph;

    Vershina[][] matrixVershin;

    public QuoridorGameLogic() {
        whitePlayer = new Player("Player 1", Color.WHITE);
        blackPlayer = new Player("Player 2", Color.BLACK);
        startGame();
    }

    public void startGame() {
        initializeAll();
        whitePlayer.x = 4;
        whitePlayer.y = 0;
        whitePlayer.coordinati = getVershinaByXY(whitePlayer.getX(),whitePlayer.getY());
        whitePlayer.moveCount = 0;

        blackPlayer.x = 4;
        blackPlayer.y = 8;
        blackPlayer.coordinati = getVershinaByXY(blackPlayer.getX(), blackPlayer.getY());
        blackPlayer.moveCount =0;

    }

    public Boolean movePlayerToXY(String color, Integer x, Integer y) {
        Player movePlayer;
        if (color.equalsIgnoreCase("black")) {
            movePlayer = blackPlayer;
        } else if (color.equalsIgnoreCase("white")) {
            movePlayer = whitePlayer;
        } else {
            return false;
        }

        Vershina moveTo = null;
        for (Object v: graph.getVertices()) {
            Vershina vershina = (Vershina) v;
            if ((vershina.getX().equals(x)) && vershina.getY().equals(y)) {
                moveTo = vershina;
            }
        }
        if (moveTo == null) {
//            "Error"
            return false;
        }

        return movePlayer(movePlayer, moveTo);

    }

    private Boolean movePlayer(Player player, Vershina where) {
        if (!checkMove(player, where)) {
            return false;
//            "Dvizenije zaprischeno"
        }
        player.coordinati = where;
        return true;
    }

    private Boolean checkMove(Player player, Vershina where) {
        Set<Rebro> rebra = graph.getEdges(player.coordinati);
        for (Rebro r: rebra) {
            if (graph.getVertices(r).contains(where)) {
                return true;
            }

        }
        return false;
    }

//    private Boolean canPutWall(x,y, v|h) {
//
//    }

    private Vershina getVershinaByXY(Integer x, Integer y) {
        Set<Vershina> allVershina = graph.getVertices();
        for(Vershina v: allVershina) {
            if ((v.getY().equals(y)) && v.getX().equals(x)) {
                return v;
            }
        }
        return null;
    }

    public void initializeAll() {
        graph = new UndirectedGraphImpl();
        InitializeVershini(graph);
        InitializeConnections(graph);
    }

    private void InitializeConnections(MutableGraph graph) {
        for (int x = 0; x < MATRIX_SIZE_X; x=x+1) {
            for (int y = 0; y < MATRIX_SIZE_Y; y=y+1) {
                if( y < MATRIX_SIZE_Y - 1) {
                    Rebro downRebro = new Rebro(String.valueOf(x).concat(" ").concat(String.valueOf(y+1)));
                    graph.addEdge(downRebro);
                    graph.connect(downRebro, matrixVershin[x][y]);
                    graph.connect(downRebro, matrixVershin[x][y + 1]);
                }

                if (x < MATRIX_SIZE_X -1) {
                    Rebro rightRebro = new Rebro(String.valueOf(x + 1).concat(" ").concat(String.valueOf(y+1)));
                    graph.addEdge(rightRebro);
                    graph.connect(rightRebro, matrixVershin[x][y]);
                    graph.connect(rightRebro, matrixVershin[x + 1][y]);
                }
            }
        }
    }

    private void InitializeVershini(MutableGraph graph) {

        matrixVershin = new Vershina[MATRIX_SIZE_X][MATRIX_SIZE_Y];
        for (int x = 0; x < MATRIX_SIZE_X; x=x+1) {
            for (int y = 0; y < MATRIX_SIZE_Y; y=y+1) {
                Vershina currentVershina = new Vershina(x,y);
                matrixVershin[x][y]= currentVershina;
                graph.addVertex(currentVershina);
            }
        }

    }


    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public MutableGraph getGraph() {
        return graph;
    }

    public Vershina[][] getMatrixVershin() {
        return matrixVershin;
    }
}
