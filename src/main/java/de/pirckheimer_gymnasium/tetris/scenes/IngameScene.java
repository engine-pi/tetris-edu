package de.pirckheimer_gymnasium.tetris.scenes;

import de.pirckheimer_gymnasium.tetris.Tetris;
import de.pirckheimer_gymnasium.tetris.tetrominos.Grid;
import de.pirckheimer_gymnasium.tetris.tetrominos.Tetromino;

import java.util.Random;

public class IngameScene extends BaseScene
{
    private Grid grid;

    private static final Random random = new Random();

    private int nextTetromino;

    private Tetromino tetromino;

    private Tetromino previewTetromino;

    public IngameScene()
    {
        super("ingame");
    }

    private void createNextTetromino()
    {
        if (previewTetromino == null)
        {
            nextTetromino = random.nextInt(7);
        }
        tetromino = Tetromino.create(this, grid, nextTetromino, 4, 16);
        nextTetromino = random.nextInt(7);

        if (previewTetromino != null) {
            previewTetromino.remove();
        }

        previewTetromino = Tetromino.create(this, null, nextTetromino, 14, 3);

    }

    public static void main(String[] args)
    {
        Tetris.start(new IngameScene(), true);
    }
}
