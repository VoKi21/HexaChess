package chessgame.figures;

import chessgame.Field;
import chessgame.Moves;
import chessgame.util.BrittleList;
import chessgame.util.Directions;
import chessgame.util.Position;

public class Rook extends Figure {
    public Rook(Position position, String color) {
        super(position, color);
    }

    @Override
    public Figures getType() {
        return Figures.ROOK;
    }

    @Override
    public Moves getMoves(Field field) {
        Moves toReturn = new Moves(false, this);
        Moves attackingMoves = new Moves(true, this);

        for (int i = 0; i < 6; i++) {
            BrittleList<Position> sequenceToAdd = new BrittleList<>();

            Position positionToAdd = this.position;
            for (int j = 0; j < 12; j++) {
                switch (i) {
                    case 0 -> {
                        positionToAdd = positionToAdd
                                .move(Directions.ABOVE);
                    }
                    case 1 -> {
                        positionToAdd = positionToAdd
                                .move(Directions.LEFTABOVE);
                    }
                    case 2 -> {
                        positionToAdd = positionToAdd
                                .move(Directions.RIGHTABOVE);
                    }
                    case 3 -> {
                        positionToAdd = positionToAdd
                                .move(Directions.LEFTBELOW);
                    }
                    case 4 -> {
                        positionToAdd = positionToAdd
                                .move(Directions.RIGHTBELOW);
                    }
                    case 5 -> {
                        positionToAdd = positionToAdd
                                .move(Directions.BELOW);
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
