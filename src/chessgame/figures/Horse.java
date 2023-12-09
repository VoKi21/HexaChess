package chessgame.figures;

import chessgame.util.Position;

public class Horse extends Figure {
    public Horse(Position position, String color) {
        super(position, color);
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public Figures getType() {
        return Figures.HORSE;
    }
}
