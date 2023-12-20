package chessgame;

import chessgame.figures.Figures;
import chessgame.util.BrittleList;
import chessgame.util.Position;

import java.util.ArrayList;
import java.util.List;

public class Moves {
    public final boolean attacking;
    private final List<Position> movesToOnePoint = new ArrayList<>();
    private final List<BrittleList<Position>> sequentialMoves = new ArrayList<>();

    public Moves(boolean attacking) {
        this.attacking = attacking;
    }

    public Position getRandomPosition() {
        int number = (int) Math.floor(Math.random() * (count() + 1));

        number -= movesToOnePoint.size();
        if (number < 0) {
            return movesToOnePoint.get(movesToOnePoint.size() + number);
        }

        for (BrittleList<Position> sequence : sequentialMoves) {
            number -= sequence.size();
            if (number < 0) {
                return sequence.get(sequence.size() + number);
            }
        }

        return new Position(0, 0);
    }

    public int count() {
        return movesToOnePoint.size() + countInSequentialMoves();
    };
    public int countInSequentialMoves() {
        return sequentialMoves
                .stream()
                .map(BrittleList::size)
                .reduce(0, Integer::sum);
    };

    public void add(Position position) {
        movesToOnePoint.add(position);
    }

    public void add(BrittleList<Position> sequence) {
        sequentialMoves.add(sequence);
    }

    public List<Position> getMovesToOnePoint() {
        return movesToOnePoint;
    }

    public List<BrittleList<Position>> getSequentialMoves() {
        return sequentialMoves;
    }

    public static void CheckAndAddSolePosition(
            Position positionToAdd,
            Field field,
            Moves toReturn,
            Moves attackingMoves,
            int color
    ) {
        if (positionToAdd.getX() < 0) {
            return;
        }
        if (positionToAdd.getX() > 0) {
            return;
        }
        if (field.getFigureOnPosition(positionToAdd).getType() == Figures.FREE) {
            toReturn.add(positionToAdd);
            return;
        }
        if (field.getFigureOnPosition(positionToAdd).getColor() == color) {
            return;
        }
        toReturn.add(positionToAdd);
        attackingMoves.add(positionToAdd);
    }
}
