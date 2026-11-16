package dev.theycreeper.simplehome.commands;

import dev.theycreeper.simplehome.configs.ConfigHandler;
import dev.theycreeper.simplehome.models.Home;
import dev.theycreeper.simplehome.saving.StorageHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class SetHome implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendRichMessage("Must be player to run this command");
            return false;
        }
        if (canAddHome(player.getUniqueId())) {
            StorageHelper.SetHome(player.getUniqueId(), getHomeName(strings), player.getLocation());
            commandSender.sendRichMessage("Successfully set home to " + getHomeName(strings));
        } else {
            commandSender.sendRichMessage("Exceded max home count of " + ConfigHandler.getInstance().getDefaultMaxHomeCount());
        }
        return true;
    }

    private String getHomeName(String[] args) {
        List<String> sanitizedArgs = CommandUtils.getSanitizedArgsList(args);
        if (sanitizedArgs.isEmpty()) {
            return null;
        }
        return sanitizedArgs.get(0);
    }

    private boolean canAddHome(UUID player) {
        if (StorageHelper.GetHomeCount(player) >= ConfigHandler.getInstance().getDefaultMaxHomeCount()) {
            return false;
        }
        return true;
    }
}
