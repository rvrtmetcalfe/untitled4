package com.company;

/**
 * Created by robert on 11/16/15.
 */
public class Item {
    //The items name
    private String itemName;
    //The items description
    private String itemDescription;
    //the room the item is in
    private Room roomContainedIn;
    //can the player add the item to their inventory
    private final boolean addable;
    //can the item be locked
    private final boolean lockable;
    //is the item locked
    private boolean locked;



    //Item constructor
    public Item(String itemName,String itemDescription,boolean addable, boolean lockable,boolean locked){
        this.itemName=itemName;
        this.itemDescription=itemDescription;
        this.addable = addable;
        this.lockable = lockable;
        if (!lockable && locked){
            System.out.println("Programming Error: Item can't be locked if unlockable!");
            System.exit(0);
        } else {
            this.locked = locked;                                   //Set locked
        }
    }



    public String getItemName(){
        return itemName;
    }
    public String getItemDescription(){
        return itemDescription;
    }

    /**
     * Method to specify if item can be added to player inventory
     * @return true if it can be added to player inventory
     */
    public boolean isAddable(){
        return addable;
    }

    /**
     * Method to get the room the item is in
     * @return roomContainedIn the room the item is in
     */
    public Room getRoomContainedIn(){
        return roomContainedIn;
    }

    /**
     * Method to set the room the item is contained in
     * @param roomContainedIn the room the item is in
     */
    public void setRoomContainedIn(Room roomContainedIn){
        this.roomContainedIn=roomContainedIn;
    }
    /**
     Method to force unlock item
     */
    public void unlockItem(){
        locked = false;
    }

    /**
     * Method to check if item is locked
     * @return true if item is locked
     */
    public boolean itemIsLocked(){
        return locked;
    }

    /**
     * Method to the the room location of the item
     * @param roomLocation  where the item is
     */
    public void setRoomLocation(Room roomLocation) {
        this.roomContainedIn = roomLocation;
    }
}
