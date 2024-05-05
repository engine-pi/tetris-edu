package de.pirckheimer_gymnasium.tetris.scenes;

import java.awt.event.KeyEvent;

import de.pirckheimer_gymnasium.tetris.Tetris;
import rocks.friedrich.engine_omega.event.KeyListener;

public class TitleScene extends BaseScene implements KeyListener
{
    public TitleScene()
    {
        super("title");
    }

    public void onKeyDown(KeyEvent e)
    {
        Tetris.start(new IngameScene());
    }

    public static void main(String[] args)
    {
        Tetris.start(new TitleScene(), true);
    }
}
