package chessgame.util;

import chessgame.Field;

public class Position {
    private int x;
    private int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean move(Directions direction) {
        switch (direction) {
            case ABOVE -> {
                if (y == Field.minInLine(x)) {
                    return false;
                }
                y--;
            }
            case LEFTABOVE -> {
                if (y == Field.minInLine(x) || x == Field.minInLine(y)) {
                    return false;
                }
                y--;
                x--;
            }
            case LEFTBELOW -> {
                if (x == Field.minInLine(y)) {
                    return false;
                }
                x--;
            }
            case BELOW -> {
                if (y == Field.maxInLine(x)) {
                    return false;
                }
                y++;
            }
            case RIGHTBELOW -> {
                if (y == Field.maxInLine(x) || x == Field.maxInLine(y)) {
                    return false;
                }
                y++;
                x++;
            }
            case RIGHTABOVE -> {
                if (x == Field.maxInLine(y)) {
                    return false;
                }
                x++;
            }
        }
        return true;
    }
    public boolean equals(Position other) {
        return this.getX() == other.getX() && this.getY() == other.getY();
    }
}
