package de.pirckheimer_gymnasium.tetris.tetrominos;

import de.pirckheimer_gymnasium.tetris.Image;
import rocks.friedrich.engine_omega.Scene;

public class Block
{
    private Scene scene;

    private Image image;

    private int x;

    private int y;

    public Block(Scene scene, String imageName, int x, int y)
    {
        this.scene = scene;
        image = new Image("blocks/" + imageName + ".png");
        image.setPosition(x, y);
        this.scene.add(image);
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void moveBy(int dX, int dY)
    {
        image.moveBy(dX, dY);
        x = x + dX;
        y = y + dY;
    }

    public void moveLeft()
    {
        moveBy(-1, 0);
    }

    public void moveRight()
    {
        moveBy(1, 0);
    }

    public void moveDown()
    {
        moveBy(0, -1);
    }

    public void remove()
    {
        scene.remove(image);
    }
}
