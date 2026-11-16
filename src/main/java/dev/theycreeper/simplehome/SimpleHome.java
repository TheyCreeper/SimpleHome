package dev.theycreeper.simplehome;

import dev.theycreeper.simplehome.commands.HomeCommand;
import dev.theycreeper.simplehome.commands.SetHome;
import dev.theycreeper.simplehome.models.Home;
import io.papermc.paper.event.entity.EntityCollideWithEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class SimpleHome extends JavaPlugin implements Listener {

    private static SimpleHome instance;
    public static SimpleHome getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        registerCommands();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("SimpleHome enabled!");
    }

    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("sethome")).setExecutor(new SetHome());
        Objects.requireNonNull(this.getCommand("home")).setExecutor(new HomeCommand());
    }
}