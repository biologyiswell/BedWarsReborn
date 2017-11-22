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
package com.github.biologyiswell.bedwars.arena.merchant;

import com.github.biologyiswell.api.util.entity.EntityUtils;
import com.github.biologyiswell.api.util.hologram.Hologram;
import com.github.biologyiswell.bedwars.BW;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

/**
 * Created by biologyiswell on 11/10/2017.
 */
@RequiredArgsConstructor
public class Merchant
{
    /**
     * Entity & Merchant Type & Location
     * This represents the entity from the merchant and the merchant type,
     * And the location that the merchant will be spawn
     */
    private Entity entity;
    private final MerchantType type;
    private final Location location;

    /**
     * Display Name,
     * This represents the hologram that hold the name to merchant, because the entity not hold the name, to see the
     * Name from the entity like (Villager) the name only shown when see for entity
     */
    private Hologram displayName;

    /**
     * Spawn,
     * This method spawn the merchant
     */
    public final Merchant spawn()
    {
        this.entity = this.location.getWorld().spawn(this.location, Villager.class);
        this.displayName = new Hologram(BW.HOLOGRAMS, this.location.clone().add(0d, 1.5d, 0d));

        if (this.type == MerchantType.ITEM_SHOP)
        {
            this.displayName.addLine("LOJA DE");
            this.displayName.addLine("ITEMS");
        } else if (this.type == MerchantType.TEAM_UPGRADES)
        {
            this.displayName.addLine("LOJA DE");
            this.displayName.addLine("APRIMORAMENTOS");
        }

        return this;
    }

    public void openGui(final Player player)
    {

    }
}
