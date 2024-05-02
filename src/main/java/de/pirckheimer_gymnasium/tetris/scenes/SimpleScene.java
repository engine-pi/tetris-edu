package de.pirckheimer_gymnasium.tetris.scenes;

import de.pirckheimer_gymnasium.tetris.Image;
import rocks.friedrich.engine_omega.Game;
import rocks.friedrich.engine_omega.Scene;

public class SimpleScene extends Scene
{
    public SimpleScene()
    {
        Image image = new Image("fullscreen/title.png");
        getCamera().setFocus(image);
        add(image);
    }

    public static void main(String[] args)
    {
        Scene scene = new SimpleScene();
        Game.start(20 * 8 * 4, 18 * 8 * 4, scene);
    }
}
