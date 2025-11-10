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

    private static void cancelFeedback(Player player) {
        player.sendActionBar(MiniMessage.miniMessage().deserialize(ConfigManager.getConfirmMessage()));
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.5f, 0.8f);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDrop(PlayerDropItemEvent event) {

        Player player = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();

        BukkitTask task = Bukkit.getScheduler().runTaskLaterAsynchronously(QDropConfirm.getInstance(), () ->
            dropping.remove(player.getUniqueId()), 5 * 20L);

        if (!ConfigManager.getWhitelistMaterials().contains(item.getType())) return;

        // Player haven't drop item before
        if (!dropping.containsKey(player.getUniqueId())) {
            dropping.put(player.getUniqueId(), item);
            cancelFeedback(player);
            event.setCancelled(true);
            task.cancel();
            return;
        }

        // if drop new item
        if (!dropping.get(player.getUniqueId()).isSimilar(item)) {
            cancelFeedback(player);
            task.cancel();
            event.setCancelled(true);
        } else {
            dropping.remove(player.getUniqueId());
            event.setCancelled(false);
        }


    }
}
