package dev.theycreeper.simplehome.commands;

import dev.theycreeper.simplehome.configs.ConfigHandler;
import dev.theycreeper.simplehome.models.Home;
import dev.theycreeper.simplehome.saving.StorageHelper;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendRichMessage("Must be player to run this command");
            return false;
        }

        String homeName = CommandUtils.getSanitizedArgsList(strings).get(0);
        Home home = StorageHelper.GetHome(player.getUniqueId(), homeName);

        player.teleport(makeLocationValid(home.getLocation()));
        commandSender.sendRichMessage("Teleported to " + homeName);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (commandSender instanceof Player player) {
            return StorageHelper.GetListHomes(player.getUniqueId());
        }
        return new ArrayList<String>();
    }

    private Location makeLocationValid(Location location) {
        while (location.getBlock().isSuffocating()) {
            location.add(0, 1, 0);
        }
        return location;
    }


}
