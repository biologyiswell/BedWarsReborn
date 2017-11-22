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

import com.github.biologyiswell.api.util.hologram.Hologram;
import com.github.biologyiswell.bedwars.BW;
import com.google.common.base.Preconditions;
import org.bukkit.Location;

/**
 * Created by biologyiswell on 07/10/2017.
 */
public class HologramGenerator extends Generator
{
    /**
     * Hologram,
     * This represents the hologram that update by generator task
     */
    private Hologram hologram;

    /**
     * HologramGenerator, constructor
     */
    public HologramGenerator(final GeneratorType type, final Location location)
    {
        super(type, location);
        Preconditions.checkState(type == GeneratorType.DIAMOND || type == GeneratorType.EMERALD, "GeneratorType can't be different from DIAMOND or EMERALD");
    }

    /**
     * Run Generator,
     * This method is overrided because to start the hologram task
     */
    @Override
    public void runGenerator()
    {
        this.hologram = new Hologram(BW.HOLOGRAMS, this.location);
        this.hologram.addLine("§eNível: §c" + this.tier + "§e.");
        this.hologram.addLine(type.translate());
        this.hologram.addLine("§eEm §6" + this.time + "" + (this.time > 1 ? "segundos" : "segundo") + "§e.");

        super.runGenerator();
    }

    /**
     * Cancel Generator,
     * This method is overrided because to cancel the hologram task
     */
    @Override
    public void cancelGenerator()
    {
        super.cancelGenerator();
        // Unregister the hologram, this method clear the all lines from the hologram and unregister the hologram
        // from the hologram manager
        BW.HOLOGRAMS.unregisterHologram(this.hologram);
    }

    /**
     * Update,
     * This method is called by the generator task
     */
    @Override
    protected void update()
    {
        super.update();

        // Update the hologram tier and hologram time to drop item
        this.hologram.setLine(0, "§eNível: §c" + this.tier + "§e.");
        this.hologram.setLine(2, "§eEm §6" + this.time + "" + (this.time > 1 ? "segundos" : "segundo") + "§e.");
    }
}
