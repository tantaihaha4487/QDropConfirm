package net.thanachot.qDropConfirm.command;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.thanachot.qDropConfirm.config.ConfigManager;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;

import java.util.Collection;
import java.util.List;

@NullMarked
public class QDropConfirmCommand implements BasicCommand {

    @Override
    public Collection<String> suggest(CommandSourceStack commandSourceStack, String[] args) {
        return List.of("reload");
    }

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (commandSourceStack.getExecutor() instanceof Player player) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    ConfigManager.reload();
                    player.sendMessage("Config reloaded!");
                } else {
                    player.sendMessage("Invalid argument: " + args[0]);
                }
            }
        }
    }
}
