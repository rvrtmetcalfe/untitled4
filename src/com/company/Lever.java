package com.company;

/**
 * Created by robert on 11/16/15.
 */
public class Lever extends Item{
    private boolean isActivated;
    public Lever(String itemName, String itemDescription, boolean addable, boolean lockable,boolean locked,boolean isActivated){
        super(itemName, "A Lever I wonder what it does?",false,false,false);
        this.isActivated=isActivated;
    }
}


