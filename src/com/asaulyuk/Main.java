package com.asaulyuk;

import org.apache.commons.graph.Edge;
import org.apache.commons.graph.MutableGraph;
import org.apache.commons.graph.domain.basic.UndirectedGraphImpl;

import java.util.List;

public class Main {
    private static final Integer MATRIX_SIZE_X = 9;
    private static final Integer MATRIX_SIZE_Y = 9;

    public static void main(String[] args) {

        MutableGraph graph = new UndirectedGraphImpl();
        Vershina[][] matrix = new Vershina[MATRIX_SIZE_X][MATRIX_SIZE_Y];

        for (int x = 0; x < MATRIX_SIZE_X; x=x+1) {
            for (int y = 0; y < MATRIX_SIZE_Y; y=y+1) {
                Vershina currentVershina = new Vershina(x,y);
                matrix[x][y]= currentVershina;
                graph.addVertex(currentVershina);
            }
        }

        for (int x = 0; x < MATRIX_SIZE_X; x=x+1) {
            for (int y = 0; y < MATRIX_SIZE_Y; y=y+1) {
                if( y < MATRIX_SIZE_Y - 1) {
                    Rebro downRebro = new Rebro(String.valueOf(x).concat(" ").concat(String.valueOf(y+1)));
                    graph.addEdge(downRebro);
                    graph.connect(downRebro, matrix[x][y]);
                    graph.connect(downRebro, matrix[x][y + 1]);
                }

                if (x < MATRIX_SIZE_X -1) {
                    Rebro rightRebro = new Rebro(String.valueOf(x + 1).concat(" ").concat(String.valueOf(y+1)));
                    graph.addEdge(rightRebro);
                    graph.connect(rightRebro, matrix[x][y]);
                    graph.connect(rightRebro, matrix[x + 1][y]);
                }
            }
        }


        for(Object i: graph.getVertices()) {
            Vershina vershina = (Vershina) i;
            if (vershina.getY().equals(5)) {
                System.out.println("Vershina: x"+vershina.getX().toString() + " y:"+vershina.getY().toString());
                for (Object r: graph.getEdges(vershina)) {
                    graph.getVertices((Edge) r).forEach(v->{
                        Vershina vershina1 = (Vershina) v;
                                System.out.println("Connect: x:"+vershina1.getX() + " ----- y:"+vershina1.getY());
                            }

                    );

                    System.out.println(((Rebro)r).getAddress());

                }
                System.out.println("");
            }

        }
        System.out.println("Vershin: " + graph.getVertices().size() + " Reber:"+graph.getEdges().size());


	// write your code here
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
