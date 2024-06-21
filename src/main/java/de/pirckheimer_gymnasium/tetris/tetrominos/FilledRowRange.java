package de.pirckheimer_gymnasium.tetris.tetrominos;

/**
 * Diese Klasse speichert, welche Zeilen vollständig sind und daher getilgt
 * werden können.
 */
public class FilledRowRange
{
    /**
     * Ab welcher y-Koordinate (einschließlich) der Bereich mit ausgefüllten Zeilen reicht.
     */
    private final int from;

    /**
     * Bis zu welcher y-Koordinate (einschließlich) der Bereich mit ausgefüllten Zeilen reicht.
     */
    private final int to;

    /**
     * @param from Ab welcher y-Koordinate (einschließlich) der Bereich mit ausgefüllten Zeilen reicht.
     * @param to Bis zu welcher y-Koordinate (einschließlich) der Bereich mit ausgefüllten Zeilen reicht.
     */
    public FilledRowRange(int from, int to)
    {
        this.from = from;
        this.to = to;
    }

    public int getFrom()
    {
        return from;
    }

    public int getTo()
    {
        return to;
    }

    /**
     * Gibt die Anzahl zurück, wie viele Zeilen abgebaut wurden.
     *
     * @return die Anzahl an Zeilen die abgebaut wurden.
     */
    public int getRowCount()
    {
        int result = to - from + 1;
        assert result > 0 && result < 5;
        return result;
    }
}