package de.pirckheimer_gymnasium.tetris.color;

import java.awt.Color;

public class GreenColorSchema implements ColorSchema
{
    public Color white()
    {
        return new Color(170, 170, 0);
    }

    public Color light()
    {
        return new Color(85, 102, 52);
    }

    public Color dark()
    {
        return new Color(51, 85, 68);
    }

    public Color black()
    {
        return new Color(34, 51, 34);
    }
}
