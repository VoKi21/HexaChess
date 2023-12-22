package chessgame.figures;

import chessgame.util.Position;

public class FigureRecord {
    private final Figures type;
    private final int color;
    private final Position position;

    public FigureRecord(Figures type, int color, Position position) {
        this.type = type;
        this.color = color;
        this.position = position;
    }

    public Figures getType() {
        return type;
    }

    public int getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }
}
