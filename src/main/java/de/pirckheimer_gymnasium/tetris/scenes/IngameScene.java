package de.pirckheimer_gymnasium.tetris.scenes;

import rocks.friedrich.engine_omega.Game;
import rocks.friedrich.engine_omega.Scene;

public class IngameScene extends BaseScene
{
    public IngameScene()
    {
        super("ingame");
    }

    public static void main(String[] args)
    {
        Scene scene = new IngameScene();
        Game.start(20 * 8 * 4, 18 * 8 * 4, scene);
    }
}
