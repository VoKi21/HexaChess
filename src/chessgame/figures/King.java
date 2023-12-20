package chessgame.figures;

import chessgame.Field;
import chessgame.Moves;
import chessgame.util.Directions;
import chessgame.util.Position;

public class King extends Figure {
    public King(Position position, String color) {
        super(position, color);
    }

    @Override
    public Figures getType() {
        return Figures.KING;
    }

    @Override
    public Moves getMoves(Field field) {
        Moves toReturn = new Moves(false, this);
        Moves attackingMoves = new Moves(true, this);

        for (int i = 0; i < 6; i++) {
            Position positionToAdd = this.position;
            switch (i / 2) {
                case 0 -> {
                    positionToAdd = positionToAdd
                            .getMovedPosition(Directions.LEFTABOVE)
                            .getMovedPosition(i % 2 == 0 ? Directions.LEFTBELOW : Directions.ABOVE);
                }
                case 1 -> {
                    positionToAdd = positionToAdd
                            .getMovedPosition(Directions.RIGHTABOVE)
                            .getMovedPosition(i % 2 == 0 ? Directions.ABOVE : Directions.RIGHTBELOW);
                }
                case 2 -> {
                    positionToAdd = positionToAdd
                            .getMovedPosition(Directions.BELOW)
                            .getMovedPosition(i % 2 == 0 ? Directions.RIGHTBELOW : Directions.LEFTBELOW);
                }
            }
            Moves.CheckAndAddSolePosition(positionToAdd, field, toReturn, attackingMoves, this.color);
        }

        for (Directions direction : Directions.values()) {
            Position positionToAdd = position.getMovedPosition(direction);
            Moves.CheckAndAddSolePosition(positionToAdd, field, toReturn, attackingMoves, this.color);
        }

        if (attackingMoves.count() == 0) {
            return toReturn;
        } else {
            return attackingMoves;
        }
    }
}
