import chessgame.Field;
import chessgame.graphics.FieldPrinter;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        boolean result = true;
        while (result) {
            FieldPrinter.printField(field);
            result = field.step();
        }
        FieldPrinter.printField(field);
    }
}