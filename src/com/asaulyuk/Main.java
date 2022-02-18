package com.asaulyuk;

import com.asaulyuk.model.QuoridorGameLogic;
import com.asaulyuk.model.Rebro;
import com.asaulyuk.model.Vershina;
import org.apache.commons.graph.Edge;
import org.apache.commons.graph.MutableGraph;
import org.apache.commons.graph.domain.basic.UndirectedGraphImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        QuoridorGameLogic gameLogic = new QuoridorGameLogic();

        Boolean result = gameLogic.movePlayerToXY("black", 5,8);

        System.out.println("Move result:" + result);

//
//
//
//
//
//        for(Object i: gameLogic.getGraph().getVertices()) {
//            Vershina vershina = (Vershina) i;
//            if (vershina.getY().equals(5)) {
//                System.out.println("Vershina: x"+vershina.getX().toString() + " y:"+vershina.getY().toString());
//                for (Object r: gameLogic.getGraph().getEdges(vershina)) {
//                    gameLogic.getGraph().getVertices((Edge) r).forEach(v->{
//                        Vershina vershina1 = (Vershina) v;
//                                System.out.println("Connect: x:"+vershina1.getX() + " ----- y:"+vershina1.getY());
//                            }
//
//                    );
//
//                    System.out.println(((Rebro)r).getAddress());
//
//                }
//                System.out.println("");
//            }
//
//        }
//        System.out.println("Vershin: " + gameLogic.getGraph().getVertices().size() + " Reber:"+gameLogic.getGraph().getEdges().size());
//

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
