package de.pirckheimer_gymnasium.tetris.tetrominos;

import de.pirckheimer_gymnasium.engine_pi.Game;
import de.pirckheimer_gymnasium.engine_pi.Scene;

public abstract class Tetromino
{
    // names[0]
    private static String[] names = { "L", "J", "I", "O", "Z", "S", "T" };

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

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
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

    protected boolean isOwnBlockPosition(int x, int y)
    {
        for (Block block : blocks)
        {
            if (block.getY() == y && block.getX() == x)
            {
                return true;
            }
        }
        return false;
    }

    protected boolean isGridTaken(int x, int y)
    {
        if (grid == null)
        {
            return false;
        }
        return !isOwnBlockPosition(x, y) && grid.isTaken(x, y);
    }

    protected void addBlocksToGrid()
    {
        if (grid == null)
        {
            return;
        }
        for (Block block : blocks)
        {
            grid.addBlock(block);
        }
    }

    protected void removeBlocksFromGrid()
    {
        if (grid == null)
        {
            return;
        }
        for (Block block : blocks)
        {
            grid.removeBlock(block);
        }
    }

    protected boolean checkLeft()
    {
        for (Block block : blocks)
        {
            if (isGridTaken(block.getX() - 1, block.getY()))
            {
                return false;
            }
        }
        return true;
    }

    public boolean moveLeft()
    {
        if (!checkLeft())
        {
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

    protected boolean checkRight()
    {
        for (Block block : blocks)
        {
            if (isGridTaken(block.getX() + 1, block.getY()))
            {
                return false;
            }
        }
        return true;
    }

    public boolean moveRight()
    {
        if (!checkRight())
        {
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

    protected boolean checkDown()
    {
        for (Block block : blocks)
        {
            if (isGridTaken(block.getX(), block.getY() - 1))
            {
                return false;
            }
        }
        return true;
    }

    public boolean moveDown()
    {
        if (!checkDown())
        {
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

    private boolean checkRotation()
    {
        for (int x = getX() - 1; x <= getX() + 1; x++)
        {
            for (int y = getY() - 1; y <= getY() + 1; y++)
            {
                if (isGridTaken(x, y))
                {
                    return false;
                }
            }
        }
        return true;
    }

    protected abstract void doRotation();

    public boolean rotate()
    {
        if (!checkRotation())
        {
            return false;
        }
        if (rotation > 2)
        {
            rotation = 0;
        }
        else
        {
            rotation++;
        }
        removeBlocksFromGrid();
        doRotation();
        addBlocksToGrid();
        return true;
    }

    public void remove()
    {
        for (Block block : blocks)
        {
            block.remove();
        }
        removeBlocksFromGrid();
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

    public static Tetromino create(Scene scene, Grid grid, int number, int x,
            int y)
    {
        return create(scene, grid, names[number], x, y);
    }
}
