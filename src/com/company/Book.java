package com.company;

/**
 * Created by robert on 11/16/15.
 */
public class Book extends Item {
    private final String title;
    private final String description;

    public Book(String itemName, String itemDescription, boolean addable, boolean lockable,boolean locked,String title, String description){
        super(itemName,"An old book I wonder what secrets it holds?"+"\nTitle: "+itemName+"" +
                "\n Description :"+itemDescription,true,false,false);
        this.title=title;
        this.description=description;
    }


}
