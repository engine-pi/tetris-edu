package de.pirckheimer_gymnasium.tetris.tetrominos;

public class SoftDrop
{
    private final int y;

    private final Tetromino tetromino;

    public SoftDrop(Tetromino tetromino)
    {
        y = tetromino.getY();
        this.tetromino = tetromino;
    }

    public int getDistance()
    {
        return y - tetromino.getY();
    }
}
