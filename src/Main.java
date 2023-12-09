import chessgame.Field;
import chessgame.graphics.FieldPrinter;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        FieldPrinter.printField(field);
    }
}