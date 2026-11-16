package dev.theycreeper.simplehome.saving;

import dev.theycreeper.simplehome.configs.ConfigHandler;
import dev.theycreeper.simplehome.models.Home;
import org.bukkit.Location;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class StorageHelper {
    private static StorageHelper instance;
    private static JsonHelper serializer;

    public StorageHelper() {
    }

    public static StorageHelper getInstance() {
        return instance;
    }

    public static List<Home> getHomes(UUID player) {
        return JsonHelper.GetPlayerHomes(player);
    }

    public static int GetHomeCount(UUID player) {
        return GetListHomes(player).size();
    }
    public static List<String> GetListHomes(UUID player) {
        List<Home> listHome = JsonHelper.GetPlayerHomes(player);
        assert listHome != null;
        return listHome.stream()
                .map(Home::getName)
                .toList();
    }
    public static Home GetHome(UUID player, String name) {
        List<Home> listHome = JsonHelper.GetPlayerHomes(player);
        assert listHome != null;
        return listHome.stream().filter(h -> h.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static void SetHome(UUID player, String name, Location location) {
        List<Home> listHome = JsonHelper.GetPlayerHomes(player);
        assert listHome != null;
        if (listHome.stream().noneMatch(home -> home.getName().equalsIgnoreCase(name))) {
            listHome.add(new Home(name, location, player));
        } else {
            listHome.removeIf(home -> home.getName().equalsIgnoreCase(name));
            listHome.add(new Home(name, location, player));
        }
        JsonHelper.HomeWrite(player, listHome);
    }

    public static void RemoveHome(UUID player, String name) {
        List<Home> listHome = JsonHelper.GetPlayerHomes(player);
        assert listHome != null;
        listHome.removeIf(home -> home.getName().equalsIgnoreCase(name));
        JsonHelper.HomeWrite(player, listHome);
    }
}
