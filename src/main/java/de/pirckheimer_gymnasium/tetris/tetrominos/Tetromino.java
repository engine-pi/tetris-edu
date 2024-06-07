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


    protected boolean isOwnBlockPosition(int x, int y) {
        for (Block block : blocks)
        {

            if (block.getY() == y && block.getX() == x) {
                return true;
            }
        }
        return false;
    }


    protected boolean isGridTaken(int x, int y) {
        if (grid == null) {
            return false;
        }

        return !isOwnBlockPosition(x, y) && grid.isTaken(x, y);
    }

   protected void addBlocksToGrid() {
       if (grid == null) {
           return;
       }
        for (Block block : blocks)
       {
           grid.addBlock(block);
       }
    }

    protected void removeBlocksFromGrid() {
        if (grid == null) {
            return;
        }
        for (Block block : blocks)
        {
            grid.removeBlock(block);
        }
    }

    protected boolean checkLeft() {
        for (Block block : blocks)
        {
            if (isGridTaken(block.getX() - 1, block.getY())) {
                return false;
            }
        }
        return true;
    }

    public boolean moveLeft()
    {
        if (!checkLeft()) {
            return false;
        }
        removeBlocksFromGrid();
        for (Block block : blocks)
        {
            block.moveLeft();
        }
        addBlocksToGrid();
        x--;
        return true;
    }

    protected boolean checkRight() {
        for (Block block : blocks)
        {
            if (isGridTaken(block.getX() + 1, block.getY())) {
                return false;
            }
        }
        return true;
    }

    public boolean moveRight()
    {
        if (!checkRight()) {
            return false;
        }
        removeBlocksFromGrid();
        for (Block block : blocks)
        {
            block.moveRight();
        }
        x++;
        addBlocksToGrid();
        return true;
    }

    protected boolean checkDown() {
        for (Block block : blocks)
        {
            if (isGridTaken(block.getX(), block.getY() - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean moveDown()
    {
        if (!checkDown()) {
            return false;
        }
        removeBlocksFromGrid();
        for (Block block : blocks)
        {
            block.moveDown();
        }
        y--;
        addBlocksToGrid();
        return true;
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
