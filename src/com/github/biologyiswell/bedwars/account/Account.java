package com.github.biologyiswell.bedwars.account;

import com.github.biologyiswell.api.util.json.exclusion.Exclude;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

/**
 * Created by biologyiswell on 06/10/2017.
 */
@Setter
@Getter
public class Account
{
    @Exclude /* not serialize to Gson */
    private transient Player player;

    /**
     * Account Informations
     */
    private int winsSolo, winsDouble, winsTeam;
    private int killsSolo, killsDouble, killsTeam;
    private int gamesSolo, gamesDouble, gamesTeam;

    private long timePlayed;

    private int coins;
    private int level;
}
