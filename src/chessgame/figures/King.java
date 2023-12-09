package chessgame.figures;

import chessgame.util.Position;

public class King extends Figure {
    public King(Position position, String color) {
        super(position, color);
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public Figures getType() {
        return Figures.KING;
    }
}
