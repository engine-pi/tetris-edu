package de.pirckheimer_gymnasium.tetris.scenes;

import de.pirckheimer_gymnasium.engine_pi.event.KeyStrokeListener;
import de.pirckheimer_gymnasium.tetris.Tetris;
import de.pirckheimer_gymnasium.tetris.tetrominos.Grid;
import de.pirckheimer_gymnasium.tetris.tetrominos.Tetromino;

import java.awt.event.KeyEvent;
import java.util.Random;

public class IngameScene extends BaseScene implements KeyStrokeListener {
    private Grid grid;

    private static final Random random = new Random();

    private int nextTetromino;

    private Tetromino tetromino;

    private Tetromino previewTetromino;

    public IngameScene() {
        super("ingame");
        grid = new Grid(Tetris.GRID_WIDTH, Tetris.HEIGHT + 1);
        createNextTetromino();
        repeat(0.2, () -> {
            moveDown();
        });
    }

    private void moveDown() {
        if (!tetromino.moveDown()) {
            createNextTetromino();
        }
    }

    private void createNextTetromino() {
        if (previewTetromino == null) {
            nextTetromino = random.nextInt(7);
        }
        tetromino = Tetromino.create(this, grid, nextTetromino, 4, 16);
        nextTetromino = random.nextInt(7);
        if (previewTetromino != null) {
            previewTetromino.remove();
        }
        previewTetromino = Tetromino.create(this, null, nextTetromino, 14, 3);
    }

    public void onKeyDown(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                tetromino.rotate();
                break;

            case KeyEvent.VK_LEFT:
                tetromino.moveLeft();
                break;

            case KeyEvent.VK_RIGHT:
                tetromino.moveRight();
                break;
        }
    }

    public static void main(String[] args) {
        Tetris.start(new IngameScene(), false);
    }
}
