/**
 * MIT License
 * <p>
 * Copyright (c) 2017 biologyiswell
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in * all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.biologyiswell.bedwars.arena.generator;

import com.github.biologyiswell.bedwars.BWConf;
import org.bukkit.Material;

/**
 * Created by biologyiswell on 07/10/2017.
 */
public enum GeneratorType
{
    /**
     * Iron,
     * This represents the generator type iron, that drop iron items an each period
     * This generator type do not create a hologram information
     */
    IRON
            {
                @Override
                public Material getDrop()
                {
                    return Material.IRON_INGOT;
                }

                @Override
                public float getTimeByTier(int tier)
                {
                    return GeneratorType.calculateTime(BWConf.instance.ironLevel, BWConf.instance.ironDecreaseCooldownPctg, tier);
                }

                @Override
                public String translate()
                {
                    return "§fFerro";
                }
            },
    /**
     * Gold,
     * This represents the generator type gold, that drop iron items an each period
     * This generator type do not create a hologram information
     */
    GOLD
            {
                @Override
                public Material getDrop()
                {
                    return Material.GOLD_INGOT;
                }

                @Override
                public float getTimeByTier(int tier)
                {
                    return GeneratorType.calculateTime(BWConf.instance.goldLevel, BWConf.instance.goldDecreaseCooldownPctg, tier);
                }

                @Override
                public String translate()
                {
                    return "§6Ouro";
                }
            },

    /**
     * Diamond,
     * This represents the generator type diamond, that drop iron items an each period
     * This generator type has a hologram information
     */
    DIAMOND
            {
                @Override
                public Material getDrop()
                {
                    return Material.DIAMOND;
                }

                @Override
                public float getTimeByTier(int tier)
                {
                    return GeneratorType.calculateTime(BWConf.instance.diamondLevel, BWConf.instance.diamondDecreaseCooldownPctg, tier);
                }

                @Override
                public String translate()
                {
                    return "§bDiamante";
                }
            },

    /**
     * Emerald,
     * This represents the generator type emerald, that drop iron items an each period
     * This generator type has a hologram information
     */
    EMERALD
            {
                @Override
                public Material getDrop()
                {
                    return Material.EMERALD;
                }

                @Override
                public float getTimeByTier(int tier)
                {
                    return GeneratorType.calculateTime(BWConf.instance.emeraldLevel, BWConf.instance.emeraldDecreaseCooldownPctg, tier);
                }

                @Override
                public String translate()
                {
                    return "§aEsmeralda";
                }
            };

    /**
     * Get Drop,
     * This method get the material from generator type
     */
    public Material getDrop()
    {
        throw new UnsupportedOperationException("Can't be get the drop without generator type");
    }

    /**
     * Get Time by Tier,
     * This method get the time from the generator type and tier
     *
     * @param tier the tier from generator
     */
    public float getTimeByTier(int tier)
    {
        throw new UnsupportedOperationException("Can't be get the timeby tier without generator type");
    }

    /**
     * Translate,
     * This method translate the name from generator type
     */
    public String translate()
    {
        throw new UnsupportedOperationException("Can't be get name translate without generator type");
    }

    /* Internal Methods */

    /**
     * Calculate Time,
     * This method calculate the time by the initial time from the generator type, percentage and tier
     */
    private static float calculateTime(final float initialTime, final float percentage, final int tier)
    {
        float time = initialTime;
        float cdr = ((100 - percentage) / 100);

        for (int i = 1; i <= tier; i++)
        {
            time = time * cdr;
        }

        return time;
    }
}
