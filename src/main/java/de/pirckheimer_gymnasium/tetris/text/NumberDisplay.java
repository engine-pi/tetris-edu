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

import de.pirckheimer_gymnasium.engine_pi.Scene;
import de.pirckheimer_gymnasium.tetris.Tetris;

public class NumberDisplay extends TextLine
{
    private int number;

    public NumberDisplay(Scene scene, int x, int y, int maxDigits)
    {
        super(scene, x, y, maxDigits);
        set(0);
    }

    public void write(int number)
    {
        super.write(String.valueOf(number),
                Tetris.COLOR_SCHEME_GREEN.getBlack(), TextAlignment.RIGHT);
    }

    public void set(int number)
    {
        this.number = number;
        write(number);
    }

    public int get()
    {
        assert number > -1;
        return number;
    }

    public void add(int number)
    {
        assert number > -1 : "Add only supports positiv values, got " + number;
        this.number += number;
        write(this.number);
    }
}
