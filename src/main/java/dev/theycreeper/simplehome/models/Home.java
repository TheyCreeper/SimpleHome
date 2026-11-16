package dev.theycreeper.simplehome.models;

import org.bukkit.Location;

import java.util.UUID;

public class Home {
    private String name;
    private Location location;
    private UUID playerUuid;

    public Home(String name, Location location, UUID playerUuid) {
        this.name = name;
        this.location = location;
        this.playerUuid = playerUuid;
    }

    public String getName() {
        return name;
    }
    public Location getLocation() {
        return location;
    }

    public UUID getPlayerUuid() {
        return playerUuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
