package com.asaulyuk;

import org.apache.commons.graph.MutableGraph;
import org.apache.commons.graph.domain.basic.UndirectedGraphImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        MutableGraph graph = new UndirectedGraphImpl();
        Vershina v1 = new Vershina(1,1);
        Vershina v2 = new Vershina(1,2);
        Vershina v3 = new Vershina(2,1);
        Vershina v4 = new Vershina(2,2);

        for (int x = 1; x <= 9; x++) {
            for (int y = 1; y <= 9; y++) {
                graph.addVertex(new Vershina(x,y));
            }
        }

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        Rebro rebroH112 = new Rebro("AB1");
        graph.addEdge(rebroH112);
        graph.connect(rebroH112, v1);
        graph.connect(rebroH112, v2);

        Rebro rebroH212 = new Rebro("AB2");
        graph.addEdge(rebroH212);
        graph.connect(rebroH212, v3);
        graph.connect(rebroH212, v4);

        Rebro rebroV112 = new Rebro("A12");
        Rebro rebroV212 = new Rebro("B12");
        graph.addEdge(rebroV112);
        graph.addEdge(rebroV212);
        graph.connect(rebroV112, v1);
        graph.connect(rebroV112, v3);
        graph.connect(rebroV212, v2);
        graph.connect(rebroV212, v4);

//        graph.disconnect();


        for (Object r: graph.getEdges(v2)) {
            Rebro rebro = (Rebro) r;
            System.out.println(rebro.getAddress() +" :"+ rebro.getValid());
        }

	// write your code here
    }

    public void moveLeft(List<Moving> movingList) {
        movingList.forEach(moving -> moving.moveLeft());
    }
}
