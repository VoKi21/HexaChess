package chessgame.figures;

import chessgame.util.Position;

public class Queen extends Figure {
    public Queen(Position position, String color) {
        super(position, color);
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public Figures getType() {
        return Figures.QUEEN;
    }
}
