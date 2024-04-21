package de.pirckheimer_gymnasium.tetris;

import ea.Game;
import ea.Scene;
import ea.actor.Text;

public class MainScene extends Scene {

    public MainScene() {
        Text text = new Text("Hello, World!", 2);
        text.setCenter(0, 0);
        add(text);
        Game.setDebug(true);
    }
}
