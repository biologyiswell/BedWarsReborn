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
package com.github.biologyiswell.bedwars.arena.generator;

import com.github.biologyiswell.bedwars.BW;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by biologyiswell on 07/10/2017.
 */
public class Generator
{
    /** Type, this represents the generator type that represents the drop item type from generator */
    @Getter
    protected GeneratorType type;

    /** Location, this rerepsents the location from the generator */
    protected Location location;

    /** Runnable, this represents the runnable that update from the generator */
    private BukkitRunnable runnable;

    /** Time, this represents the time to drop the item type from generator type */
    protected float time;

    /** Tier, this represents the tier from the generator */
    protected int tier;

    /**
     * Generator, constructor
     */
    public Generator(final GeneratorType type, final Location location)
    {
        this.type = type;
        this.location = location;
        this.tier = 1;

        // Get the time from generator type
        this.time = type.getTimeByTier(this.tier);
    }

    /**
     * Run Generator,
     * This method start the task from generator to update the generator
     */
    public void runGenerator()
    {
        this.runnable = new BukkitRunnable()
        {
            @Override
            public void run()
            {
                update();
            }
        };
        this.runnable.runTaskTimer(BW.instance, 0L, 20L);
    }

    /**
     * Cancel Generator,
     * This method cancel the task from generator
     */
    public void cancelGenerator()
    {
        this.runnable.cancel();
    }

    /**
     * Update,
     * This method is called by the generator task
     */
    protected void update()
    {
        time--;

        if (time <= 0)
        {
            location.getWorld().dropItemNaturally(location, new ItemStack(type.getDrop(), 1));
            time = type.getTimeByTier(tier);
        }
    }

    /**
     * Upgrade Tier,
     * This method upgrade the tier from the generator
     */
    public void upgradeTier()
    {
        this.tier++;
    }
}