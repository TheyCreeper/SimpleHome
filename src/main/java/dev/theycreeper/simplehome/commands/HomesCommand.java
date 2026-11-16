package dev.theycreeper.simplehome.commands;

import dev.theycreeper.simplehome.SimpleHome;
import dev.theycreeper.simplehome.saving.StorageHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendRichMessage("Must be player to run this command");
            return false;
        }
        StorageHelper.GetListHomes(player.getUniqueId());
//        int maxHomeCnt = SimpleHome.getInstance().getConfig().
//        commandSender.sendRichMessage("Successfully set home to " + getHomeName(strings));
        return false;
    }
}
