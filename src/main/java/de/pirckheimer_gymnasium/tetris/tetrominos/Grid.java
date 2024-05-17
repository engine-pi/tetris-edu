package de.pirckheimer_gymnasium.tetris.tetrominos;

public class Grid {

    private Block[][] grid;

    public Grid(int width, int height) {
        grid = new Block[width][height];
    }

    public int getWidth() {
        return grid.length;
    }

    public int getHeight() {
        return grid[0].length;
    }

    public void addBlock(Block block) {
        grid[block.getX()][block.getY()] = block;
    }

    public void removeBlock(Block block) {
        grid[block.getX()][block.getY()] = null;
    }

    public boolean isTaken(int x, int y) {
        return x < 0 || x >= getWidth() || y >= getHeight() || y < 0 || grid[x][x] != null;
    }


}
