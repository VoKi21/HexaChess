package chessgame.figures;

import chessgame.util.Color;
import chessgame.util.Position;

public class Rook extends Figure {
    public Rook(Position position, String color) {
        super(position, color);
    }

    @Override
    public boolean Move() {
        return false;
    }

    @Override
    public String getSymbol() {
        return color == Color.blackFigure() ? "\uDB82\uDC33" : "\uDB82\uDC5B";
    }

    @Override
    public Figures getType() {
        return Figures.ROOK;
    }
}
