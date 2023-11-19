package chessgame.figures;

import chessgame.util.Position;

public class FreeCell extends Figure {
    private final Figures type = Figures.FREE;

    public FreeCell() {
        super(null, "");
    }

    @Override
    public boolean Move() {
        return false;
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public String getSymbol() {
        return " ";
    }

    @Override
    public Figures getType() {
        return type;
    }
}
