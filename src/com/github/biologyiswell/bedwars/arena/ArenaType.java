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
package com.github.biologyiswell.bedwars.arena;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by biologyiswell on 07/10/2017.
 */
@RequiredArgsConstructor
public enum ArenaType
{
    /**
     * Solo,
     * This represents the Solo Arena Type, that in the arena the teams are have 1 player
     */
    SOLO(8),

    /**
     * Double,
     * This represents the Double Arena Type, that in the arena the teams are have 2 players
     */
    DOUBLE(16),

    /**
     * Team,
     * This represents the Team Arena Type, that in the arena the teams are have 4 players
     */
    TEAM(32);

    /** Size, this represents the max size players that can join in arena */
    @Getter
    private final int size;

    /**
     * Get Players By Team,
     * This method get players by arenateam by each Arena Type
     */
    public int getPlayersByTeam()
    {
        return this.size / SOLO.getSize();
    }
}