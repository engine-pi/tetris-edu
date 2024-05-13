package de.pirckheimer_gymnasium.tetris.tetrominos;

import rocks.friedrich.engine_omega.Scene;

public class L extends Tetromino
{
    public L(Scene scene, int x, int y)
    {
        super(scene, "L", x, y);
        addBlock(0, x, y);
        addBlock(1, x - 1, y);
        addBlock(2, x + 1, y);
        addBlock(3, x - 1, y - 1);
    }

    protected void doRotation()
    {
        switch (rotation)
        {
        // 0 -> 1
        case 1:
            moveBlock(1, 1, 1);
            moveBlock(2, -1, -1);
            moveBlock(3, 0, 2);
            break;

        // 1 -> 2
        case 2:
            moveBlock(1, 1, -1);
            moveBlock(2, -1, 1);
            moveBlock(3, 2, 0);
            break;

        // 2 -> 3
        case 3:
            moveBlock(1, -1, -1);
            moveBlock(2, 1, 1);
            moveBlock(3, 0, -2);
            break;

        // 3 -> 0
        case 0:
            moveBlock(1, -1, 1);
            moveBlock(2, 1, -1);
            moveBlock(3, -2, 0);
            break;
        }
    }
}
