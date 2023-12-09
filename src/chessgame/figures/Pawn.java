package chessgame.figures;

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
    public boolean move() {
        boolean success = false;
        for (int i = 0; i < (untouched ? 2 : 1); i++) {
            success = position.move(color == Color.blackFigure() ? Directions.BELOW : Directions.ABOVE);
        }
        if (untouched && success) {
            untouched = false;
        }
        return success;
    }
}
