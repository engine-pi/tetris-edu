package de.pirckheimer_gymnasium.tetris.scenes;

import de.pirckheimer_gymnasium.tetris.Tetris;
import rocks.friedrich.engine_omega.event.KeyListener;

import java.awt.event.KeyEvent;

public class CopyrightScene extends BaseScene implements KeyListener
{
    public CopyrightScene()
    {
        super("copyright");
        // Lambda-Ausdruck = anonyme Funktion () -> {}
        delay(4, () -> startTitleScene());
    }

    public void startTitleScene()
    {
        Tetris.start(new TitleScene());
    }

    public void onKeyDown(KeyEvent e)
    {
        startTitleScene();
    }

    public static void main(String[] args)
    {
        Tetris.start(new CopyrightScene(), true);
    }
}
