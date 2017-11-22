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
package com.github.biologyiswell.bedwars.arena.solo;

import com.github.biologyiswell.bedwars.account.Account;
import com.github.biologyiswell.bedwars.arena.Arena;
import com.github.biologyiswell.bedwars.arena.arenateam.ArenaTeam;
import com.github.biologyiswell.bedwars.arena.arenateam.solo.ArenaTeamSolo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by biologyiswell on 08/10/2017.
 */
public class ArenaSolo extends Arena
{
    /**
     * Found Empty Team,
     * This method found a empty arenateam that is not complete with the amount of the players that an arena arenateam need be
     * have
     */
    @Override
    public ArenaTeam foundEmptyTeam()
    {
        return this.spawns.keySet().stream().filter(team -> ((ArenaTeamSolo) team).getAccount() == null).findFirst().get();
    }

    /**
     * Get Players,
     * This method get the all players from the arena
     */
    @Override
    public List<Account> getPlayers()
    {
        return this.spawns.keySet().stream().map(team -> ((ArenaTeamSolo) team).getAccount()).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * Found Arena By Player,
     * This method found the arena arenateam that the player are registered
     */
    @Override
    public ArenaTeam foundArenaByPlayer(Account account)
    {
        return this.spawns.keySet().stream().filter(team -> ((ArenaTeamSolo) team).getAccount() == account).findFirst().get();
    }

    /**
     * Should Start Time,
     * This method check if the should start time
     */
    @Override
    public boolean shouldStartTime()
    {
        return this.spawns.keySet().stream().filter(team -> ((ArenaTeamSolo) team).getAccount() != null).count() > (type.getSize() / 2) + 2;
    }

    /**
     * Teleport Players To Team Location,
     * This method teleport the all plaeyrs that are in arena to arena team location
     */
    @Override
    protected void teleportPlayersToTeamLocation()
    {
        this.spawns.entrySet().forEach(entry -> ((ArenaTeamSolo) entry).getAccount().getPlayer().teleport(entry.getValue()));
    }
}
