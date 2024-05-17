package de.pirckheimer_gymnasium.tetris.tetrominos;

import rocks.friedrich.engine_omega.Game;
import rocks.friedrich.engine_omega.Scene;

public abstract class Tetromino
{
    private Scene scene;

    private String name;

    private int x;

    private int y;

    private Block[] blocks;

    public int rotation;

    public Tetromino(Scene scene, String name, int x, int y)
    {
        this.scene = scene;
        this.name = name;
        this.x = x;
        this.y = y;
        blocks = new Block[4];
    }

    protected void addBlock(int index, int x, int y)
    {
        Block block;
        if (Game.isDebug())
        {
            block = new Block(this.scene, "Debug-" + index, x, y);
        }
        else
        {
            block = new Block(this.scene, name, x, y);
        }
        blocks[index] = block;
    }

    protected void moveBlock(int index, int dX, int dY)
    {
        blocks[index].moveBy(dX, dY);
    }

    public void moveLeft()
    {
        for(int i = 0; i < blocks.length; i++) {
            blocks[i].moveLeft();
    }
    }

    public void moveRight()
    {
        for(int i = 0; i < blocks.length; i++) {
            blocks[i].moveRight();
        }
    }

    public void moveDown() {
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].moveDown();
        }
    }
    protected abstract void doRotation();

    public void rotate()
    {
        if (rotation > 2)
        {
            rotation = 0;
        }
        else
        {
            rotation++;
        }
        doRotation();
    }

    public static Tetromino create(Scene scene, String name, int x, int y)
    {
        switch (name)
        {
        case "L":
            return new L(scene, x, y);

        case "J":
            return new J(scene, x, y);

        case "I":
            return new I(scene, x, y);

        case "O":
            return new O(scene, x, y);

        case "Z":
            return new Z(scene, x, y);

        case "S":
            return new S(scene, x, y);

        case "T":
            return new T(scene, x, y);

        default:
            return new L(scene, x, y);
        }
    }
}
