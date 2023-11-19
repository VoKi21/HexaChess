package chessgame.figures;

import chessgame.util.Color;
import chessgame.util.Position;

public class Horse extends Figure {
    public Horse(Position position, String color) {
        super(position, color);
    }

    @Override
    public boolean Move() {
        return false;
    }

    @Override
    public String getSymbol() {
        return color == Color.blackFigure() ? "\uDB85\uDDBF" : "\uDB85\uDDC1";
    }

    @Override
    public Figures getType() {
        return Figures.HORSE;
    }
}
