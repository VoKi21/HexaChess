package chessgame;

import chessgame.figures.*;
import chessgame.util.Position;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final List<Figure> field = new ArrayList<>();

    public Field() {
        for (int i = 0; i < 9; i++) {
            field.add(new Pawn(new Position(i + 1, Math.min(i, 4)), "BLACK"));
            field.add(new Pawn(new Position(i + 1, Math.max(i + 2, 6)), "WHITE"));
        }

        field.add(new Rook(new Position(2, 0), "BLACK"));
        field.add(new Rook(new Position(8, 3), "BLACK"));
        field.add(new Rook(new Position(2, 7), "WHITE"));
        field.add(new Rook(new Position(8, 10), "WHITE"));

        field.add(new Horse(new Position(3, 0), "BLACK"));
        field.add(new Horse(new Position(7, 2), "BLACK"));
        field.add(new Horse(new Position(3, 8), "WHITE"));
        field.add(new Horse(new Position(7, 10), "WHITE"));

        field.add(new Queen(new Position(4, 0), "BLACK"));
        field.add(new King(new Position(6, 1), "BLACK"));
        field.add(new Queen(new Position(4, 9), "WHITE"));
        field.add(new King(new Position(6, 10), "WHITE"));

        for (int i = 0; i < 3; i++) {
            field.add(new Bishop(new Position(5, i), "BLACK"));
            field.add(new Bishop(new Position(5, 10 - i), "WHITE"));
        }
    }

    public Figure getFigureOnPosition(Position position) {
        for (Figure figure : field) {
            if (figure.getPosition().equals(position)) {
                return figure;
            }
        }
        return new FreeCell(position);
    }

    public static int maxInLine(int line) {
        return Math.min(10, 5 + line);
    }

    public static int minInLine(int line) {
        return Math.max(0, line - 5);
    }
}
