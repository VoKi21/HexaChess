package chessgame.figures;

import chessgame.util.Position;

public class Rook extends Figure {
    public Rook(Position position, String color) {
        super(position, color);
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public Figures getType() {
        return Figures.ROOK;
    }
}
