package chessgame.figures;

import chessgame.Field;
import chessgame.Moves;
import chessgame.util.Directions;
import chessgame.util.Position;

public class Horse extends Figure {
    public Horse(Position position, String color) {
        super(position, color);
    }

    @Override
    public Figures getType() {
        return Figures.HORSE;
    }

    @Override
    public Moves getMoves(Field field) {
        Moves toReturn = new Moves();
        Moves attackingMoves = new Moves();

        for (int i = 0; i < 6; i++) {

            Position positionToAdd = this.position;
            for (int j = 0; j < 2; j++) {
                switch (i / 2) {
                    case 0 -> {
                        positionToAdd = positionToAdd
                                .move(Directions.LEFTABOVE)
                                .move(i % 2 == 0 ? Directions.LEFTBELOW  : Directions.ABOVE);
                    }
                    case 1 -> {
                        positionToAdd = positionToAdd
                                .move(Directions.RIGHTABOVE)
                                .move(i % 2 == 0 ? Directions.ABOVE      : Directions.RIGHTBELOW);
                    }
                    case 2 -> {
                        positionToAdd = positionToAdd
                                .move(Directions.BELOW)
                                .move(i % 2 == 0 ? Directions.RIGHTBELOW : Directions.LEFTBELOW);
                    }
                }

                switch (i) {
                    case 0 -> {
                        positionToAdd = positionToAdd
                                .move(j == 0 ? Directions.LEFTBELOW : Directions.LEFTABOVE);
                    }
                    case 1 -> {
                        positionToAdd = positionToAdd
                                .move(j == 0 ? Directions.LEFTABOVE : Directions.ABOVE);
                    }
                    case 2 -> {
                        positionToAdd = positionToAdd
                                .move(j == 0 ? Directions.ABOVE : Directions.RIGHTABOVE);
                    }
                    case 3 -> {
                        positionToAdd = positionToAdd
                                .move(j == 0 ? Directions.RIGHTABOVE : Directions.RIGHTBELOW);
                    }
                    case 4 -> {
                        positionToAdd = positionToAdd
                                .move(j == 0 ? Directions.RIGHTBELOW : Directions.BELOW);
                    }
                    case 5 -> {
                        positionToAdd = positionToAdd
                                .move(j == 0 ? Directions.BELOW : Directions.LEFTBELOW);
                    }
                }

                if (positionToAdd.getX() > 0) {
                    continue;
                }
                if (field.getFigureOnPosition(positionToAdd).getType() == Figures.FREE) {
                    toReturn.add(positionToAdd);
                    continue;
                }
                if (field.getFigureOnPosition(positionToAdd).getColor() == this.color) {
                    continue;
                }
                toReturn.add(positionToAdd);
                attackingMoves.add(positionToAdd);
            }
        }
        if (attackingMoves.getMovesToOnePoint().isEmpty()) {
            return toReturn;
        } else {
            return attackingMoves;
        }
    }
}
