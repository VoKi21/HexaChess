package chessgame.figures;

import chessgame.util.Position;

public class FreeCell extends Figure {
    private final Figures type = Figures.FREE;

    public FreeCell(Position position) {
        super(position, "");
    }

    public FreeCell() {
        super(null, "");
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
    public Figures getType() {
        return type;
    }
}
