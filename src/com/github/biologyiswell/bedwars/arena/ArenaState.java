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

/**
 * Created by biologyiswell on 07/10/2017.
 */
public enum ArenaState
{
    /**
     * Waiting,
     * This represents that the arena has been waiting, that can players can be join in the arena case if the arena
     * is not full
     */
    WAITING,

    /**
     * Started,
     * This represents that the game already started and the players can not be join in arena
     */
    STARTED,

    /**
     * Ending,
     * This represents that the arena has been ending
     */
    ENDING,

    /**
     * Building Mode,
     * This represents that the arena is in building mode,
     * That the arena that is in building mode can not start the arena
     */
    BUILDING_MODE;
}
