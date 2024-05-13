package de.pirckheimer_gymnasium.tetris.tetrominos;

import rocks.friedrich.engine_omega.Scene;

public class J extends Tetromino
{
    public J(Scene scene, int x, int y)
    {
        super(scene, "J", x, y);
        addBlock(0, x, y);
        addBlock(1, x - 1, y);
        addBlock(2, x + 1, y);
        addBlock(3, x + 1, y - 1);
    }

    protected void doRotation()
    {
        switch (rotation)
        {
            case 0:
                moveBlock(0, 0, 0);
                break;

            case 1:
                moveBlock(0, 0, 0);
                break;

            case 2:
                moveBlock(0, 0, 0);
                moveBlock(1, -1, 1);
                moveBlock(2, 1, -1);
                moveBlock(3, 2, -2);
                break;


            case 3:
                moveBlock(0, 0, 0);
                moveBlock(1, 1, -1);
                moveBlock(2, -1, 1);
                moveBlock(3, -2, 2);
                break;
        }

    }
}
