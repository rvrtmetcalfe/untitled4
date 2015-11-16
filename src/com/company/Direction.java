package com.company;

/**
 * Created by Rtmetcalfe on 11/9/2015.
 */
public enum Direction {
    NORTH(0, -1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);
    final int dx;
    final int dy;

    Direction(final int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction get(int dx, int dy) {
        if (dx == 0) {
            if (dy == -1) return NORTH;
            if (dy == 1) return SOUTH;
        }
        if (dy == 0) {
            if (dx == -1) return WEST;
            if (dx == 1) return EAST;
        }
        return null;
    }

    public int getDy() {
        return this.dy;
    }

    public int getDx() {
        return this.dx;
    }

    public Direction getOpposite(Direction direction) {
        switch (direction) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            default:
                return this;
        }
    }
}
