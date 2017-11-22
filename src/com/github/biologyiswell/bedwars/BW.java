package com.github.biologyiswell.bedwars;

import com.github.biologyiswell.api.util.hologram.HologramManager;
import com.github.biologyiswell.api.util.json.JsonUtils;
import com.github.biologyiswell.api.util.server.PluginUtils;
import com.github.biologyiswell.bedwars.manager.AccountManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by biologyiswell on 06/10/2017.
 */
public class BW extends JavaPlugin
{
    public static BW instance;

    /** Gson, this represents the gson that serialize and de-serialize the objects with JSON format */
    public static final Gson GSON = JsonUtils.addGsonAdapters(new GsonBuilder().setPrettyPrinting()).create();

    /** Managers Count, this serves to verify if the all managers has been registered */
    public static int managersCount;

    /** HologramManager, this represents the manager that register the holograms */
    public static final HologramManager HOLOGRAMS = new HologramManager();

    @Override
    public void onEnable()
    {
        PluginUtils.initDatabase(this);

        // .. configuration ..
        BWConf.readConfiguration();

        // TODO Register managers

        checkManagersLoad();

        // TODO Register listeners

        getLogger().info("BedWars Reborn has been enabled.");
    }

    @Override
    public void onDisable()
    {
        // .. configuration ..
        BWConf.saveConfiguration();

        getLogger().info("BedWars Reborn has been disabled.");
    }

    /**
     * Check Managers Load,
     * This method check if the all managers that are registered in BedWars Reborn has been initialized correctly,
     * Case if the a any manager can not be initialize the plugin has been disabled
     */
    private void checkManagersLoad()
    {
        if (managersCount < 3)
        {
            getLogger().info("BedWars Reborn Managers not initialize correctly then, the plugin will be disabled.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }
}
