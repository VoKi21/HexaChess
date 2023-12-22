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
    public void setPosition(Position position) {
        if (untouched) {
            untouched = false;
        }
        super.setPosition(position);
    }

    @Override
    public Moves getMoves(Field field) {
        Moves toReturn = new Moves(false, this);
        Moves attackingMoves = new Moves(true, this);

        Position curr = position;
        for (int i = 0; i < (untouched ? 2 : 1); i++) {
            curr = curr.getMovedPosition(color == Color.blackFigure() ? Directions.BELOW : Directions.ABOVE);
            if (curr.getX() < 0) {
                continue;
            }
            if (field.getFigureOnPosition(curr).getType() == Figures.FREE) {
                toReturn.add(curr);
                continue;
            }
            if (toReturn.getMovesToOnePoint().isEmpty() || !attackingMoves.getMovesToOnePoint().isEmpty()) {
                break;
            }
        }

        attackingMoves = new Moves(true, this);

        Directions[] attackingDirections = new Directions[2];
        if (color == Color.blackFigure()) {
            attackingDirections[0] = Directions.RIGHTBELOW;
            attackingDirections[1] = Directions.LEFTBELOW;
        } else {
            attackingDirections[0] = Directions.RIGHTABOVE;
            attackingDirections[1] = Directions.LEFTABOVE;
        }

        for (Directions direction : attackingDirections) {
            Position positionToAdd = position.getMovedPosition(direction);
            Figure figure = field.getFigureOnPosition(positionToAdd);
            if (figure.getType() != Figures.FREE && figure.getColor() != this.color) {
                attackingMoves.add(positionToAdd);
            }
        }

        if (attackingMoves.count() == 0) {
            return toReturn;
        } else {
            return attackingMoves;
        }
    }
}
