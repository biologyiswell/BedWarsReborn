package com.github.biologyiswell.bedwars.manager;

import com.github.biologyiswell.api.util.io.FileType;
import com.github.biologyiswell.api.util.io.FileUtils;
import com.github.biologyiswell.bedwars.BW;
import com.github.biologyiswell.bedwars.account.Account;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by biologyiswell on 06/10/2017.
 */
public class AccountManager
{
    public static final Map<UUID, Account> ACCOUNTS = new HashMap<>();

    // It was not tested to use the async methods

    /* Database Methods */

    /**
     * Init Database,
     * This method initialize the AccountManager database
     */
    public static void initDatabase()
    {
        FileUtils.createNewFile(new File(BW.instance.getDataFolder(), "accounts"), FileType.FOLDER, false);
        BW.managersCount++;
    }

    /* Methods */

    /**
     * Register Account,
     * This method register the account, this method get the storage from player and check if the player has a storage
     * in database, if the player has storage then load the account otherwise create the account
     */
    public static void registerAccount(final Player player)
    {
        final File storage = getPlayerAccount(player);
        final Account account;

        if (storage.exists())
        {
            account = loadAccount(storage);
        } else
        {
            account = createAccount(storage);
        }

        // Update the player account, because the account is registered by the player,
        // And to avoid use methods like getPlayerAccount is defined a player field for Account class
        account.setPlayer(player);
        ACCOUNTS.put(player.getUniqueId(), account);
    }

    /**
     * Unregister Account,
     * This method write serialize the account with JSON format from player to database file
     */
    public static void unregisterAccount(final Player player)
    {
        saveAccount(player);
        ACCOUNTS.remove(player.getUniqueId());
    }

    /* Internal Methods */

    /**
     * Create Account,
     * This method create the account from player and serialize the account with JSON format and write the account
     * in a database file
     */
    private static Account createAccount(final File storage)
    {
        final Account account = new Account();
        // Write the account serialize with JSON format to database
        FileUtils.write(storage, BW.GSON.toJson(account), "Ocorreu um erro ao criar o arquivo " + storage.getName());
        return account;
    }

    /**
     * Load Account,
     * This method load the account from database file that are written with JSON format
     */
    private static Account loadAccount(final File storage)
    {
        // Load the account that are serialize with JSON format in database file
        return BW.GSON.fromJson(FileUtils.makeFileReader(storage, "Ocorreu um erro ao carregar o arquivo " + storage.getName()), Account.class);
    }

    /**
     * Save Account,
     * This method write the account from player serialize with JSON format to database file
     */
    private static void saveAccount(final Player player)
    {
        final File storage = getPlayerAccount(player);
        FileUtils.write(storage, BW.GSON.toJson(ACCOUNTS.get(player.getUniqueId())), "Ocorreu um erro ao salvar o arquivo " + storage.getName());
    }

    /**
     * Get Player Account,
     * This method get the player account storage that represents the database file,
     * That are registered by the player name
     */
    private static File getPlayerAccount(final Player player)
    {
        return new File(new File(BW.instance.getDataFolder(), "accounts"), player.getName() + ".json");
    }

    /**
     * AccountListener,
     * This class represents a listener class that listen the events for register and unregister the players
     * When the players join in server and quit from server
     */
    public static class AccountListener implements Listener
    {
        /**
         * Join,
         * This method is called when player join in server
         */
        @EventHandler
        public void join(PlayerJoinEvent event)
        {
            AccountManager.registerAccount(event.getPlayer());
        }

        /**
         * Quit,
         * This method is called when player quit from server
         */
        @EventHandler
        public void quit(PlayerQuitEvent event)
        {
            AccountManager.unregisterAccount(event.getPlayer());
        }
    }
}
