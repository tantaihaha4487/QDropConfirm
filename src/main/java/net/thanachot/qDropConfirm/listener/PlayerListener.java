package net.thanachot.qDropConfirm.listener;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.thanachot.qDropConfirm.QDropConfirm;
import net.thanachot.qDropConfirm.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class PlayerListener implements Listener {

    private final HashMap<UUID, ItemStack> dropping = new HashMap<>();
    private final HashMap<UUID, BukkitTask> tasks = new HashMap<>();

    private static void cancelFeedback(Player player) {
        player.sendActionBar(MiniMessage.miniMessage().deserialize(ConfigManager.getConfirmMessage()));
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.5f, 0.8f);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDrop(PlayerDropItemEvent event) {

        Player player = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();
        UUID playerUUID = player.getUniqueId();

        if (!ConfigManager.getWhitelistMaterials().contains(item.getType())) {
            return;
        }

        if (tasks.containsKey(playerUUID)) {
            tasks.get(playerUUID).cancel();
            tasks.remove(playerUUID);
        }

        // Player is confirming a drop for the same item.
        if (dropping.containsKey(playerUUID) && dropping.get(playerUUID).isSimilar(item)) {
            event.setCancelled(false);
            dropping.remove(playerUUID);
            return;
        }

        // This is a new item drop confirmation.
        event.setCancelled(true);
        cancelFeedback(player);

        // Set/update the item being confirmed.
        dropping.put(playerUUID, item);

        // This runs if the player doesn't confirm in time.~
        BukkitTask newTask = Bukkit.getScheduler().runTaskLater(QDropConfirm.getInstance(), () -> {
            dropping.remove(playerUUID);
            tasks.remove(playerUUID);
        }, ConfigManager.getDelay());

        // Store the new task.
        tasks.put(playerUUID, newTask);
    }
}
