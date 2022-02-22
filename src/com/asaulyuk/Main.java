package com.asaulyuk;

import com.asaulyuk.model.Placement;
import com.asaulyuk.model.QuoridorGameLogic;
import com.asaulyuk.model.Vershina;
import org.apache.commons.graph.MutableGraph;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        QuoridorGameLogic gameLogic = new QuoridorGameLogic();
        Scanner s = new Scanner(System.in);
        Integer personCount = 0;
        while(personCount<1 || personCount>2) {
            System.out.println("Chose mode:");
            System.out.println("1 - for Single user and PC");
            System.out.println("2 - for two users");
            personCount = s.nextInt();
        }
        gameLogic.InitializeGame(personCount);

        String choseColor="";
        while(!gameLogic.startGame(choseColor)) {
            System.out.println("Choose color 'white' or 'black':");
             choseColor = s.next();
        }


        Boolean wallResult = gameLogic.placeWall(3,0, Placement.Horizontal);
        System.out.println("Wall result:" + wallResult);
        System.out.println("Move result:" + gameLogic.movePlayerToXY("white", 5,0));
        System.out.println("Move result:" + gameLogic.movePlayerToXY("white", 5,1));
        System.out.println("Move result:" + gameLogic.movePlayerToXY("white", 4,1));
        System.out.println("Move result:" + gameLogic.movePlayerToXY("white", 4,0));
        System.out.println("Move result:" + gameLogic.movePlayerToXY("white", 3,1));
        System.out.println("Move result:" + gameLogic.movePlayerToXY("white", 3,0));
        System.out.println("Move result:" + gameLogic.movePlayerToXY("white", 2,1));
        System.out.println("Move result:" + gameLogic.movePlayerToXY("white", 2,0));


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
