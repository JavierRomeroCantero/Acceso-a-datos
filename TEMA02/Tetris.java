import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

public class Tetris extends JPanel {
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private static final int TILE_SIZE = 30;
    private Timer timer;
    private boolean isFallingFinished = false;
    private boolean isPaused = false;
    private int score = 0;
    private int curX = 0;
    private int curY = 0;
    private Shape curPiece;
    private Tetrominoe[] board;

    enum Tetrominoe { NoShape, ZShape, SShape, LineShape, TShape, SquareShape, LShape, MirroredLShape }

    public Tetris() {
        initBoard();
    }

    private void initBoard() {
        setFocusable(true);
        curPiece = new Shape();
        board = new Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];
        addKeyListener(new TAdapter());
        clearBoard();
        
        timer = new Timer(400, e -> gameCycle());
        timer.start();
        newPiece();
    }

    private void gameCycle() {
        update();
        repaint();
    }

    private void update() {
        if (isPaused) return;
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }

    private void clearBoard() {
        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {
            board[i] = Tetrominoe.NoShape;
        }
    }

    private Tetrominoe shapeAt(int x, int y) {
        return board[(y * BOARD_WIDTH) + x];
    }

    private void newPiece() {
        curPiece.setRandomShape();
        curX = BOARD_WIDTH / 2 + 1;
        curY = BOARD_HEIGHT - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {
            curPiece.setShape(Tetrominoe.NoShape);
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over! Puntuación: " + score);
            clearBoard();
            score = 0;
            timer.start();
            newPiece();
        }
    }

    private void oneLineDown() {
        if (!tryMove(curPiece, curX, curY - 1)) {
            pieceDropped();
        }
    }

    private void dropDown() {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1)) {
                break;
            }
            newY--;
        }
        pieceDropped();
    }

    private void pieceDropped() {
        for (int i = 0; i < 4; i++) {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BOARD_WIDTH) + x] = curPiece.getShape();
        }
        removeFullLines();
        if (!isFallingFinished) {
            newPiece();
        }
    }

    private boolean tryMove(Shape newPiece, int newX, int newY) {
        for (int i = 0; i < 4; i++) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
                return false;
            }
            if (shapeAt(x, y) != Tetrominoe.NoShape) {
                return false;
            }
        }
        curPiece = newPiece;
        curX = newX;
        curY = newY;
        repaint();
        return true;
    }

    private void removeFullLines() {
        int numFullLines = 0;
        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {
            boolean lineIsFull = true;
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (shapeAt(j, i) == Tetrominoe.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }
            if (lineIsFull) {
                numFullLines++;
                for (int k = i; k < BOARD_HEIGHT - 1; k++) {
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }
        if (numFullLines > 0) {
            score += numFullLines * 100;
            isFallingFinished = true;
            curPiece.setShape(Tetrominoe.NoShape);
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * TILE_SIZE;

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                Tetrominoe shape = shapeAt(j, BOARD_HEIGHT - i - 1);
                if (shape != Tetrominoe.NoShape) {
                    drawSquare(g, j * TILE_SIZE, boardTop + i * TILE_SIZE, shape);
                }
            }
        }

        if (curPiece.getShape() != Tetrominoe.NoShape) {
            for (int i = 0; i < 4; i++) {
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);
                drawSquare(g, x * TILE_SIZE, boardTop + (BOARD_HEIGHT - y - 1) * TILE_SIZE, curPiece.getShape());
            }
        }
        
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 20);
    }

    private void drawSquare(Graphics g, int x, int y, Tetrominoe shape) {
        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), new Color(102, 204, 102), 
                           new Color(102, 102, 204), new Color(204, 204, 102), new Color(204, 102, 204), 
                           new Color(102, 204, 204), new Color(218, 170, 0) };
        Color color = colors[shape.ordinal()];
        g.setColor(color);
        g.fillRect(x + 1, y + 1, TILE_SIZE - 2, TILE_SIZE - 2);
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (curPiece.getShape() == Tetrominoe.NoShape) return;
            int keycode = e.getKeyCode();
            switch (keycode) {
                case KeyEvent.VK_LEFT: tryMove(curPiece, curX - 1, curY); break;
                case KeyEvent.VK_RIGHT: tryMove(curPiece, curX + 1, curY); break;
                case KeyEvent.VK_DOWN: oneLineDown(); break;
                case KeyEvent.VK_UP: tryMove(curPiece.rotate(), curX, curY); break;
                case KeyEvent.VK_SPACE: dropDown(); break;
                case KeyEvent.VK_P: isPaused = !isPaused; break;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Java Tetris");
        Tetris game = new Tetris();
        frame.add(game);
        frame.setSize(BOARD_WIDTH * TILE_SIZE + 15, BOARD_HEIGHT * TILE_SIZE + 40);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

class Shape {
    private Tetris.Tetrominoe pieceShape;
    private int[][] coords;
    private int[][][] coordsTable;

    public Shape() {
        coords = new int[4][2];
        coordsTable = new int[][][] {
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
            { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
            { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
        };
        setShape(Tetris.Tetrominoe.NoShape);
    }

    public void setShape(Tetris.Tetrominoe shape) {
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 2; ++j) {
                coords[i][j] = coordsTable[shape.ordinal()][i][j];
            }
        }
        pieceShape = shape;
    }

    public void setRandomShape() {
        int x = Math.abs((int) (Math.random() * 7)) + 1;
        setShape(Tetris.Tetrominoe.values()[x]);
    }

    public Tetris.Tetrominoe getShape() { return pieceShape; }
    public int x(int index) { return coords[index][0]; }
    public int y(int index) { return coords[index][1]; }
    public int minY() {
        int m = coords[0][1];
        for (int i = 0; i < 4; i++) m = Math.min(m, coords[i][1]);
        return m;
    }

    public Shape rotate() {
        if (pieceShape == Tetris.Tetrominoe.SquareShape) return this;
        Shape result = new Shape();
        result.pieceShape = pieceShape;
        for (int i = 0; i < 4; ++i) {
            result.coords[i][0] = coords[i][1];
            result.coords[i][1] = -coords[i][0];
        }
        return result;
    }
}