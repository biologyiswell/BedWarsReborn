/**
 * MIT License
 *
 * Copyright (c) 2017 biologyiswell
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in * all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.biologyiswell.bedwars.arena.arenateam;

/**
 * Created by biologyiswell on 07/10/2017.
 */
public enum TeamColor
{
    /**
     * TeamColor,
     * This represents the all teams colors that can be registered in spawn from players
     * The TeamColors is represented by the data from colors (0-15)
     */

    WHITE,
    ORANGE,
    MAGENTA,
    LIGHT_BLUE,
    YELLOW,
    LIME,
    PINK,
    GRAY,
    LIGHT_GRAY,
    CYAN,
    PURPLE,
    BLUE,
    BROWN,
    GREEN,
    RED,
    BLACK;

    /**
     * Get Team Color By Data,
     * This method get the arenateam color by the data
     */
    public TeamColor getTeamColorByData(byte data)
    {
        if (data < 0 || data > 15)
        {
            data = 0;
        }

        for (TeamColor color : values())
        {
            if (color.ordinal() == data)
            {
                return color;
            }
        }

        return null;
    }

    /**
     * Get Data By Color,
     * This method get the color data from the arenateam color
     */
    public byte getDataByColor(final TeamColor color)
    {
        return (byte) color.ordinal();
    }
}
