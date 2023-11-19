package chessgame.figures;

import chessgame.util.Color;
import chessgame.util.Position;

public class Bishop extends Figure {
    public Bishop(Position position, String color) {
        super(position, color);
    }

    @Override
    public boolean Move() {
        return false;
    }

    @Override
    public String getSymbol() {
        return color == Color.blackFigure() ? "\uDB81\uDFC6" : "\uDB82\uDC5C";
    }

    @Override
    public Figures getType() {
        return Figures.BISHOP;
    }
}
