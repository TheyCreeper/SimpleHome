package dev.theycreeper.simplehome.commands;

import dev.theycreeper.simplehome.models.Home;
import dev.theycreeper.simplehome.saving.StorageHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DelHome implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendRichMessage("Must be player to run this command");
            return false;
        }
        String homeName = CommandUtils.getSanitizedArgsList(strings).get(0);
        StorageHelper.RemoveHome(player.getUniqueId(), homeName);
        commandSender.sendRichMessage("Removed " + homeName);
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (commandSender instanceof Player player) {
            return StorageHelper.GetListHomes(player.getUniqueId());
        }
        return new ArrayList<String>();
    }
}
