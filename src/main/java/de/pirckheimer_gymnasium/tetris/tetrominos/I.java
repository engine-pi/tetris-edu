package de.pirckheimer_gymnasium.tetris.tetrominos;

import de.pirckheimer_gymnasium.engine_pi.Scene;

public class I extends Tetromino
{
    public I(Scene scene, Grid grid, int x, int y)
    {
        super(scene, grid, "I", x, y);
        addBlock(0, x, y);
        addBlock(1, x - 1, y);
        addBlock(2, x + 1, y);
        addBlock(3, x + 2, y);
    }

    protected void doRotation()
    {
        switch (rotation)
        {
        // 3 -> 0
        // 1 -> 2
        case 0:
        case 2:
            moveBlock(0, 0, 0);
            moveBlock(1, -1, 1);
            moveBlock(2, 1, -1);
            moveBlock(3, 2, -2);
            break;

        // 0 -> 1
        // 2 -> 3
        case 1:
        case 3:
            moveBlock(0, 0, 0);
            moveBlock(1, 1, -1);
            moveBlock(2, -1, 1);
            moveBlock(3, -2, 2);
            break;
        }
    }
}
