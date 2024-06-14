package de.pirckheimer_gymnasium.tetris;

import de.pirckheimer_gymnasium.tetris.scenes.TitleScene;
import de.pirckheimer_gymnasium.engine_pi.Game;
import de.pirckheimer_gymnasium.engine_pi.Scene;

import java.awt.Color;

public class Tetris
{
    /**
     * Die Größe eines Blocks in Pixeln. In dem klassischen Gameboy-Tetris hat
     * ein Block die Größe {@code 8x8}, die Blockgröße beträgt also {@code 8}
     * Pixel.
     */
    public static final int BLOCK_SIZE = 8;

    /**
     * Die Breite des Spielfelds in Blöcken. In dem klassischen Gameboy-Tetris
     * passen 20 Blöcken in die Breite.
     */
    public static final int WIDTH = 20;

    /**
     * Die Höhe des Spielfelds in Blöcken. In dem klassischen Gameboy-Tetris
     * passen 18 Blöcken in die Höhe.
     */
    public static final int HEIGHT = 18;

    /**
     * Die Skalierung des Spiels. Wird zum Beispiel 4 eingestellt, so wird ein
     * {@code 8x8} Block auf {@code 32x32} vergrößert.
     */
    public static final int SCALE = 4;

    /**
     * Das Spielfeld hat rechts einen Abstand zum Bildschirmrand von 8 Blöcken.
     */
    public static final int GRID_WIDTH = 10;

    public static final Color COLOR_WHITE = null;

    public static final Color COLOR_LIGHT = null;

    public static final Color COLOR_DARK = null;

    public static final Color COLOR_BLACK = null;

    public static void start(Scene scene, boolean debug)
    {
        scene.getCamera().setMeter(BLOCK_SIZE * SCALE);
        Game.setDebug(debug);
        if (!Game.isRunning())
        {
            Game.start(WIDTH * BLOCK_SIZE * SCALE, HEIGHT * BLOCK_SIZE * SCALE,
                    scene);
        }
        else
        {
            Game.transitionToScene(scene);
        }
    }

    public static void start(Scene scene)
    {
        start(scene, false);
    }

    public static void start()
    {
        start(new TitleScene());
    }

    public static void main(String[] args)
    {
        start();
    }
}
