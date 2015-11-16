package com.company;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robert on 11/16/15.
 * Class to generate the maze in and hold player location
 *
 */
public class Dungeon {

    private Point playerLoc = new Point();
    public Maze maze;

    public Dungeon(){
        maze = new Maze( 5,5);
        maze.solve();
        maze.updateGrid();
        System.out.println(maze);
        System.out.println("Address:" + Maze.visitedRooms.size());
        System.out.println("Address:" + Maze.neighborRooms.size());
    }

    public Room getPlayerRoom(){
        return maze.getRoom(playerLoc.x,playerLoc.y);
    }
    public Point getPlayerLoc(){
        return playerLoc;
    }
    public void setPlayerLoc(Point playerLoc) {
        this.playerLoc=playerLoc;
    }



}
