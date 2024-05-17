package de.pirckheimer_gymnasium.tetris.tetrominos;

import rocks.friedrich.engine_omega.Game;
import rocks.friedrich.engine_omega.Scene;

public abstract class Tetromino
{
    private Scene scene;

    private Grid grid;

    private String name;

    private int x;

    private int y;

    private Block[] blocks;

    public int rotation;

    public Tetromino(Scene scene, Grid grid, String name, int x, int y)
    {
        this.scene = scene;
        this.grid = grid;
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
        for (Block blocks : blocks)
        {
            blocks.moveLeft();
        }
    }

    public void moveRight()
    {
        for (Block block : blocks)
        {
            block.moveRight();
        }
    }

    public void moveDown()
    {
        for (Block block : blocks)
        {
            block.moveDown();
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

    public static Tetromino create(Scene scene, Grid grid, String name, int x,
            int y)
    {
        switch (name)
        {
        case "L":
            return new L(scene, grid, x, y);

        case "J":
            return new J(scene, grid, x, y);

        case "I":
            return new I(scene, grid, x, y);

        case "O":
            return new O(scene, grid, x, y);

        case "Z":
            return new Z(scene, grid, x, y);

        case "S":
            return new S(scene, grid, x, y);

        case "T":
            return new T(scene, grid, x, y);

        default:
            return new L(scene, grid, x, y);
        }
    }
}
