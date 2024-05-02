package de.pirckheimer_gymnasium.tetris.scenes;

import rocks.friedrich.engine_omega.Game;
import rocks.friedrich.engine_omega.Scene;

public class TitleScene extends BaseScene
{
    public TitleScene()
    {
        super("title");
    }

    public static void main(String[] args)
    {
        Scene scene = new TitleScene();
        Game.start(20 * 8 * 4, 18 * 8 * 4, scene);
    }
}
