package de.pirckheimer_gymnasium.tetris.scenes;

import rocks.friedrich.engine_omega.Scene;
import rocks.friedrich.engine_omega.actor.Text;

public class HelloWorldScene extends Scene
{
    public HelloWorldScene()
    {
        Text text = new Text("Hello, World!", 2);
        text.setCenter(0, 0);
        add(text);
    }
}
