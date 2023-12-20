package chessgame.figures;

import chessgame.Field;
import chessgame.Moves;
import chessgame.util.Color;
import chessgame.util.Directions;
import chessgame.util.Position;

public class Pawn extends Figure {
    private boolean untouched = true;

    public Pawn(Position position, String color) {
        super(position, color);
    }

    @Override
    public Figures getType() {
        return Figures.PAWN;
    }

    @Override
    public Moves getMoves(Field field) {
        Moves toReturn = new Moves(false);
        Moves attackingMoves = new Moves(true);

        Position curr = position;
        for (int i = 0; i < (untouched ? 2 : 1); i++) {
            curr = curr.move(color == Color.blackFigure() ? Directions.BELOW : Directions.ABOVE);
            Moves.CheckAndAddSolePosition(
                curr,
                field,
                toReturn,
                attackingMoves,
                color
            );
            if (toReturn.getMovesToOnePoint().isEmpty() || !attackingMoves.getMovesToOnePoint().isEmpty()) {
                break;
            }
        }
        Moves.CheckAndAddSolePosition(
                position.move(color == Color.blackFigure() ? Directions.BELOW : Directions.ABOVE),
                field,
                toReturn,
                attackingMoves,
                color
        );

        attackingMoves = new Moves(true);

        Directions[] attackingDirections = new Directions[2];
        if (color == Color.blackFigure()) {
            attackingDirections[0] = Directions.RIGHTBELOW;
            attackingDirections[1] = Directions.LEFTBELOW;
        } else {
            attackingDirections[0] = Directions.RIGHTABOVE;
            attackingDirections[1] = Directions.LEFTABOVE;
        }

        for (Directions direction : attackingDirections) {
            Position positionToAdd = position.move(direction);
            Figure figure = field.getFigureOnPosition(positionToAdd);
            if (figure.getType() != Figures.FREE && figure.getColor() != this.color) {
                attackingMoves.add(positionToAdd);
            }
        }

        if (attackingMoves.getMovesToOnePoint().isEmpty()) {
            return toReturn;
        } else {
            return attackingMoves;
        }
    }
}
