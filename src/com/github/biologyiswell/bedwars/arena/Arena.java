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
package com.github.biologyiswell.bedwars.arena;

import com.github.biologyiswell.api.util.entity.PlayerUtils;
import com.github.biologyiswell.bedwars.BW;
import com.github.biologyiswell.bedwars.account.Account;
import com.github.biologyiswell.bedwars.arena.generator.Generator;
import com.github.biologyiswell.bedwars.arena.arenateam.ArenaTeam;
import com.github.biologyiswell.bedwars.arena.generator.GeneratorType;
import com.github.biologyiswell.bedwars.arena.merchant.Merchant;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

/**
 * Created by biologyiswell on 07/10/2017.
 */
public class Arena
{
    /**
     * Name & Id, default configurations from arena
     */
    private String name;
    private int id;

    /**
     * Type & State, that represents the default configuration for specif the arena
     */
    protected ArenaType type;
    private ArenaState state;

    /**
     * ArenaTeam and Location, this represents the location from each arena arenateam
     */
    protected Map<ArenaTeam, Location> spawns;

    /** Merchants, this represents the merchants from the arena */
    protected Map<Location, Merchant> merchants;

    /**
     * Generator list, this represents the generators that have in arena
     */
    private List<Generator> generators;

    /**
     * Runnable & Time, this represents the runnable from task and the time from the arena
     */
    private BukkitRunnable runnable;
    private int time;

    /* Methods */

    /**
     * Add Player,
     * This method add the player that get the account from player to add to the arena
     */
    public void addPlayer(@NonNull final Account account)
    {
        // Found an empty arenateam that represents an each method from each arena class type
        final ArenaTeam emptyTeam = foundEmptyTeam();

        // This check avoid to the account join in null arenateam and also avoid to the throw the NullPointerException,
        // Then this condition refuse the connection the account in this arena
        // TODO When refuse the player connection, send the player to another empty arena?
        if (emptyTeam == null)
        {
            account.getPlayer().sendMessage("§cOps! Ocorreu um erro ao conectar você a está arena, tente novamente.");
            return;
        }

        emptyTeam.addAccount(account);
        sendArenaMessages("§7" + account.getPlayer().getDisplayName() + " §eentrou na sala §b(" + getPlayers().size() + "/" + type.getSize() + ")");
    }

    /**
     * Remove Player,
     * This method remove the player that get the account from the arena
     */
    public void removePlayer(@NonNull final Account account)
    {
        final ArenaTeam team = foundArenaByPlayer(account);
        // Check if occur an error when found the team from player, the team can not be null,
        // Then if the team is null the player is analized
        if (team == null)
        {
            account.getPlayer().sendMessage("§cOps! Ocorreu um erro ao remover você da partida, você está sendo analisado pelo servidor.");
            // TODO: 08/10/2017 Kick the player from the server to analize the error that occur
            return;
        }

        team.removeAccount(account);
        sendArenaMessages("§7" + account.getPlayer().getDisplayName() + " §csaiu da sala §b(" + getPlayers().size() + "/" + type.getSize() + ")");
    }

    /**
     * Run Arena,
     * This method run the task from the arena
     */
    public void runArena()
    {
        this.runWait();
    }

    /**
     * Run Wait,
     * This method run the wait task
     */
    private void runWait()
    {
        this.time = 120;
        this.state = ArenaState.WAITING;

        this.runnable = new BukkitRunnable()
        {
            @Override
            public void run()
            {
                if (shouldStartTime()) // Check if the arena have the necessary players to start the arena
                {
                    time--;

                    if (time == 60 || time == 30 || time == 20 || time == 10)
                    {
                        sendArenaMessages("§eInício em §6" + time + " §esegundos.");
                        playArenaSound(Sound.NOTE_STICKS, 1f, 1f);

                        // Send arena title if the time is equals 10
                        if (time == 10) sendArenaTitle("§a§l10", "");
                    }

                    if (time > 0 && time <= 5)
                    {
                        sendArenaMessages("§eInício em §6" + time + "§esegundos.");
                        playArenaSound(Sound.NOTE_STICKS, 1f, 1f);
                        sendArenaTitle("§c§l" + time, "");
                    }

                    if (time <= 0) // This represents the time to start the arena
                    {
                        if (getPlayers().size() < type.getSize())
                        {
                            time = 60;
                            sendArenaMessages("§cNão foi possível iniciar a arena, pois falta jogadores, o temporizador foi definido para " + time + " segundos.");
                        } else // Start the game \/
                        {
                            cancel(); // Cancel this task
                            runStart(); // Run the start task from the arena
                        }
                    }
                }
            }
        };

        this.runnable.runTaskTimer(BW.instance, 0L, 20L);
        setRunnable(runnable);
    }

    /**
     * Run Start,
     * This method run the start task from the arena
     */
    private void runStart()
    {
        this.time = 60 * 30;
        this.state = ArenaState.STARTED;

        teleportPlayersToTeamLocation();
        updatePlayers();
        startAllGenerators();

        this.runnable = new BukkitRunnable()
        {
            @Override
            public void run()
            {
                time--;

                // This method check if the arena should end, because if not have players or have only one player
                if (shouldEndArena())
                {
                    // This method end the game
                    endGame();
                }

                // This method check the upgrade from generator
                checkUpdateGenerator();

            }
        };

        this.runnable.runTaskTimer(BW.instance, 0L, 20L);
        setRunnable(this.runnable);
    }

    /**
     * End Game,
     * This method end the game
     */
    private void endGame()
    {
        this.generators.forEach(Generator::cancelGenerator);
        // TODO: 09/10/2017 Make stuff on this method
    }

    /* Overrided Methods */

    /**
     * Should Start Time,
     * This method check if the should start time
     */
    public boolean shouldStartTime()
    {
        throw new UnsupportedOperationException("Can't be check if the wait time should be start");
    }

    /**
     * Found Empty Team,
     * This method found a empty arenateam that is not complete with the amount of the players that an arena arenateam need be
     * have
     */
    public ArenaTeam foundEmptyTeam() // ## This method need be Overrided by (ArenaSolo, ArenaDouble and ArenaTeam)
    {
        throw new UnsupportedOperationException("Can't be found empty arenateam without arena type");
    }

    /**
     * Get Players,
     * This method get the all players from the arena
     */
    public List<Account> getPlayers()
    {
        throw new UnsupportedOperationException("Can't be get the players from the arena without arena type");
    }

    /**
     * Found Arena By Player,
     * This method found the arena arenateam that the player are registered
     */
    public ArenaTeam foundArenaByPlayer(final Account account)
    {
        throw new UnsupportedOperationException("Can't be get the arena arenateam from the player without arena type");
    }

    /**
     * Teleport Players To Team Location,
     * This method teleport the all plaeyrs that are in arena to arena team location
     */
    protected void teleportPlayersToTeamLocation()
    {
        throw new UnsupportedOperationException("Can't be teleport the all players that are in arena to team location without arena type");
    }

    /* Utils methods */

    /**
     * Update Players,
     * This method update the all players that are in arena, like (gamemode, health, food)
     */
    private void updatePlayers()
    {
        getPlayers().forEach(account -> PlayerUtils.updatePlayer(account.getPlayer()));
    }

    /**
     * Set Runnable,
     * This method set the runnable from the arena
     */
    protected void setRunnable(@NonNull BukkitRunnable runnable)
    {
        // Check if the runnable is not null, this is to avoid to change the runnable and the runnable that is setted is running,
        // Then if the runnable is not null cancel the runnable to set the new runnable
        if (this.runnable != null)
        {
            this.runnable.cancel();
        }

        this.runnable = runnable;
    }

    /**
     * Send Arena Messages,
     * This method send a array of messages to all players that are in arena
     */
    protected void sendArenaMessages(final String... messages)
    {
        this.getPlayers().forEach(account ->
        {
            for (final String message : messages)
            {
                account.getPlayer().sendMessage(message);
            }
        });
    }

    /**
     * Play Arena Sound,
     * This method play sound to all players that are in arena
     */
    protected void playArenaSound(final Sound sound, final float volume, final float pitch)
    {
        this.getPlayers().forEach(account -> account.getPlayer().playSound(account.getPlayer().getLocation(), sound, volume, pitch));
    }

    /**
     * Send Arena Title,
     * This method send the arena title for all players that are in arena
     */
    @SuppressWarnings("deprecated")
    protected void sendArenaTitle(final String title, final String subtitle)
    {
        this.getPlayers().forEach(account -> account.getPlayer().sendTitle(title, subtitle));
    }

    /**
     * Start All Generators,
     * This method start the all generators that are registered in the arena
     */
    private void startAllGenerators()
    {
        this.generators.forEach(Generator::runGenerator);
    }

    /**
     * Check Update Generator,
     * This method check the time to upgrade the generator
     */
    private void checkUpdateGenerator()
    {
        if (time == time - 600 || time == time - 1200) // 10 minutes && 20 minutes
        {
            this.generators.stream().filter(gen -> gen.getType() == GeneratorType.DIAMOND).forEach(Generator::upgradeTier);
        } else if (time == time - 900 || time == time - 1500) // 15 minutes && 25 minutes
        {
            this.generators.stream().filter(gen -> gen.getType() == GeneratorType.EMERALD).forEach(Generator::upgradeTier);
        }
    }

    /**
     * Should End Game,
     * This method check if the game is should end, because not have anyone player in arena or have only one player in
     * arena
     */
    private boolean shouldEndArena()
    {
        return getPlayers().size() <= 1;
    }
}