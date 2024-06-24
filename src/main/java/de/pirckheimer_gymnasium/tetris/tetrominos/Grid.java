package de.pirckheimer_gymnasium.tetris.tetrominos;

public class Grid
{
    private Block[][] grid;

    public Grid(int width, int height)
    {
        grid = new Block[width][height];
    }

    public int getWidth()
    {
        return grid.length;
    }

    public int getHeight()
    {
        return grid[0].length;
    }

    public void addBlock(Block block)
    {
        grid[block.getX()][block.getY()] = block;
    }

    public void removeBlock(Block block)
    {
        grid[block.getX()][block.getY()] = null;
    }

    public boolean isTaken(int x, int y)
    {
        return x < 0 || x >= getWidth() || y < 0 || y >= getHeight()
                || grid[x][y] != null;
    }

    private boolean isRowFull(int y)
    {
        for (int x = 0; x < getWidth(); x++)
        {
            if (grid[x][y] == null)
            {
                return false;
            }
        }
        return true;
    }

    public FilledRowRange getFilledRowRange()
    {
        int from = -1;
        int to = -1;
        for (int y = 0; y < getHeight(); y++)
        {
            if (isRowFull(y))
            {
                if (from == -1)
                {
                    from = y;
                }
                to = y;
            }
        }
        if (from > -1 && to > -1)
        {
            return new FilledRowRange(from, to);
        }
        else
        {
            return null;
        }
    }

    public void clearRow(int y)
    {
        for (int x = 0; x < getWidth(); x++)
        {
            if (isTaken(x, y))
            {
                grid[x][y].remove();
                grid[x][y] = null;
            }
        }
    }

    /**
     * Leer das ganze Gitter
     */
    public void clear()
    {
        for (int y = 0; y < getHeight(); y++)
        {
            clearRow(y);
        }
    }

    public void removeFilledRowRange(FilledRowRange range)
    {
        if (range == null)
        {
            return;
        }
        for (int y = range.getFrom(); y <= range.getTo(); y++)
        {
            clearRow(y);
        }
    }

    /**
     * Löst einen Erdrutsch (landslide) aus, das heißt alle Blöcke oberhalb des
     * getilgten Bereichs werden nach unten bewegt.
     *
     * @param range Der getilgte Bereich mit vollen Zeilen.
     */
    public void triggerLandslide(FilledRowRange range)
    {
        if (range == null)
        {
            return;
        }
        for (int y = range.getTo() + 1; y < getHeight(); y++)
        {
            for (int x = 0; x < getWidth(); x++)
            {
                if (isTaken(x, y))
                {
                    Block block = grid[x][y];
                    block.moveBy(0, -range.getRowCount());
                    grid[x][y] = null;
                    grid[x][y - range.getRowCount()] = block;
                }
            }
        }
    }
}
