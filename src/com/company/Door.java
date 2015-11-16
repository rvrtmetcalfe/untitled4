package com.company;

/**
 * Created by robert on 11/16/15.
 */
public class Door extends Item {
    //What room does this door lead to
    private Room roomTo;
    //What direction is it in
    private Direction direction;
    //Door Constructor
    public Door(String itemName, String itemDescription, boolean addable, boolean lockable, boolean locked,Direction direction, Room roomTo){
        super(itemName, itemDescription,false,true,false);
        this.direction=direction;
        this.roomTo=roomTo;
    }

    /**
     * Method to get the door direction
     * @return direction the door's direction
     */
    public Direction getDirection(){
        return direction;
    }

    /**
     * Method to set the room the door leads to
     * @param roomTo the room the door leads to
     */
    public void setRoomTo(Room roomTo){
        this.roomTo=roomTo;
    }

    /**
     * Method to set the direction of the room
     * @param direction the direction the room is in
     */
    public void setDirection(Direction direction){
        this.direction=direction;
    }

}
