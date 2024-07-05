package de.pirckheimer_gymnasium.tetris.scenes;

import java.awt.event.KeyEvent;

import de.pirckheimer_gymnasium.tetris.Tetris;
import de.pirckheimer_gymnasium.engine_pi.event.KeyStrokeListener;

public class TitleScene extends BaseScene implements KeyStrokeListener
{
    public TitleScene()
    {
        super("title");
        Sound.playTitle();
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
