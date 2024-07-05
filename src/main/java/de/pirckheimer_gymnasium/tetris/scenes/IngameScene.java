package de.pirckheimer_gymnasium.tetris.scenes;

import de.pirckheimer_gymnasium.engine_pi.actor.Rectangle;
import de.pirckheimer_gymnasium.engine_pi.event.KeyStrokeListener;
import de.pirckheimer_gymnasium.engine_pi.event.PeriodicTaskExecutor;
import de.pirckheimer_gymnasium.tetris.Tetris;
import de.pirckheimer_gymnasium.tetris.tetrominos.FilledRowRange;
import de.pirckheimer_gymnasium.tetris.tetrominos.Grid;
import de.pirckheimer_gymnasium.tetris.tetrominos.SoftDrop;
import de.pirckheimer_gymnasium.tetris.tetrominos.Tetromino;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import de.pirckheimer_gymnasium.engine_pi.event.PressedKeyRepeater;

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

    private int level = 0;

    private int score = 0;

    private int clearedLines = 0;

    public IngameScene()
    {
        super("ingame");
        grid = new Grid(Tetris.GRID_WIDTH, Tetris.HEIGHT + 1);
        createNextTetromino();
        periodicTask = repeat(calculateDownInterval(), () -> { // Geschwindigkeit
            // des nach unten
            // Bewegens
            moveDown();
        }); // Lambda Ausdruck
        keyRepeater = new PressedKeyRepeater();
        keyRepeater.addListener(KeyEvent.VK_LEFT, this::moveLeft);
        keyRepeater.addListener(KeyEvent.VK_RIGHT, this::moveRight);
        keyRepeater.addListener(KeyEvent.VK_DOWN,
                // initial
                () -> {
                    softDrop = new SoftDrop(tetromino);
                    // repeated
                }, () -> {
                    moveDown();
                    // final
                }, () -> {
                    softDrop = null;
                });
    }

    public void setScore(int lines)
    {
        clearedLines = clearedLines + lines;
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
        score = score + s * (lines + 1);
        // 0 - 9 = level 0; 10 - 19 = Level 1; ...
        level = clearedLines / 10;
    }

    /**
     * inteval/ 53 = 1/ 59,73; -> interval = 1 / 59,73 * 53
     */
    private double calculateDownInterval()
    {
        return 1.0 / GB_FRAME_RATE * GB_FRAMES_PER_ROW[level];
    }

    private void moveLeft()
    {
        if (tetromino.moveLeft())
        {
            Sound.playBlockMove();
        }
    }

    private void moveRight()
    {
        if (tetromino.moveRight())
        {
            Sound.playBlockMove();
        }
    }

    private void moveDown()
    {
        if (!tetromino.moveDown())
        {
            if (softDrop != null)
            {
                score = score + softDrop.getDistance();
            }
            FilledRowRange range = grid.getFilledRowRange();
            if (range != null)
            {
                grid.removeFilledRowRange(range);
                grid.triggerLandslide(range);
                setScore(range.getRowCount());
            }
            System.out.println("Score = " + score);
            System.out.println("Reihen = " + clearedLines);
            System.out.println("Level = " + level);
            createNextTetromino();
        }
    }

    private void clearLines(FilledRowRange range)
    {
        Rectangle overlay = addRectangle(10, range.getRowCount(), 0,
                range.getFrom());
        overlay.setColor(Color.GRAY);
        overlay.setVisible(false);
        periodicTask.pause();
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
                if (tetromino.rotate()) {
                    Sound.playBlockRotate();
                }
                break;
        }
    }

    public static void main(String[] args)
    {
        Tetris.start(new IngameScene(), false);
    }
}
