package de.pirckheimer_gymnasium.tetris.scenes;

import java.awt.event.KeyEvent;
import java.util.Random;

import de.pirckheimer_gymnasium.engine_pi.actor.Rectangle;
import de.pirckheimer_gymnasium.engine_pi.event.KeyStrokeListener;
import de.pirckheimer_gymnasium.engine_pi.event.PeriodicTaskExecutor;
import de.pirckheimer_gymnasium.engine_pi.event.PressedKeyRepeater;
import de.pirckheimer_gymnasium.tetris.Tetris;
import de.pirckheimer_gymnasium.tetris.tetrominos.FilledRowRange;
import de.pirckheimer_gymnasium.tetris.tetrominos.Grid;
import de.pirckheimer_gymnasium.tetris.tetrominos.SoftDrop;
import de.pirckheimer_gymnasium.tetris.tetrominos.Tetromino;
import de.pirckheimer_gymnasium.tetris.text.NumberDisplay;

public class IngameScene extends BaseScene implements KeyStrokeListener
{
    private Grid grid;

    private static final Random random = new Random();

    private int nextTetromino;

    private Tetromino tetromino;

    private Tetromino previewTetromino;

    private PressedKeyRepeater keyRepeater;

    private PeriodicTaskExecutor periodicTask;

    private SoftDrop softDrop;

    private final int[] GB_FRAMES_PER_ROW = { 53, 40, 41, 37, 33, 28, 22, 17,
            11, 10, 9, 8, 7, 6, 6, 5, 5, 4, 4, 3 };

    private final double GB_FRAME_RATE = 59.73;

    private NumberDisplay level;

    private NumberDisplay score;

    private NumberDisplay clearedLines;

    private boolean isInAnimation;

    public IngameScene()
    {
        super("ingame");
        grid = new Grid(Tetris.GRID_WIDTH, Tetris.HEIGHT + 1);
        score = new NumberDisplay(this, 13, 14, 4);
        level = new NumberDisplay(this, 12, 10, 4);
        clearedLines = new NumberDisplay(this, 12, 7, 4);
        createNextTetromino();
        Sound.playKorobeiniki();
        // Geschwindigkeit
        // des nach unten
        // Bewegens
        periodicTask = repeat(calculateDownInterval(), () -> {
            if (softDrop == null)
            {
                moveDown();
            }
        });
        keyRepeater = new PressedKeyRepeater();
        keyRepeater.addListener(KeyEvent.VK_LEFT, this::moveLeft);
        keyRepeater.addListener(KeyEvent.VK_RIGHT, this::moveRight);
        // final
        keyRepeater.addListener(KeyEvent.VK_DOWN,
                // initial
                () -> {
                    softDrop = new SoftDrop(tetromino);
                    // repeated
                }, this::moveDown, () -> {
                    softDrop = null;
                });
    }

    public void setScore(int lines)
    {
        int s = 40;
        if (lines == 2)
        {
            s = 100;
        }
        else if (lines == 3)
        {
            s = 300;
        }
        else if (lines == 4)
        {
            s = 1200;
        }
        clearedLines.add(lines);
        level.set(clearedLines.get() / 10);
        score.add(s * (level.get() + 1));
    }

    /**
     * inteval/ 53 = 1/ 59,73; -> interval = 1 / 59,73 * 53
     */
    private double calculateDownInterval()
    {
        return 1.0 / GB_FRAME_RATE * GB_FRAMES_PER_ROW[level.get()];
    }

    private void moveLeft()
    {
        if (isInAnimation)
        {
            return;
        }
        if (tetromino.moveLeft())
        {
            Sound.playBlockMove();
        }
    }

    private void moveRight()
    {
        if (isInAnimation)
        {
            return;
        }
        if (tetromino.moveRight())
        {
            Sound.playBlockMove();
        }
    }

    private void moveDown()
    {
        if (isInAnimation)
        {
            return;
        }
        Sound.playBlockMove();
        if (!tetromino.moveDown())
        {
            if (softDrop != null)
            {
                score.add(softDrop.getDistance());
            }
            keyRepeater.stop();
            Sound.playBlockDrop();
            softDrop = null;
            FilledRowRange range = grid.getFilledRowRange();
            if (range != null)
            {
                clearLines(range);
            }
            else
            {
                createNextTetromino();
            }
        }
    }

    public void rotate()
    {
        if (isInAnimation)
        {
            return;
        }
        if (tetromino.rotate())
        {
            Sound.playBlockRotate();
        }
    }

    private void clearLines(FilledRowRange range)
    {
        isInAnimation = true;
        Rectangle overlay = addRectangle(10, range.getRowCount(), 0,
                range.getFrom());
        overlay.setColor(Tetris.COLOR_SCHEME_GREEN.getLight());
        overlay.setVisible(false);
        periodicTask.pause();
        if (range.getRowCount() < 4)
        {
            Sound.playRowClear1to3();
        }
        else
        {
            Sound.playRowClear4();
        }
        repeat(0.167, 8, (counter) -> {
            switch (counter)
            {
            case 1:
            case 3:
            case 5:
                overlay.setVisible(true);
                break;

            case 2:
            case 4:
            case 6:
                overlay.setVisible(false);
                break;

            case 7:
                grid.removeFilledRowRange(range);
                break;

            case 8:
                grid.triggerLandslide(range);
                remove(overlay);
                createNextTetromino();
                periodicTask.resume();
                setScore(range.getRowCount());
                Sound.playBlockDrop();
                periodicTask.setInterval(calculateDownInterval());
                isInAnimation = false;
                break;
            }
        });
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
            rotate();
            break;
        }
    }

    public static void main(String[] args)
    {
        Tetris.start(new IngameScene(), false);
    }
}
