package com.company;

/**
 * Created by robert on 11/16/15.
 */
/*
 Enum class to specify symbol
 */
public enum Symbols {
    DASHES('-'), DOTS('.'), STARS('*'), ARROWS('^'), LINES('|');

    private char sym;

    Symbols(char sym) {
        this.sym = sym;
    }

    /**
     * Method to check if a character is a symbol if it is return it
     * @param sym
     * @return the symbol
     */
    public static Symbols getSymbolsForSym(final char sym) {
        for (Symbols type : Symbols.values())
            if (type.sym == sym)
                return type;
        return null;
    }

    /**
     * Method to get a random symbol
     * @return the random symbol
     */
    public static Symbols getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }

};
