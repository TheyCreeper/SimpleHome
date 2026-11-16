package dev.theycreeper.simplehome.configs;

import dev.theycreeper.simplehome.SimpleHome;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import org.bukkit.Material;

import java.util.ArrayList;

public class ConfigHandler {

    private static ConfigHandler instance;

    private int maxhomeNumber;

    public static ConfigHandler getInstance() {
        if (instance == null) instance = new ConfigHandler();
        return instance;
    }

    public void loadConfigValues() {
        SimpleHome.getInstance().reloadConfig();
        FileConfiguration config = SimpleHome.getInstance().getConfig();
        List<String> blockList = config.getStringList("blacklisted-blocks");
        maxhomeNumber = config.getInt("max-home-number");
    }

    public int getDefaultMaxHomeCount() {
        return maxhomeNumber;
    }
}