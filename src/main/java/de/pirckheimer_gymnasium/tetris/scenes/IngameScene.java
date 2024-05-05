package de.pirckheimer_gymnasium.tetris.scenes;

import de.pirckheimer_gymnasium.tetris.Tetris;

public class IngameScene extends BaseScene
{
    public IngameScene()
    {
        super("ingame");
    }

    public static void main(String[] args)
    {
        Tetris.start(new IngameScene(), true);
    }
}
