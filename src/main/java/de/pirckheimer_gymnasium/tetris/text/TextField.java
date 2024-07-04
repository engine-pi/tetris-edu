/*
 * Copyright (c) 2024 Josef Friedrich and contributors.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.pirckheimer_gymnasium.tetris.text;

import java.awt.Color;

import de.pirckheimer_gymnasium.engine_pi.Scene;

/**
 * Ein rechteckiges Feld, in das mit den typischen Tetris-Buchstaben geschrieben
 * werden kann.
 */
public class TextField
{
    /**
     * Eine Referenz auf die {@link Scene Szene}, in der das Textfeld angezeigt
     * werden soll.
     */
    private Scene scene;

    /**
     * Die x-Koordinate des linken oberen Ecks des Textfelds, in das geschrieben
     * werden soll.
     */
    private int x;

    /**
     * Die y-Koordinate des linken oberen Ecks des Textfelds, in das geschrieben
     * werden soll.
     */
    private int y;

    /**
     * Die Anzahl an Zeilen, die das Textfeld maximal enthalten darf.
     */
    private int lines;

    /**
     * Die Anzahl an Buchstaben, die in eine Zeile passen.
     */
    private int width;

    /**
     * Ein zweidimensionales Feld, das als Speicher für die Buchstaben dient.
     */
    private TextLine[] textLines;

    /**
     *
     * @param scene Eine Referenz auf die {@link Scene Szene}, in der das
     *              Textfeld angezeigt werden soll.
     * @param x     Die x-Koordinate des linken oberen Ecks des Textfelds, in
     *              das geschrieben werden soll.
     * @param y     Die y-Koordinate des linken oberen Ecks des Textfelds, in
     *              das geschrieben werden soll.
     * @param lines Die Anzahl an Zeilen, die das Textfeld maximal enthalten
     *              darf.
     * @param width Die Anzahl an Buchstaben, die in eine Zeile passen.
     */
    public TextField(Scene scene, int x, int y, int lines, int width)
    {
        this.scene = scene;
        this.x = x;
        this.y = y;
        this.width = width;
        this.lines = lines;
        textLines = new TextLine[lines];
    }

    public void write(String text, Color color)
    {
        clear();
        // Ist der Text null oder eine Zeichenkette mit keinem Zeichen, zeichnen
        // wir keinen Text und verlassen die Methode vorzeitig.
        if (text == null || text.length() == 0)
        {
            return;
        }
        if (text.length() > lines * width)
        {
            throw new RuntimeException("Der Text passt nicht in das Textfeld");
        }
        int lineIndex = 0;
        for (int i = 0; i < text.length(); i += width)
        {
            TextLine line = new TextLine(scene, x, y - lineIndex, width);
            line.write(text.substring(i, Math.min(text.length(), i + width)),
                    color, TextAlignment.LEFT);
            textLines[lineIndex] = line;
            lineIndex++;
        }
    }

    /**
     * Löscht alle Zeilen aus dem Textfeld.
     */
    public void clear()
    {
        for (TextLine line : textLines)
        {
            if (line != null)
            {
                line.clear();
            }
        }
    }
}
