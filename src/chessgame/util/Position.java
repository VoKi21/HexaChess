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

    public void move(Position newPosition) {
        this.x = newPosition.getX();
        this.y = newPosition.getY();
    }
    public Position getMovedPosition(Directions direction) {
        switch (direction) {
            case ABOVE -> {
                if (!(y == Field.minInLine(x))) {
                    return new Position(x, y - 1);
                }
            }
            case LEFTABOVE -> {
                if (!(y == Field.minInLine(x) || x == Field.minInLine(y))) {
                    return new Position(x - 1, y - 1);
                }
            }
            case LEFTBELOW -> {
                if (!(x == Field.minInLine(y))) {
                    return new Position(x - 1, y);
                }
            }
            case BELOW -> {
                if (!(y == Field.maxInLine(x))) {
                    return new Position(x, y + 1);
                }
            }
            case RIGHTBELOW -> {
                if (!(y == Field.maxInLine(x) || x == Field.maxInLine(y))) {
                    return new Position(x + 1, y + 1);
                }
            }
            case RIGHTABOVE -> {
                if (!(x == Field.maxInLine(y))) {
                    return new Position(x + 1, y);
                }
            }
        }
        return new Position(-20, -20);
    }

    public boolean equals(Position other) {
        return this.getX() == other.getX() && this.getY() == other.getY();
    }
}
