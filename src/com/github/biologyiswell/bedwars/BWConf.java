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
package com.github.biologyiswell.bedwars;

import com.github.biologyiswell.api.util.io.FileType;
import com.github.biologyiswell.api.util.io.FileUtils;

import java.io.File;

/**
 * Created by biologyiswell on 07/10/2017.
 */
public class BWConf
{
    /** BWConf instance, this represents the instance from the BedWars Reborn configuration */
    public static BWConf instance = new BWConf();

    /* Generator Configs */

    /**
     * {type}Level, this representsthe initial level from generator type
     * {type}DecreaseCooldownPctg, this represents the decrease cooldown percentage that represents an each upgrade from generator
     * making that the generator is more faster
     */
    public float ironLevel = 5.0f;
    public float ironDecreaseCooldownPctg = 20.0f;

    public float goldLevel = 8.0f;
    public float goldDecreaseCooldownPctg = 22.75f;

    public float diamondLevel = 45.0f;
    public float diamondDecreaseCooldownPctg = 30f;

    public float emeraldLevel = 65.0f;
    public float emeraldDecreaseCooldownPctg = 40f;

    /* Methods */

    /**
     * Save Configuration,
     * This method save the configuration to database file
     */
    protected static void saveConfiguration()
    {
        // Check if the instance is null, to set the instance like BWConf
        if (instance == null)
        {
            instance = new BWConf();
        }

        final File file = new File(BW.instance.getDataFolder(), "conf.json");

        FileUtils.createNewFile(file, FileType.FILE, false);
        FileUtils.write(file, BW.GSON.toJson(BWConf.instance), "Ocorreu um erro ao salvar o arquivo " + file.getName());
    }

    /**
     * Read Configuration,
     * This method read the configuration from database file
     */
    protected static void readConfiguration()
    {
        // This can be provide because when this BWConf is loaded by ClassLoader the BWConf.instance is loaded with default BWConf
        // Then check this to get the configuration informations from the database file
        if (instance != null)
        {
            // The last object that represents the default BWConf() is discarded by the GC
            instance = null;
        }

        final File storage = new File(BW.instance.getDataFolder(), "conf.json");

        // Check if the storage exists
        if (!storage.exists())
        {
            // The storage not exists then the configuration is write to the database file to can get by the GSON from readConfiguration
            saveConfiguration();
        }

        instance = BW.GSON.fromJson(FileUtils.makeFileReader(storage, "Ocorreu um erro ao carregar o arquivo " + storage.getName()), BWConf.class);
    }
}
