package chessgame.figures;

import chessgame.util.Color;
import chessgame.util.Position;

public class King extends Figure {
    public King(Position position, String color) {
        super(position, color);
    }

    @Override
    public boolean Move() {
        return false;
    }

    @Override
    public String getSymbol() {
        return color == Color.blackFigure() ? "\uDB82\uDD53" : "\uDB82\uDC57";
    }

    @Override
    public Figures getType() {
        return Figures.KING;
    }
}
