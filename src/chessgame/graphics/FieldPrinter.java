package chessgame.graphics;

import chessgame.Field;
import chessgame.figures.Figure;
import chessgame.util.Color;
import chessgame.util.Position;

public class FieldPrinter {
    public static void printField(Field field) {
        String[][] cellColors = new String[11][11];
        String[] colors = new String[]{Color.blackBackground(), Color.greyBackground(), Color.whiteBackground()};
        for (int y = 0; y < 11; y++) {
            int currentColor = y < 6 ? y % 3 : 2 - Math.abs((2 - y) % 3);
            for (int x = Field.minInLine(y); x <= Field.maxInLine(y); x++) {
                cellColors[x][y] = colors[currentColor];
                currentColor = (currentColor + 1) % 3;
            }
        }

        System.out.print("   ");
        for (int i = 0; i < 11; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        for (int y = 0; y < 11; y++) {
            System.out.print(y + " " + (y == 10 ? "" : " "));
            for (int i = 0; i < Field.minInLine(y); i++) {
                System.out.print("  ");
            }
            for (int x = Field.minInLine(y); x <= Field.maxInLine(y); x++) {
                Figure figure = field.getFigureOnPosition(new Position(x, y));
                System.out.printf("%c[%d;%sm%s ", (char) 27, figure.getColor(), cellColors[x][y], getSymbol(figure));
            }
            System.out.println((char) 27 + "[m");
        }
    }

    private static String getSymbol(Figure figure) {
        return switch (figure.getType()) {
            case PAWN   -> figure.getColor() == Color.blackFigure() ? "\uDB81\uDCE5" : "\uDB82\uDC59";
            case ROOK   -> figure.getColor() == Color.blackFigure() ? "\uDB82\uDC33" : "\uDB82\uDC5B";
            case HORSE  -> figure.getColor() == Color.blackFigure() ? "\uDB85\uDDBF" : "\uDB85\uDDC1";
            case QUEEN  -> figure.getColor() == Color.blackFigure() ? "\uE26E"       : "\uDB82\uDC5A";
            case BISHOP -> figure.getColor() == Color.blackFigure() ? "\uDB81\uDFC6" : "\uDB82\uDC5C";
            case KING   -> figure.getColor() == Color.blackFigure() ? "\uDB82\uDD53" : "\uDB82\uDC57";
            default -> " ";
        };
    }
}
