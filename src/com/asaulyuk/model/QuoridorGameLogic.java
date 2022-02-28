package com.asaulyuk.model;

import org.apache.commons.graph.MutableGraph;
import org.apache.commons.graph.domain.basic.UndirectedGraphImpl;


import java.util.HashSet;
import java.util.Set;


public class QuoridorGameLogic {
    public static final Integer MATRIX_SIZE_X = 9;
    public static final Integer MATRIX_SIZE_Y = 9;


    Player blackPlayer;
    Player whitePlayer;

    MutableGraph graph;

    Vershina[][] matrixVershin;

    Placement[][] wallMatrix;

    Integer personCount = 0;

    Player currentPlayer;

    public boolean isGameStarted() {
        return gameStarted;
    }

    boolean gameStarted;


    public QuoridorGameLogic() {
        whitePlayer = new Player("Player 1", Player.PlayerColor.WHITE);
        blackPlayer = new Player("Player 2", Player.PlayerColor.BLACK);

    }

    public void initializeGame(Integer personCount) {
        this.personCount=personCount;
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

    public Boolean startGame(String color) {

        if (color.equalsIgnoreCase("black")) {
            currentPlayer = blackPlayer;
        } else if (color.equalsIgnoreCase("white")) {
            currentPlayer = whitePlayer;
        } else {
            return false;
        }

        if (personCount==1) {
            if (currentPlayer.equals(blackPlayer)) {
                whitePlayer.isUserPlayer = false;
            } else {
                blackPlayer.isUserPlayer = false;
            }
        }
        gameStarted=true;
        System.out.println("Game begins, first move is for:"+getCurrentPlayerColor());
        return true;

    }


    public Boolean move(Integer x, Integer y) {
        Vershina moveTo = null;
        moveTo = matrixVershin[x][y];

        if (moveTo == null) {
            //            "Error"
            return false;
        }

        Boolean moveResult = movePlayer(currentPlayer, moveTo);

        return moveResult;

    }

    public boolean moveOrJump(Integer x, Integer y) {
        Boolean moveSuccess = move(x,y);
        if (!moveSuccess) {
                moveSuccess = jump(x,y);
        }
        return moveSuccess;
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
        moveTo = matrixVershin[x][y];

        if (moveTo == null) {
//            "Error"
            return false;
        }

        return movePlayer(movePlayer, moveTo);

    }

    public Boolean jump(Integer x, Integer y) {
        Player jumper = currentPlayer;
        Player oponent = getOtherPlayer(jumper);
//      check if we not try to jump onto oponent
        if ((oponent.x==x) && (oponent.y==y)) {
            return false;
        }
//      check if we are not devided from oponent
        Set<Rebro> ourRebra = graph.getEdges(jumper.coordinati);
        Set<Rebro> oponentRebra = graph.getEdges(oponent.coordinati);

        Set<Rebro> intersection = ourRebra;
        intersection.retainAll(oponentRebra);

        if (!anyRebroIsValid(intersection)) {
            // No connection, we can't jump
            return false;
        }

//      Check is available behind oponent
        Placement placementOn;
        if (jumper.x.equals(oponent.x)) {
            placementOn = Placement.Vertical;
        } else {
            placementOn = Placement.Horizontal;
        }

        Rebro behindOponent = null;
        Set<Rebro> noIntersectRebra = oponentRebra;
        noIntersectRebra.removeAll(intersection);
        for(Rebro r:noIntersectRebra) {
            if (r.getValid() && r.placement.equals(placementOn)) {
                behindOponent = r;
            }
        }
        if (behindOponent==null) {
            // No rebro behind.
//            Check leftRight move
            return false;
        } else {
            //Move to point;

            for(Object v: graph.getVertices(behindOponent)) {
                Vershina vershina = (Vershina) v;
                if (vershina.getX().equals(x) && vershina.getY().equals(y)) {
                    jumper.coordinati=vershina;
                    jumper.x = vershina.getX();
                    jumper.y = vershina.getY();
                    switchPlayer();
                    return true;
                }

            }
        }
//        switchPlayer();
        return false;
    }

    private Player getOtherPlayer(Player player) {
        if (player.equals(blackPlayer)) {
            return whitePlayer;
        } else {
            return blackPlayer;
        }
    }

    private Boolean anyRebroIsValid(Set<Rebro> rebra) {
        for(Rebro r: rebra) {
            if (r.getValid()) {
                return true;
            }
        }
        return false;
    }

    private Boolean movePlayer(Player player, Vershina where) {
        if (!checkMove(player, where)) {
            return false;
//            "Dvizenije zaprischeno"
        }
        player.coordinati = where;
        player.x = where.getX();;
        player.y = where.getY();
        switchPlayer();
        return true;
    }

    private Boolean checkMove(Player player, Vershina where) {
        if (getOtherPlayer(player).coordinati.equals(where)) {
            return false;
        }
        Set<Rebro> rebra = graph.getEdges(player.coordinati);
        for (Rebro r: rebra) {
            if ((r.getValid()) && (graph.getVertices(r).contains(where))) {
                return true;
            }

        }
        return false;
    }

    public Boolean placeWall(Integer x, Integer y, Placement placement ) {

// checks: must place 2 walls, place is available, path is possible
        if (IsWallValid(x,y,placement)) {
                wallMatrix[x][y] = placement;
                updateGraphWithWall(x,y,placement);
                switchPlayer();
                return true;
//
//              Stavit nelzja, Blokirovka prohoda

        } else {
//          Mesto Zanjato
            return false;
        }


    }

    private void updateGraphWithWall(Integer x, Integer y, Placement placement) {
        Vershina v1 = matrixVershin[x][y];
        Vershina v2 = matrixVershin[x][y+1];
        Vershina v3 = matrixVershin[x+1][y];
        Vershina v4 = matrixVershin[x+1][y+1];
        Set<Vershina> vershini = new HashSet<>();
        vershini.add(v1);
        vershini.add(v2);
        vershini.add(v3);
        vershini.add(v4);

        Set<Rebro> rebra = new HashSet<>();
        Set<Rebro> validRebra = new HashSet<>();
        rebra.addAll(graph.getEdges(v1));
        rebra.addAll(graph.getEdges(v2));
        rebra.addAll(graph.getEdges(v3));
        rebra.addAll(graph.getEdges(v4));
        for (Rebro rebro: rebra) {

            Boolean rebroIsValid = true;
            for(Object obj: graph.getVertices(rebro)) {
                Vershina vershina = (Vershina) obj;
                rebroIsValid = vershini.contains(vershina);
                if (!rebroIsValid) {
                    break;
                }

            }
            if (rebroIsValid) {
                validRebra.add(rebro);
            }
        }

        for(Rebro rebro: validRebra) {
            if (!rebro.placement.equals(placement)) {
                rebro.setValid(false);
            }
        }
    }

    private Boolean IsWallValid(Integer x, Integer y, Placement placement) {
        if ((x >= MATRIX_SIZE_X-1) || (y>= MATRIX_SIZE_Y-1)) {
            return false;
        }
        if (wallMatrix[x][y]==null) {
            if (placement.equals(Placement.Horizontal)) {
                return checkLeftRightPlacement(x,y);
            }
            if (placement.equals(Placement.Vertical)) {
                return checkUpDownPlacement(x,y);

            }
        } else {
            return false;
        }
        return false;
    }

    private Boolean checkUpDownPlacement(Integer x, Integer y) {
        boolean result = false;

        if (y>0) {
            if ((wallMatrix[x][y-1]==null) || (wallMatrix[x][y-1].equals(Placement.Horizontal))) {
                result = true;
            }
        } else {
            result = true;
        }
        if ((result) && (y<MATRIX_SIZE_Y-2)) {
            result = false;
            if ((wallMatrix[x][y+1]==null) || (wallMatrix[x][y+1].equals(Placement.Horizontal))) {
                result = true;
            }

        }

        return result;
    }

    private boolean checkLeftRightPlacement(Integer x, Integer y) {
        boolean result = false;

        if (x>0) {
            if ((wallMatrix[x-1][y]==null) || (wallMatrix[x-1][y].equals(Placement.Vertical))) {
                result = true;
            }
        } else {
            result = true;
        }
        if ((result) && (x<MATRIX_SIZE_X-2)) {
            result = false;
            if ((wallMatrix[x+1][y]==null) || (wallMatrix[x+1][y].equals(Placement.Vertical))) {
                result = true;
            }

        }

        return result;
    }


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
        InitializeWall();
    }

    private void InitializeWall() {
        wallMatrix = new Placement[MATRIX_SIZE_X-1][MATRIX_SIZE_Y-1];
    }

    private void InitializeConnections(MutableGraph graph) {
        for (int x = 0; x < MATRIX_SIZE_X; x=x+1) {
            for (int y = 0; y < MATRIX_SIZE_Y; y=y+1) {
                if( y < MATRIX_SIZE_Y - 1) {
                    Rebro downRebro = new Rebro(String.valueOf(x).concat(" ").concat(String.valueOf(y+1)), Placement.Vertical);
                    graph.addEdge(downRebro);
                    graph.connect(downRebro, matrixVershin[x][y]);
                    graph.connect(downRebro, matrixVershin[x][y + 1]);
                }

                if (x < MATRIX_SIZE_X -1) {
                    Rebro rightRebro = new Rebro(String.valueOf(x + 1).concat(" ").concat(String.valueOf(y+1)), Placement.Horizontal);
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

    private void switchPlayer() {
        currentPlayer.moveCount++;
        if (currentPlayer.equals(blackPlayer)) {
            currentPlayer = whitePlayer;
        } else {
            currentPlayer = blackPlayer;
        }
    }

    public String getCurrentPlayerColor() {
        return currentPlayer.equals(blackPlayer) ? "black"+blackPlayer.getX().toString()+blackPlayer.getY().toString() : "white"+whitePlayer.getX().toString()+whitePlayer.getY().toString();
    }

    public Placement[][] getWallMatrix() {
        return wallMatrix;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
