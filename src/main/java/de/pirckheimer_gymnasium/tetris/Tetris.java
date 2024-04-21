package de.pirckheimer_gymnasium.tetris;

import ea.Game;
import ea.Scene;

public class Tetris {

    public static void main(String[] args) {
        Scene scene = new MainScene();
        Game.start(400, 300, scene);
    }

}
