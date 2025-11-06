package net.thanachot.qDropConfirm.listener;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.thanachot.qDropConfirm.QDropConfirm;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerListener implements Listener {

    private final Set<UUID> dropping = new HashSet<>();


    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {

        Player player = event.getPlayer();

        if (!dropping.contains(player.getUniqueId())) {
            dropping.add(player.getUniqueId());
            event.setCancelled(true);
            player.sendActionBar(MiniMessage.miniMessage().deserialize("(i) Tap <b>Q</b> Again to drop!"));
            Bukkit.getScheduler().runTaskLater(QDropConfirm.getInstance(), () -> {
                dropping.remove(player.getUniqueId());
            }, 5 * 20L);
            return;
        }
    }

}
