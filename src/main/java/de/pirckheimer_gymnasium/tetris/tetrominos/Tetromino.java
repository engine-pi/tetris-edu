package de.pirckheimer_gymnasium.tetris.tetrominos;

import rocks.friedrich.engine_omega.Scene;

public class Tetromino
{
    private Scene scene;

    private int x;

    private int y;

    public Tetromino(Scene scene, int x, int y)
    {
        this.scene = scene;
        this.x = x;
        this.y = y;
    }
}
