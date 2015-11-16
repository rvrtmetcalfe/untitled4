package com.company;


import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import java.util.Map;
import java.util.Random;

public class Maze {
    //Maze Size
    public int dimensionX, dimensionY;
    //Size of printed maze
    public int gridDimensionX, gridDimensionY;
    //Printed maze grid
    public char[][] grid;
    //Array of rooms
    public Room[][] rooms;
    //Randomizer
    public Random random = new Random();
    //Arraylist of visited Rooms
    public static ArrayList<Room> visitedRooms = new ArrayList<Room>();
    //Arraylist of neighbor rooms
    public static ArrayList<Room> neighborRooms = new ArrayList<Room>();
    //Hashmap of rooms in the order they are created
    public static HashMap<Integer, Room> orderMap = new HashMap<Integer, Room>();


    // Constructor for Maze based on set dimensions

    public Maze(int dimensionX, int dimensionY) {
        //Set the dimensions
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        //Set the grid dimensions
        gridDimensionX = dimensionX * 4 + 1;
        gridDimensionY = dimensionY * 2 + 1;
        //build the blank grid
        grid = new char[gridDimensionX][gridDimensionY];
        //initialize the maze
        init();
        //generate the maze
        generateMaze();
        //solve the maze
        solve(0,0,this.dimensionX,this.dimensionY);
    }
    //initializer
    private void init() {
        //Create the empty array of rooms based on the set dimensions
        rooms = new Room[dimensionX][dimensionY];

        //For loop for the rows and columns of the maze grid
        for (int x = 0; x < dimensionX; x++) {
            for (int y = 0; y < dimensionY; y++) {
                //Create the blank room for maze generation
                rooms[x][y] = new Room(x, y, false);
            }
        }
    }

    //Set default maze start point 0,0
    private void generateMaze() {
        generateMaze(0, 0);
    }
    // Specify point for maze generation
    private void generateMaze(int x, int y) {
        generateMaze(getRoom(x, y));
    }

    //Set maze generation based on starting room
    private void generateMaze(Room start) {
        // Check if starting room exists first
        if (start == null) return;
        // Stop room from being generated over
        start.open = false;
        //Create and arraylist of the rooms
        ArrayList<Room> rooms = new ArrayList<Room>();
        //add the start room to the list
        rooms.add(start);

        //Set loop for maze generation to start once rooms is initialized with start room
        while (!rooms.isEmpty()) {
            Room room;
            // force short room chains
            if (random.nextInt(6) == 0)
                room = rooms.remove(random.nextInt(rooms.size()));
            else room = rooms.remove(rooms.size() - 1);
            //Create arraylist of room neighbors
            ArrayList<Room> neighbors = new ArrayList<Room>();
            // Room adjacency test
            Room[] potentialNeighbors = new Room[]{
                    getRoom(room.x + 1, room.y),
                    getRoom(room.x, room.y + 1),
                    getRoom(room.x - 1, room.y),
                    getRoom(room.x, room.y - 1)
            };
            //Iterate through adjacent rooms
            for (Room other : potentialNeighbors) {
                //Check if the neighbors are walls, closed for generation or if they exist
                if (other == null || other.wall || !other.open) continue;
                neighbors.add(other);
            }
            //check if the room has neighbors  yet
            if (neighbors.isEmpty()) continue;
            //Get a random room neighbor
            Room selected = neighbors.get(random.nextInt(neighbors.size()));
            //Close it from further generation
            selected.open = false;
            //Add the room to the arraylists
            room.addNeighbor(selected);
            rooms.add(room);
            rooms.add(selected);
            addVisitedRooms(room);
            neighborRooms(selected);

        }
        //Create a hashmap of rooms based on order
        HashMap<Integer, Room> orderMap = new HashMap<Integer, Room>();
        int i = 1, j = 0;
        //iterate through list of visited rooms
        for (Room order : getVisitedRoom()) {
            //add the room to the hashmap setting its integer to its order value
            orderMap.put(i, order);
            //set the room order number to equal the generation order number
            order.setOrderNumber(i);
            i++;
        }
        //iterate through the hashmap
        for (Map.Entry<Integer, Room> entry : orderMap.entrySet()) {
            Room room = entry.getValue();
            //For each of the rooms neighbors iterate through to add doors
            for (i = 0; room.getNeighbors().size() > i; i++) {
                Room neighbors = room.getNeighbors().get(i);
                int x = neighbors.x - room.x;
                int y = neighbors.y - room.y;
                Door newDoor= new Door("A "+neighbors.getColor()+" Door with "+neighbors.getOrderNumber()+" "+neighbors.getSymbol()+" to the "+Direction.get(x,y),"A door",false,false,false,Direction.get(x,y),neighbors);
                room.addDoor(newDoor,Direction.get(x, y), neighbors);
                System.out.println(entry.getKey() + " : " + room + ":" + neighbors + Direction.get(x, y) + " : " + room.getNeighbors()+room.getColor()+" : "+room.getSymbol());
            }
        }

    }

    /**
    Method to add neighbor rooms
     */
    public static void neighborRooms(Room room) {
        neighborRooms.add(room);
    }
    /*
    Method to get the ordered hashmap
     */
    public static HashMap<Integer, Room> getOrderMap() {
        return orderMap;
    }
    /*
    Method to get the neighbor rooms arraylist
     */
    public static ArrayList<Room> getNeighborRooms() {
        return neighborRooms;
    }
    /*
    Method to add rooms to the visited rooms arraylist
    checking if a room has been added first before appending
     */
    public static void addVisitedRooms(Room room) {
        if (!visitedRooms.contains(room)){
            visitedRooms.add(room);}
    }
    /*
    Method to get arraylist of rooms
     */
    public static ArrayList<Room> getVisitedRoom() {
        return visitedRooms;
    }

    /*
    Method to get a specified room
    checking if room requested is a valid room
     */
    public Room getRoom(int x, int y) {
        try {
            return rooms[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
    /*
    Method to solve the maze
     */
    public void solve() {
        // Solve from top right to bottom left
        this.solve(0, 0, dimensionX - 1, dimensionY - 1);
    }

    /*
    Method to solve the maze from specified start point to specified endpoint
     */
    public void solve(int startX, int startY, int endX, int endY) {
        // Iterate through the rows of rooms, priming them to be solved
        for (Room[] roomrow : this.rooms) {
            for (Room room : roomrow) {
                room.parent = null;
                room.visited = false;
                room.inPath = false;
                room.travelled = 0;
                room.projectedDist = -1;
            }
        }
        //Rooms still open for solving
        ArrayList<Room> openRooms = new ArrayList<Room>();
        //Testing current room
        Room endRoom = getRoom(endX, endY);
        addVisitedRooms(endRoom);
        if (endRoom == null) return;
        {
            Room start = getRoom(startX, startY);
            if (start == null) return; // quit if start out of bounds
            start.projectedDist = getProjectedDistance(start, 0, endRoom);
            start.visited = true;
            openRooms.add(start);
        }
        boolean solving = true;
        while (solving) {
            if (openRooms.isEmpty()) return;
            // sort openRooms according to shortest projected distance
            Collections.sort(openRooms, new Comparator<Room>() {
                @Override
                public int compare(Room room1, Room room2) {
                    double diff = room1.projectedDist - room2.projectedDist;
                    if (diff > 0) return 1;
                    else if (diff < 0) return -1;
                    else return 0;
                }
            });
            Room current = openRooms.remove(0); // remove room least projectedDist
            if (current == endRoom) break; // at end
            for (Room neighbor : current.neighbors) {
                double projDist = getProjectedDistance(neighbor,
                        current.travelled + 1, endRoom);
                if (!neighbor.visited || // not visited yet
                        projDist < neighbor.projectedDist) { // shorter path
                    neighbor.parent = current;
                    neighbor.visited = true;
                    neighbor.projectedDist = projDist;
                    neighbor.travelled = current.travelled + 1;
                    if (!openRooms.contains(neighbor))
                        openRooms.add(neighbor);
                }
            }
        }
        // create path from end to beginning
        Room backtracking = endRoom;
        backtracking.inPath = true;
        while (backtracking.parent != null) {
            backtracking = backtracking.parent;
            backtracking.inPath = true;
        }
    }

    // get the projected distance
    public double getProjectedDistance(Room current, double travelled, Room end) {
        return travelled + Math.abs(current.x - end.x) +
                Math.abs(current.y - current.x);
    }

    //Create printable maze
    public void updateGrid() {
        char backChar = '-', wallChar = 'E', roomChar = ' ', pathChar = '-', doorOpen = '+', doorClosed = '=';
        // fill background
        for (int x = 0; x < gridDimensionX; x++) {
            for (int y = 0; y < gridDimensionY; y++) {
                grid[x][y] = backChar;
            }
        }
        // build walls
        for (int x = 0; x < gridDimensionX; x++) {
            for (int y = 0; y < gridDimensionY; y++) {
                if (x % 4 == 0 || y % 2 == 0)
                    grid[x][y] = wallChar;
            }
        }
        // make meaningful representation
        for (int x = 0; x < dimensionX; x++) {
            for (int y = 0; y < dimensionY; y++) {
                Room current = getRoom(x, y);
                int gridX = x * 4 + 2, gridY = y * 2 + 1;
                if (current.inPath) {
                    grid[gridX][gridY] = (char) (current.getOrderNumber() + 97);
                    if (current.isRoomBelowNeighbor())
                        if (getRoom(x, y + 1).inPath) {
                            grid[gridX][gridY + 1] = (char) (current.getOrderNumber() + 97);
                            ;

                        } else {
                            grid[gridX][gridY + 1] = pathChar;

                        }
                    if (current.isRoomRightNeighbor())
                        if (getRoom(x + 1, y).inPath) {
                            grid[gridX + 2][gridY] = pathChar;
                            grid[gridX + 1][gridY] = pathChar;
                            grid[gridX + 3][gridY] = pathChar;
                        } else {
                            grid[gridX + 2][gridY] = pathChar;
                            grid[gridX + 1][gridY] = pathChar;
                            grid[gridX + 3][gridY] = pathChar;
                        }
                } else {
                    grid[gridX][gridY] = roomChar;
                    if (current.isRoomBelowNeighbor()) {
                        grid[gridX][gridY + 1] = pathChar;

                    }
                    if (current.isRoomRightNeighbor()) {
                        grid[gridX + 2][gridY] = pathChar;
                        grid[gridX + 3][gridY] = pathChar;
                    }
                }
                grid[gridX][gridY] = (char) (current.getOrderNumber() + 97);

            }
        }}


    //Print the maze
    @Override
    public String toString() {
        updateGrid();
        String output = "";
        for (int y = 0; y < gridDimensionY; y++) {
            for (int x = 0; x < gridDimensionX; x++) {
                output += grid[x][y];
            }
            output += "\n";
        }
        return output;
    }

}