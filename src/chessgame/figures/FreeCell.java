package chessgame.figures;

import chessgame.Field;
import chessgame.Moves;
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

    @Override
    public Moves getMoves(Field field) {
        return null;
    }
}
