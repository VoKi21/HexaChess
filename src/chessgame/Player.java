package chessgame;

import chessgame.figures.Figure;
import chessgame.util.Color;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public final int color;

    public Player(int color) {
        this.color = color;
    }

    public void step(Field field) {
        List<Moves> moves = getAllMoves(field);
        Moves randomMove = moves.get((int) (Math.random() * (moves.size() + 1)));
        if (randomMove.attacking) {
            field.kill(randomMove.figure, randomMove.getRandomPosition());
        } else {
            field.move(randomMove.figure, randomMove.getRandomPosition());
        }
    }

    public List<Moves> getAllMoves(Field field) {
        List<Moves> result = new ArrayList<>();
        for (Figure figure : getFigures(field)) {
            result.add(figure.getMoves(field));
        }

        if (
                result
                        .stream()
                        .anyMatch(moves -> moves.attacking)
        ) {
            result = result
                    .stream()
                    .filter(moves -> moves.attacking)
                    .toList();
        }

        return result;
    }

    public List<Figure> getFigures(Field field) {
        return (color == Color.blackFigure() ? field.getBlackFigures() : field.getWhiteFigures());
    }
}
