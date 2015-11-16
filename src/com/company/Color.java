package com.company;

/**
 * Created by robert on 11/16/15.
 */
    /*
    Public color enum for colors
     */

public enum Color {
   RED("Red"),ORANGE("Orange"),BLUE("Blue"),GREEN("Green"),BLACK("Black"),YELLOW("Yellow"),PURPLE("Purple");

    private final String str;
    Color(String s){
        str=s;
    }
    public String toString(){
        return str;
    }

    public static Color getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }

};