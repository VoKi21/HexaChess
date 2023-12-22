import chessgame.Field;
import chessgame.graphics.HexagonalChessGUI;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        new HexagonalChessGUI(field);
    }
}