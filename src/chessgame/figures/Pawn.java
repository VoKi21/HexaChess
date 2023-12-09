package chessgame.figures;

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
}
