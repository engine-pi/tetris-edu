package de.pirckheimer_gymnasium.tetris.tetrominos;

import rocks.friedrich.engine_omega.Scene;

public class Z extends Tetromino
{
    public Z(Scene scene, int x, int y)
    {
        super(scene, "Z", x, y);
        addBlock(0, x, y);
        addBlock(1, x - 1, y);
        addBlock(2, x, y - 1);
        addBlock(3, x + 1, y - 1);
    }

    protected void doRotation()
    {
    }
}
