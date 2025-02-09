package de.pirckheimer_gymnasium.tetris.debug;

import java.awt.Color;
import java.awt.event.KeyEvent;

import de.pirckheimer_gymnasium.tetris.Tetris;
import de.pirckheimer_gymnasium.tetris.tetrominos.Grid;
import de.pirckheimer_gymnasium.tetris.tetrominos.Tetromino;
import de.pirckheimer_gymnasium.engine_pi.Game;
import de.pirckheimer_gymnasium.engine_pi.Scene;
import de.pirckheimer_gymnasium.engine_pi.actor.Text;
import de.pirckheimer_gymnasium.engine_pi.event.KeyStrokeListener;

public class TetrominoDebugScene extends Scene implements KeyStrokeListener
{
    private Text rotation;

    Tetromino[] t;

    Grid grid;

    public TetrominoDebugScene()
    {
        rotation = new Text("0", 2);
        rotation.setColor(Color.WHITE);
        rotation.setPosition(2, Tetris.HEIGHT / 2);
        grid = new Grid(Tetris.WIDTH, Tetris.HEIGHT);
        getCamera().setPostion(Tetris.WIDTH / 2, Tetris.HEIGHT / 2);
        add(rotation);
        t = new Tetromino[7];
        // L I J
        // O
        // Z T S
        int x1 = 3;
        int x2 = 9;
        int x3 = 16;
        int y1 = 3;
        int y2 = 8;
        int y3 = 13;
        t[0] = createTetromino("L", x1, y3);
        t[1] = createTetromino("I", x2, y3);
        t[2] = createTetromino("J", x3, y3);
        t[3] = createTetromino("O", x2, y2);
        t[4] = createTetromino("Z", x1, y1);
        t[5] = createTetromino("T", x2, y1);
        t[6] = createTetromino("S", x3, y1);
    }

    private Tetromino createTetromino(String name, int x, int y)
    {
        return Tetromino.create(this, grid, name, x, y);
    }

    @Override
    public void onKeyDown(KeyEvent keyEvent)
    {
        switch (keyEvent.getKeyCode())
        {
        case KeyEvent.VK_LEFT:
            for (Tetromino i : t)
            {
                i.moveLeft();
            }
            break;

        case KeyEvent.VK_RIGHT:
            for (Tetromino i : t)
            {
                i.moveRight();
            }
            break;

        case KeyEvent.VK_DOWN:
            for (Tetromino i : t)
            {
                i.moveDown();
            }
            break;

        case KeyEvent.VK_SPACE:
            for (Tetromino i : t)
            {
                i.rotate();
            }
            rotation.setContent(String.valueOf(t[0].rotation));
            break;
        }
    }

    public static void start(boolean debug)
    {
        // Muss vorher gesetzt werden
        Game.setDebug(debug);
        Tetris.start(new TetrominoDebugScene(), debug);
    }

    public static void main(String[] args)
    {
        start(false);
    }
}
