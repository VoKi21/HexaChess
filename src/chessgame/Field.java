package chessgame;

import chessgame.figures.*;
import chessgame.util.Color;
import chessgame.util.Position;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final List<Figure> whiteFigures = new ArrayList<>();
    private final List<Figure> blackFigures = new ArrayList<>();

    private final Player whitePlayer = new Player(Color.whiteFigure());
    private final Player blackPlayer = new Player(Color.blackFigure());

    private boolean nowBlackStep = false;

    public Field() {
        for (int i = 0; i < 9; i++) {
            blackFigures.add(new Pawn(new Position(i + 1, Math.min(i, 4)), "BLACK"));
            whiteFigures.add(new Pawn(new Position(i + 1, Math.max(i + 2, 6)), "WHITE"));
        }

        blackFigures.add(new Rook(new Position(2, 0), "BLACK"));
        blackFigures.add(new Rook(new Position(8, 3), "BLACK"));
        whiteFigures.add(new Rook(new Position(2, 7), "WHITE"));
        whiteFigures.add(new Rook(new Position(8, 10), "WHITE"));

        blackFigures.add(new Horse(new Position(3, 0), "BLACK"));
        blackFigures.add(new Horse(new Position(7, 2), "BLACK"));
        whiteFigures.add(new Horse(new Position(3, 8), "WHITE"));
        whiteFigures.add(new Horse(new Position(7, 10), "WHITE"));

        blackFigures.add(new Queen(new Position(4, 0), "BLACK"));
        blackFigures.add(new King(new Position(6, 1), "BLACK"));
        whiteFigures.add(new Queen(new Position(4, 9), "WHITE"));
        whiteFigures.add(new King(new Position(6, 10), "WHITE"));

        for (int i = 0; i < 3; i++) {
            blackFigures.add(new Bishop(new Position(5, i), "BLACK"));
            whiteFigures.add(new Bishop(new Position(5, 10 - i), "WHITE"));
        }
    }

    public boolean step() {
        if (nowBlackStep) {
            blackPlayer.step(this);
            nowBlackStep = false;
        } else {
            whitePlayer.step(this);
            nowBlackStep = true;
        }
        return checkGameOver() == 0;
    }

    public void move(Figure figure, Position position) {
        figure.setPosition(position);
        System.out.println(figure.getColorName() + figure.getType() + " to " + position.getX() + "." + position.getY());
    }

    public void kill(Figure figure, Position position) {
        Figure killed = getFigureOnPosition(position);
        if (killed.getColor() == Color.blackFigure()) {
            blackFigures.remove(killed);
        } else {
            whiteFigures.remove(killed);
        }
        move(figure, position);
        System.out.println(figure.getColorName() + figure.getType() + " kills " + killed.getColorName() + killed.getType() + ".");
    }

    private int checkGameOver() { //0 - not a gameover, 1 - white, 2 - black
        boolean whiteHasKing = false;
        for (Figure figure : whiteFigures) {
            if (figure.getType() == Figures.KING) {
                whiteHasKing = true;
            }
        }
        boolean blackHasKing = false;
        for (Figure figure : blackFigures) {
            if (figure.getType() == Figures.KING) {
                blackHasKing = true;
            }
        }

        if (blackHasKing && whiteHasKing) {
            return 0;
        }
        if (whiteHasKing) {
            System.out.println("WHITE WIN");
            return 1;
        }
        System.out.println("BLACK WIN");
        return 2;
    }

    public Figure getFigureOnPosition(Position position) {
        for (Figure figure : whiteFigures) {
            if (figure.getPosition().equals(position)) {
                return figure;
            }
        }
        for (Figure figure : blackFigures) {
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

    public List<Figure> getWhiteFigures() {
        return whiteFigures;
    }

    public List<Figure> getBlackFigures() {
        return blackFigures;
    }
}
