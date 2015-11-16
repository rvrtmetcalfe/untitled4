package com.company;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Dungeon dungeon = new Dungeon();
        Point point = new Point(4,4);
        dungeon.setPlayerLoc(point);




        Book book1 = new Book("Book1"," ",true,false,false,"BOOK 1 THE BEGINNING","THIS IS THE FIRST BOOK THIS IS A TEST");

        dungeon.getPlayerRoom().addItem(book1);
        System.out.println(dungeon.getPlayerLoc());
        System.out.println(dungeon.getPlayerRoom().color);
        System.out.println(dungeon.getPlayerRoom().getSymbol());
        System.out.println(dungeon.getPlayerRoom().getNeighbors());


        Scanner keyboard = new Scanner(System.in);

        System.out.println("You enter a "+dungeon.getPlayerRoom().color+" room with "+dungeon.getPlayerRoom().orderNum+" "+dungeon.getPlayerRoom().symbol+" on the floor.");
        System.out.println("Items in this room "+dungeon.getPlayerRoom().getItem("book1").getItemDescription());

        System.out.println("Doors in this room :");
        for(Direction direction : Direction.values()) {
            if(dungeon.getPlayerRoom().getDoor(direction)!=null){
            System.out.println(dungeon.getPlayerRoom().getDoor(direction).getItemName());}
        }



    }
}

