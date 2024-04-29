package de.pirckheimer_gymnasium.tetris.color;

import java.awt.Color;

import de.pirckheimer_gymnasium.tetris.Tetris;

public class CustomColorSchema implements ColorSchema
{
    private ColorSchema green;

    public CustomColorSchema()
    {
        green = new GreenColorSchema();
    }

    public Color white()
    {
        if (Tetris.COLOR_WHITE != null)
        {
            return Tetris.COLOR_WHITE;
        }
        return green.white();
    }

    public Color light()
    {
        if (Tetris.COLOR_LIGHT != null)
        {
            return Tetris.COLOR_LIGHT;
        }
        return green.light();
    }

    public Color dark()
    {
        if (Tetris.COLOR_DARK != null)
        {
            return Tetris.COLOR_DARK;
        }
        return green.dark();
    }

    public Color black()
    {
        if (Tetris.COLOR_BLACK != null)
        {
            return Tetris.COLOR_BLACK;
        }
        return green.black();
    }
}
