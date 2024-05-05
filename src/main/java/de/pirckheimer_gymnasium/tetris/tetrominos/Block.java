package de.pirckheimer_gymnasium.tetris.tetrominos;

import de.pirckheimer_gymnasium.tetris.Image;

public class Block
{
    private Image image;

    public Block(String name)
    {
        image = new Image("blocks/" + name + ".png");
    }
}
