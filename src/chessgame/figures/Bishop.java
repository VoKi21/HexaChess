package chessgame.figures;

import chessgame.util.Position;

public class Bishop extends Figure {
    public Bishop(Position position, String color) {
        super(position, color);
    }

    @Override
    public Figures getType() {
        return Figures.BISHOP;
    }
}
