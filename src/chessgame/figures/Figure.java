package chessgame.figures;

import chessgame.util.Color;
import chessgame.util.Position;

import java.util.Objects;

public abstract class Figure {
    protected final int color;
    protected final Position position;

    public Figure(Position position, String color) {
        this.color = Objects.equals(color, "BLACK") ? Color.blackFigure() : Color.whiteFigure();
        this.position = position;
    }

    public abstract boolean Move();

    public int getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public abstract String getSymbol();

    public abstract Figures getType();
}
