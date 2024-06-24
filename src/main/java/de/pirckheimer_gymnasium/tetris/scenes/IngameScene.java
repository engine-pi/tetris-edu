package de.pirckheimer_gymnasium.tetris.scenes;

import de.pirckheimer_gymnasium.engine_pi.event.KeyStrokeListener;
import de.pirckheimer_gymnasium.engine_pi.event.PressedKeyRepeater;
import de.pirckheimer_gymnasium.tetris.Tetris;
import de.pirckheimer_gymnasium.tetris.tetrominos.Grid;
import de.pirckheimer_gymnasium.tetris.tetrominos.Tetromino;

import java.awt.event.KeyEvent;
import java.util.Random;

public class IngameScene extends BaseScene implements KeyStrokeListener
{
    private Grid grid;

    private static final Random random = new Random();

    private int nextTetromino;

    private Tetromino tetromino;

    private Tetromino previewTetromino;

    private final int[] GB_FRAMES_PER_ROW = { 53, 49, 45, 41, 37, 33, 28, 22,
            17, 11, 10, 9, 8, 7, 6, 6, 5, 5, 4, 4, 3 };

    private final double GB_FRAME_RATE = 59.73;

    private int level = 0;

    private PressedKeyRepeater keyRepeater;

    public IngameScene()
    {
        super("ingame");
        grid = new Grid(Tetris.GRID_WIDTH, Tetris.HEIGHT + 1);
        createNextTetromino();
        repeat(calculateDownInterval(), () -> {
            moveDown();
        });
        keyRepeater = new PressedKeyRepeater(0.08, 0.15);
        keyRepeater.addListener(KeyEvent.VK_LEFT, () -> {
            tetromino.moveLeft();
        });
        keyRepeater = new PressedKeyRepeater(0.08, 0.15);
        keyRepeater.addListener(KeyEvent.VK_RIGHT, () -> {
            tetromino.moveRight();
        });
    }

    /**
     * interval / 53 = 1 / 59.73 -> interval = 1 / 59.73 * 53
     */
    private double calculateDownInterval()
    {
        return 1.0 / GB_FRAME_RATE * GB_FRAMES_PER_ROW[level];
    }

    private void moveDown()
    {
        if (!tetromino.moveDown())
        {
            createNextTetromino();
        }
    }

    private void createNextTetromino()
    {
        if (previewTetromino == null)
        {
            nextTetromino = random.nextInt(7);
        }
        tetromino = Tetromino.create(this, grid, nextTetromino, 4, 16);
        nextTetromino = random.nextInt(7);
        if (previewTetromino != null)
        {
            previewTetromino.remove();
        }
        previewTetromino = Tetromino.create(this, null, nextTetromino, 14, 3);
    }

    public void onKeyDown(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
        case KeyEvent.VK_SPACE:
            tetromino.rotate();
            break;
        }
    }

    public static void main(String[] args)
    {
        Tetris.start(new IngameScene(), false);
    }
}
