package chessgame.figures;

import chessgame.Field;
import chessgame.Moves;
import chessgame.util.BrittleList;
import chessgame.util.Directions;
import chessgame.util.Position;

public class Bishop extends Figure {
    public Bishop(Position position, String color) {
        super(position, color);
    }

    @Override
    public Figures getType() {
        return Figures.BISHOP;
    }

    @Override
    public Moves getMoves(Field field) {
        Moves toReturn = new Moves();
        Moves attackingMoves = new Moves();
        for (int i = 0; i < 6; i++) {
            BrittleList<Position> sequenceToAdd = new BrittleList<>();

            Position positionToAdd = this.position;
            for (int j = 0; j < 5; j++) {
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
                if (positionToAdd.getX() < 0) {
                    break;
                }
                sequenceToAdd.add(positionToAdd);
            }

            for (int j = 0; j < sequenceToAdd.size(); j++) {
                Position positionInSequence = sequenceToAdd.get(j);
                Figure figureOnPosition = field.getFigureOnPosition(positionInSequence);
                if (figureOnPosition.getType() == Figures.FREE) {
                    continue;
                }
                if (figureOnPosition.getColor() == this.color) {
                    sequenceToAdd.remove(j);
                    break;
                } else {
                    attackingMoves.add(positionInSequence);
                    if (j + 1 < sequenceToAdd.size()) {
                        sequenceToAdd.remove(j + 1);
                    }
                }
            }

            if (!sequenceToAdd.isEmpty()) {
                toReturn.add(sequenceToAdd);
            }
        }
        if (attackingMoves.getMovesToOnePoint().isEmpty()) {
            return toReturn;
        } else {
            return attackingMoves;
        }
    }
}
