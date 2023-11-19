package chessgame;

import chessgame.figures.*;
import chessgame.util.Color;
import chessgame.util.Position;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final List<Figure> field = new ArrayList<>();
    private final String[][] cellColors = new String[11][11];

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

        String[] colors = new String[]{Color.blackBackground(), Color.greyBackground(), Color.whiteBackground()};
        for (int y = 0; y < 11; y++) {
            int currentColor = y < 6 ? y % 3 : 2 - Math.abs((2 - y) % 3);
            for (int x = minInLine(y); x <= maxInLine(y); x++) {
                cellColors[x][y] = colors[currentColor];
                currentColor = (currentColor + 1) % 3;
            }
        }
    }

    public Figure getFigureOnPosition(Position position) {
        for (Figure figure : field) {
            if (figure.getPosition().equals(position)) {
                return figure;
            }
        }
        return new FreeCell();
    }

    public void draw() {
        System.out.print("   ");
        for (int i = 0; i < 11; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        for (int y = 0; y < 11; y++) {
            System.out.print(y + " " + (y == 10 ? "" : " "));
            for (int i = 0; i < minInLine(y); i++) {
                System.out.print("  ");
            }
            for (int x = minInLine(y); x <= maxInLine(y); x++) {
                Figure figure = getFigureOnPosition(new Position(x, y));
                System.out.printf("%c[%d;%sm%s ", (char) 27, figure.getColor(), cellColors[x][y], figure.getSymbol());
            }
            System.out.println((char) 27 + "[m");
        }
    }

    public static int maxInLine(int line) {
        return Math.min(10, 5 + line);
    }

    public static int minInLine(int line) {
        return Math.max(0, line - 5);
    }
}
