package de.pirckheimer_gymnasium.tetris.tetrominos;

import rocks.friedrich.engine_omega.Scene;

public class S extends Tetromino
{
    public S(Scene scene, Grid grid, int x, int y)
    {
        super(scene, grid, "S", x, y);
        addBlock(0, x, y);
        addBlock(1, x + 1, y);
        addBlock(2, x - 1, y - 1);
        addBlock(3, x, y - 1);
    }

    protected void doRotation()
    {
        switch (rotation)
        {
        // 0 -> 1
        // 2 -> 3
        case 1:
        case 3:
            moveBlock(1, -2, 0);
            moveBlock(2, 0, 2);
            break;

        // 3 -> 0
        // 1 -> 2
        case 0:
        case 2:
            moveBlock(1, 2, 0);
            moveBlock(2, 0, -2);
            break;
        }
    }
}
