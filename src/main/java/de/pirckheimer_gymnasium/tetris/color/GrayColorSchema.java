package de.pirckheimer_gymnasium.tetris.color;

import java.awt.Color;

public class GrayColorSchema implements ColorSchema
{
    public Color white()
    {
        return new Color(255, 255, 255);
    }

    public Color light()
    {
        return new Color(173, 173, 173);
    }

    public Color dark()
    {
        return new Color(82, 82, 82);
    }

    public Color black()
    {
        return new Color(0, 0, 0);
    }
}
