package de.pirckheimer_gymnasium.tetris.scenes;

import de.pirckheimer_gymnasium.tetris.Image;
import de.pirckheimer_gymnasium.engine_pi.Scene;

public class BaseScene extends Scene
{
    Image background;

    public BaseScene(String imageFilename)
    {
        background = new Image("fullscreen/" + imageFilename + ".png");
        getCamera().setFocus(background);
        add(background);
    }
}
