package chessgame.graphics;

import chessgame.Field;
import chessgame.History;
import chessgame.figures.Figure;
import chessgame.figures.FigureRecord;
import chessgame.figures.Figures;
import chessgame.util.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class HexagonalChessGUI extends JFrame {

    private BufferedImage boardImage;
    private final Field field;
    private final History history = new History();
    private boolean pause = false;

    public HexagonalChessGUI(Field field) {
        this.field = field;
        recordStep();

        try {
            boardImage = ImageIO.read(new File("resources/board1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Hexagonal Chess");
        setSize(boardImage.getWidth(), boardImage.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(boardImage, 0, 0, this);

                drawAllFigures(history.getCurrentSituation());
            }
        };

        add(boardPanel);

        final Timer[] timer = new Timer[1];

        timer[0] = new Timer(1000, e -> {
            if (history.inTheEnd() && !field.isGameOver()) {
                field.step();
                FieldPrinter.printField(field);
                recordStep();
            }
            history.next();
            redraw();
            if (field.isGameOver() && history.inTheEnd()) {
                timer[0].stop();
                pause = true;
                redraw();
            }
        });
        timer[0].start();

        JPanel buttonPanel = makeButtonPanel(field, timer);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel makeButtonPanel(Field field, Timer[] timer) {
        JButton nextStepButton = new JButton("Next Step");
        JButton playStopButton = new JButton("Stop");
        JButton prevStepButton = new JButton("Previous Step");

        nextStepButton.addActionListener(e -> {
            if (!pause) {
                return;
            }
            boolean hasNext = history.next();
            if (!hasNext && !field.isGameOver()) {
                field.step();
                recordStep();
                history.next();
                FieldPrinter.printField(field);
            }
            redraw();
        });

        playStopButton.addActionListener(e -> {
            if (history.inTheEnd() && field.isGameOver()) {
                pause = true;
                playStopButton.setText("Resume");
                System.out.println("eouthdi");
                return;
            }
            pause = !pause;
            if (pause) {
                playStopButton.setText("Resume");
                timer[0].stop();
            } else {
                playStopButton.setText("Stop");
                timer[0].start();
            }
        });

        prevStepButton.addActionListener(e -> {
            if (!pause) {
                return;
            }
            if (history.prev()) {
                redraw();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevStepButton);
        buttonPanel.add(playStopButton);
        buttonPanel.add(nextStepButton);
        return buttonPanel;
    }

    private void drawAllFigures(List<FigureRecord> figures) {
        for (FigureRecord figure : figures) {
            int x = figure.getPosition().getX() * 60 + 76; // Replace with actual x-coordinate calculation
            int y = figure.getPosition().getY() * 70 - figure.getPosition().getX() * 35 + 220; // Replace with actual y-coordinate calculation

            String pieceImageFileName = getImageFileName(figure.getColor(), figure.getType());
            BufferedImage pieceImage;
            try {
                pieceImage = ImageIO.read(new File("resources/" + pieceImageFileName));
                this.getGraphics().drawImage(pieceImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH), x, y, this);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void recordStep() {
        List<FigureRecord> situation = new ArrayList<>();
        for (Figure blackFigure : field.getBlackFigures()) {
            situation.add(blackFigure.getRecord());
        }
        for (Figure whiteFigure : field.getWhiteFigures()) {
            situation.add(whiteFigure.getRecord());
        }
        history.add(situation);
    }

    public void redraw() {
        this.getGraphics().drawImage(boardImage, 0, 0, this);

        drawAllFigures(history.getCurrentSituation());
    }

    private String getImageFileName(int color, Figures type) {
        return (color == Color.blackFigure() ? "black_" : "white_") + type.toString().toLowerCase() + ".png";
    }
}
