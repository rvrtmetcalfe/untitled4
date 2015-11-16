package com.company;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Rtmetcalfe on 11/9/2015.
 */

    public class Room {

        int orderNum;//Order of generated in maze algorithm.
        // coordinates
        int x, y;
        // List of adjacent connected rooms
        ArrayList<Room> neighbors = new ArrayList<Room>();
        // List of doors for connected rooms
        ArrayList<Door> doors = new ArrayList<Door>();
        // check if visited by maze solver
        boolean visited = false;
        // previous room visited by maze solver
        Room parent = null;
        //if used by maze solver in pending solution
        boolean inPath = false;
        // distance in rooms the solver has traversed
        double travelled;
        // solver estimated distance until the end
        double projectedDist;
        // Non traversable room
        boolean wall = true;
        // boolean to determine if used in generation yet
        boolean open = true;
        //Room color
        Color color;
        //Room symbol
        Symbols symbol;
        //Arraylist of room items
        ArrayList<Item> items = new ArrayList<Item>();

        // Construct Room at x, y
        Room(int x, int y) {
            this(x, y, true);
        }

        //Construct room at x, y and with whether it isWall set random color and symbol
        Room(int x, int y, boolean isWall) {
            this.x = x;
            this.y = y;
            this.wall = isWall;
            color=Color.getRandom();
            symbol=Symbols.getRandom();
        }
        /**
        Method to get room generation order number
         @return orderNum the rooms order number
         */
        public int getOrderNumber() {
            return orderNum;
        }

        /**
        Method to set the room generation order number
         @param i the rooms order generation number
         */
        public void setOrderNumber(int i) {
            orderNum = i;
        }
        /**
        Method to add neighbor rooms to eachothers lists of neighbors
         @param other the other room to be added to the list
         */
        void addNeighbor(Room other) {
            if (!this.neighbors.contains(other)) { // avoid duplicates
                this.neighbors.add(other);
            }
            if (!other.neighbors.contains(this)) { // avoid duplicates
                other.neighbors.add(this);
            }
        }

        /**
        Method to get the list of the current room's neighbors
         @return list of neighbors
         */
        public ArrayList<Room> getNeighbors() {
            return neighbors;
        }

        /**
        Method to check if a room is beneath a neighbor
         @return true or false
         */
        boolean isRoomBelowNeighbor() {
            return this.neighbors.contains(new Room(this.x, this.y + 1));
        }

        /**
        Method to check if a room is to the right of a neighbor
         @return true or false
         */
        boolean isRoomRightNeighbor() {
            return this.neighbors.contains(new Room(this.x + 1, this.y));
        }

        /**
        Method to parse room coords as strings
         @return formatted coordinates as string
         */
        @Override
        public String toString() {
            return String.format("Room(%s, %s)", x, y);
        }

        /**
        Method to check if room is the same
        @return true or false
         */
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Room)) return false;
            Room otherRoom = (Room) other;
            return (this.x == otherRoom.x && this.y == otherRoom.y);
        }

        /**
        Method to generate a hash code for room
         @return hashcode
         */
        @Override
        public int hashCode() {
            // random hash code method designed to be usually unique
            return this.x + this.y * 256;
        }
        /**
        Method to set rooms color
         @param color specified color
         */
        public void setColor(Color color){
            this.color=Color.getRandom();
        }
    /**
    Method to set rooms symbol
     @param symbol specified symbol
     */
        public void setSymbol(Symbols symbol){
            this.symbol=Symbols.getRandom();
        }


    /**
     * Method to get room color
     * @return rooms color
     */
        public Color getColor(){
            return color;
        }

    /**
     *  Method to get room symbol
     * @return rooms symbol
     */
        public Symbols getSymbol(){
            return symbol;
        }
    /**
    Method to add item to room
     @param item item to be added
     */
        public void addItem(Item item){
            items.add(item);
            item.setRoomLocation(this); //Tell item what room this is
        }
    /**
     * Method to remove item from room
     * @param item  remove item from the room
     */
    public void removeItem(Item item){
        items.remove(item);
        item.setRoomLocation(null);
    }

    /**
     Method to get an item from room
     * @param item  string of items name
     * @return item or null
     */
    public Item getItem(String item){
        //check the rooms items for matching item name
        for(Item i : items){
            if(i.getItemName().equalsIgnoreCase(item)){
                return i;
            }
        }
        //if not present
        return null;
    }


    /**
    Method to add a door to the room.
     @param newDoor The door object to be added
     @param direction the direction the door is in the room
     @param roomTo the room the door leads to
     */
    public void addDoor(Door newDoor,Direction direction,Room roomTo){
        //Check if a door exists for the set direction already
        for(Door d : doors){
            if(d.getDirection().equals(direction)){
                return;
            }
        }
        //add the door and set its direction and roomTo
        newDoor.setDirection(direction);
        newDoor.setRoomTo(roomTo);
        doors.add(newDoor);
    }

    /**
     * Method to get list of rooms doors
     * @return doors the list of doors
     */
    public ArrayList<Door> getDoors(){
        return doors;
    }

    /**
     * Method to get a specific door based on direction
     * @param direction the direction the door is in
     * @return the door if it exists in the specified direction
     */
    public Door getDoor(Direction direction){
        for(Door d: doors){
            if(d.getDirection().equals(direction)){
                return d;
            }
        }
        return null;
    }


}










