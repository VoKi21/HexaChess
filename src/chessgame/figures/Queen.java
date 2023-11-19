package chessgame.figures;

import chessgame.util.Color;
import chessgame.util.Position;

public class Queen extends Figure {
    public Queen(Position position, String color) {
        super(position, color);
    }

    @Override
    public boolean Move() {
        return false;
    }

    @Override
    public String getSymbol() {
        return color == Color.blackFigure() ? "\uE26E" : "\uDB82\uDC5A";
    }

    @Override
    public Figures getType() {
        return Figures.QUEEN;
    }
}
