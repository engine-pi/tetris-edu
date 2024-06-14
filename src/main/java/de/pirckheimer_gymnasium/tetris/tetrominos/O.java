package de.pirckheimer_gymnasium.tetris.tetrominos;

import de.pirckheimer_gymnasium.engine_pi.Scene;

public class O extends Tetromino
{
    public O(Scene scene, Grid grid, int x, int y)
    {
        super(scene, grid, "O", x, y);
        addBlock(0, x, y);
        addBlock(1, x + 1, y);
        addBlock(2, x, y - 1);
        addBlock(3, x + 1, y - 1);
    }

    protected void doRotation()
    {
        // keine Rotation notwendig bei O
    }
}
