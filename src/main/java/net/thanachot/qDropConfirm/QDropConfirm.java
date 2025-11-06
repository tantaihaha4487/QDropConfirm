package net.thanachot.qDropConfirm;

import net.thanachot.qDropConfirm.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class QDropConfirm extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info("Q Drop Confirm Enabled!!");

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static QDropConfirm getInstance() {
        return getPlugin(QDropConfirm.class);
    }
}
